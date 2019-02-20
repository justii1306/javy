import java.util.Iterator;
import java.util.TreeMap;

public class Main{

    public static void main(String[] args) {

        TreeMap<NrTelefoniczny, Wpis> treeMap = new TreeMap<>();

        Osoba osoba1 = new Osoba("Justyna", "Misior", "Wróbla 18a/2", 730262026, 48);
        osoba1.opis();
        Osoba osoba2 = new Osoba("Mariusz", "Czajkowski", "Pięna 34/5/6", 796012131, 48);
        osoba2.opis();
        Firma firma1 = new Firma("Gruby Benek", "Ulica 42",123456789, 69);
        firma1.opis();
        Firma firma2 = new Firma("PizzaPlace", "Aleja 26",987654321, 45);
        firma2.opis();

        treeMap.put(osoba1.getAdres().getNumer(), osoba1);
        treeMap.put(osoba2.getAdres().getNumer(), osoba2);
        treeMap.put(firma1.getAdres().getNumer(), firma1);
        treeMap.put(firma2.getAdres().getNumer(), firma2);

        for (Iterator i = treeMap.keySet().iterator(); i.hasNext();) {
            Object key = i.next();
            Wpis value = treeMap.get(key);
            System.out.println(key + ": ");
            value.opis();
        }
    }
}
