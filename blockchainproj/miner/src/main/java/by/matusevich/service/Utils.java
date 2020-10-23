package by.matusevich.service;

import by.matusevich.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class Utils {

    @Autowired
    MiningBlockService miningBlockService;

    //we take our block and put it fields(4 out of 5) to string
    public static String blockToString(Block block) {
        return block.getPreviousHash() + block.getTimestamp() + block.getTransaction() + block.getBlockId();
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
        Block firstBlock = miningBlockService.findBlockById(0);
        if (firstBlock.getBlockId() != 0) {
            return false;
        }
        if (firstBlock.getPreviousHash() != null) {
            return false;
        }
        return firstBlock.getHash() != null &&
                Utils.calculateHash(firstBlock).equals(firstBlock.getHash());
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
            return newBlock.getHash() != null &&
                    Utils.calculateHash(newBlock).equals(newBlock.getHash());
        }
        return false;
    }

    /*
    cycle for list of blocks, to check validity of blocks using previous method
     */
    public boolean isBlockchainValid() {
        List<Block> blockList = miningBlockService.getAll();
        if (!isFirstBlockValid()) {
            return false;
        }
        for (int i = 1; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            Block previousBlock = blockList.get(i - 1);

            if (!isValidNewBlock(currentBlock, previousBlock)) {
                return false;
            }
        }
        return true;
    }

}
