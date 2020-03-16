package Presidential_Elections.Electoral_Campaign;

import Presidential_Elections.Presidential_Candidates.Candidate;

import java.util.Random;

public abstract class Campaign {

    private Candidate campaignOrganizer;

    Campaign(Candidate campaignOrganizer) {
        this.campaignOrganizer = campaignOrganizer;
    }

    public abstract void startOfCampaign();

    int randomNumbersOfDay(){
        Random roll = new Random();
        return roll.nextInt(5) + 20;
    }

    abstract int getNumberOfVotersGenerated();

    Candidate getCampaignOrganizer() {
        return campaignOrganizer;
    }
}
