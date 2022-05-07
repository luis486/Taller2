package provider;

import db.DbConnection;
import entity.Product;
import entity.Person;
import entity.WishList;
import model.Order;
import model.OrderInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;


public class WishListProvider {

    public void addWL(WishList wl) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="INSERT INTO ordersA00369008(creationDate, userID,payed,payDate) VALUES ($CREATIONDATE,'$USERID',$PAYED,$PAYDATE)";
        sql= sql.replace("$CREATIONDATE", Long.toString(new Date().getTime()));

        PersonProvider provider = new PersonProvider();
        int id = provider.findUser(wl.getUserID());

        if(id==0){
            connection.close();
            return;
        }else {
            sql = sql.replace("$USERID", Integer.toString(id));
            sql = sql.replace("$PAYED", "-1");
            sql = sql.replace("$PAYDATE", "0");
            connection.runQuery(sql);
            connection.close();
        }
    }

}