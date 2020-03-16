package Presidential_Elections.Presidential_Candidates;

import Presidential_Elections.Electoral_Campaign.Campaign;
import Presidential_Elections.Electoral_Campaign.NormalCampaign;
import Presidential_Elections.Electoral_Campaign.CriminalCampaign;

import java.time.LocalDate;
import java.util.Random;

public class PoliticalCandidate extends Candidate {

    public PoliticalCandidate() {
        super();
        if (CandidateEducation.randomTrueOrOFalse()){ // SECONDARY_EDUCATION, HIGHER_EDUCATION;
            this.setEducation(CandidateEducation.SECONDARY_EDUCATION);
        }else {
            this.setEducation(CandidateEducation.HIGHER_EDUCATION);
        }
    }

    public void organizingTheCampaign() {
        LocalDate startDate = LocalDate.now();
        Random roll = new Random();
        if (roll.nextBoolean()) {
            Campaign criminalCampaign = new CriminalCampaign(this);
            criminalCampaign.startOfCampaign();
        }else {
            Campaign normalCampaign = new NormalCampaign(this);
            normalCampaign.startOfCampaign();
        }
    }
}
