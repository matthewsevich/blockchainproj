package by.matusevich.repository;

import by.matusevich.pojo.Block;
import org.springframework.data.repository.CrudRepository;

public interface BlockRepo extends CrudRepository<Block, Long> {

    Block findFirstByOrderByTimestampDesc();
}
