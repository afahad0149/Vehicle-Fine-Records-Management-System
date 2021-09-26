import java.security.SecureRandom;

public class Salt
{
//    Length of Salt Value
    private static final int SIZE = 16;

//    Generate a random salt value.
    public static byte[] gen()
    {
        SecureRandom sr = new SecureRandom();
        byte[] b = new byte[SIZE];
        sr.nextBytes(b);
        return b;
    }
}
