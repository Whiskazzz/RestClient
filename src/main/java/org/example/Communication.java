package org.example;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {
    private static final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    private RestTemplate restTemplate;

    public String showAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        System.out.println(responseEntity.getHeaders().get("Set-Cookie").get(0).split(";")[0].split("=")[1]);
        return responseEntity.getHeaders().get("Set-Cookie").get(0).split(";")[0].split("=")[1];

    }

    public String saveUser(String xren) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("JSESSIONID", xren);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        User user = new User(3l, "James", "Brown", (byte) 12);
        HttpEntity <User> entity = new HttpEntity<User>(user, headers);
        String temp = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
        System.out.println(temp);

        return temp;
    }

    public String changeUser(String xren) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("JSESSIONID", xren);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        User user = new User(3l, "Thomas", "Shelby", (byte) 12);
        HttpEntity <User> entity = new HttpEntity<User>(user, headers);
        String temp = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
        System.out.println(temp);

        return temp;
    }

    public void deleteUser(long id) {
        restTemplate.delete(URL+"/"+id);
    }

}
