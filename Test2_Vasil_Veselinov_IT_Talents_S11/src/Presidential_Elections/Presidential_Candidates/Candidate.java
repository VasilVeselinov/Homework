package Presidential_Elections.Presidential_Candidates;

import java.util.Objects;
import java.util.Random;

public abstract class Candidate {

    private static int numberOfCandidate = 0;
    private final String nameOfCandidate;
    private CandidateEducation education;
    private double budget;

    public Candidate() {
        this.nameOfCandidate = "Candidate " + Candidate.numberOfCandidate++;
        Random roll = new Random();
        this.budget = roll.nextInt(250000) + 50000;
    }

    public String getNameOfCandidate() {
        return nameOfCandidate;
    }

    void setEducation(CandidateEducation education) {
        this.education = education;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public CandidateEducation getEducation() {
        return education;
    }

    public abstract void organizingTheCampaign();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return nameOfCandidate.equals(candidate.nameOfCandidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfCandidate);
    }
}
