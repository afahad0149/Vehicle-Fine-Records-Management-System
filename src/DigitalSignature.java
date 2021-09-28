import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class DigitalSignature
{
//    Hashing Algorithm
    private String HASHING_ALGO = "SHA-256";

//    Crypto Algorithm
    private String CRYPTO_ALGO = "SHA256withECDSA";

    private Cipher cipher;
    private KeyPairGenerator keygen;
    private KeyPair keypair;

//    Constructor
    public DigitalSignature()
    {
        try
        {
            cipher = Cipher.getInstance(CRYPTO_ALGO);
            keygen = KeyPairGenerator.getInstance(CRYPTO_ALGO);
            keypair = keygen.generateKeyPair();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    Encryption Cipher
    public byte[] hash(String data)
    {
        byte[] hashBytes = null;
//        API-MessageDigest
        try
        {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGO);
            hashBytes = md.digest(data.getBytes());
            return hashBytes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    encrypt; verify (decrypt)
    public String encrypt(byte[] hash)
    {
        byte[] dsBytes = null;
        try
        {
//            init
            cipher.init(Cipher.ENCRYPT_MODE, keypair.getPrivate());
            dsBytes = cipher.doFinal(hash);
        }
        catch (Exception e)
        {
        }

        if (dsBytes == null)
        {
            return Base64.getEncoder().encodeToString(dsBytes);
        }

        else
        {
            return null;
        }
    }

    public boolean verify(String data, String ds)
    {
        byte[] dataHash = hash(data);
        byte[] dsBytes = null;

        try
        {
            cipher.init(Cipher.DECRYPT_MODE, keypair.getPublic());
            dsBytes = cipher.doFinal(Base64.getDecoder().decode(ds));
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return Arrays.equals(dataHash, dsBytes);
    }
}
