package by.matusevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Block implements Serializable {

    @Id
    private long blockId; //was initially String and UUID, changed to long to simplify

    private String hash;

    private String previousHash;

    private long timestamp;

    private String transaction; //1 transaction per block to simplify

    private int nonce;
}
