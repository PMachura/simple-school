package machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers;

import machura.przemyslaw.fissst.recruitment.school.domain.SchoolClass;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.SchoolClassDAO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolClassMapper {

    public static SchoolClassDAO map(SchoolClass schoolClass) {
        return SchoolClassDAO.builder()
                .id(schoolClass.getId())
                .name(schoolClass.getName())
                .build();
    }

    public static SchoolClass map(SchoolClassDAO dao) {
        return SchoolClass.builder()
                .id(dao.getId())
                .name(dao.getName())
                .build();
    }

    public static List<SchoolClass> map(Collection<? extends SchoolClassDAO> daos) {
        return daos.stream()
                .map(SchoolClassMapper::map)
                .collect(Collectors.toList());
    }
}
