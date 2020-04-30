package blockchain;

public class Main {
    public static void main(String[] args) {
        int hashNumber = 5;

        Block cursor = null;
        for (int i = 0; i < hashNumber; i++) {
            cursor = new Block(cursor);
            System.out.println(cursor);
            System.out.println();
        }
    }
}


