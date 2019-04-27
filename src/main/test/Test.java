import com.infoshareacademy.User;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class Test {

    @org.junit.Test
    public void addUserTest() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:81/rest/addUser");

        User user = new User();
        user.setName("ISA 123");
        user.setSurname("ISA 456");

        Response response = target.request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));


        response.getEntity();
    }

    @org.junit.Test
    public void editUserTest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:81/rest/user");

        User user = new User();
        user.setId(3);
        user.setName("ISA 123 - update");
        user.setSurname("ISA 456 - update");
        Response response = target.request().put(Entity.entity(user, MediaType.APPLICATION_JSON));
    }

    @org.junit.Test
    public void deleteUserTest() {

        WebTarget target = getWebTarget("http://localhost:81/rest/user?id=2");

        Response response = target.request().delete();
    }


    private WebTarget getWebTarget(String uri) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);
        return client.target(uri);
    }

    @org.junit.Test
    public void testGetUsersByProxy() {

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath("http://localhost:81/rest/"));

        UserService proxy = target.proxy(UserService.class);

        proxy.getUsers().forEach(
                user -> System.out.println(user.getName())
        );
    }

    @org.junit.Test
    public void testAddUsersByProxy() {

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath("http://localhost:81/rest/"));

        UserService proxy = target.proxy(UserService.class);

        proxy.addUser(new User());
    }

    @org.junit.Test
    public void deleteUsersByProxy(){

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath("http://localhost:81/rest/"));

        UserService proxy = target.proxy(UserService.class);

        proxy.deleteUser(4);

    }
}



