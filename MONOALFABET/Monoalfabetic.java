import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    public static final char[] abecedari = { 'a', 'á', 'b', 'c', 'ç', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k',
            'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };

    public static void main(String[] args) {
        char[] permutacio = permutaAlfabet(abecedari);
        String textOriginal = "Hola món!";
        String textXifrat = xifraMonoAlfa(textOriginal, permutacio);
        System.out.println("Text xifrat: " + textXifrat);
        String textDesxifrat = desxifraMonoAlfa(textXifrat, permutacio);
        System.out.println("Text desxifrat: " + textDesxifrat);
    }
    // metode que permuta el abecedari amb Shuffled
    public static char[] permutaAlfabet(char[] abecedari) {
        List<Character> abecedariList = new ArrayList<>();
        for (char letra : abecedari) {
            abecedariList.add(letra);
        }
        Collections.shuffle(abecedariList);
        char[] abecedariShuffled = new char[abecedariList.size()];
        for (int i = 0; i < abecedariList.size(); i++) {
            abecedariShuffled[i] = abecedariList.get(i);
        }
        return abecedariShuffled;
    }

    public static String xifraMonoAlfa(String cadena, char[] permutacio) {
        StringBuilder novaParaula = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean mayus = Character.isUpperCase(lletra);
            lletra = Character.toLowerCase(lletra);

            int index = buscaLletra(lletra);
            if (index != -1) {
                char lletraXifrada = permutacio[index];
                novaParaula.append(mayus ? Character.toUpperCase(lletraXifrada) : lletraXifrada);
            } else {
                // Si el caràcter no és una lletra (espai, símbol, etc.), es manté igual
                novaParaula.append(lletra);
            }
        }
        return novaParaula.toString();
    }
    //buscar la lletra al abecedari
    public static int buscaLletra(char lletra) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == lletra) {
                return i;
            }
        }
        return -1;
    }
    //desxifrar la paraula al abecedari permutat
    public static String desxifraMonoAlfa(String cadena, char[] permutacio) {
        StringBuilder paraulaOriginal = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean mayus = Character.isUpperCase(lletra);
            lletra = Character.toLowerCase(lletra);

            int index = buscaLletraPermutat(lletra, permutacio);
            if (index != -1) {
                char lletraDesxifrada = abecedari[index];
                paraulaOriginal.append(mayus ? Character.toUpperCase(lletraDesxifrada) : lletraDesxifrada);
            } else {
                paraulaOriginal.append(lletra);
            }
        }
        return paraulaOriginal.toString();
    }
    //buscar la lletra al abecedari permutat
    public static int buscaLletraPermutat(char lletra, char[] permutacio) {
        for (int i = 0; i < permutacio.length; i++) {
            if (permutacio[i] == lletra) {
                return i;
            }
        }
        return -1;
    }
}
