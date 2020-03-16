package Presidential_Elections.CIK;

import Presidential_Elections.Presidential_Candidates.Candidate;
import Presidential_Elections.Voters.VoterType;

import java.util.ArrayList;

class VotingPaper {

    private ArrayList<Candidate> candidatesOfVotingPaper;
    private Candidate voteForCandidate;
    private VoterType typeOfVoter;

    VotingPaper(ArrayList<Candidate> candidatesOfVotingPaper) {
        this.candidatesOfVotingPaper = new ArrayList<>(candidatesOfVotingPaper);
    }

    void setVoteForCandidate(Candidate voteForCandidate) {
        this.voteForCandidate = voteForCandidate;
    }

    void setTypeOfVoter(VoterType typeOfVoter) {
        this.typeOfVoter = typeOfVoter;
    }

    ArrayList<Candidate> getCandidatesOfVotingPaper() {
        return candidatesOfVotingPaper;
    }

    Candidate getVoteForCandidate() {
        return voteForCandidate;
    }
}
