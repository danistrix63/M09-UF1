import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    private static final String CHARSET = "abcdefABCDEF1234567890!";
    private int npass = 0;

    // Método para obtener SHA-512 con salt
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(salt.getBytes());
        byte[] hash = sha512.digest(pw.getBytes());
        return HexFormat.of().formatHex(hash);
    }

    // Método para obtener PBKDF2 con salt
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(hash);
    }

    // Método para realizar fuerza bruta con 5 bucles
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;

        for (char c1 : CHARSET.toCharArray()) {
            for (char c2 : CHARSET.toCharArray()) {
                for (char c3 : CHARSET.toCharArray()) {
                    for (char c4 : CHARSET.toCharArray()) {
                        for (char c5 : CHARSET.toCharArray()) {
                            String pw = "" + c1 + c2 + c3 + c4 + c5;
                            String hashAttempt = alg.equals("SHA-512")
                                    ? getSHA512AmbSalt(pw, salt)
                                    : getPBKDF2AmbSalt(pw, salt);
                            npass++;

                            if (hashAttempt.equals(hash)) {
                                return pw;
                            }
                        }
                    }
                }
            }
        }

        return null; // No se encontró
    }

    // Método para obtener el número de intentos
    public int getNpass() {
        return npass;
    }

    // Método para calcular el tiempo transcurrido
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours % 24, minutes % 60, seconds % 60, millis % 1000);
    }
}
