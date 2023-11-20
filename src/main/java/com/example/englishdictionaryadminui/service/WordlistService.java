package com.example.englishdictionaryadminui.service;

import com.example.englishdictionaryadminui.models.Word;
import com.example.englishdictionaryadminui.models.Wordlist;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;
import java.util.Map;

@Service
public class WordlistService {
    String Port = "4040";
    private final String Host= "http://localhost:";
    public ResponseEntity<List<Wordlist>> getAllWordlist() {
        try {
            String url = Host + Port + "/api/wordlists/r4qtSQKgvrWtswB6o3Trs18st2j1";
            WebClient webClient = WebClient.create(url);
            List<Wordlist> wordlist = webClient.get()
                    .retrieve()
                    .toEntity(List.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(wordlist);
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
    public ResponseEntity<Wordlist> getWordlistById(String userId, String wordlistId)
    {
        String url = Host + Port + "/api/wordlists/"+ userId +"/"+ wordlistId;
        try{
            WebClient webClient = WebClient.create(url);
            Wordlist wordlist = webClient.get()
                    .uri(url,userId,wordlistId)
                    .retrieve()
                    .toEntity(Wordlist.class)
                    .block()
                    .getBody();
            return ResponseEntity.ok(wordlist);
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
    public ResponseEntity<String> deleteWordlist(String id) {
        String url = Host + Port + "/api/wordlists/{id}";
        try {
            WebClient webClient = WebClient.create(url);
            String response = webClient.delete()
                    .uri(url,id)
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
    public ResponseEntity<String> renameWordlist(String id, String name) {
        try {
            WebClient webClient = WebClient.create(Host + Port + "/api/wordlists/name/" + id + "/" + name);
            String response = webClient.patch().retrieve().toEntity(String.class).block().getBody();
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

    public ResponseEntity<String> removeWordFromWordlist(String wordlistId, String wordId) {
        String url = Host + Port + "/api/wordlists/{wordlistId}/word/{id}";
        try {
            WebClient webClient = WebClient.create(url);
            String response = webClient.delete()
                    .uri(url,wordlistId,wordId)
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
}
