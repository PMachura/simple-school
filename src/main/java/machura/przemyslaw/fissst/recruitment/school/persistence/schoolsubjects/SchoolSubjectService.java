package machura.przemyslaw.fissst.recruitment.school.persistence.schoolsubjects;

import io.vavr.control.Either;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolSubjectDAO;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolSubject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolSubjectService {
    Either<Failure, SchoolSubject> create(SchoolSubjectDAO schoolSubject);

    Either<Failure, List<SchoolSubjectDAO>> create(Iterable<SchoolSubjectDAO> schoolSubject);

    Either<Failure, SchoolSubject> findById(long id);
}
