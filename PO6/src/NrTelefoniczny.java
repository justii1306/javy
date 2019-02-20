public class NrTelefoniczny implements Comparable<NrTelefoniczny>{

    private int nrTelefonu;
    private int nrKierunkowy;

    public NrTelefoniczny(int nrTelefonu, int nrKierunkowy) {
        this.nrTelefonu = nrTelefonu;
        this.nrKierunkowy = nrKierunkowy;
    }

    public int getNrTelefonu() {
        return nrTelefonu;
    }

    public int getNrKierunkowy() {
        return nrKierunkowy;
    }

    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public void setNrKierunkowy(int nrKierunkowy) {
        this.nrKierunkowy = nrKierunkowy;
    }

    @Override
    public String toString(){
        return "" + this.nrKierunkowy + this.nrTelefonu;
    }

    @Override
    public int compareTo(NrTelefoniczny o) {
        Long tenNumer = Long.parseLong(this.toString());
        Long porównywanyNumer = Long.parseLong(o.toString());
        return Long.compare(tenNumer, porównywanyNumer);
    }
}
