package net.piramide.gestionale_pizzeria;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabasePizze {

    public static void main(String[] args) throws IOException {
        DatabasePizze ciao = new DatabasePizze();
        //System.out.println(ciao.getPrezzo("Margherita"));
        ciao.getIngredienti("Margherita");


    }
    String referenceDatabase = "src/main/resources/DataBasePizza";

    public DatabasePizze() {
    }

    public double getPrezzo(String nome) throws IOException {
        String riga;
        int i;
        FileReader fr = new FileReader(referenceDatabase);
        BufferedReader bw = new BufferedReader(fr);

        for (i = 0; i < contaRighe(); i++) {
            riga = bw.readLine();

            // Dividi la riga in base alla virgola
            String[] parti = riga.split(", ");

            // Verifica se la prima parola è uguale al nome dato
            if (parti[0].equals(nome)) {
                // Ottieni il secondo elemento (indice 1) che contiene il prezzo
                String prezzoStringa = parti[1];

                // Converte la stringa in un valore numerico
                double prezzo = Double.parseDouble(prezzoStringa);

                fr.close();
                bw.close();
                // Ora 'prezzo' contiene il valore numerico del prezzo
                return prezzo;
            }
        }
        fr.close();
        bw.close();  // Assicurati di chiudere il BufferedReader alla fine
        return 0;
    }

    public void getIngredienti(String nome) throws IOException {
        String riga;
        int i;
        FileReader fr = new FileReader(referenceDatabase);
        BufferedReader bw = new BufferedReader(fr);

        for (i = 0; i < contaRighe(); i++) {
            riga = bw.readLine();

            // Dividi la riga in base alla virgola
            String[] parti = riga.split(", ");

            // Verifica se la prima parola è uguale al nome dato
            if (parti[0].equals(nome)) {
                // Trova l'indice della prima virgola
                int indicePrimaVirgola = riga.indexOf(',');

                riga = riga.substring(indicePrimaVirgola + 1).trim();

                indicePrimaVirgola = riga.indexOf(',');
                riga = riga.substring(indicePrimaVirgola + 1).trim();

                //rimuove le parentesi quadre prima e dopo
                riga = riga.substring(1, riga.length() - 1);

                System.out.println(riga);


                break;
            }
        }
        fr.close();
        bw.close();  // Assicurati di chiudere il BufferedReader alla fine

    }


    public int contaRighe() throws IOException {
        FileReader fr = new FileReader(referenceDatabase);
        BufferedReader bw = new BufferedReader(fr);
        int contatoreRighe = 0;

        while (bw.readLine() != null) {
            contatoreRighe++;
        }

        fr.close();
        bw.close();
        return contatoreRighe;
    }

}
