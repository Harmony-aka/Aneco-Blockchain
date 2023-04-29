import com.google.gson.GsonBuilder;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NoneChain {
    //создаем массив, в который будем принимать добытые блоки
    public static ArrayList<Block> chain = new ArrayList<Block>();
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    /*
    задаем сложность, которую необходимо решить для майнинга блока
    сложность 1-2 решается мгновенно
    для теста подходит сложность 4-6
    * */
    public static int difficulty = 3;

    public static void main(String[] args) {
        //добываем первый блок с нашим сообщением
        chain.add(new Block("The first block in the NoneChain network!", "0"));
        out.println("Trying to Mine block 1... ");
        chain.get(0).mineBlock(difficulty);
        //добываем второй блок с другим сообщением и вычислениями из предыдущего блока
        chain.add(new Block("The second block in the NoneChain network!",chain.get(chain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        chain.get(1).mineBlock(difficulty);
        //проверяем результаты вычислений
        out.println("\nBlockchain is Valid: " + isChainValid());
        //создаем массив при помощи GsonBuilder
        String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        out.println("\nThe block chain: ");
        out.println(chainJson);

        //Первая версия создания блока в нашей сети
//        Block genesisBlock = new Block();
//        out.println("NoneChain network [1] " + genesisBlock.hash);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //цикл для проверки хэшей блокчейна
        for(int i=1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }

}
