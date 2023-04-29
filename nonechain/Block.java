import java.util.Date;

public class Block {

    public String hash; //хэш нашего блока
    public String previousHash; //хэш предыдущего блока
    private String data; //дата нашего блока, в примере была представлена простым сообщением
    private long timeStamp; //количество миллисекунд

    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }

    public String calculateHash() {
        return StringUtil.applyHash256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
    }

    public void mineBlock(int difficulty) {

        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! " + hash);
    }

}
