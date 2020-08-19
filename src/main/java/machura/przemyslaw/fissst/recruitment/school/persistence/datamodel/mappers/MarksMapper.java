package machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.mappers;


import machura.przemyslaw.fissst.recruitment.school.domain.Marks;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentsMarksDAO;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MarksMapper {

    public static Marks map(StudentsMarksDAO studentsMarks) {
        return Marks.builder()
                .id(studentsMarks.getId())
                .mark(studentsMarks.getMark())
                .subject(SchoolSubjectMapper.map(studentsMarks.getSchoolSubject()))
                .build();
    }

    public static StudentsMarksDAO map(Marks marks) {
        return StudentsMarksDAO.builder()
                .id(marks.getId())
                .mark(marks.getMark())
                .schoolSubject(SchoolSubjectMapper.toDAO(marks.getSubject()))
                .build();
    }

    public static List<Marks> toDomain(Collection<? extends StudentsMarksDAO> collection) {
        return collection.stream()
                .map(MarksMapper::map)
                .collect(Collectors.toList());
    }

    public static Set<StudentsMarksDAO> toDTO(Collection<? extends Marks> collection) {
        return collection.stream()
                .map(MarksMapper::map)
                .collect(Collectors.toSet());
    }
}
