package services;

import entity.*;
import model.*;
import provider.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("order")
public class WishListServices {

    @POST
    @Path("addToWishList")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addWL(WishList wl){

        WishListProvider wlp = new WishListProvider();
        try {
            wlp.addToWishList(wl);
            return Response.status(200).entity(wl).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }

    @PUT
    @Path("addProductToOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProductToOrder(Order order){

        WishListProvider wlp = new WishListProvider();
        try {
            wlp.addProductToOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }
    @DELETE
    @Path("deleteProduct/{info}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteProductFromOrder(@PathParam("info") String info){

        String[] parts = info.split("-");
        WishListProvider wlp = new WishListProvider();
        try {
            wlp.deleteProduct(parts);
            return Response.status(200).entity(parts).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }

    @PUT
    @Path("removeProduct")
    @Produces("application/json")
    @Consumes("application/json")
    public Response removeProduct(Order order){

        WishListProvider wlp = new WishListProvider();
        try {
            wlp.removeProduct(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }

    @PUT
    @Path("update/{info}")
    @Produces("application/json")
    public Response update(@PathParam("info") String info){

        WishListProvider wlp = new WishListProvider();
        try {
            WishList wishList = wlp.update(info);
            return Response.status(200).entity(wishList).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }


    @POST
    @Path("getInfo/{info}")
    @Produces("application/json")
    public Response getInfo(@PathParam("info") String info){

        WishListProvider wlp = new WishListProvider();
        try {
            OrderInformation data = wlp.getInfo(info);
            return Response.status(200).entity(data).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }
}