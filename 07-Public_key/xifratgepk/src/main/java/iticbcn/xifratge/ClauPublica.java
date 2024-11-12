package iticbcn.xifratge;

import java.security.*;
import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes());
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        return new String(msgDesxifrat);
    }
}
