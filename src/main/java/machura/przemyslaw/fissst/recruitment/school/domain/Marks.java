package machura.przemyslaw.fissst.recruitment.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public final class Marks {
    private final Long id;
    private final SchoolSubject subject;
    private final Integer mark;
}
