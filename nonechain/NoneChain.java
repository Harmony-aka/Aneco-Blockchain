import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NoneChain {
    //создаем массив, в который будем принимать добытые блоки
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int difficulty = 4;

    public static void main(String[] args) {

        out.println("Введите сообщение транзакции");
        String data_msg = in.nextLine();
        out.println("Trying to Mine block " + 1 + "...");
        addBlock(new Block(data_msg, "0"), difficulty);
        int i = 1;
        while(in.hasNextLine()) {
//            difficulty++;
            i++;
            data_msg = in.nextLine();
            out.println("Trying to Mine block " + i + "...");
            addBlock(new Block(data_msg, blockchain.get(blockchain.size() - 1).hash), difficulty);
            out.println(difficulty);
        }

        Scan scan = new Scan();
        scan.scanner();



//        System.out.println("Trying to Mine block 1... ");
//        addBlock(new Block("Hi im the first block", "0"));
//
//        System.out.println("Trying to Mine block 2... ");
//        addBlock(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//
//        System.out.println("Trying to Mine block 3... ");
//        addBlock(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));


    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock, int difficult) {
        newBlock.mineBlock(difficult);
        blockchain.add(newBlock);
    }
}
