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

//    Get-set Methods for API access
    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getNoticeID()
    {
        return NoticeID;
    }

    public void setNoticeID(String NoticeID)
    {
        this.NoticeID = NoticeID;
    }

    public String getIDCategory()
    {
        return IDCategory;
    }

    public void setIDCategory(String IDCategory)
    {
        this.IDCategory = IDCategory;
    }

    public String getNoticeType()
    {
        return NoticeType;
    }

    public void setNoticeType(String NoticeType)
    {
        this.NoticeType = NoticeType;
    }

    public String getOffenseType()
    {
        return OffenseType;
    }

    public void setOffenseType(String OffenseType)
    {
        this.OffenseType = OffenseType;
    }

    public String getOffenseLocation()
    {
        return OffenseLocation;
    }

    public void setOffenseLocation(String OffenseLocation)
    {
        this.OffenseLocation = OffenseLocation;
    }

    public String getOffenseDate()
    {
        return OffenseDate;
    }

    public void setOffenseDate(String OffenseDate)
    {
        this.OffenseDate = OffenseDate;
    }

    public String getPaymentDate()
    {
        return PaymentDate;
    }

    public void setPaymentDate(String PaymentDate)
    {
        this.PaymentDate = PaymentDate;
    }

    public String getHash()
    {
        return hash;
    }

    public void sethash(String hash)
    {
        this.hash = hash;
    }

    public String getPrevioushash()
    {
        return previoushash;
    }

    public void setPrevioushash(String previoushash)
    {
        this.previoushash = previoushash;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

//    Overriding toString Method
    @Override
    public String toString()
    {
        return "Block{" + "index = " + index + ", NoticeID = " + NoticeID + ", IDCategory = " + IDCategory
                + ", NoticeType = " + NoticeType + ", OffenseType = " + OffenseType + ", OfenseLocation = " + OffenseLocation
                + ", OffenseDate = " + OffenseDate + ", PaymentDate = " + PaymentDate + ", hash = " + hash
                + ", previoushash = " + previoushash + ", timestamp = " + timestamp + '}';
    }
}
