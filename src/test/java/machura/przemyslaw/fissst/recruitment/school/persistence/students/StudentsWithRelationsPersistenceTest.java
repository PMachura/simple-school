package machura.przemyslaw.fissst.recruitment.school.persistence.students;

import io.vavr.control.Either;
import machura.przemyslaw.fissst.recruitment.school.DataSupplier;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.SchoolClassDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.SchoolSubjectDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentsMarksDAO;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolClass;
import machura.przemyslaw.fissst.recruitment.school.domain.SchoolSubject;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import machura.przemyslaw.fissst.recruitment.school.persistence.schoolclass.SchoolClassService;
import machura.przemyslaw.fissst.recruitment.school.persistence.schoolsubjects.SchoolSubjectService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentsWithRelationsPersistenceTest {

    @Autowired
    private StudentPersistenceService studentService;

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private SchoolSubjectService schoolSubjectService;

    private SchoolClass schoolClass;
    private SchoolSubject schoolSubject;

    @BeforeAll
    private void initializeClassesAndSubjects() {
        schoolClass = schoolClassService.create(SchoolClassDAO.builder().name("4FF").build()).get();
        schoolSubject = schoolSubjectService.create(SchoolSubjectDAO.builder().name("Something").build()).get();
    }

    @Test
    public void shouldBeAbleToAddRelationsAndSaveStudent() {
        StudentDAO validStudent = createValidStudentWithRelations();

        Either<Failure, Student> student = studentService.create(validStudent);

        assertTrue(student.isRight());
        assertNotNull(student.get().getId());
    }

    @Test
    public void shouldBeAbleToAddRelationsAndFindsSavedStudent() {
        StudentDAO validStudent = createValidStudentWithRelations();

        Student student = studentService.create(validStudent).get();
        Either<Failure, Student> foundMaybe = studentService.findById(student.getId());

        assertTrue(foundMaybe.isRight());
        Student found = foundMaybe.get();
        assertEquals(found.getId(), found.getId());
        assertNotNull(found.getSchoolClass());
        assertEquals(found.getSchoolClass().getId(), schoolClass.getId());
        assertNotNull(found.getMarks());
        assertEquals(found.getMarks().size(), 1);
        assertEquals(found.getMarks().get(0).getMark(), 5);
    }

    private StudentDAO createValidStudentWithRelations() {
        return DataSupplier.getValidStudentSetup()
                .marks(Collections.singleton(StudentsMarksDAO.builder()
                        .mark(5)
                        .schoolSubject(SchoolSubjectDAO.builder().id(schoolSubject.getId()).build())
                        .build()))
                .schoolClass(SchoolClassDAO.builder().id(schoolClass.getId()).build())
                .build();
    }

}


