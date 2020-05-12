package blockchain;

import java.util.ArrayList;

public class BlockChain {

    static ArrayList<Block> listOfBlocks = new ArrayList<>();

    static volatile int zeros = 0;

    static void addBlock(Block block) {
        listOfBlocks.add(block);
    }

    static void printBlocks() {
        for (Block b : listOfBlocks) {
            System.out.println(b);
            System.out.println();
        }
    }

    synchronized static String regulatorN(Block cursor) {
        if (cursor == null) {
            zeros++;
            return ("N was increased to " + zeros);
        } else if (cursor.miningDuration < 15) {
            zeros++;
            return ("N was increased to " + zeros);
        } else if (cursor.miningDuration > 25) {
            zeros--;
            return ("N was decreased by 1");
        } else {
            return ("N stays the same");
        }
    }

    volatile static Block cursor = null;

    static synchronized void createBlock() {

        for (int i = 0; i < 2; i++) {
            cursor = new Block(cursor, zeros, Thread.currentThread().getId(), regulatorN(cursor));
//            System.out.println(cursor);
//            System.out.println();
            listOfBlocks.add(cursor);
        }

    }
}
