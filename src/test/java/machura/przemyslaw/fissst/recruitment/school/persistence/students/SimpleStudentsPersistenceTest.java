package machura.przemyslaw.fissst.recruitment.school.persistence.students;

import io.vavr.control.Either;
import machura.przemyslaw.fissst.recruitment.school.DataSupplier;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentDAO;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SimpleStudentsPersistenceTest {

    @Autowired
    private StudentPersistenceService studentService;

    @Test
    public void shouldCreateValidStudent() {
        StudentDAO validStudent = DataSupplier.getValidStudentSetup().build();

        Either<Failure, Student> student = studentService.create(validStudent);

        assertTrue(student.isRight());
        assertNotNull(student.get().getId());
    }

    @Test
    public void shouldBeAbleToFindSavedStudent() {
        StudentDAO validStudent = DataSupplier.getValidStudentSetup().build();

        Student student = studentService.create(validStudent).get();
        Either<Failure, Student> found = studentService.findById(student.getId());

        assertTrue(found.isRight());
        assertEquals(student.getId(),found.get().getId());
    }

    @Test
    public void shouldNotReturnStudentItItNotExists() {
        Either<Failure, Student> found = studentService.findById(1);

        assertTrue(found.isLeft());
    }

    @ParameterizedTest
    @MethodSource("invalidStudentsTestCases")
    public void shouldNotCreateInvalidStudent(StudentDAO invalidStudent) {
        Either<Failure, Student> student = studentService.create(invalidStudent);

        assertTrue(student.isLeft());
    }

    public static List<StudentDAO> invalidStudentsTestCases() {
        return List.of(
                DataSupplier.getValidStudentSetup()
                        .firstName(null).build(),

                DataSupplier.getValidStudentSetup()
                        .firstName("a").build(),

                DataSupplier.getValidStudentSetup()
                        .birthDate(null).build(),

                DataSupplier.getValidStudentSetup()
                        .pesel("123456").build(),

                DataSupplier.getValidStudentSetup()
                        .address(DataSupplier.getValidAddressSetup()
                                .street(null).build())
                        .build(),

                DataSupplier.getValidStudentSetup()
                        .address(DataSupplier.getValidAddressSetup()
                                .streetNumber(0).build())
                        .build(),

                DataSupplier.getValidStudentSetup()
                        .address(DataSupplier.getValidAddressSetup()
                                .street("1").build())
                        .build(),

                DataSupplier.getValidStudentSetup()
                        .address(DataSupplier.getValidAddressSetup()
                                .city("a").build())
                        .build(),

                DataSupplier.getValidStudentSetup()
                        .address(DataSupplier.getValidAddressSetup()
                                .city(null).build())
                        .build()
        );
    }

}
