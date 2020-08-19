package machura.przemyslaw.fissst.recruitment.school.datatransfermodel.mappers;

import machura.przemyslaw.fissst.recruitment.school.domain.Address;
import machura.przemyslaw.fissst.recruitment.school.domain.Marks;
import machura.przemyslaw.fissst.recruitment.school.domain.Student;
import machura.przemyslaw.fissst.recruitment.school.datatransfermodel.StudentDAO;

import java.util.Collections;
import java.util.Optional;

public class StudentMapper {

    public static Student map(StudentDAO student){
        return Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getBirthDate())
                .pesel(student.getPesel())
                .schoolClass(Optional.ofNullable(student.getSchoolClass()).map(SchoolClassMapper::map).orElse(null))
                .marks(Optional.ofNullable(student.getMarks()).map(MarksMapper::toDomain).orElse(Collections.emptyList()))
                .address(AddressMapper.from(student.getAddress()))
                .dyslexia(student.isDyslexia())
                .build();
    }

    public static StudentDAO map(Student student){
        return StudentDAO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getBirthDate())
                .pesel(student.getPesel())
                .schoolClass(SchoolClassMapper.map(student.getSchoolClass()))
                .marks(MarksMapper.toDTO(student.getMarks()))
                .address(AddressMapper.from(student.getAddress()))
                .dyslexia(student.isDyslexia())
                .build();
    }
}
