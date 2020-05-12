package blockchain;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new MiningThreads();
        Thread t2 = new MiningThreads();
        Thread t3 = new MiningThreads();
        Thread t4 = new MiningThreads();
        Thread t5 = new MiningThreads();

        t1.start();
        t2.start();
        t3.start();
        t3.join();

        Thread.sleep(5000);
        BlockChain.printBlocks();
    }
}


class MiningThreads extends Thread{
    @Override
    public void run() {
        BlockChain.createBlock();
    }
}


