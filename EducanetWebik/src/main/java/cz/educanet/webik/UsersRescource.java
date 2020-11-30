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
        if(userCheck(user)) {

            return Response.ok("This username already exists").build();
        }
        names.add(user);

        return Response.ok("This username is ok").build();
    }

    //TODO: Create DELETE,PUT

    @DELETE
    public Response removeUser(@QueryParam("username") String username) {
        if(username != null) {
            names.clear();
            //names.remove(user + username);
            //names.remove(user);

            return Response.ok().build();
        }
        return Response.serverError().build();

    }

    @PUT
    @Path("/{username}")
    public Object changeUser(User user, @PathParam("username") String username, @QueryParam("newUsername") String newUsername) {
        User reuser = new User(username);
            for (int i = 0; i < names.size(); i++) {
                if(names.get(i).getUsername().equals(reuser.getUsername())) {
                    names.get(i).renameUser(newUsername);

                    return Response.ok("Username changed successfully").build();

                }
            }
            return Response.ok("Username already exists").build();
        }


    public Boolean userCheck(User user) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getUsername().equals(user.getUsername())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

