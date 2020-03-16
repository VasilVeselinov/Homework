package Presidential_Elections.CIK;

import Presidential_Elections.Presidential_Candidates.Candidate;
import Presidential_Elections.Voters.Voter;
import Presidential_Elections.Voters.VoterType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class SectionForVoting {

    private String nameOfCity;
    private VotingPaper votingPaperWhitCandidate;
    private ArrayList<Voter> votersOfCity;
    private Stack<VotingPaper> ballotBox;
    private int numberOfCorruptionVote;
    private int numberOfInvalidVote;

    SectionForVoting(String nameOfCity, ArrayList<Candidate> listOfCandidates, ArrayList<Voter> votersOfCity) {
        this.nameOfCity = nameOfCity;
        this.votingPaperWhitCandidate = new VotingPaper(listOfCandidates);
        this.votersOfCity = new ArrayList<>(votersOfCity);
        this.subscribeVoter();
        this.ballotBox = new Stack<>();
    }

    String getNameOfCity() {
        return nameOfCity;
    }

    public ArrayList<Candidate> getVotingPaperWhitCandidate() {
        return this.votingPaperWhitCandidate.getCandidatesOfVotingPaper();
    }

    private void subscribeVoter() {
        for (Voter voter : this.votersOfCity) {
            voter.setCurrentSectionForVoting(this);
        }
    }

    Stack<VotingPaper> getBallotBox() {
        return ballotBox;
    }

    ArrayList<Voter> getVotersOfCity() {
        return votersOfCity;
    }

    int getNumberOfCorruptionVote() {
        return numberOfCorruptionVote;
    }

    int getNumberOfInvalidVote() {
        return numberOfInvalidVote;
    }

    void startOfVotingInCity() {
        this.votingUneducatedVoters();
        this.votingMiddleClassVoters();
        this.votingWealthyVoters();
    }

    private void votingUneducatedVoters() {
        Random roll = new Random();
        ArrayList<Voter> uneducatedVoters = new ArrayList<>();
        for (Voter voter : this.votersOfCity) {
            if (voter.getVoterType() == VoterType.UNEDUCATED) {
                uneducatedVoters.add(voter);
            }
        }
        int numberOfVotesOfUneducatedVoter = (int) (uneducatedVoters.size() * 0.9); // less 10% uneducated voters
        for (int i = 0; i < numberOfVotesOfUneducatedVoter; i++) {
            VotingPaper tempVotingPaper = new VotingPaper(this.votingPaperWhitCandidate.getCandidatesOfVotingPaper());
            tempVotingPaper.setTypeOfVoter(uneducatedVoters.get(i).getVoterType());
            tempVotingPaper.setVoteForCandidate(uneducatedVoters.get(i).getCandidate());
            if (roll.nextInt(100) > 40) { // 40 % for invalid vote
                if (uneducatedVoters.get(i).getIsItCorrupt()) {
                    this.numberOfCorruptionVote++;
                }

                this.ballotBox.add(tempVotingPaper);
            } else {
                this.numberOfInvalidVote++;
            }
        }
    }

    private void votingMiddleClassVoters() {
        Random roll = new Random();
        ArrayList<Voter> middleClassVoters = new ArrayList<>();
        for (Voter voter : this.votersOfCity) {
            if (voter.getVoterType() == VoterType.MIDDLE_CLASS) {
                middleClassVoters.add(voter);
            }
        }
        int numberOfVotesOfMiddleClassVoter = (int) (middleClassVoters.size() * 0.7); // less 30% middle class voters
        boolean hasChangeCandidate = false;
        for (int i = 0; i < numberOfVotesOfMiddleClassVoter; i++) {
            VotingPaper tempVotingPaper;
            if (i < numberOfVotesOfMiddleClassVoter * 0.7) { // less 30% for other candidate
                tempVotingPaper = new VotingPaper(this.votingPaperWhitCandidate.getCandidatesOfVotingPaper());
                tempVotingPaper.setTypeOfVoter(middleClassVoters.get(i).getVoterType());
                tempVotingPaper.setVoteForCandidate(middleClassVoters.get(i).getCandidate());
            } else {
                hasChangeCandidate = true;
                middleClassVoters.get(i).changeCandidate();
                tempVotingPaper = new VotingPaper(this.votingPaperWhitCandidate.getCandidatesOfVotingPaper());
                tempVotingPaper.setTypeOfVoter(middleClassVoters.get(i).getVoterType());
                tempVotingPaper.setVoteForCandidate(middleClassVoters.get(i).getCandidate());
            }
            if (roll.nextInt(100) > 10) { // 10 % for invalid vote
                if (middleClassVoters.get(i).getIsItCorrupt() && !hasChangeCandidate) {
                    this.numberOfCorruptionVote++;
                }

                this.ballotBox.add(tempVotingPaper);
            } else {
                this.numberOfInvalidVote++;
            }
        }
    }

    private void votingWealthyVoters() {
        ArrayList<Voter> wealthyVoters = new ArrayList<>();
        for (Voter voter : this.votersOfCity) {
            if (voter.getVoterType() == VoterType.WEALTHY) {
                wealthyVoters.add(voter);
            }
        }
        int numberOfVotesOfWealthyVoter = (int) (wealthyVoters.size() * 0.5); // less 50% wealthy voters
        boolean hasChangeCandidate = false;
        for (int i = 0; i < numberOfVotesOfWealthyVoter; i++) {
            VotingPaper tempVotingPaper;
            if (i < numberOfVotesOfWealthyVoter * 0.5) { // less 50% for other candidate
                tempVotingPaper = new VotingPaper(this.votingPaperWhitCandidate.getCandidatesOfVotingPaper());
                tempVotingPaper.setTypeOfVoter(wealthyVoters.get(i).getVoterType());
                tempVotingPaper.setVoteForCandidate(wealthyVoters.get(i).getCandidate());
            } else {
                hasChangeCandidate = true;
                wealthyVoters.get(i).changeCandidate();
                tempVotingPaper = new VotingPaper(this.votingPaperWhitCandidate.getCandidatesOfVotingPaper());
                tempVotingPaper.setTypeOfVoter(wealthyVoters.get(i).getVoterType());
                tempVotingPaper.setVoteForCandidate(wealthyVoters.get(i).getCandidate());
            }

            if (wealthyVoters.get(i).getIsItCorrupt() && !hasChangeCandidate) {
                this.numberOfCorruptionVote++;
            }

            this.ballotBox.add(tempVotingPaper);
        }
    }
}
