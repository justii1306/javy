import com.sun.prism.impl.Disposer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Tabela {
    ArrayList<Rekord> listaRekordów;
    String nazwyKolumn[];

    public Tabela(String nazwyKolumn[]) {
        this.listaRekordów = new ArrayList<>();
        this.nazwyKolumn = nazwyKolumn;
    }

    public boolean insert(String polaRekordu[]){
        if (polaRekordu.length != this.nazwyKolumn.length)
            return false;
        Rekord rekord = new Rekord(polaRekordu);
        listaRekordów.add(rekord);
        return true;
    }

    public void select(){
        for (String kolumna : nazwyKolumn)
            System.out.print("[" + kolumna + "] ");
        System.out.println(": ");
        for (Rekord rekord : listaRekordów){
            for (String pole : rekord.polaRekordu)
                System.out.print("[" + pole + "] ");
            System.out.println();
        }
    }

    public void select(String numeryKolumn[]){
        Vector<Integer> poprawneKolumny = new Vector<>();
        int liczbaNiepoprawnychKolumn = 0;
        for (String numerKolumny : numeryKolumn){
            if (Integer.parseInt(numerKolumny) <= nazwyKolumn.length){
                if (!poprawneKolumny.contains(Integer.parseInt(numerKolumny)-1))
                    poprawneKolumny.add(Integer.parseInt(numerKolumny)-1);
            } else
                liczbaNiepoprawnychKolumn++;
        }
        for (Integer kolumna : poprawneKolumny)
            System.out.print("[" + nazwyKolumn[kolumna] + "] ");
        System.out.println(": ");
        for (Rekord rekord : listaRekordów) {
            for (Integer kolumna : poprawneKolumny)
                System.out.print("[" + rekord.polaRekordu[kolumna] + "] ");
            System.out.println();
        }
    }

    public void wypiszNazwyKolumn(){
        for (String kolumna : nazwyKolumn)
            System.out.print("[" + kolumna + "] ");
        System.out.println();
    }

    public void update(String[] numeryKolumn) {
        boolean poprawnaOdpowiedz = false;
        String input;
        String polaRekordu[];
        Scanner sc = new Scanner(System.in);
        Vector<Integer> poprawneKolumny = new Vector<>();
        for (String numerKolumny : numeryKolumn){
            if (Integer.parseInt(numerKolumny) <= nazwyKolumn.length){
                if (!poprawneKolumny.contains(Integer.parseInt(numerKolumny)-1))
                    poprawneKolumny.add(Integer.parseInt(numerKolumny)-1);
            }
        }
        for (Rekord rekord : listaRekordów) {
            for (Integer kolumna : poprawneKolumny)
                System.out.print("[" + rekord.polaRekordu[kolumna] + "] ");
            poprawnaOdpowiedz = false;
            while (poprawnaOdpowiedz == false) {
                System.out.println("\nCzy chcesz zaktualizować ten rekord? (y/n)");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")){
                        System.out.println("Zaktualizowano rekord");
                            if (input.equalsIgnoreCase("y")){
                                System.out.println("Podaj " + poprawneKolumny.size() + " wartości oddzielone przecinkiem i spacją");
                                input = sc.nextLine();
                                polaRekordu = input.split(", ");
                                if (polaRekordu.length != poprawneKolumny.size()) {
                                    System.out.println("Wprawadzono niepoprawną ilosc wartosci, spróbuj ponowanie");
                                    break;
                                } else {
                                    int i = 0;
                                    for (Integer kolumna : poprawneKolumny) {
                                        rekord.polaRekordu[kolumna] = polaRekordu[i];
                                        i++;
                                    }
                                }
                    }
                    poprawnaOdpowiedz = true;
                }
            }
        }
    }

    public void delete() {
        boolean poprawnaOdpowiedz = false;
        String input;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < listaRekordów.size(); i++) {
            for (String pole : listaRekordów.get(i).polaRekordu)
                System.out.print("[" + pole + "] ");
            poprawnaOdpowiedz = false;
            while (poprawnaOdpowiedz == false) {
                System.out.println("\nCzy chcesz usunąć ten rekord? (y/n)");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")){
                    if (input.equalsIgnoreCase("y")){
                        listaRekordów.remove(listaRekordów.get(i));
                        System.out.println("Usunięto rekord");
                        i--;
                    }
                }
                poprawnaOdpowiedz = true;
            }
        }
    }

    public void zapiszDoPliku(String nazwaPliku) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(nazwaPliku));
        for (int i = 0; i < nazwyKolumn.length-1; i++){
            pw.print(nazwyKolumn[i]);
            pw.print(", ");
        }
        pw.print(nazwyKolumn[nazwyKolumn.length-1]);
        pw.print("\n");
        for (Rekord rekord : listaRekordów){
            for (int i = 0; i < rekord.polaRekordu.length-1; i++){
                pw.print(rekord.polaRekordu[i]);
                pw.print(", ");
            }
            pw.print(rekord.polaRekordu[rekord.polaRekordu.length-1]);
            pw.print("\n");
        }
        pw.close();
    }

    public void odczytajZPliku(String nazwaPliku) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
        String nazwyKolumnLinia = br.readLine();
        setNazwyKolumn(nazwyKolumnLinia.split(", "));
        String linia;
        wyczyscRekordy();
        while ((linia = br.readLine()) != null){
            Rekord rekord = new Rekord(linia.split(", "));
            listaRekordów.add(rekord);
        }
        br.close();
    }

    public void setNazwyKolumn(String[] nazwyKolumn) {
        this.nazwyKolumn = nazwyKolumn;
    }

    public void wyczyscRekordy(){
        listaRekordów.clear();
    }
}
