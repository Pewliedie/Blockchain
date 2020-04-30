package blockchain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
    private static final AtomicInteger UID_SUPPLIER = new AtomicInteger(0);

    private final String previousHash;
    private final String hash;
    private final long timestamp;
    private final int uid;

    public Block(Block previous) {
        previousHash = previous == null? "0": previous.hash;
        timestamp = new Date().getTime();
        uid = UID_SUPPLIER.getAndIncrement();

        hash = StringUtil.sha256(this.toString());
    }

    @Override
    public String toString() {
        return String.format("Block:%n" +
                "Id: %d%n" +
                "Timestamp: %d%n" +
                "Hash of the previous block: %n" +
                "%s%n" +
                "Hash of the block: %n" +
                "%s", uid, timestamp, previousHash, hash);
    }
}