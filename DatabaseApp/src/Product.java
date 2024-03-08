public class Product {
    private int id;
    private String nazwa;
    private double cena;
    private int ilosc;
    private int idKategorii;


    public Product(int id, String nazwa, double cena, int ilosc, int idKategorii) {
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
        this.idKategorii = idKategorii;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public int getIdKategorii() {
        return idKategorii;
    }
}
