package by.matusevich.pojo;

import by.matusevich.validator.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
/*
allmost same thing like hello-spring project
annotations to validate form's input
*/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Size(min = 4, max = 16, message = "required 4-16 characters")
    private String userName;

    @PhoneNumber(message = "only BY phonenumbers XX-XXX-XXXX starting with 33 , 44 , 29 , 25")
    private String phoneNumber;

    @Email(message = "valid email plz")
    private String emailAddress;

    private String userPassword;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AppRole> roles;

}
