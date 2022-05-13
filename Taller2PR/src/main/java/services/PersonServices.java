package services;

import entity.Person;
import entity.WishList;
import model.Json;
import provider.PersonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("person")
public class PersonServices {

    @POST
    @Path("addPerson")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addPerson(Person person){

        PersonProvider pp = new PersonProvider();
        try {
            pp.addPerson(person);
            return Response.status(200).entity(person).build();
        } catch (SQLException e) {

            Json m = new Json("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Json m = new Json("Error", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

}