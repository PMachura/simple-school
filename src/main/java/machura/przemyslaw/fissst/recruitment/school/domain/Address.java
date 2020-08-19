package machura.przemyslaw.fissst.recruitment.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public final class Address {
    private final Long id;
    private final String country;
    private final String city;
    private final String street;
    private final Integer streetNumber;
    private final Integer houseNumber;
}
