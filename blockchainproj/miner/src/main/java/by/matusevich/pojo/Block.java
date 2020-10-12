package by.matusevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Block implements Serializable {

    @Id
    private long blockId;

    private String hash;

    private String previousHash;

    private long timestamp;

    private String transaction;

}
