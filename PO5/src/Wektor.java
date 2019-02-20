import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Vector;

public class Wektor {
    private Vector<Integer> wektor;
    private boolean poprawnyWektor;

    public Wektor(Vector<Integer> wektor) {
        this.wektor = wektor;
        this.poprawnyWektor = true;
    }

    public Wektor(String wektor) {
        this.wektor = new Vector<>();
        this.poprawnyWektor = sprawdzWektorJestPoprawny(wektor);
        if (this.poprawnyWektor) {
            wektor = sprawdzCzyNaPoczatkuWektoraJestPlus(wektor);
            for (int i = 0; i < wektor.length(); i++) {
                this.wektor.add(wektor.charAt(i) - '0');
            }
        } else {
            System.out.println("Podano niepoprawny wektor: " + wektor);
        }
    }

    private boolean sprawdzWektorJestPoprawny(String wektor){
        if (wektor.startsWith("-"))
            return false;
        else {
            try {
                new BigInteger(wektor);
            } catch (NumberFormatException e) {
                return false;
            } catch (ArithmeticException e) { //BigInteger constructors and operations throw ArithmeticException when the result is out of the supported range
                return false;
            }
        }
        return true;
    }

    String sprawdzCzyNaPoczatkuWektoraJestPlus(String wektor){
        return (wektor.startsWith("+")) ? wektor.substring(1) : wektor;
    }

    public void zapiszDoPliku(String nazwaPliku) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(nazwaPliku));
        for (Integer liczba : this.wektor) {
            pw.print(liczba);
            pw.print(" ");
        }
        pw.close();
    }

    public boolean isPoprawnyWektor() {
        return poprawnyWektor;
    }

    public Vector<Integer> getWektor() {
        return wektor;
    }

    static boolean sprawdzCzyWektorySaTejSamejDlugosci(Wektor wektor1, Wektor wektor2) throws WektoryRoznejDlugosciException {
        if (wektor1.getWektor().size() != wektor2.getWektor().size()){
            throw new WektoryRoznejDlugosciException(wektor1, wektor2);
        } else return true;
    }

    static Wektor dodajDwaWektory(Wektor wektor1, Wektor wektor2){
        Vector<Integer> vectorWynikowy = new Vector<>();
        for (int i = 0; i < wektor1.getWektor().size(); i++)
            vectorWynikowy.add(wektor1.getWektor().elementAt(i) + wektor2.getWektor().elementAt(i));
        return new Wektor(vectorWynikowy);
    }
}
