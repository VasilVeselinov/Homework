package Presidential_Elections;

import Presidential_Elections.CIK.CIK;
import Presidential_Elections.Presidential_Candidates.Candidate;
import Presidential_Elections.Presidential_Candidates.CriminalCandidate;
import Presidential_Elections.Presidential_Candidates.PoliticalCandidate;
import Presidential_Elections.Presidential_Candidates.ShowmanCandidate;

import java.util.HashSet;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {

        // Generating candidates for the presidential campaign
        HashSet<Candidate> candidates = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Random roll = new Random();
            int chanceForCandidateType = roll.nextInt(100);
            Candidate candidate;
            if (chanceForCandidateType < 33){
                candidate = new CriminalCandidate();
            }else {
                if (chanceForCandidateType < 66){
                    candidate = new PoliticalCandidate();
                }else {
                    candidate = new ShowmanCandidate();
                }
            }
            candidates.add(candidate);
        }

        // Creation of a CIK for the presidential election
        CIK cIKForPresidentialElections = new CIK();

        // Adding candidates to the CIK for the presidential election
        cIKForPresidentialElections.addCandidates(candidates);

        // Candidates organize election campaigns
        for(Candidate candidate: candidates){
            candidate.organizingTheCampaign();
        }

        // CIK organizes sections in each city
        cIKForPresidentialElections.makeSectionForVoting();

        // CIK gives the start to the vote
        cIKForPresidentialElections.startOfVoting();


        // Print ranking
        cIKForPresidentialElections.printRanking();


        // Print of statistics
        cIKForPresidentialElections.printOfStatistics();
    }
}
