package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class XifradorAES implements Xifrador {
    private static final String ALGORISME_XIFRAT = "AES";
    private static final String ALGORISME_HASH = "SHA-256";
    private static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] msgBytes = msg.getBytes();
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] keyBytes = sha.digest(clau.getBytes("UTF-8"));
            keyBytes = Arrays.copyOf(keyBytes, 32);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] msgXifrat = cipher.doFinal(msgBytes);
            byte[] ivIMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
            System.arraycopy(iv, 0, ivIMsgXifrat, 0, MIDA_IV);
            System.arraycopy(msgXifrat, 0, ivIMsgXifrat, MIDA_IV, msgXifrat.length);

            return new TextXifrat(Base64.getEncoder().encode(ivIMsgXifrat));
        } catch (Exception e) {
            System.err.println("Error en cifrado AES: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bIvIMsgXifrat = Base64.getDecoder().decode(xifrat.getBytes());
            System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
            System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);

            MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] keyBytes = sha.digest(clau.getBytes("UTF-8"));
            keyBytes = Arrays.copyOf(keyBytes, 32);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
            return new String(msgDesxifrat);
        } catch (Exception e) {
            System.err.println("Error en descifrado AES: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}
