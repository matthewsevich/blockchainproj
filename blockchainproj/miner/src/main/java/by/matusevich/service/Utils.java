package by.matusevich.service;

import by.matusevich.pojo.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class Utils {

    @Autowired
    BlockService blockService;
    private final static Logger log = LoggerFactory.getLogger(Utils.class);

    //we take our block and put it fields(5 out of 6) to string
    public static String blockToString(Block block) {
        return block.getNonce() + block.getPreviousHash() + block.getTimestamp() + block.getTransaction() + block.getBlockId();
    }

    //calculating hash of block
    public static String calculateHash(Block block) {
        String ourBlockString = blockToString(block);
        if (ourBlockString != null) {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
            final byte[] bytes = digest.digest(ourBlockString.getBytes());
            final StringBuilder builder = new StringBuilder();
            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    builder.append('0');
                }
                builder.append(hex);
            }
            return builder.toString();
        }
        return null;
    }

    //block validation of genesis block
    public boolean isFirstBlockValid() {
        Block firstBlock = blockService.findBlockById(0);
        if (firstBlock.getBlockId() != 0) {
            log.info("first");
            return false;
        }
        if (firstBlock.getPreviousHash() == null) {
            log.info("second");
            return false;
        }
        if (firstBlock.getHash() == null ||
                !Utils.calculateHash(firstBlock).equals(firstBlock.getHash())) {
            log.info("third");

            return false;
        }

        return true;
    }

    //validation of new created block
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (newBlock != null && previousBlock != null) {
            if (previousBlock.getBlockId() + 1 != newBlock.getBlockId()) {
                return false;
            }
            if (newBlock.getPreviousHash() == null ||
                    !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
            if (newBlock.getHash() == null ||
                    !Utils.calculateHash(newBlock).equals(newBlock.getHash())) {
                return false;
            }
            return true;
        }
        return false;
    }

    /*
    cycle for list of blocks, to check validity of blocks using previous method
     */
    public boolean isBlockchainValid() {
        List<Block> blockList = blockService.getAll();
        if (!isFirstBlockValid()) {
            log.info("is first block valid {}", isFirstBlockValid());
            return false;
        }
        for (int i = 1; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            Block previousBlock = blockList.get(i - 1);

            if (!isValidNewBlock(currentBlock, previousBlock)) {
                log.info("isValidNewBlock {},{},{}", isValidNewBlock(currentBlock, previousBlock), currentBlock, previousBlock);
                return false;
            }
        }
        log.info("blockchain is valid true from utils");
        return true;
    }

    public static String zeros(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("0".repeat(Math.max(0, length)));
        return builder.toString();
    }

}
