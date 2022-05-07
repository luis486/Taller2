package provider;

import db.DbConnection;
import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductProvider {

    public void addProduct(Product product) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();

        String sql="INSERT INTO productsA00369267 (name,price) VALUES ('$NAME','$PRICE')";
        sql= sql.replace("$NAME",product.getName());
        //sql= sql.replace("$QUANTITY",Integer.toString(product.getQuantity()));
        sql = sql.replace("$PRICE", Double.toString(product.getPrice()));
        connection.runQuery(sql);
        connection.close();
    }

    public int findProductId(String name) throws SQLException, ClassNotFoundException {

        DbConnection connection = new DbConnection();
        String sql="SELECT id FROM productsA00369267 WHERE name = '$NAME'";
        sql = sql.replace("$NAME", name);
        ResultSet results =  connection.getData(sql);
        int foundId = 0;
        while(results.next()) {
            foundId = results.getInt("id");
        }
        connection.close();
        return foundId;
    }

}
