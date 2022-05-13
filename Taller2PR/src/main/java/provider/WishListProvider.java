package provider;
import db.DbConnection;

import entity.Product;
import entity.WishList;
import model.Order;
import model.OrderInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class WishListProvider {

    public void addToWishList(WishList wishList) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="INSERT INTO ordersA00369008 (userID) VALUES ('$USERID')";
        sql= sql.replace("$CREATIONDATE", Long.toString(new Date().getTime()));

        int id = wishList.getId();

            sql = sql.replace("$USERID", Integer.toString(id));
            sql = sql.replace("$PAID", "-1");
            sql = sql.replace("$PAYDATE", "0");
            connection.runQuery(sql);
            connection.close();

    }


    public void addProductToOrder(Order order) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="INSERT INTO order_productsA00369008(amount, orderID, productID) VALUES ('$ORDERID','$PRODUCTID','AMOUNT')";
        sql= sql.replace("$ORDERID", Integer.toString(order.getOrderId()));
        sql = sql.replace("$AMOUNT", Integer.toString(order.getAmount()));
        int id = Integer.parseInt(order.getProductName());


        sql = sql.replace("$PRODUCTID", Integer.toString(id));
        connection.runQuery(sql);
        connection.close();
    }

    public void deleteProduct(String[] parts) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String productName = parts[1];
        ProductProvider provider = new ProductProvider();

        String sql="DELETE FROM order_productsA00369008 WHERE orderId = $ORDERID AND productId = $PRODUCTID";
        sql= sql.replace("$ORDERID", parts[0]);
        int id = provider.findProductName(productName);

        if(id==0){
            conn.close();
        }else{
            sql = sql.replace("$PRODUCTID", Integer.toString(id));
            conn.runQuery(sql);
            conn.close();
        }

    }

    public void removeProduct(Order order) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        ProductProvider provider = new ProductProvider();

        String sql="UPDATE order_productsA00369008 SET amount = $AMOUNT WHERE orderId = $ORDERID AND productID = $PRODUCTID";
        sql= sql.replace("$ORDERID",  Integer.toString(order.getOrderId())); //Hacer cambio a time unix
        sql = sql.replace("$AMOUNT",    Integer.toString(order.getAmount()));
        int id = provider.findProductName(order.getProductName());

        if(id==0){
            connection.close();

        }else {

            sql = sql.replace("$PRODUCTID", Integer.toString(id));
            System.out.println(sql);
            connection.runQuery(sql);
            connection.close();
        }
    }

    public WishList update(String info) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="UPDATE ordersA00369008 SET paid = 1 WHERE id = $ID";
        long time = System.currentTimeMillis();

        sql = sql.replace("$ID", info);

        connection.runQuery(sql);

        sql = "UPDATE ordersA00369008 SET payDate = '$PAY' WHERE id = $ID";

        sql = sql.replace("$ID", info);
        sql = sql.replace("$PAY", Long.toString(time));
        connection.runQuery(sql);

        sql = "SELECT ordersA00369008.id,ordersA00369008.creationDate, ordersA00369008.paid, ordersA00369008.payDate, ordersA00369008.userID FROM ordersA00369008 WHERE id = '$ID' AND payDate = $PAYDATE ";
        sql = sql.replace("$ID", info);
        sql = sql.replace("$PAYDATE", Long.toString(time));

        ArrayList<WishList> wishLists = new ArrayList<>();

        ResultSet results =  connection.getData(sql);

        while(results.next()){

            int id = results.getInt("id");
            long creationDate = results.getLong("creationDate");
            int payed = results.getInt("payed");
            long payDate = results.getLong("payDate");
            int userId = results.getInt("userID");
            WishList wl = new WishList(id,creationDate,payed,payDate,userId);
            wishLists.add(wl);
        }

        connection.close();
        return wishLists.get(0);

    }

    public OrderInformation getInfo(String info) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql = "SELECT productsA00369008.id, productsA00369008.name, productsA00369008.price, order_productsA00369008.amount FROM (productsA00369008 INNER JOIN order_productsA00369008 " +
                "ON productsA00369008.id = order_productsA00369008.productID)INNER JOIN ordersA00369008 ON order_productsA00369008.orderID = ordersA00369008.id WHERE ordersA00369008.id = $ORDERID";
        sql = sql.replace("$ORDERID", info);
        ArrayList<Product> products = new ArrayList<>();

        ResultSet results =  connection.getData(sql);

        while(results.next()){

            int id = results.getInt("id");
            String name = results.getString("name");
            int price = results.getInt("price");
            Product product = new Product(id,name,price);
            products.add(product);
        }

        String data = "";
        int productAmount = 0;
        int totalPrice = 0;
        for(int i=0; i < products.size();i++){

            data +=  "\n " + (i+1) + "Name: " + products.get(i).getName() + ", Price: " + products.get(i).getPrice() + ", Quantity: " + products.get(i).getAmount();
            productAmount+= products.get(i).getAmount();
            totalPrice+= (products.get(i).getAmount()*products.get(i).getPrice());
        }
        OrderInformation oi = new OrderInformation(data,productAmount,totalPrice);


        connection.close();
        return oi;




    }

}