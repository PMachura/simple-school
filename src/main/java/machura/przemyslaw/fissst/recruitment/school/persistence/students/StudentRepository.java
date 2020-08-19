package machura.przemyslaw.fissst.recruitment.school.persistence.students;

import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentDAO, Long>{

    @Query("SELECT student FROM StudentDAO student LEFT JOIN FETCH student.marks LEFT JOIN FETCH student.schoolClass WHERE student.id = (:id)")
    Optional<StudentDAO> findByIdWithClassAndMarks(@Param("id") Long id);
}
