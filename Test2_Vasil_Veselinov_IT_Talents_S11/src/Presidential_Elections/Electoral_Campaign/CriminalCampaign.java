package Presidential_Elections.Electoral_Campaign;

import Presidential_Elections.CIK.CIK;
import Presidential_Elections.Voters.Voter;
import Presidential_Elections.Presidential_Candidates.Candidate;

import java.util.Random;

public class CriminalCampaign extends Campaign {

    public CriminalCampaign(Candidate campaignOrganizer) {
        super(campaignOrganizer);
    }

    public void startOfCampaign() {
        int campaignPeriod = this.randomNumbersOfDay();
        for (int i = 0; i < campaignPeriod; i++) {
            for (int j = 0; j < this.getNumberOfVotersGenerated(); j++) {
                if (j < this.getNumberOfVotersGenerated() / 2){
                    Voter voter = new Voter(this.getCampaignOrganizer(),false);
                    CIK.addVoter(voter);
                }else {
                    double amountPerVote = this.randomAmountPerVote();
                    if (amountPerVote <= this.getCampaignOrganizer().getBudget()) {
                        Voter voter = new Voter(this.getCampaignOrganizer(), true);
                        CIK.addVoter(voter);
                        this.getCampaignOrganizer().setBudget(this.getCampaignOrganizer().getBudget() - amountPerVote);
                    }else {
                        return;
                    }
                }
            }
        }
    }

    @Override
    int getNumberOfVotersGenerated() {
        return 120;
    }

    private int randomAmountPerVote(){
        Random roll = new Random();
        return roll.nextInt(20) + 30;
    }
}
