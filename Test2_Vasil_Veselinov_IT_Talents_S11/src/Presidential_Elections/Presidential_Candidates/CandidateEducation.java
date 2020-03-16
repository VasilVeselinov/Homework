package Presidential_Elections.Presidential_Candidates;

import java.util.Random;

public enum CandidateEducation {

    WITHOUT_EDUCATION, PRIMARY_EDUCATION, SECONDARY_EDUCATION, HIGHER_EDUCATION;

    static boolean randomTrueOrOFalse(){
        Random roll = new Random();
        return roll.nextBoolean();
    }

    static CandidateEducation randomThreeTypeEducation(){
        Random roll = new Random();
        int typeOfEducation = roll.nextInt(3);
        // (0):WITHOUT_EDUCATION, (1):PRIMARY_EDUCATION, (2):HIGHER_EDUCATION;
        if (typeOfEducation == 0){
            return CandidateEducation.WITHOUT_EDUCATION;
        }else {
            if (typeOfEducation == 1){
                return CandidateEducation.PRIMARY_EDUCATION;
            }else {
                return CandidateEducation.HIGHER_EDUCATION;
            }
        }
    }
}
