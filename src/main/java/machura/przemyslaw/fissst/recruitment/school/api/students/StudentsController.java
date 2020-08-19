package machura.przemyslaw.fissst.recruitment.school.api.students;

import lombok.RequiredArgsConstructor;
import machura.przemyslaw.fissst.recruitment.school.common.Failure;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentDAO;
import machura.przemyslaw.fissst.recruitment.school.persistence.students.StudentPersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping(StudentsController.BASE_PATH)
@RequiredArgsConstructor
public class StudentsController {

    static final String BASE_PATH = "/students";

    private static final Function<Failure, ResponseEntity> illegalInputHandler = failure -> ResponseEntity.badRequest().body(failure.getReason());
    private static final Function<Failure, ResponseEntity> resourceNotFoundHandler = failure -> ResponseEntity.notFound().build();
    private static final Function<Failure, ResponseEntity> improperRequestHandler = failure -> ResponseEntity.badRequest().body(failure.getReason());
    private static final Function<Failure, ResponseEntity> defaultRFailureHandler = failure -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");

    private static final Map<Failure.Status, Function<Failure, ResponseEntity>> failureHandlers = Map.of(
            Failure.Status.ILLEGAL_INPUT, illegalInputHandler,
            Failure.Status.RESOURCE_NOT_FOUND, resourceNotFoundHandler,
            Failure.Status.IMPROPER_REQUEST, improperRequestHandler,
            Failure.Status.SERVER_ERROR, defaultRFailureHandler
    );

    private final Function<Failure, ResponseEntity> failureHandler = failure ->  failureHandlers.getOrDefault(failure.getStatus(), defaultRFailureHandler).apply(failure);
    private final StudentPersistenceService studentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createStudent(@RequestBody @Valid StudentDAO studentForCreation) {
        return studentService.create(studentForCreation)
                .fold(failureHandler,
                        validStudent -> {
                            URI location = MvcUriComponentsBuilder
                                    .fromMethodCall(MvcUriComponentsBuilder.on(this.getClass()).getStudent(validStudent.getId()))
                                    .build()
                                    .toUri();
                            return ResponseEntity.created(location).body(validStudent);
                        });
    }

    @GetMapping(path = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudent(@PathVariable long id) {
        return studentService.findById(id)
                .fold(failureHandler, ResponseEntity::ok);


    }

}
