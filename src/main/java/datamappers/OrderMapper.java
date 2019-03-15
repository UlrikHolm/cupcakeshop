package datamappers;

import model.Order;
import model.OrderLinje;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static List<OrderLinje> loadOrderLinje() {
        List<OrderLinje> tempOrderLinjer = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql;
        try {
            Connection con = Connector.connection();
            sql = "SELECT * FROM Cupcake.OrdreLinjer;";
            ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                int ordrelinjeId = resultSet.getInt("ordrelinje_id");
                int ordreId = resultSet.getInt("ordre_id");
                int topId = resultSet.getInt("top_id");
                int bundId = resultSet.getInt("bund_id");
                int antal = resultSet.getInt("antal");
                int prisIalt = resultSet.getInt("pris_ialt");

                OrderLinje orderLinje = new OrderLinje(ordrelinjeId, ordreId, topId, bundId, antal, prisIalt);
                tempOrderLinjer.add(orderLinje);
            }

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tempOrderLinjer;
    }


    public  static List<Order> loadOrder() {

        List<Order> orderList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM Cupcake.Ordre;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                Order order = new Order
                        (resultSet.getInt("ordre_id"),
                                resultSet.getString("tidspunkt"),
                                resultSet.getInt("bruger_id"),
                                resultSet.getInt("total_sum"));
                orderList.add(order);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;

    }

    public static void createOrderLinje(OrderLinje orderLinje) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Connection con = Connector.connection();
            sql = "INSERT INTO `Cupcake`.`OrdreLinjer` (`ordre_id`, `top_id`, `bund_id`, `antal`, `pris_ialt`) VALUES (?, ?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery(sql);

            ps.setInt(1, orderLinje.getOrdreID());
            ps.setInt(2, orderLinje.getTopID());
            ps.setInt(3, orderLinje.getBundID());
            ps.setInt(4, orderLinje.getAntal());
            ps.setInt(5, orderLinje.getPrisIalt());

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Connection con = Connector.connection();
            sql = "INSERT INTO `Cupcake`.`Ordre` (`tidspunkt`, `bruger_id`, `total_sum`) VALUES (?, ?, ?)";

            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery(sql);

            //PSinsertDate.setObject(1, LocalDate.parse("2017-01-22"));

            ps.setString(1, order.getTimeNow());
            ps.setInt(2, order.getBrugerID());
            ps.setInt(3, order.getTotalSum());

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Order lastOrder() {

        Order lastOrder = new Order();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql;
        try {
            Connection con = Connector.connection();
            //sql = "SELECT * FROM Cupcake.Ordre ORDER BY ordre_id DESC LIMIT 1;";
            //sql = "select * from Cupcake.Ordre where ordre_id=(select max(ordre_id) from Cupcake.Ordre)";
            sql = "SELECT * FROM Cupcake.Ordre WHERE ordre_id = (SELECT MAX(ordre_id) FROM Cupcake.Ordre); ";
            ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                int ordreId = resultSet.getInt("ordre_id");
                String timeNow = resultSet.getString("tidspunkt");
                int brugerId = resultSet.getInt("bruger_id");
                int totalSum = resultSet.getInt("total_sum");

                lastOrder = new Order(ordreId,timeNow,brugerId,totalSum);
            }

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastOrder;
    }

    public static void createTempKurv(OrderLinje orderLinje) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            Connection con = Connector.connection();
            //sql = "INSERT INTO `Cupcake`.`OrdreLinjer` (`ordre_id`, `top_id`, `bund_id`, `antal`, `pris_ialt`) VALUES (?, ?, ?, ?, ?);";
            sql = "INSERT INTO `Cupcake`.`TempKurv` (`top_id`, `bund_id`, `antal`, `pris_ialt`) VALUES (?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery(sql);


            ps.setInt(1, orderLinje.getTopID());
            ps.setInt(2, orderLinje.getBundID());
            ps.setInt(3, orderLinje.getAntal());
            ps.setInt(4, orderLinje.getPrisIalt());

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<OrderLinje> loadTempKurv() {
        List<OrderLinje> tempOrderList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql;
        try {
            Connection con = Connector.connection();
            sql = "SELECT * FROM Cupcake.TempKurv;";
            ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                int ordrelinjeID = resultSet.getInt("ordrelinje_id");
                int topID = resultSet.getInt("top_id");
                int bundID = resultSet.getInt("bund_id");
                int antal = resultSet.getInt("antal");
                int prisIalt = resultSet.getInt("pris_ialt");
                OrderLinje tempOrderLinje = new OrderLinje();
                tempOrderLinje.setOrdrelinjeID(ordrelinjeID);
                tempOrderLinje.setTopID(topID);
                tempOrderLinje.setBundID(bundID);
                tempOrderLinje.setAntal(antal);
                tempOrderLinje.setPrisIalt(prisIalt);
                tempOrderList.add(tempOrderLinje);
            }

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempOrderList;
    }


    public static void deleteTempKurv() {

        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        try {
            Connection con = Connector.connection();
            sql = "TRUNCATE TABLE Cupcake.TempKurv;";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteLinjeKurv(int ordrelinjeID) {

        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        try {
            Connection con = Connector.connection();
            sql = "DELETE FROM Cupcake.TempKurv WHERE ordrelinje_id=?;";
            ps = con.prepareStatement(sql);

            ps.setInt(1, ordrelinjeID);
            ps.executeUpdate();

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
