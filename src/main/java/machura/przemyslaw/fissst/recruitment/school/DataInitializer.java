package machura.przemyslaw.fissst.recruitment.school;

import io.vavr.Tuple;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolClassDAO;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolSubjectDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass.SchoolClassService;
import machura.przemyslaw.fissst.recruitment.school.persistence.schoolsubjects.SchoolSubjectService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class DataInitializer {

    private final SchoolClassService schoolClassService;
    private final SchoolSubjectService schoolSubjectService;

    @EventListener(ApplicationReadyEvent.class)
    public void insertData() {
        initializeClasses();
        initializeSubjects();
    }

    private void initializeClasses() {
        schoolClassService.create(
                Stream.of(Tuple.of(1, "1A"), Tuple.of(2, "1B"), Tuple.of(3, "1C"), Tuple.of(4, "2A"), Tuple.of(5, "2B"), Tuple.of(6, "2C"))
                        .map(classIdWithName -> SchoolClassDAO.builder()
                                .id((long) classIdWithName._1)
                                .name(classIdWithName._2)
                                .build())
                        .collect(Collectors.toList())
        );
    }

    private void initializeSubjects() {
        schoolSubjectService.create(
                Stream.of(Tuple.of(1, "History"), Tuple.of(2, "Math"), Tuple.of(3, "ComputerScience"))
                        .map(subjectIdWithName -> SchoolSubjectDAO.builder()
                                .id((long)subjectIdWithName._1)
                                .name(subjectIdWithName._2)
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
