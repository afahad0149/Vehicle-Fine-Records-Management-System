import java.security.MessageDigest;

public class BlockHasher
{
//    Define messagedigest
    private static MessageDigest md;

//    Hashing the block with salt
    public static String hash(byte[] blockBytes, String ALGO)
    {
        String hashOutput = null;
        byte[] inputBytes = blockBytes;

        try
        {
            md = MessageDigest.getInstance(ALGO); //Hash using SHA-256.
            md.update(inputBytes); //feed in input bytearray to md instance

//            Adding salt.
            md.update(Salt.gen());

//            Generate the hash output as byte[]
            byte[] hashBytes = md.digest();

//            Byte[] not much readability hence convert to Hex format.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++)
            {
                sb. append(Integer.toHexString(0xFF & hashBytes[i]));
            }
            hashOutput = sb.toString();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return hashOutput; //Return SHA-256 hashed Block data.
    }
}
