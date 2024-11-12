package iticbcn.xifratge;

import java.security.KeyPair;
import javax.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String[] args) throws Exception {
        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";

        // Generar el par de claves RSA
        KeyPair parellClaus = cp.generaParellClausRSA();

        // Cifrar el mensaje
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());

        System.out.println("=================================");
        System.out.print("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));

        // Descifrar el mensaje
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        System.out.println("=================================");
        System.out.println("Text desxifrat: " + msgDesxifrat);

        // Mostrar claves
        System.out.println("Clau pública: " + DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded()));
        System.out.println("Clau privada: " + DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded()));
    }
}
