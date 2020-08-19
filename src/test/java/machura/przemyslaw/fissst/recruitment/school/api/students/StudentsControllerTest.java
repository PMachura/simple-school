package machura.przemyslaw.fissst.recruitment.school.api.students;

import io.vavr.control.Either;
import machura.przemyslaw.fissst.recruitment.school.DataSupplier;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers.StudentMapper;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import machura.przemyslaw.fissst.recruitment.school.persistence.students.StudentPersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentsControllerTest {

    private static final String STUDENTS_PATH = "/students";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentPersistenceService studentPersistenceService;

    @Test
    public void shouldReturnNotFoundWhenStudentDoesNotExists() throws Exception {
        when(studentPersistenceService.findById(anyLong())).thenReturn(Either.left(Failure.from("Not found", Failure.Status.RESOURCE_NOT_FOUND)));
        this.mockMvc.perform(get(STUDENTS_PATH + "/999")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkWhenStudentIsFound() throws Exception {
        Student student = StudentMapper.map(DataSupplier.getValidStudentSetup().build());
        when(studentPersistenceService.findById(anyLong())).thenReturn(Either.right(student));
        this.mockMvc.perform(get(STUDENTS_PATH + "/999"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
