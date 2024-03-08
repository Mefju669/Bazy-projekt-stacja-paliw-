import oracle.jdbc.internal.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseApi {
    public static final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static Connection connection;


    public static boolean LoginIntoDatabase(String login, String password)
    {
        try
        {
            connection = DriverManager.getConnection(url, login, password);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> res = new ArrayList<>();

        try {
            String query = "SELECT ID, Nazwa FROM baza_stacji.KategoriaProduktow ORDER BY ID ASC"; //
            try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)) {


                while (rs.next()) {
                    int kategoriaID = rs.getInt("ID");
                    String nazwa = rs.getString("Nazwa");

                    res.add(new Category(kategoriaID, nazwa));
                    //System.out.println("ID: " + kategoriaID + ", Nazwa: " + nazwa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    public static void addCategory(String nazwa) {
        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.DodajNowaKategorieProduktu(?)}")) {//
                cs.setString(1, nazwa);
                cs.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Category> findCategory(String nazwa) {
        ArrayList<Category> res = new ArrayList<>();

        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.WyszukajKategorieProduktuPoNazwie(?, ?)}")) {//
                cs.setString(1, nazwa);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();


                try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                    while (rs.next()) {
                        int kategoriaID = rs.getInt("ID");
                        String kategoriaNazwa = rs.getString("Nazwa");

                        res.add(new Category(kategoriaID, kategoriaNazwa));
                        //System.out.println("ID: " + kategoriaID + ", Nazwa: " + kategoriaNazwa);
                        // Dodaj wyświetlanie lub przetwarzanie innych danych z pozostałych kolumn
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    public static void deleteCategory(int ID) {
        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.UsunKategorieProduktu(?)}")) {//
                cs.setInt(1, ID);
                cs.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static ArrayList<Worker> getAllWorkers() {
        ArrayList<Worker> res = new ArrayList<>();
        String sql = "SELECT ID, Imie, Nazwisko, Email, Stanowisko, Wynagrodzenie FROM baza_stacji.Pracownik ORDER BY ID ASC";//
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Lista pracowników:");
            System.out.println("ID | Imię | Nazwisko | Email | Stanowisko | Wynagrodzenie");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String imie = resultSet.getString("Imie");
                String nazwisko = resultSet.getString("Nazwisko");
                String email = resultSet.getString("Email");
                String stanowisko = resultSet.getString("Stanowisko");
                double wynagrodzenie = resultSet.getDouble("Wynagrodzenie");

                res.add(new Worker(id, imie, nazwisko, email, stanowisko, wynagrodzenie));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void addWorker(String imie, String nazwisko, String email, String stanowisko, String wynagrodzenie) {
        try {
            // Wywołanie procedury DodajNowegoPracownika
            String query = "{CALL baza_stacji.DodajNowegoPracownika(?, ?, ?, ?, ?)}";//
            try (CallableStatement statement = connection.prepareCall(query)) {
                statement.setString(1, imie);
                statement.setString(2, nazwisko);
                statement.setString(3, email);
                statement.setString(4, stanowisko);
                statement.setString(5, wynagrodzenie);

                // Wykonanie procedury
                statement.execute();
                System.out.println("Dodano nowego pracownika.");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd przy dodawaniu pracownika.");
            e.printStackTrace();
            System.out.println(" Coś poszło nie tak, prawodpodobnie twoje stanowisko nie posiada uprawnień do tej operacji.");
        }
    }

    public static List<Worker> findWorker(String nazwisko) {
        ArrayList<Worker> res = new ArrayList<>();

        try {
            // Wywołaj procedurę
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.WyszukajPracownikaPoNazwisku(?, ?)}")) {//
                cs.setString(1, nazwisko);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();

                // Pobierz wyniki
                try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                    if (!rs.isBeforeFirst()) {
                        System.out.println("Brak wyników dla nazwiska: " + nazwisko);
                    } else {
                        while (rs.next()) {
                            // Przetwarzaj wyniki
                            int id = rs.getInt("ID");
                            String imie = rs.getString("Imie");
                            String nazwiskoResult = rs.getString("Nazwisko");
                            String email = rs.getString("Email");
                            String stanowisko = rs.getString("Stanowisko");
                            double wynagrodzenie = rs.getDouble("Wynagrodzenie");
                            res.add(new Worker(id, imie, nazwisko, email, stanowisko, wynagrodzenie));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Coś poszło nie tak, prawodpodobnie twoje stanowisko nie posiada uprawnień do tej operacji.");
        }

        return res;
    }

    public static void editWorker(int ID, String imie, String nazwisko, String email, String stanowisko, double wynagrodzenie) {
        try {
            // Wywołaj procedurę
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.AktualizujInformacjeOPracowniku(?, ?, ?, ?, ?, ?)}")) {//
                cs.setInt(1, ID);
                cs.setString(2, imie);
                cs.setString(3, nazwisko);
                cs.setString(4, email);
                cs.setString(5, stanowisko);
                cs.setDouble(6, wynagrodzenie);
                cs.execute();
            }

            System.out.println("Zaktualizowano informacje o pracowniku o ID: " + ID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Coś poszło nie tak, prawodpodobnie twoje stanowisko nie posiada uprawnień do tej operacji. Albo pracownik jest autorem raportu, więc usuń najpierw jego raporty.");
        }
    }

    public static void deleteWorker(int ID) {
        try {
            // Wywołaj procedurę
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.UsunPracownika(?)}")) {//
                cs.setInt(1, ID);
                cs.execute();
            }

            System.out.println("Usunięto pracownika o ID: " + ID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Coś poszło nie tak, prawodpodobnie twoje stanowisko nie posiada uprawnień do tej operacji.");
        }
    }





    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> res = new ArrayList<>();
        String sql = "SELECT ID, Nazwa, Cena, Ilosc, ID_Kategorii FROM baza_stacji.Produkt ORDER BY ID ASC";//
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Lista produktów:");
            System.out.println("ID | Nazwa | Cena | Ilość | ID Kategorii");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nazwa = resultSet.getString("Nazwa");
                double cena = resultSet.getDouble("Cena");
                int ilosc = resultSet.getInt("Ilosc");
                int idKategorii = resultSet.getInt("ID_Kategorii");

                res.add(new Product(id, nazwa, cena, ilosc, idKategorii));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static void addProduct(String nazwa, String cena, int ilosc, int idKategorii) {
        // Wywołanie procedury
        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.DodajNowyProdukt(?, ?, ?, ?)}")) {//
            // Parametry wejściowe
            callableStatement.setString(1, nazwa);
            callableStatement.setString(2, cena);
            callableStatement.setInt(3, ilosc);
            callableStatement.setInt(4, idKategorii);

            // Wykonanie procedury
            callableStatement.execute();

            System.out.println("Nowy produkt dodany: " + nazwa);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void editProduct(int id, String nazwa, double cena, int ilosc, int idKategorii) {
        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.AktualizujProdukt(?, ?, ?, ?, ?)}")) {//
            callableStatement.setInt(1, id);
            callableStatement.setString(2, nazwa);
            callableStatement.setDouble(3, cena);
            callableStatement.setInt(4, ilosc);
            callableStatement.setInt(5, idKategorii);

            callableStatement.execute();
            System.out.println("Produkt został zaktualizowany.");
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd przy aktualizacji produktu.");
            e.printStackTrace();
        }
    }




    public static void deleteProduct(int id) {
        // Wywołanie procedury
        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.UsunProdukt(?)}")) {//
            // Parametr wejściowy
            callableStatement.setInt(1, id);

            // Wykonanie procedury
            callableStatement.execute();

            System.out.println("Produkt o ID " + id + " został usunięty.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Product> findProductByName(String nazwa) {
        ArrayList<Product> res = new ArrayList<>();

        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.WyszukajProduktPoNazwie(?, ?)}")) {//
            callableStatement.setString(1, nazwa);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(2)) {
                System.out.println("Znalezione produkty:");
                System.out.println("ID | Nazwa | Cena | Ilość | ID Kategorii");

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nazwaProduktu = resultSet.getString("Nazwa");
                    double cena = resultSet.getDouble("Cena");
                    int ilosc = resultSet.getInt("Ilosc");
                    int idKategorii = resultSet.getInt("ID_Kategorii");

                    res.add(new Product(id, nazwa, cena, ilosc, idKategorii));

                }
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd przy wyszukiwaniu produktu.");
            e.printStackTrace();
        }
        return res;
    }


    public static List<Product> findProductByPrice(String minCena, String maxCena) {
        ArrayList<Product> res = new ArrayList<>();

        // Wywołanie procedury
        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.WyszukajProduktPoCenie(?, ?, ?)}")) {//
            // Parametry wejściowe
            callableStatement.setString(1, minCena);
            callableStatement.setString(2, maxCena);

            // Parametr wyjściowy (kursor)
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);

            // Wykonanie procedury
            callableStatement.execute();

            // Pobranie wyników z kursora
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(3)) {
                System.out.println("Znalezione produkty w przedziale cenowym od " + minCena + " do " + maxCena + ":");
                System.out.println("ID | Nazwa | Cena | Ilość | ID Kategorii");

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nazwaProduktu = resultSet.getString("Nazwa");
                    double cena = resultSet.getDouble("Cena");
                    int ilosc = resultSet.getInt("Ilosc");
                    int idKategorii = resultSet.getInt("ID_Kategorii");

                    res.add(new Product(id, nazwaProduktu, cena, ilosc, idKategorii));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static List<Product> findProductByAmount(int minIlosc, int maxIlosc) {
        ArrayList<Product> res = new ArrayList<>();

        // Wywołanie procedury
        try (CallableStatement callableStatement = connection.prepareCall("{call baza_stacji.WyszukajProduktPoIlosci(?, ?, ?)}")) {//
            // Parametry wejściowe
            callableStatement.setInt(1, minIlosc);
            callableStatement.setInt(2, maxIlosc);

            // Parametr wyjściowy (kursor)
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);

            // Wykonanie procedury
            callableStatement.execute();

            // Pobranie wyników z kursora
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(3)) {
                System.out.println("Znalezione produkty w przedziale ilościowym od " + minIlosc + " do " + maxIlosc + ":");
                System.out.println("ID | Nazwa | Cena | Ilość | ID Kategorii");

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nazwaProduktu = resultSet.getString("Nazwa");
                    double cena = resultSet.getDouble("Cena");
                    int ilosc = resultSet.getInt("Ilosc");
                    int idKategorii = resultSet.getInt("ID_Kategorii");

                    res.add(new Product(id, nazwaProduktu, cena, ilosc, idKategorii));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }




    public static ArrayList<Report> getAllReports() {
        ArrayList<Report> res = new ArrayList<>();
        try {
            String query = "SELECT ID, DataWykonania, Nazwa, ID_Autora FROM baza_stacji.Raporty ORDER BY ID ASC";//
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(query)) {

                while (rs.next()) {
                    int raportID = rs.getInt("ID");
                    Date dataWykonania = rs.getDate("DataWykonania");
                    String raportNazwa = rs.getString("Nazwa");
                    int autorID = rs.getInt("ID_Autora");

                    res.add(new Report(raportID, dataWykonania, raportNazwa, autorID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static ArrayList<Report> findReportByAutorId(int ID) {
        ArrayList<Report> res = new ArrayList<>();

        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.WyszukajRaportPoAutorze(?, ?)}")) {//
                cs.setInt(1, ID);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();

                try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                    while (rs.next()) {
                        int raportID = rs.getInt("ID");
                        Date dataWykonania = rs.getDate("DataWykonania");
                        String raportNazwa = rs.getString("Nazwa");
                        int autorID = rs.getInt("ID_Autora");
                        String tresc = rs.getString("Tresc");

                        res.add(new Report(raportID, dataWykonania, raportNazwa, autorID));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static ArrayList<Report> findReportByName(String nazwa) {
        ArrayList<Report> res = new ArrayList<>();

        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.WyszukajRaportPoNazwie(?, ?)}")) {//
                cs.setString(1, nazwa);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();

                try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                    while (rs.next()) {
                        int raportID = rs.getInt("ID");
                        Date dataWykonania = rs.getDate("DataWykonania");
                        String raportNazwa = rs.getString("Nazwa");
                        int autorID = rs.getInt("ID_Autora");
                        String tresc = rs.getString("Tresc");

                        res.add(new Report(raportID, dataWykonania, raportNazwa, autorID));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void addReport(String nazwa, int id_autora, String tresc) {
        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.DodajNowyRaport(?, ?, ?)}")) {//
                cs.setString(1, nazwa);
                cs.setInt(2, id_autora);
                cs.setString(3, tresc);
                cs.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteReport(int ID) {
        try {
            try (CallableStatement cs = connection.prepareCall("{call baza_stacji.UsunRaport(?)}")) {//
                cs.setInt(1, ID);
                cs.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
