package com.votingapp.minivoting.service;

import com.votingapp.minivoting.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotingService {
    private List<Candidate> candidates = new ArrayList<>();

    public void addCandidate(String name) {
        candidates.add(new Candidate(name));
    }

    public void vote(int index) {
        if (index >= 0 && index < candidates.size()) {
            candidates.get(index).vote();
        }
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<Candidate> getWinners() {
        int maxVotes = candidates.stream().mapToInt(Candidate::getVotes).max().orElse(0);
        List<Candidate> winners = new ArrayList<>();
        for (Candidate c : candidates) {
            if (c.getVotes() == maxVotes) {
                winners.add(c);
            }
        }
        return winners;
    }

    public int getTotalVotes() {
        return candidates.stream().mapToInt(Candidate::getVotes).sum();
    }
    public void resetCandidates() {
        candidates.clear(); // Or reset logic specific to your data structure
    }
    

}
