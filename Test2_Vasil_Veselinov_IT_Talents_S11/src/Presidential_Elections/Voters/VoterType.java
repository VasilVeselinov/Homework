package Presidential_Elections.Voters;

import java.util.Random;

public enum VoterType {

    UNEDUCATED, MIDDLE_CLASS, WEALTHY;

    static VoterType randomTypeOfVoter(){
        Random roll = new Random();
        int rollVoterType = roll.nextInt(3);
        if (rollVoterType == 0){
            return VoterType.UNEDUCATED;
        }else {
            if (rollVoterType == 1){
                return VoterType.MIDDLE_CLASS;
            }else {
                return VoterType.WEALTHY;
            }
        }
    }
}
