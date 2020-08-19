package machura.przemyslaw.fissst.recruitment.school.persistence.schoolsubjects;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolSubjectDAO;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers.SchoolSubjectMapper;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers.StudentMapper;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolSubject;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class SchoolSubjectsServiceImpl implements SchoolSubjectService {

    private final String DEFAULT_ERROR_MESSAGE = "Repository problems";

    private final SchoolSubjectRepository schoolSubjectRepository;

    @Override
    public Either<Failure, SchoolSubject> create(SchoolSubjectDAO subjectDAO) {
        return Try.ofSupplier(() -> schoolSubjectRepository.save(subjectDAO))
                .toEither()
                .map(SchoolSubjectMapper::map)
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }

    @Override
    public Either<Failure, List<SchoolSubjectDAO>> create(Iterable<SchoolSubjectDAO> schoolSubjects) {
        return Try.ofSupplier(() -> schoolSubjectRepository.saveAll(schoolSubjects))
                .toEither()
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }

    @Override
    public Either<Failure, SchoolSubject> findById(long id) {
        return Try.ofSupplier(() -> schoolSubjectRepository.findById(id))
                .toEither()
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE))
                .flatMap(studentMaybe -> studentMaybe.isEmpty() ?
                        Either.left(Failure.from("Not found", Failure.Status.RESOURCE_NOT_FOUND))
                        :
                        Either.right(SchoolSubjectMapper.map(studentMaybe.get())));

    }
}
