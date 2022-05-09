package services;

import entity.Product;
import model.Json;
import provider.ProductProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("product")
public class ProductServices {

    @POST
    @Path("addProduct")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProduct(Product product){
        ProductProvider provider = new ProductProvider();
        try {
            provider.addProduct(product);
            return Response.status(200).entity(product).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }
}
