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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Size(min = 4, max = 16, message = "required at least 4 characters")
    private String userName;

    @PhoneNumber(message = "invalid")
    private String phoneNumber;

    @Email(message = "valid email plz")
    private String emailAddress;

    private String userPassword;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AppRole> roles;

}
