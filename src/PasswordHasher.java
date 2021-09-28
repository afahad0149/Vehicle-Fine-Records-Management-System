import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher
{
//    Hash the password String.
    public static String hash(String passwordToHash)
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Hash using SHA-256.
            byte[] bytes = md.digest(passwordToHash.getBytes()); // feed in input bytearray to md instance.
            StringBuilder sb = new StringBuilder();

//            Append string values to byte[]
            for (int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString(); // Create hashed form of password.
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword; // Return SHA-256 hashed password form.
    }
}
