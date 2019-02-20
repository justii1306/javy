public class WektoryRoznejDlugosciException extends Exception{
    private Wektor wektor1;
    private Wektor wektor2;

    public WektoryRoznejDlugosciException(Wektor wektor1, Wektor wektor2) {
        super();
        this.wektor1 = wektor1;
        this.wektor2 = wektor2;
    }

    public WektoryRoznejDlugosciException(String message, Wektor wektor1, Wektor wektor2) {
        super(message);
        this.wektor1 = wektor1;
        this.wektor2 = wektor2;
    }

    public WektoryRoznejDlugosciException(String message, Throwable cause, Wektor wektor1, Wektor wektor2) {
        super(message, cause);
        this.wektor1 = wektor1;
        this.wektor2 = wektor2;
    }

    public WektoryRoznejDlugosciException(Throwable cause, Wektor wektor1, Wektor wektor2) {
        super(cause);
        this.wektor1 = wektor1;
        this.wektor2 = wektor2;
    }

    public Wektor getWektor1() {
        return wektor1;
    }

    public Wektor getWektor2() {
        return wektor2;
    }

    public Integer getWektor1Size(){
        return wektor1.getWektor().size();
    }

    public Integer getWektor2Size(){
        return wektor2.getWektor().size();
    }
}
