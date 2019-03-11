package datamappers;

import model.Ordre;
import util.Connector;

import javax.persistence.criteria.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdrerMapper {


    public  static List<Ordre> readOrdre() {

        List<Ordre> brugerList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Ordre;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                Ordre ordre = new Ordre
                        (resultSet.getInt("ordre_id"),
                                resultSet.getInt("dato"),
                                resultSet.getInt("bruger_id"),
                                resultSet.getInt("total_sum"));
                brugerList.add(ordre);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brugerList;

    }

}
