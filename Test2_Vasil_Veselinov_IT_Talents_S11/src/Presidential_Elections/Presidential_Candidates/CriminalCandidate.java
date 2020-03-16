package Presidential_Elections.Presidential_Candidates;

import Presidential_Elections.Electoral_Campaign.Campaign;
import Presidential_Elections.Electoral_Campaign.CriminalCampaign;

import java.time.LocalDate;

public class CriminalCandidate extends Candidate {



    public CriminalCandidate() {
        super();
        if (CandidateEducation.randomTrueOrOFalse()){ // WITHOUT_EDUCATION, PRIMARY_EDUCATION
            this.setEducation(CandidateEducation.WITHOUT_EDUCATION);
        }else {
            this.setEducation(CandidateEducation.PRIMARY_EDUCATION);
        }
    }

    public void organizingTheCampaign() {
        LocalDate startDate = LocalDate.now();
        Campaign criminalCampaign = new CriminalCampaign(this);
        criminalCampaign.startOfCampaign();
    }
}
