package by.matusevich.service;

import by.matusevich.ApplicationConfiguration;
import by.matusevich.pojo.Block;
import by.matusevich.pojo.Transaction;
import by.matusevich.repository.BlockRepo;
import by.matusevich.repository.TransactionRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BlockServiceTest {

    @Autowired
    BlockService blockService;

    @MockBean
    BlockRepo blockRepo;
    @MockBean
    TransactionRepo transactionRepo;

    @Test
    public void createGenesisBlock() {
        Transaction genesisTransaction = new Transaction();

        Block genesisBlock = blockService.createGenesisBlock(genesisTransaction);

        Assert.assertEquals("0", genesisBlock.getPreviousHash());
        Assert.assertEquals(0, genesisBlock.getBlockId());
        Assert.assertEquals(0, genesisBlock.getNonce());
        Assert.assertNotNull(genesisBlock);
    }

    @Test
    public void saveBlock() {
        Block block = new Block();
        blockService.saveBlock(block);
        Mockito.verify(blockRepo, Mockito.times(1)).save(block);
    }

    @Test
    public void getLastBlock() {
        blockService.getLastBlock();
        Mockito.verify(blockRepo, Mockito.times(1)).findFirstByOrderByTimestampDesc();
    }

    @Test
    public void count() {
        long count = blockService.count();

        Assert.assertEquals(0, count);
        Mockito.verify(blockRepo, Mockito.times(1)).count();
    }

    @Test
    public void createBlock() {
        Transaction tr = new Transaction();

        Mockito.doReturn(new Block())
                .when(blockRepo)
                .findFirstByOrderByTimestampDesc();
        Block block = blockService.createBlock(tr);

        Assert.assertEquals(0, block.getNonce());
        Assert.assertEquals(1, block.getBlockId());

        Mockito.verify(blockRepo, Mockito.times(2)).findFirstByOrderByTimestampDesc();

    }

    @Test
    public void getAll() {
        blockService.getAll();

        Mockito.verify(blockRepo, Mockito.times(1)).findAll();

    }

    @Test
    public void findBlockById() {
    }
}