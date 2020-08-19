package machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass;

import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolClassDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClassDAO, Long> {
}
