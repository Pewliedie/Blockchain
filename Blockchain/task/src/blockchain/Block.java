package blockchain;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Block {
    Random random = new Random();
    private static final AtomicInteger UID_SUPPLIER = new AtomicInteger(1);
    private volatile long minerNumber;
    private volatile String previousHash;
    private volatile String hash;
    private volatile long timestamp;
    private volatile int uid;
    private volatile long magicNumber;
    public volatile long miningDuration;
    public volatile String increaseN;


    public Block(Block previous, int zeros, long minerNumber, String increaseN) {
        synchronized (Block.class) {
            this.minerNumber = minerNumber;
            this.increaseN = increaseN;

            long startTime = new Date().getTime();
            previousHash = previous == null ? "0" : previous.hash;
            timestamp = new Date().getTime();
            uid = UID_SUPPLIER.getAndIncrement();

            do {
                magicNumber = random.nextInt(1000000000);
                hash = StringUtil.sha256(this.toString());
            } while (!hash.startsWith("0".repeat(zeros)));
            long endTime = new Date().getTime();
            miningDuration = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
        }
    }

    @Override
    public synchronized String toString() {
        return String.format(

                "Block:%n" +
                        "Created by miner # %d%n" +
                        "Id: %d%n" +
                        "Timestamp: %d%n" +
                        "Magic number: %d%n" +
                        "Hash of the previous block: %n" +
                        "%s%n" +
                        "Hash of the block: %n" +
                        "%s%n" +
                        "Block was generating for %d seconds %n" +
                        "%s", minerNumber, uid, timestamp, magicNumber, previousHash, hash, miningDuration,increaseN);
    }
}