package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private Random random;
    private char[] permutacio;

    private static final char[] ABECEDARI = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                                             'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private void initRandom(String clau) throws ClauNoSuportada {
        try {
            long seed = Long.parseLong(clau);
            random = new Random(seed);
            permutaAlfabet();
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    private void permutaAlfabet() {
        List<Character> abecedariList = new ArrayList<>();
        for (char c : ABECEDARI) {
            abecedariList.add(c);
        }
        Collections.shuffle(abecedariList, random);
        permutacio = new char[abecedariList.size()];
        for (int i = 0; i < abecedariList.size(); i++) {
            permutacio[i] = abecedariList.get(i);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return new TextXifrat(msg.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return new String(xifrat.getBytes());
    }
}
