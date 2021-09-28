import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Keymaker
{
    KeyPairGenerator keygen;
    KeyPair keypair;

//    Constructor
    public Keymaker() throws Exception
    {
        keygen = KeyPairGenerator.getInstance(Config.ALGORITHM); //ECIES
        keygen.initialize(256); //This small keysize for ECC is more effective than 1024 keysize of RSA
    }

//    Make a keypair
    public static void mkKeyPair()
    {
        try
        {
//            Generate Key Pairs
            Keymaker keymaker = new Keymaker();
            keymaker.keypair = keymaker.keygen.generateKeyPair();

//            Get public key
            PublicKey publicKey = keymaker.keypair.getPublic();

//            Get private key
            PrivateKey privateKey = keymaker.keypair.getPrivate();

//            Store public key and private key into a file
            store(Config.PUBLICKEY_FILE, publicKey.getEncoded());
            store(Config.PRIVATEKEY_FILE, privateKey.getEncoded());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    Write key data to the file
    public static void store(String path, byte[] key)
    {
        File file = new File(path);
        file.getParentFile().mkdirs();

        try
        {
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
