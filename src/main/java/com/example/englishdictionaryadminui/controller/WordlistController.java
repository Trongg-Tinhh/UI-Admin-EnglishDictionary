package com.example.englishdictionaryadminui.controller;

import com.example.englishdictionaryadminui.models.Word;
import com.example.englishdictionaryadminui.service.WordlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wordlists")
public class WordlistController {
    @Autowired
    WordlistService wordlistService;

    @GetMapping
    public String getDefaultWordlist(
            Model model
    ) {
        if( wordlistService.getAllWordlist().getStatusCode().is2xxSuccessful())
        {
            model.addAttribute("wordlists", wordlistService.getAllWordlist().getBody());
            return "wordlist/wordlists";
        }
        else {
            return "error/500";
        }
    }

    @GetMapping("/detail/{wordlistId}")
    public String getWordlistDetail(
            Model model,
            @PathVariable("wordlistId") String wordlistId
    ) {
        if(wordlistService.getWordlistById("default", wordlistId).getStatusCode().is2xxSuccessful())
        {
            model.addAttribute("wordlist", wordlistService.getWordlistById("default", wordlistId).getBody());
            return "wordlist/wordlistDetail";
        }
        else {
            return "error/500";
        }
    }
    @PostMapping("/rename")
    public String renameWordlist(
            Model model,
            @RequestParam("id") String wordlistId,
            @RequestParam("name") String name
    ) {
        if (wordlistService.renameWordlist(wordlistId, name).getStatusCode().is2xxSuccessful()) {
            return "redirect:/wordlists";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/delete/{wordlistId}")
    public String deleteWordlist(
            Model model,
            @PathVariable("wordlistId") String wordlistId
    ) {
        if (wordlistService.deleteWordlist(wordlistId).getStatusCode().is2xxSuccessful()) {
            return "redirect:/wordlists";
        }
        else {
            return "error/404";
        }
    }
    @GetMapping("/delete/{wordlistId}/word/{wordId}")
    public String removeWordFromWordlist(
            Model model,
            @PathVariable("wordlistId") String wordlistId,
            @PathVariable("wordId") String wordId
    )
    {
        if (wordlistService.removeWordFromWordlist(wordlistId, wordId).getStatusCode().is2xxSuccessful()) {
            return "redirect:/wordlists/detail/" + wordlistId;
        }
        else {
            return "error/404";
        }
    }
}
