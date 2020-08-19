package machura.przemyslaw.fissst.recruitment.school.datatransfermodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Table(name = "address")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String country;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String city;

    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String street;

    @Column(nullable = false)
    @Min(1)
    private Integer streetNumber;

    @Min(1)
    private Integer houseNumber;



}
