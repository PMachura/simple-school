package machura.przemyslaw.fissst.recruitment.school.persistence.schoolsubjects;

import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolSubjectDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubjectDAO, Long> {
}
