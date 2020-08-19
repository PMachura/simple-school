package machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers;

import machura.przemyslaw.fissst.recruitment.school.domain.Address;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.AddressDAO;

public class AddressMapper {
    public static Address from(AddressDAO dao) {
        return Address.builder()
                .id(dao.getId())
                .country(dao.getCountry())
                .city(dao.getCity())
                .street(dao.getStreet())
                .streetNumber(dao.getStreetNumber())
                .houseNumber(dao.getHouseNumber())
                .build();
    }

    public static AddressDAO from(Address address) {
        return AddressDAO.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .streetNumber(address.getStreetNumber())
                .houseNumber(address.getHouseNumber())
                .build();
    }
}
