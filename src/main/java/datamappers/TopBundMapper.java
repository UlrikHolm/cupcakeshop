package datamappers;

import model.TopCake;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopBundMapper {


    public  static List<TopCake> readTops() {

        List<TopCake> topList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Top;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                TopCake topCake = new TopCake
                        (resultSet.getInt("top_id"),
                                resultSet.getString("navn_top"),
                                resultSet.getInt("pris_top"));
                topList.add(topCake);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topList;

    }
}
