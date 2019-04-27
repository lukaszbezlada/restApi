import com.infoshareacademy.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Test {

    @org.junit.Test
    public void addUserTest(){

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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:81/rest/user?id=1");

        User user = new User();
        Response response = target.request().delete();
    }

// method to refactor
//    private WebTarget getWebTarget (String uri) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(uri);
//        return client.target(uri);
//    }
}


