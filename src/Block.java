import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable
{
//    Block Properties
    public static int counter;

    private int index;
    private String NoticeID, IDCategory, NoticeType,
            OffenseType, OffenseLocation, OffenseDate, PaymentDate; //data
    private String hash, previoushash;
    private long timestamp;

//    Constructor
    public Block(String NoticeID, String IDCategory, String NoticeType, String OffenseType,
                 String OffenseLocation, String OffenseDate, String PaymentDate, String previoushash) throws Exception
    {
        this.index = counter;
        counter++;
        this.NoticeID = NoticeID;
        this.IDCategory = IDCategory;
        this.NoticeType = NoticeType;
        this.OffenseType = OffenseType;
        this.OffenseLocation = OffenseLocation;
        this.OffenseDate = OffenseDate;
        this.PaymentDate = PaymentDate;
        this.previoushash = previoushash;
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();

//        Generate current hash for the block
        byte[] blockBytes = Block.getBytes(this);

        if(blockBytes != null)
        {
//            Complicate the blockBytes by combining timestamp, data, and previoushash.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(blockBytes);
            baos.write(previoushash.getBytes());
            baos.write(Long.toString(timestamp).getBytes());
            this.hash = BlockHasher.hash(baos.toByteArray(), "SHA-256");
        }

        else
        {
            throw new Exception("Unable to generate current block hash");
        }
    }

    private static byte[] getBytes(Block blk)
    {
        try(
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos); //getBytes returns in object form.
                )
        {
            out.writeObject(blk);
            return baos.toByteArray(); //Return byte[]

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
