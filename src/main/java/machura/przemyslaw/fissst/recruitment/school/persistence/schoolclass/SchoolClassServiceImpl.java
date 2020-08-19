package machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.SchoolClassDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.mappers.SchoolClassMapper;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private final String DEFAULT_ERROR_MESSAGE = "Repository problems";

    private final SchoolClassRepository schoolClassRepository;

    @Override
    public Either<Failure, SchoolClass> create(SchoolClassDAO grade) {
        return Try.ofSupplier(() -> schoolClassRepository.save(grade))
                .toEither()
                .map(SchoolClassMapper::map)
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }

    @Override
    public Either<Failure, List<SchoolClass>> create(Iterable<SchoolClassDAO> grades) {
        return Try.ofSupplier(() -> schoolClassRepository.saveAll(grades))
                .toEither()
                .map(SchoolClassMapper::map)
                .mapLeft(throwable -> Failure.from(DEFAULT_ERROR_MESSAGE));
    }
}
