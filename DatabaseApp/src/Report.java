import java.sql.*;

public class Report {
    private int raportID;
    private Date dataWykonania;
    private String raportNazwa;
    private int autorID;

    public Report(int raportID, Date dataWykonania, String raportNazwa, int autorID) {
        this.raportID = raportID;
        this.dataWykonania = dataWykonania;
        this.raportNazwa = raportNazwa;
        this.autorID = autorID;
    }

    public int getRaportID() {
        return raportID;
    }

    public Date getDataWykonania() {
        return dataWykonania;
    }

    public String getRaportNazwa() {
        return raportNazwa;
    }

    public int getAutorID() {
        return autorID;
    }
}
