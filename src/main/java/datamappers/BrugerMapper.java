package datamappers;

import model.Bruger;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrugerMapper {


    public  static List<Bruger> readBrugers() {

        List<Bruger> brugerList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Bruger;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                Bruger bruger = new Bruger
                        (resultSet.getInt("bruger_id"),
                                resultSet.getInt("brugertype_id"),
                                resultSet.getString("email"),
                                resultSet.getString("kodeord"),
                                resultSet.getInt("saldo"));
                brugerList.add(bruger);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brugerList;
    }

    public static void createBruger(Bruger bruger) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Connection con = Connector.connection();


            sql = "INSERT INTO `Cupcake`.`Bruger` (`brugertype_id`, `email`, `kodeord`, `saldo`) VALUES (?, ?, ?, ?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, bruger.getBrugerType());
            ps.setString(2, bruger.getEmail());
            ps.setString(3, bruger.getKodeord());
            ps.setInt(4, bruger.getSaldo());

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void udskiftSaldo(Bruger bruger) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Connection con = Connector.connection();


            sql = "UPDATE Cupcake.Bruger SET saldo = ? WHERE (bruger_id = ?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, bruger.getSaldo());
            ps.setInt(2, bruger.getBrugerID());


            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
