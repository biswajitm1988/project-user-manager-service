package com.fsd.service.user.manager;

import com.fsd.service.user.manager.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagerServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/task/manager";
    }

    /**
     * Here we test that we can get all the Users in the database
     * using the GET method
     */
    @Test
    public void testGetAllUsers() {

        ResponseEntity<List> response = restTemplate.exchange(getRootUrl() + "/getAllUsers",
                HttpMethod.GET, new HttpEntity<String>(null, new HttpHeaders()), List.class);

        System.out.println(response.getBody().get(0));
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(!response.getBody().isEmpty());
    }

    /**
     * Here we test that we can fetch a single User using its id
     */
    @Test
    public void testGetUserById() {
        Long id =1l;
        User user = restTemplate.getForObject(getRootUrl() + "/getUserById/"+id, User.class);

        System.out.println(user.getEmployeeId());
        Assert.assertNotNull(user);
    }

    /**
     * Here we test that we can create a task using the POST method
     */
    @Test
    public void testCreateAndDeleteUser() {
        User user = new User();
        user.setFirstName("Biswajit");
        user.setLastName("Mohapatra");
        user.setEmployeeId("262595");

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/addUser", user, User.class);
        User newUser = postResponse.getBody();
        Assert.assertEquals(user.getEmployeeId(),newUser.getEmployeeId());

        //Delete newly Created User
        /*System.out.println("Calling Delete User");
        restTemplate.delete(getRootUrl() + "/deleteUser/"+newUser.getUserId());
        User tempUser = restTemplate.getForObject(getRootUrl() + "/getUserById/" + newUser.getUserId(), User.class);
        System.out.println("After Delete User"+ tempUser);
        Assert.assertNull(tempUser);*/
    }

    /**
     * Here we test that we can update a task's information using the PUT method
     */
    @Test
    public void testUpdateUser() {
        /*Long id = 1l;
        User task = restTemplate.getForObject(getRootUrl() + "/getUserById/" + id, User.class);
        User tempUser = new User();
        BeanUtils.copyProperties(task,tempUser);
        ParentUser pt = new ParentUser();
        pt.setParentUserSummary("Test Parent User PUT");
        task.setUserSummary("Test User PUT");
        task.setPriority(9);
        task.setStartDate(new Date());
        task.setParentUser(pt);

        restTemplate.put(getRootUrl() + "/updateUser", task);

        User updatedUser = restTemplate.getForObject(getRootUrl() + "/getUserById/" + id, User.class);
        System.out.println(updatedUser.getUserSummary());
        Assert.assertEquals(task.getUserSummary(), updatedUser.getUserSummary());

        //Put back Original User
        System.out.println("Put Back Original User");
        restTemplate.put(getRootUrl() + "/updateUser", tempUser);
        updatedUser = restTemplate.getForObject(getRootUrl() + "/getUserById/" + id, User.class);
        Assert.assertEquals(tempUser.getUserSummary(), updatedUser.getUserSummary());*/
    }
}
