import com.google.gson.Gson;

public class Scan {


    void scanner() {

        System.out.println("\nBlockchain is Valid: " + NoneChain.isChainValid());

        String blockchainJson = StringUtil.getJson(NoneChain.blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);

    }
}
