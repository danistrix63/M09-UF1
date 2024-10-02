/*
 *Este programa implementa el cifrado y descifrado utilizando el algoritmo ROT13,
 *La función `xifraRot13` cifra un texto desplazando 13 posiciones en el alfabeto,
 *La función `desxifraRot13` descifra el texto, revirtiendo el desplazamiento de 13 posiciones.
 *El abecedario tiene la letra Ñ por lo tanto són 27 letras
 */
public class Rot13 {
    public static void main(String[] args) {
        // Prueba del cifrado ROT13 con desplazamiento 7
        String texto = "HolaÑ";
        String textoCifrado = xifraRot13(texto, 7);
        System.out.println("Texto cifrado: " + textoCifrado);
        
        // Prueba del descifrado ROT13 con desplazamiento 7
        String textoDescifrado = desxifraRot13(textoCifrado, 7);
        System.out.println("Texto descifrado: " + textoDescifrado); // Debería mostrar "HolaÑ"
        
        // Prueba de fuerza bruta sobre el texto cifrado
        System.out.println("\nPrueba de fuerza bruta:");
        forcaBrutaRotX(textoCifrado);
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
                            int xifradorNum = x + desplaçament;
                            // Si se pasa del length del abecedario, restamos el length
                            if(xifradorNum >= abecedariMay.length) {
                                xifradorNum -= abecedariMay.length;
                            }
                            novaParaula += abecedariMay[xifradorNum];
                            break;
                        }
                    }
                // Si es minuscula
                } else {
                    for(int y = 0; y < abecedariMin.length;y++) {
                        if(paraula.charAt(i) == abecedariMin[y]) {
                            int xifradorNum = y + desplaçament;
                            // Si se pasa del length del abecedario restamos el length
                            if(xifradorNum >= abecedariMin.length) {
                                xifradorNum -= abecedariMin.length;
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
                            int desxifradorNum = x - desplaçament;
                            // Si se pasa de 0, sumamos el length
                            if(desxifradorNum < 0) {
                                desxifradorNum += abecedariMay.length;
                            }
                            novaParaula += abecedariMay[desxifradorNum];
                            break;
                        }
                    }
                // Si es minuscula
                } else {
                    for(int y = 0; y < abecedariMin.length;y++) {
                        if(paraula.charAt(i) == abecedariMin[y]) {
                            int desxifradorNum = y - desplaçament;
                            // Si se pasa de 0, sumamos el length
                            if(desxifradorNum < 0) {
                                desxifradorNum += abecedariMin.length;
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
    public static void forcaBrutaRotX(String cadenaXifrada) {
        // Longitud del abecedario
        int longitudAbecedari = abecedariMin.length;
        
        // Iteramos sobre todos los desplazamientos posibles (de 1 hasta la longitud del abecedario)
        for (int desplaçament = 1; desplaçament < longitudAbecedari; desplaçament++) {
            // Desxiframos la cadena con el desplazamiento actual
            String resultat = desxifraRot13(cadenaXifrada, desplaçament);
            
            // Mostramos el resultado
            System.out.println("Desplaçament " + desplaçament + ": " + resultat);
        }
    }

}

