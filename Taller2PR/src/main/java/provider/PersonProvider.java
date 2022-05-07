package provider;

import db.DbConnection;
import entity.WishList;
import entity.Product;
import entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonProvider {

    public void addPerson(Person person) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="INSERT INTO usersA00369008 (name, nationalId) VALUES ('$NAME','$USERID')";
        sql= sql.replace("$NAME",person.getName());
        sql= sql.replace("$USERID",person.getNatId());
        connection.runQuery(sql);
        connection.close();
    }

    public int findUser(int natId) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();
        String sql="SELECT id FROM usersA00369267 WHERE nationalId = '$NATIONALID'";
        sql = sql.replace("$NATIONALID", Integer.toString(natId));
        ResultSet results =  conn.getData(sql);
        int foundId = 0;

        while(results.next()) {
            foundId = results.getInt("id");
        }
        conn.close();
        return foundId;
    }

    public ArrayList<WishList> getRecords(String natId) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        int id = findUser(Integer.parseInt(natId));

        String sql = "SELECT ordersA00369008.id, ordersA00369008.creationDate, ordersA00369008.payed, ordersA00369008.payDate FROM ordersA00369008 WHERE ordersA00369008.userID = $ORDERID AND ordersA00369008.payed = 1" ;
        sql = sql.replace("$ORDERID",Integer.toString(id));
        ArrayList<WishList> orders = new ArrayList<>();

        ResultSet results =  connection.getData(sql);

        while(results.next()){
            int id2 = results.getInt("id");
            String creationDate  = results.getString("creationDate");
            int payed = results.getInt("payed");
            String payDate = results.getString("payDate");
            if(payDate==null){
                payDate = "0";
            }
            WishList wl = new WishList(id2,Long.valueOf(creationDate),payed,Long.valueOf(payDate),id);
            orders.add(wl);
        }
        return orders;
    }
}