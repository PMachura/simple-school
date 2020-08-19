package machura.przemyslaw.fissst.recruitment.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class Student {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String pesel;
    private final SchoolClass schoolClass;
    private final List<Marks> marks;
    private final Address address;
    private final boolean dyslexia;
}
