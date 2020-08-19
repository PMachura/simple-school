package machura.przemyslaw.fissst.recruitment.school.persistence.students;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.StudentDAO;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers.StudentMapper;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass.SchoolClassRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
@Service
@RequiredArgsConstructor
public class StudentPersistenceService implements StudentService {

    private final String DEFAULT_ERROR_MESSAGE = "Repository problems";

    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    public Either<Failure, Student> create(StudentDAO student) {
        if (!Objects.isNull(student.getId()))
            return Either.left(Failure.from("Student should not has id", Failure.Status.ILLEGAL_INPUT));

        return Try.ofSupplier(() -> studentRepository.save(student))
                .toEither()
                .map(StudentMapper::map)
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }

    public Either<Failure, Student> update(StudentDAO student) {
        if (Objects.isNull(student.getId()))
            return Either.left(Failure.from("Student has not id", Failure.Status.ILLEGAL_INPUT));

        return Try.ofSupplier(() -> studentRepository.save(student))
                .toEither()
                .map(StudentMapper::map)
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }

    public Either<Failure, Student> findById(long id) {
        return Try.ofSupplier(() -> findByIdUnchecked(id))
                .toEither()
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE))
                .flatMap(studentMaybe -> studentMaybe.isEmpty() ?
                        Either.left(Failure.from("Not found", Failure.Status.RESOURCE_NOT_FOUND))
                        :
                        Either.right(StudentMapper.map(studentMaybe.get())));

    }

    protected Optional<StudentDAO> findByIdUnchecked(long id) {
        return studentRepository.findByIdWithClassAndMarks(id);
    }


}
