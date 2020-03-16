package Presidential_Elections.Electoral_Campaign;

import Presidential_Elections.CIK.CIK;
import Presidential_Elections.Voters.Voter;
import Presidential_Elections.Presidential_Candidates.Candidate;

public class NormalCampaign extends Campaign {

    public NormalCampaign(Candidate campaignOrganizer) {
        super(campaignOrganizer);
    }

    public void startOfCampaign() {
        int campaignPeriod = this.randomNumbersOfDay();
        for (int i = 0; i < campaignPeriod; i++) {
            for (int j = 0; j < this.getNumberOfVotersGenerated(); j++) {
                Voter voter = new Voter(this.getCampaignOrganizer(),false);
                CIK.addVoter(voter);
            }
        }
    }

    @Override
    int getNumberOfVotersGenerated() {
        return 100;
    }
}
