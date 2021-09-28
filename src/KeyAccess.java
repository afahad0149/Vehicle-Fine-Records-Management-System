import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyAccess
{
    public static PublicKey getPublicKey() throws Exception
    {
//        content is stored in byte[] array, need to convert back to original form (keyspec).
        byte[] keyBytes = Files.readAllBytes(Paths.get(Config.PUBLICKEY_FILE));

//        convert to proper keyspec
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Config.ALGORITHM).generatePublic(spec);
    }

    public static PrivateKey getPrivateKey() throws Exception
    {
//        content is stored in byte[] array, need to convert back to original form (keyspec).
        byte[] keyBytes = Files.readAllBytes(Paths.get(Config.PRIVATEKEY_FILE));

//        convert to proper keyspec
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Config.ALGORITHM).generatePrivate(spec);
    }
}
