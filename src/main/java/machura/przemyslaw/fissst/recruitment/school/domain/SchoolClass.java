package machura.przemyslaw.fissst.recruitment.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SchoolClass {
    private final Long id;
    private final String name;
}
