package datamappers;

import model.BundCake;
import model.TopCake;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public  static List<BundCake> readBunds() {

        List<BundCake> bundList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Bund;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                BundCake bundCake = new BundCake
                        (resultSet.getInt("bund_id"),
                                resultSet.getString("navn_bund"),
                                resultSet.getInt("pris_bund"));
                bundList.add(bundCake);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bundList;

    }

    public  static HashMap<Integer,TopCake> readTopsHash() {

        HashMap<Integer,TopCake> topTabel = new HashMap<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Top;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                int topID =resultSet.getInt("top_id");
                String navnTop = resultSet.getString("navn_top");
                int prisTop = resultSet.getInt("pris_top");
                TopCake tempTopCake = new TopCake(topID, navnTop, prisTop);
                topTabel.put(topID,tempTopCake);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topTabel;
    }

    public  static HashMap<Integer,BundCake> readBundsHash() {

        HashMap<Integer,BundCake> bundTabel = new HashMap<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Bund;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                int bundID =resultSet.getInt("bund_id");
                String navnBund = resultSet.getString("navn_bund");
                int prisBund = resultSet.getInt("pris_bund");
                BundCake tempBundCake = new BundCake(bundID, navnBund, prisBund);
                bundTabel.put(bundID,tempBundCake);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bundTabel;
    }


}
