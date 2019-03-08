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

}
