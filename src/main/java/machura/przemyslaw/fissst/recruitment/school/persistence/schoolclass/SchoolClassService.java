package machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass;

import io.vavr.control.Either;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolClassDAO;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolClass;

import java.util.List;

public interface SchoolClassService {
    Either<Failure, SchoolClass> create(SchoolClassDAO grade);

    Either<Failure, List<SchoolClass>> create(Iterable<SchoolClassDAO> grades);
}
