package machura.przemyslaw.fissst.recruitment.school;

import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.AddressDAO;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.StudentDAO;

import java.time.LocalDate;

public class DataSupplier {
    public static StudentDAO.StudentDAOBuilder getValidStudentSetup(){
        return StudentDAO.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .birthDate(LocalDate.of(1994,10,10))
                .pesel("94072304399")
                .address(getValidAddressSetup().build())
                .dyslexia(true);
    }

    public static AddressDAO.AddressDAOBuilder getValidAddressSetup() {
        return AddressDAO.builder()
                .country("Poland")
                .city("Gliwice")
                .street("Dworcowa")
                .streetNumber(12)
                .houseNumber(23);
    }
}
