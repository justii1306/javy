import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String nazwyKolumn[] = {"Tytuł", "Autor", "Liczba stron"};
        String numeryKolumn[];
        Tabela przykladowaTabela = new Tabela(nazwyKolumn);
        String input;
        int wybór;
        Scanner sc = new Scanner(System.in);

        String polaRekordu[] = {"Harry Potter", "J.K. Rowling", "843"};
        przykladowaTabela.insert(polaRekordu);
        polaRekordu = new String[]{"Zmierzch", "Nie pamietam", "132"};
        przykladowaTabela.insert(polaRekordu);
        while(0==0){
            System.out.println("Wybierz co chcesz zrobić: ");
            System.out.println("1. SELECT");
            System.out.println("2. INSERT");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. Zapisz do pliku");
            System.out.println("6. Odczytaj z pliku");
            wybór = sc.nextInt();
            switch (wybór){
                case 1:
                    System.out.println("Wybierz czy chcesz wybrac wszystkie pola, czy tylko niektóre: ");
                    System.out.println("1. Wszystkie");
                    System.out.println("2. Niektóre");
                    wybór = sc.nextInt();
                    switch (wybór){
                        case 1:
                            przykladowaTabela.select();
                            break;
                        case 2:
                            przykladowaTabela.wypiszNazwyKolumn();
                            System.out.println("Podaj numery kolumn (od 1 do " + przykladowaTabela.nazwyKolumn.length + ") oddzielone spacją");
                            sc.nextLine();
                            input = sc.nextLine();
                            numeryKolumn = input.split(" ");
                            try {
                                for (String kolumna : numeryKolumn){
                                        Integer.parseInt(kolumna);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Wprawadzono niepoprawną wartość, spróbuj ponowanie");
                                break;
                            }
                            przykladowaTabela.select(numeryKolumn);
                            break;
                        default:
                            System.out.println("Niepoprawny wybór");
                            break;
                    }
                    break;
                case 2:
                    przykladowaTabela.wypiszNazwyKolumn();
                    System.out.println("Podaj " + przykladowaTabela.nazwyKolumn.length + " wartości oddzielone przecinkiem i spacją");
                    sc.nextLine();
                    input = sc.nextLine();
                    polaRekordu = input.split(", ");
                    if (polaRekordu.length != przykladowaTabela.nazwyKolumn.length) {
                        System.out.println("Wprawadzono niepoprawną ilosc wartosci, spróbuj ponowanie");
                        break;
                    } else {
                        przykladowaTabela.insert(polaRekordu);
                        System.out.println("Dodano rekord");
                    }
                    break;

                case 3:
                    przykladowaTabela.wypiszNazwyKolumn();
                    System.out.println("Podaj numery kolumn (od 1 do " + przykladowaTabela.nazwyKolumn.length + ") oddzielone spacją");
                    sc.nextLine();
                    input = sc.nextLine();
                    numeryKolumn = input.split(" ");
                    try {
                        for (String kolumna : numeryKolumn){
                            Integer.parseInt(kolumna);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wprawadzono niepoprawną wartość, spróbuj ponowanie");
                        break;
                    }
                    przykladowaTabela.update(numeryKolumn);
                    break;

                case 4:
                    przykladowaTabela.delete();
                    break;

                case 5:
                    try {
                        przykladowaTabela.zapiszDoPliku("tabela.txt");
                        System.out.println("Zapisano tabele do pliku");
                    } catch (IOException e){
                        System.out.println("Nie udało się zapisać do pliku");
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        przykladowaTabela.odczytajZPliku("tabela.txt");
                        System.out.println("Odczytano tabele z pliku");
                    } catch (IOException e){
                        System.out.println("Nie udało się odczytać z pliku");
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Niepoprawny wybór");
                    break;
            }
        }
    }
}
