package by.matusevich.service;

import by.matusevich.pojo.Block;
import org.springframework.stereotype.Service;

@Service
public class MiningBlock {

    public Block mineBlock(Block blockToMine, int difficulty) {
        int nonce = blockToMine.getNonce();
        blockToMine.setHash(Utils.calculateHash(blockToMine));
        while (!blockToMine.getHash().substring(0, difficulty).equals(Utils.zeros(difficulty))) {

            nonce++;
            blockToMine.setNonce(nonce);
            blockToMine.setHash(Utils.calculateHash(blockToMine));
        }
        return blockToMine;
    }
}
