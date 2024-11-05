package iticbcn.xifratge;

public class XifradorMonoalfabetic implements Xifrador {
    private char[] permutacio;

    public XifradorMonoalfabetic() {
        permutacio = permutaAlfabet();
    }

    private char[] permutaAlfabet() {
        // Implementa la permutació aquí
        return new char[0];
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        // Lògica de xifrat
        return new TextXifrat(msg.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        // Lògica de desxifrat
        return new String(xifrat.getBytes());
    }
}
