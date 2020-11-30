package cz.educanet.webik;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON) //[POST] localhost:8080/nazev-appky/api/users
public class UsersRescource {
    private static List<User> names = new ArrayList();
    private String user = "";
    private String http = "http://127.0.0.1:8080/EducanetWebik/api/users";

    @GET //[GET] localhost:8080/nazev-appky/api/users
    public Response getAll() {

        return Response.ok(names).build();
    }

    @POST //Add user to array
    public Response createUser(@QueryParam("username") String username) {
        User user = new User(username);
        names.add(user);

        return Response.ok().build();
    }

    //TODO: Create DELETE,PUT

    @DELETE
    public Response removeUser(@QueryParam("username") String username) {
        if(username != null) {
            names.remove(user + username);

            return Response.ok().build();
        }
        return Response.serverError().build();

    }

    @PUT
    @Path("/{username}")
    public Response changeUser(@PathParam("username") String username, @QueryParam("username") String newUsername) {
        User user = new User(username);
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getUsername().equals(user.getUsername())) {
                names.get(i).setUsername(newUsername);

                return Response.ok().build();
            }
        }
        return Response.serverError().build();
    }
}

