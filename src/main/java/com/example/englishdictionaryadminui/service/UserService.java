package com.example.englishdictionaryadminui.service;

import com.example.englishdictionaryadminui.models.User;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final String Port = "4040";
    /*private final String Host= "http://localhost:";*/
    private final String Host= "http://10.1.3.91:";
    public ResponseEntity<Map<String,String >> getAllGender()
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/user/gender");
            Map<String,String> gender = webClient.get()
                    .retrieve()
                    .toEntity(Map.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(gender);
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Map<String,String >> getAllLevel()
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/user/level");
            Map<String,String> level = webClient.get()
                    .retrieve()
                    .toEntity(Map.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(level);
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Map<String,String >> getAllOccupation()
    {
        try{
            WebClient webClient = WebClient.create(Host+Port+"/api/user/occupation");
            Map<String,String> occupation = webClient.get()
                    .retrieve()
                    .toEntity(Map.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(occupation);
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<List<User>> getAllUser()
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/get/all/user");
            List<User> users = webClient.get()
                    .retrieve()
                    .toEntityList(User.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(users);
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    public ResponseEntity<User> getUserById(String id)
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/get/user/"+id);
            User user = webClient.get()
                    .retrieve()
                    .toEntity(User.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(user);
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<String> editGenderById(String id, String name)
    {
        try {
            String url = Host+Port+"/api/admin/edit/gender";
            Map<String,String> gender = Map.of("docId",id,"name",name);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json");
            HttpEntity<Map<String,String>> request = new HttpEntity<>(gender,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
            return response;
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    public ResponseEntity<String> editLevelById(String id, String name)
    {
        try {
            String url =Host+Port+"/api/admin/edit/level";
            Map<String,String> level = Map.of("docId",id,"name",name);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json");
            HttpEntity<Map<String,String>> request = new HttpEntity<>(level,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
            return response;
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<String> editOccupationById(String id, String name)
    {
        try {
            String url = Host+Port+"/api/admin/edit/occupation";
            Map<String,String> occupation = Map.of("docId",id,"name",name);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json");
            HttpEntity<Map<String,String>> request = new HttpEntity<>(occupation,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
            return response;
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<String> deleteGenderById(String id)
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/delete/gender/"+id);
            String response = webClient.delete()
                    .retrieve()
                    .toEntity(String.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(response);
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<String> deleteLevelById(String id)
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/delete/level/"+id);
            String response = webClient.delete()
                    .retrieve()
                    .toEntity(String.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(response);
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<String> deleteOccupationById(String id)
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/delete/occupation/"+id);
            String response = webClient.delete()
                    .retrieve()
                    .toEntity(String.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(response);
        }
        catch (WebServerException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<String> disableUserById(String id)
    {
        try {
            WebClient webClient = WebClient.create(Host+Port+"/api/admin/disable/user/"+id);
            String response = webClient.delete()
                    .retrieve()
                    .toEntity(String.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(response);
        }
        catch (WebServerException e)
        {
            return ResponseEntity.badRequest().build();
        }
        catch (WebClientException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
