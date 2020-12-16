package by.matusevich.service;

import by.matusevich.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Utils {

    @Autowired
    BlockService blockService;

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

    public static String zeros(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("0".repeat(Math.max(0, length)));
        return builder.toString();
    }
}
