import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockFunctions
{
    //    Add Blocks to the Blockchain - validation whether 1st block or next block.
    public static void inputBlock(String NoticeID, String IDCategory, String NoticeType,
                                  String OffenseType, String OffenseLocation,
                                  String OffenseDate, String PaymentDate)
    {
        File file = new File(Paths.get("ledger.txt").toString());
        boolean exists = file.exists();

//        Check if file created or file contains something.
        if (exists == false || file.length() == 0)
            genesisBlock(NoticeID, IDCategory, NoticeType, OffenseType,
                    OffenseLocation, OffenseDate, PaymentDate);
        else
            nextBlock(NoticeID, IDCategory, NoticeType, OffenseType,
                    OffenseLocation, OffenseDate, PaymentDate);
    }

//    Create the first Block in the Blockchain
    static void genesisBlock(String NoticeID, String IDCategory, String NoticeType,
                             String OffenseType, String OffenseLocation,
                             String OffenseDate, String PaymentDate)
    {
        try
        {
            Block genesis = new Block(NoticeID, IDCategory, NoticeType, OffenseType,
                    OffenseLocation, OffenseDate, PaymentDate, "0");
            Blockchain.nextBlock(genesis);
        }
        catch(Exception ex)
        {
            Logger.getLogger(BlockFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Blockchain.distribute();
        }
    }

//    Add NewBlock to the Blockchain.
    static void nextBlock(String NoticeID, String IDCategory, String NoticeType,
                             String OffenseType, String OffenseLocation,
                             String OffenseDate, String PaymentDate)
    {
        try
        {
            String previousHash = Blockchain.get().getLast().getHash();

            Block b = new Block(NoticeID, IDCategory, NoticeType, OffenseType,
                    OffenseLocation, OffenseDate, PaymentDate, "0");
            Blockchain.nextBlock(b);
        }
        catch(Exception ex)
        {
            Logger.getLogger(BlockFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Blockchain.distributeAppend();
        }
    }
}
