/*
 *Este programa implementa el cifrado y descifrado utilizando el algoritmo ROT13,
 *La función `xifraRot13` cifra un texto desplazando 13 posiciones en el alfabeto,
 *La función `desxifraRot13` descifra el texto, revirtiendo el desplazamiento de 13 posiciones.
 *El abecedario tiene la letra Ñ por lo tanto són 27 letras
 */
public class Rot13 {
    public static void main(String [] args) {
        // Prueba del cifrado ROT13
        String texto = "HolaÑ";
        String textoCifrado = xifraRot13(texto);
        System.out.println("Texto cifrado: " + textoCifrado);
        // Prueba del descifrado ROT13
        String textoDescifrado = desxifraRot13(textoCifrado);
        System.out.println("Texto descifrado: " + textoDescifrado); // Debería mostrar HolaÑ
    }
    //arrays globals
    public static final char[] abecedariMin = {'a', 'á', 'b', 'c', 'ç', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] abecedariMay = {'A', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
        
    //xifrador
    public static final String xifraRot13(String paraula,int desplaçament) {
        String novaParaula = "";
        for(int i = 0; i < paraula.length();i++) {
            // Verifica si es letra
            if(Character.isLetter(paraula.charAt(i))) {
                // Si es mayúscula
                if(Character.isUpperCase(paraula.charAt(i))) {
                    for(int x = 0; x < abecedariMay.length;x++)  {
                        if(paraula.charAt(i) == abecedariMay[x]) {
                            int xifradorNum = x + 13;
                            // Si se pasa de 27, restamos 27
                            if(xifradorNum >= abecedariMay.length) {
                                xifradorNum -= 27;
                            }
                            novaParaula += abecedariMay[xifradorNum];
                            break;
                        }
                    }
                // Si es minuscula
                } else {
                    for(int y = 0; y < abecedariMin.length;y++) {
                        if(paraula.charAt(i) == abecedariMin[y]) {
                            int xifradorNum = y + 13;
                            // Si se pasa de 27, restamos 27
                            if(xifradorNum >= abecedariMin.length) {
                                xifradorNum -= 27;
                            }
                            novaParaula += abecedariMin[xifradorNum];
                            break;
                        }
                    }
                }
            //si no es letra la dejamos como esta
            } else {
                novaParaula += paraula.charAt(i);
            }
        }
        return novaParaula;
    }
    //desxifrador
    public static final String desxifraRot13(String paraula,int desplaçament) {
        String novaParaula = "";
        for(int i = 0; i < paraula.length();i++) {
            // Verifica si es letra
            if(Character.isLetter(paraula.charAt(i))) {
                // Si es mayúscula
                if(Character.isUpperCase(paraula.charAt(i))) {
                    for(int x = 0; x < abecedariMay.length;x++)  {
                        if(paraula.charAt(i) == abecedariMay[x]) {
                            int desxifradorNum = x - 13;
                            // Si se pasa de 0, sumamos 27
                            if(desxifradorNum < 0) {
                                desxifradorNum += 27;
                            }
                            novaParaula += abecedariMay[desxifradorNum];
                            break;
                        }
                    }
                // Si es minuscula
                } else {
                    for(int y = 0; y < abecedariMin.length;y++) {
                        if(paraula.charAt(i) == abecedariMin[y]) {
                            int desxifradorNum = y - 13;
                            // Si se pasa de 0, sumamos 27
                            if(desxifradorNum < 0) {
                                desxifradorNum += 27;
                            }
                            novaParaula += abecedariMin[desxifradorNum];
                            break;
                        }
                    }
                }
            //si no es letra la dejamos como esta
            } else {
                novaParaula += paraula.charAt(i);
            }
        }
        return novaParaula;
    }

}

