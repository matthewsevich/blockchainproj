package by.matusevich.service;

import by.matusevich.pojo.Block;
import by.matusevich.pojo.Transaction;
import by.matusevich.repository.BlockRepo;
import by.matusevich.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiningBlockService {

    @Autowired
    BlockRepo blockRepo;
    @Autowired
    TransactionRepo transactionRepo;

    public Block createGenesisBlock(Transaction genesisTransaction) {
        Block genesisBlock = new Block();
        genesisBlock.setBlockId(0);
        genesisBlock.setPreviousHash("0");
        genesisBlock.setTimestamp(System.currentTimeMillis());
        genesisBlock.setTransaction(genesisTransaction.toString());
        genesisBlock.setHash(Utils.calculateHash(genesisBlock.toString()));
        return genesisBlock;
    }

    public void saveBlock(Block block) {
        blockRepo.save(block);
    }

    public Block getLastBlock() {
        return blockRepo.findFirstByOrderByTimestampDesc();
    }

    public long count() {
        return blockRepo.count();
    }

    public Block createBlock(Transaction transaction) {
        Block block = new Block();
        block.setBlockId(getLastBlock().getBlockId() + 1);
        block.setPreviousHash(getLastBlock().getHash());
        block.setTimestamp(System.currentTimeMillis());
        if (transaction != null) {
            transaction.setStatus("Accepted");
            transactionRepo.save(transaction);
            block.setTransaction(transaction.toString());
            block.setHash(
                    Utils.calculateHash(
                            block.toString()));
        }
        return block;
    }

    public List<Block> getAll() {
        return (List<Block>) blockRepo.findAll();
    }

    public Block findBlockById(long id) {
        return blockRepo.findById(id).orElse(null);
    }
}
