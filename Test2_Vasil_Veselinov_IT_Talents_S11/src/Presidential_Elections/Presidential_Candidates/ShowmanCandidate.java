package Presidential_Elections.Presidential_Candidates;

import Presidential_Elections.Electoral_Campaign.Campaign;
import Presidential_Elections.Electoral_Campaign.NormalCampaign;

import java.time.LocalDate;

public class ShowmanCandidate extends Candidate {

    public ShowmanCandidate() {
        super();
        this.setEducation(CandidateEducation.randomThreeTypeEducation());
        // WITHOUT_EDUCATION, PRIMARY_EDUCATION, HIGHER_EDUCATION;
    }

    public void organizingTheCampaign() {
        LocalDate startDate = LocalDate.now();
        Campaign normalCampaign = new NormalCampaign(this);
        normalCampaign.startOfCampaign();
    }
}
