import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    public static final char[] abecedari = { 'a', 'á', 'b', 'c', 'ç', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k',
            'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };
    public static char[] permutacioGlobal;
    private static Random random;

    public static void main(String[] args) {
        String clauSecreta = "contrasenya";
        String msgs[] = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(String clauSecreta) {
        random = new Random(clauSecreta.hashCode());
        permutaAlfabet();
    }

    // Genera una permutación del abecedario
    public static void permutaAlfabet() {
        List<Character> abecedariList = new ArrayList<>();
        for (char letra : abecedari) {
            abecedariList.add(letra);
        }
        Collections.shuffle(abecedariList, random);
        permutacioGlobal = new char[abecedariList.size()];
        for (int i = 0; i < abecedariList.size(); i++) {
            permutacioGlobal[i] = abecedariList.get(i);
        }
    }

    // Cifra el mensaje usando cifrado polialfabético
    public static String xifraPoliAlfa(String msg) {
        StringBuilder msgXifrat = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            boolean mayus = Character.isUpperCase(lletra);
            lletra = Character.toLowerCase(lletra);

            int index = buscaLletra(lletra);
            if (index != -1) {
                char lletraXifrada = permutacioGlobal[index];
                msgXifrat.append(mayus ? Character.toUpperCase(lletraXifrada) : lletraXifrada);
            } else {
                msgXifrat.append(lletra);
            }
            // Permuta el abecedario para la próxima letra
            permutaAlfabet();
        }
        return msgXifrat.toString();
    }

    // Descifra el mensaje
    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder msgDesxifrat = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletra = msgXifrat.charAt(i);
            boolean mayus = Character.isUpperCase(lletra);
            lletra = Character.toLowerCase(lletra);

            int index = buscaLletraPermutat(lletra, permutacioGlobal);
            if (index != -1) {
                char lletraDesxifrada = abecedari[index];
                msgDesxifrat.append(mayus ? Character.toUpperCase(lletraDesxifrada) : lletraDesxifrada);
            } else {
                msgDesxifrat.append(lletra); // Mantiene caracteres no alfabéticos
            }
            // Permuta el abecedario para la próxima letra
            permutaAlfabet();
        }
        return msgDesxifrat.toString();
    }

    // Busca la letra en el abecedario
    public static int buscaLletra(char lletra) {
        for (int i = 0; i < abecedari.length; i++) {
            if (abecedari[i] == lletra) {
                return i;
            }
        }
        return -1;
    }

    // Busca la letra en la permutación
    public static int buscaLletraPermutat(char lletra, char[] permutacio) {
        for (int i = 0; i < permutacio.length; i++) {
            if (permutacio[i] == lletra) {
                return i;
            }
        }
        return -1;
    }
}
