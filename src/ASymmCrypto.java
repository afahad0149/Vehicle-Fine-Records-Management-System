import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class ASymmCrypto
{
    private Cipher cipher;

//    Constructor
    public ASymmCrypto()
    {
        try
        {
            cipher = Cipher.getInstance(Config.ALGORITHM);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    Encryption using public key
    public String encrypt(String data, PublicKey key) throws Exception
    {
//        Convert to byteArray for encryption
        String cipherText = null;
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());

        cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        return cipherText;
    }

//    decryption using private key
    public String decrypt(String cipherText, PrivateKey key) throws Exception
    {
//        Convert to string for decryption.
        String data = null;
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] dataBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));

        data = new String(dataBytes);
        return data;
    }
}
