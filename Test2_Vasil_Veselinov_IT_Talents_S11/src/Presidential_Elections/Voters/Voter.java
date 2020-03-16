package Presidential_Elections.Voters;

import Presidential_Elections.CIK.CIK;
import Presidential_Elections.CIK.SectionForVoting;
import Presidential_Elections.Presidential_Candidates.Candidate;

import java.util.Objects;
import java.util.Random;

public class Voter {

    private static int numberOfVoters = 1;
    private VoterType voterType;
    private final String nameOfVoter;
    private boolean isMale;
    private String city;
    private Candidate candidate;
    private boolean isItCorrupt;
    private SectionForVoting currentSectionForVoting;

    public Voter(Candidate candidate, boolean isItCorrupt) {
        this.nameOfVoter = "Voter " + Voter.numberOfVoters++;
        Random roll = new Random();
        this.isMale = roll.nextBoolean();
        this.city = CIK.randomCity();
        this.voterType = VoterType.randomTypeOfVoter(); // UNEDUCATED, MIDDLE_CLASS, WEALTHY
        this.candidate = candidate;
        this.isItCorrupt = isItCorrupt;
    }

    public void setCurrentSectionForVoting(SectionForVoting currentSectionForVoting) {
        this.currentSectionForVoting = currentSectionForVoting;
    }

    public String getCity() {
        return city;
    }

    public VoterType getVoterType() {
        return voterType;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public boolean getIsItCorrupt(){
        return isItCorrupt;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void changeCandidate() {
        Candidate currentCandidate = this.candidate;
        Random roll = new Random();
        int index = roll.nextInt(this.currentSectionForVoting.getVotingPaperWhitCandidate().size());
        Candidate newCandidate = this.currentSectionForVoting.getVotingPaperWhitCandidate().get(index);
        if (currentCandidate == newCandidate){
            this.changeCandidate();
        }else {
            this.candidate = newCandidate;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter)) return false;
        Voter voter = (Voter) o;
        return nameOfVoter.equals(voter.nameOfVoter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfVoter);
    }
}
