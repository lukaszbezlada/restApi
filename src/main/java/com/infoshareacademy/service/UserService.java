package com.infoshareacademy.service;

import com.infoshareacademy.Credentials;
import com.infoshareacademy.User;
import com.infoshareacademy.UserStore;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("/")
public class UserService {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserStore userStore;

    public UserService() {
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.ok("hello!").build();
    }

    @GET
    @Path("/hello/{nn}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@PathParam("nn") String name) {

        return Response.ok("Saying hello to " + name + "!").build();
    }

    @GET
    @Path("/agent")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserAgent(@HeaderParam("user-agent") String userAgent) {

        return Response.ok(userAgent).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        Collection<User> users = userStore.getBase().values();
        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users).build();
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("id") Integer id) {
        Optional<User> user = userStore.findById(id);
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        }

        //return Response.status(404).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response getLoginForm() {
        String html = "<form action=\"authenticate\" method=\"POST\">\n"
                + "      <input type=\"text\" name=\"username\"/><br/>\n"
                + "      <input type=\"password\" name=\"password\"/><br/>\n"
                + "      <input type=\"Submit\"/>\n"
                + "    </form>";

        return Response.ok(html).build();
    }

    @POST
    @Path("/authenticate")
    public Response authenticate(@FormParam("username") String username,
                                 @FormParam("password") String password) {

        if (userStore.authenticate(username, password)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(Credentials credentials) {

        return authenticate(credentials.getUser(), credentials.getPassword());
    }

    @POST
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        int newId = userStore.getNewId();

        userStore.add(new User(user.getName(),
                user.getSurname(),
                newId,
                user.getCredentials()));

        return getUsers();
    }

    @PUT
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {

        if (userStore.getBase().containsKey(user.getId())) {
            userStore.getBase().put(user.getId(), user);
            return Response.ok(user).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/deleteuser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("id") Integer id) {

        if (userStore.getBase().containsKey(id)) {
            userStore.getBase().remove(id);
            return getUsers();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
