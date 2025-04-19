package com.votingapp.minivoting.controller;

import com.votingapp.minivoting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VotingController {

    @Autowired
    private VotingService service;

    @GetMapping("/")
public String home(Model model, @ModelAttribute("message") String message) {
    model.addAttribute("candidates", service.getCandidates());
    model.addAttribute("totalVotes", service.getTotalVotes());
    model.addAttribute("message", message); // 👈 add this line
    return "index";
}


    @PostMapping("/add")
    public String addCandidate(@RequestParam String name) {
        service.addCandidate(name);
        return "redirect:/";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam int index, RedirectAttributes redirectAttributes) {
        service.vote(index);
        redirectAttributes.addFlashAttribute("message", "✅ Vote successfully submitted!");
        return "redirect:/";
    }
    


    @GetMapping("/result")
    public String result(Model model) {
        model.addAttribute("candidates", service.getCandidates());
        model.addAttribute("winners", service.getWinners());
        return "result";
    }


    @PostMapping("/reset-candidates")
    public String resetCandidates(@RequestParam(name = "confirmReset", required = true) boolean confirmReset) {
        if (confirmReset) {
            // Assuming VotingService has a method to reset all candidates
            service.resetCandidates();
            return "redirect:/"; // Redirect after reset
        }
        // Return to previous page or show cancel message
        return "redirect:/";
    }
    
    

}
