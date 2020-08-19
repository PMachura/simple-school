package machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.mappers;

import machura.przemyslaw.fissst.recruitment.school.domain.SchoolSubject;
import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.SchoolSubjectDAO;

public class SchoolSubjectMapper {

    public static SchoolSubjectDAO toDAO(SchoolSubject schoolSubject){
        return SchoolSubjectDAO.builder()
                .id(schoolSubject.getId())
                .name(schoolSubject.getName())
                .build();
    }

    public static SchoolSubject map(SchoolSubjectDAO dao){
        return SchoolSubject.builder()
                .id(dao.getId())
                .name(dao.getName())
                .build();
    }
}
