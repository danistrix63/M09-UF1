import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    public static void main(String[] args) {

    }

    public static final char[] abecedari = { 'a', 'á', 'b', 'c', 'ç', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k',
            'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };

    public static char[] permutaAlfabet(Char[] abecedari) {
        List<Character> abecedariList = new ArrayList<>();
        for (char letra : abecedari) {
            abecedariList.add(letra);
        }
        Collections.shuffle(abecedariList);
        char[] abecedarishuffled = new char[abecedariList.size()];
        for(int i = 0; i < abecedariList.length();i++){
            abecedarishuffled[i] = abecedariList.get(i);
        }
        return abecedarishuffled;
    }
    public static string xifraMonoAlfa(String cadena ) {
        String novaParaula = "";
        boolean mayus = false;
        for(int i = 0; i < cadena.length(); i++) {
            
        }
    }
}
