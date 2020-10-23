package by.matusevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    //transaction's "home"
    private String walletId;

    //transaction's "destination"
    private String receiverId;

    private int value;

    //3 statuses "pending", "accepted", "genesis"
    private String status;
}
