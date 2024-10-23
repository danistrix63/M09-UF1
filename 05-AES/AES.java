import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    // Definiciones a nivel de clase
    public static final String ALGORISME_XIFRAT = "AES"; // Algoritmo de cifrado
    public static final String ALGORISME_HASH = "SHA-256"; // Algoritmo de hash para la clave
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding"; // Modo de operación AES
    private static final int MIDA_IV = 16; // Tamaño del vector de inicialización (IV) en bytes
    private static byte[] iv = new byte[MIDA_IV]; // Array para almacenar el IV
    private static final String CLAU = "LaClauSecretaQueVulguis"; // Clave secreta para el cifrado

    /**
     * Método para cifrar un mensaje utilizando AES en modo CBC con PKCS5Padding.
     *
     * @param msg  El mensaje en texto claro que queremos cifrar.
     * @param clau La clave utilizada para el cifrado.
     * @return Un String codificado en Base64 que contiene el IV concatenado con el mensaje cifrado.
     * @throws Exception En caso de error durante el proceso de cifrado.
     */
    public static String xifraAES(String msg, String clau) throws Exception {
        // Obtener los bytes del mensaje a cifrar
        byte[] msgBytes = msg.getBytes();

        // Generar un IV (vector de inicialización) seguro de forma aleatoria
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // Llenamos el array 'iv' con valores aleatorios
        IvParameterSpec ivSpec = new IvParameterSpec(iv); // Creamos el IV para el cifrado

        // Generar un hash SHA-256 a partir de la clave
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = sha.digest(clau.getBytes("UTF-8")); // Convertir la clave en un hash
        keyBytes = Arrays.copyOf(keyBytes, 32); // Aseguramos que el tamaño de la clave sea de 256 bits
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT); // Creamos la clave para el cifrado

        // Configurar el cifrador para AES en modo CBC con relleno PKCS5
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec); // Inicializamos el cifrador

        // Cifrar el mensaje
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        // Concatenar el IV y el mensaje cifrado en un solo array de bytes
        byte[] ivIMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, ivIMsgXifrat, 0, MIDA_IV); // Insertamos el IV al inicio
        System.arraycopy(msgXifrat, 0, ivIMsgXifrat, MIDA_IV, msgXifrat.length); // Insertamos el mensaje cifrado

        // Retornar el mensaje cifrado en formato Base64 para facilitar su lectura
        return Base64.getEncoder().encodeToString(ivIMsgXifrat);
    }

    /**
     * Método para descifrar un mensaje cifrado utilizando AES en modo CBC con PKCS5Padding.
     *
     * @param bIvIMsgXifratBase64 El mensaje cifrado en formato Base64 que incluye el IV al inicio.
     * @param clau La clave utilizada para el descifrado.
     * @return El mensaje descifrado en formato String.
     * @throws Exception En caso de error durante el proceso de descifrado.
     */
    public static String desxifraAES(String bIvIMsgXifratBase64, String clau) throws Exception {
        // Decodificar el mensaje desde Base64 a un array de bytes
        byte[] bIvIMsgXifrat = Base64.getDecoder().decode(bIvIMsgXifratBase64);

        // Extraer el IV del mensaje cifrado (primeros 16 bytes)
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV); // Extraemos el IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv); // Creamos el IV para el descifrado

        // Extraer la parte cifrada del mensaje (el resto después de los primeros 16 bytes)
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);

        // Generar un hash SHA-256 a partir de la clave
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = sha.digest(clau.getBytes("UTF-8"));
        keyBytes = Arrays.copyOf(keyBytes, 32); // Aseguramos que el tamaño de la clave sea de 256 bits
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT); // Creamos la clave para el descifrado

        // Configurar el descifrador para AES en modo CBC con relleno PKCS5
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec); // Inicializamos el descifrador

        // Descifrar el mensaje
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        // Retornar el mensaje descifrado como un String
        return new String(msgDesxifrat);
    }

    /**
     * Método principal para ejecutar pruebas de cifrado y descifrado de mensajes.
     *
     * @param args Argumentos del programa (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Array de mensajes a cifrar
        String[] msgs = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora ïlla Ôtto"
        };

        // Probar el cifrado y descifrado para cada mensaje
        for (String msg : msgs) {
            try {
                // Cifrar el mensaje
                String msgXifratBase64 = xifraAES(msg, CLAU);
                // Descifrar el mensaje cifrado
                String desxifrat = desxifraAES(msgXifratBase64, CLAU);

                // Mostrar los resultados
                System.out.println("Mensaje original: " + msg);
                System.out.println("Mensaje cifrado (Base64): " + msgXifratBase64);
                System.out.println("Mensaje descifrado: " + desxifrat);
                System.out.println();
            } catch (Exception e) {
                System.err.println("Error durante el proceso de cifrado/descifrado: " + e.getLocalizedMessage());
            }
        }
    }
}
