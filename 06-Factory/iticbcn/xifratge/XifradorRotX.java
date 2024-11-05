package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private static final int MAX_DESPLAÇAMENT = 40;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = obtenirDesplaçament(clau);
        return new TextXifrat(xifraRotX(msg, desplaçament).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = obtenirDesplaçament(clau);
        return desxifraRotX(new String(xifrat.getBytes()), desplaçament);
    }

    private int obtenirDesplaçament(String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if (desplaçament < 0 || desplaçament > MAX_DESPLAÇAMENT) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desplaçament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    private String xifraRotX(String msg, int desplaçament) {
        StringBuilder resultat = new StringBuilder();
        for (char c : msg.toCharArray()) {
            resultat.append(rotar(c, desplaçament));
        }
        return resultat.toString();
    }

    private String desxifraRotX(String msg, int desplaçament) {
        return xifraRotX(msg, -desplaçament);
    }

    private char rotar(char c, int desplaçament) {
        if (!Character.isLetter(c)) return c;
        char base = Character.isUpperCase(c) ? 'A' : 'a';
        return (char) ((c - base + desplaçament + 26) % 26 + base);
    }
}
