package Presidential_Elections.CIK;

import Presidential_Elections.Presidential_Candidates.Candidate;
import Presidential_Elections.Presidential_Candidates.CandidateEducation;
import Presidential_Elections.Voters.Voter;
import Presidential_Elections.Voters.VoterType;

import java.util.*;

import static Presidential_Elections.Presidential_Candidates.CandidateEducation.SECONDARY_EDUCATION;


public class CIK {

    public static String[] cities = {"Sofia", "Plovdiv", "Varna", "Burgas", "Veliko Tarnovo", "Dobrich", "Kardzhali"};
    private ArrayList<SectionForVoting> sectionsForVoting = new ArrayList<>();
    private static HashSet<Voter> voters = new HashSet<>();
    private ArrayList<Candidate> candidates;
    private TreeMap<Candidate, TreeMap<String, Integer>> ranking;
    private int totalVoterVotes;

    public CIK() {
        this.candidates = new ArrayList<>();
    }

    public static void addVoter(Voter voter) {
        CIK.voters.add(voter);
    }

    public void addCandidates(HashSet<Candidate> candidates) {
        this.candidates.addAll(candidates);
    }

    public static String randomCity(){
        Random roll = new Random();
        return CIK.cities[roll.nextInt(7)];
    }

    public void makeSectionForVoting() {
        for (int i = 0; i < CIK.cities.length; i++) {
            ArrayList<Voter> tempList = new ArrayList<>();
            for (Voter voter : CIK.voters) {
                if (voter.getCity().equals(CIK.cities[i])) {
                    tempList.add(voter);
                }
            }
            this.sectionsForVoting.add(new SectionForVoting(CIK.cities[i], this.candidates, tempList));
        }
    }

    public void startOfVoting() {
        for (SectionForVoting city : this.sectionsForVoting) {
            city.startOfVotingInCity();
        }

        // CIK make ranking after voting
        this.makeRanking();
    }

    private void makeRanking() {
        this.ranking = new TreeMap<>((o1, o2) -> o1.getNameOfCandidate().compareTo(o2.getNameOfCandidate()));
        for (Candidate candidate : this.candidates) {
            this.ranking.put(candidate, new TreeMap<String, Integer>());
            for (SectionForVoting city : this.sectionsForVoting) {
                int counterForVoteForCity = 0;
                for (int i = 0; i < city.getBallotBox().size(); i++) {
                    if (city.getBallotBox().get(i).getVoteForCandidate().getNameOfCandidate().
                            equals(candidate.getNameOfCandidate())) {
                        counterForVoteForCity++;
                    }
                }
                this.ranking.get(candidate).put(city.getNameOfCity(), counterForVoteForCity);
            }
        }
    }

    public void printRanking() {
        for (Candidate keyCandidate : this.ranking.keySet()) {
            System.out.println(keyCandidate.getNameOfCandidate() + ":");
            for (Map.Entry<String, Integer> city : this.ranking.get(keyCandidate).entrySet()) {
                System.out.println("\t\t" + city.getKey() + " - " + city.getValue());
            }
        }
    }

    public void printOfStatistics() {
        System.out.println("----------Statistics for Winner and Runner up (1 and 2)----------");
        printOfElectionWinnerAndRunnerUp();
        System.out.println("----------Statistics for total of voters voted (3)----------");
        printTotalOfVotersVoted();
        System.out.println("----------Statistics for Voting activity (4)----------");
        printVotingActivity();
        System.out.println("----------Statistics for Voting activity of city (5)----------");
        printVotingActivityOfCity();
        System.out.println("----------Statistics for Election votes purchased (6)----------");
        printPercentageOfCorruptionVote();
        System.out.println("----------Statistics for Invalid votes (7)----------");
        printInvalidVotes();
        System.out.println("----------Register of candidates by city (8)----------");
        printRegisterOfCandidatesByCity();
        System.out.println("----------Favorite candidates for the class group (9)----------");
        printFavoriteCandidatesForTheClassGroup();
        System.out.println("----------The city with the most voters (10)----------");
        printTheCityWithTheMostVoters();
        System.out.println("----------The city with the least invalid votes (11)----------");
        printTheCityWithTheLeastInvalidVotes();
        System.out.println("----------The city with the most votes purchased (12)----------");
        printTheCityWithMostCorruptionVotes();
        System.out.println("----------Number of votes for candidates for education (13)----------");
        printNumberOfVotesForCandidatesForEducation();
    }

    private TreeMap<Integer, Candidate> makeRankingTotalVoters() {
        TreeMap<Integer, Candidate> rankingForWinner = new TreeMap<>((o1, o2) -> {
            if (o1.equals(o2)) {
                return 1;
            }
            return o2 - o1;
        });
        for (Candidate keyCandidate : this.ranking.keySet()) {
            int totalVote = 0;
            for (Integer vote : this.ranking.get(keyCandidate).values()) {
                totalVote += vote;
            }
            rankingForWinner.put(totalVote, keyCandidate);
        }
        return rankingForWinner;
    }

    private void printOfElectionWinnerAndRunnerUp() {
        TreeMap<Integer, Candidate> rankingForWinner = this.makeRankingTotalVoters();
        System.out.println("Winner is:");
        System.out.println(rankingForWinner.firstEntry().getValue().getNameOfCandidate() + " with " +
                rankingForWinner.firstKey() + " votes");
        rankingForWinner.pollFirstEntry();
        System.out.println("The second candidate in ranking is:");
        System.out.println(rankingForWinner.firstEntry().getValue().getNameOfCandidate() + " with " +
                rankingForWinner.firstKey() + " votes");
    }

    private void printTotalOfVotersVoted() {
        TreeMap<Integer, Candidate> makeRanking = this.makeRankingTotalVoters();
        int totalVoters = 0;
        for (Integer key : makeRanking.keySet()) {
            totalVoters += key;
        }
        this.totalVoterVotes = totalVoters;
        System.out.println("Total of voters voted: " + totalVoters);
    }

    private void printVotingActivity() {
        double percentageOfVoters = ((double) this.totalVoterVotes / CIK.voters.size()) * 100;
        System.out.printf("Total %.2f %% voting activity\n ", percentageOfVoters);
    }

    private void printVotingActivityOfCity() {
        TreeMap<Double, String> rankingOfCity = new TreeMap<>((o1, o2) -> {
            if (Double.compare(o2, o1) == 0) {
                return 1;
            }
            return Double.compare(o2, o1);
        });
        for (SectionForVoting city : this.sectionsForVoting) {
            double percentageOfVoters = ((double) city.getBallotBox().size() / city.getVotersOfCity().size()) * 100;
            rankingOfCity.put(percentageOfVoters, city.getNameOfCity());
        }

        for (Map.Entry<Double, String> key : rankingOfCity.entrySet()) {
            System.out.printf("%s with %.2f %% voting activity\n", key.getValue(), key.getKey());
        }
    }

    private void printPercentageOfCorruptionVote() {
        int totalOfCorruptionVote = 0;
        for (SectionForVoting city : this.sectionsForVoting) {
            totalOfCorruptionVote += city.getNumberOfCorruptionVote();
        }

        double percentageOfCorruptionVote = ((double) totalOfCorruptionVote / this.totalVoterVotes) * 100;

        System.out.printf("%.2f %% corruption votes\n", percentageOfCorruptionVote);
    }

    private void printInvalidVotes() {
        int totalOfInvalidVote = 0;
        for (SectionForVoting city : this.sectionsForVoting) {
            totalOfInvalidVote += city.getNumberOfInvalidVote();
        }

        double percentageOfInvalidVote = ((double) totalOfInvalidVote / this.totalVoterVotes) * 100;

        System.out.printf("%.2f %% invalid votes\n", percentageOfInvalidVote);
    }

    private void printRegisterOfCandidatesByCity() {
        TreeMap<SectionForVoting, TreeMap<Integer, Candidate>> rankingByCity =
                new TreeMap<>((o1, o2) -> o1.getNameOfCity().compareTo(o2.getNameOfCity()));
        for (SectionForVoting city : this.sectionsForVoting) {
            rankingByCity.put(city, new TreeMap<Integer, Candidate>((o1, o2) -> {
                if (o1.equals(o2)) {
                    return 1;
                }
                return o2 - o1;
            }));
            for (Candidate candidate : this.candidates) {
                int counterForVoteForCity = 0;
                for (int i = 0; i < city.getBallotBox().size(); i++) {
                    if (city.getBallotBox().get(i).getVoteForCandidate().getNameOfCandidate().
                            equals(candidate.getNameOfCandidate())) {
                        counterForVoteForCity++;
                    }
                }
                rankingByCity.get(city).put(counterForVoteForCity, candidate);
            }
        }

        for (SectionForVoting key : rankingByCity.keySet()) {
            System.out.println(key.getNameOfCity() + ":");
            for (Map.Entry<Integer, Candidate> entry : rankingByCity.get(key).entrySet()) {
                System.out.println("\t" + entry.getValue().getNameOfCandidate());
            }
        }
    }

    private void printFavoriteCandidatesForTheClassGroup() {
        TreeMap<VoterType, TreeMap<Integer, Candidate>> rankingByClassGroup =
                new TreeMap<>();
        for(VoterType type: VoterType.values()) {
            for (SectionForVoting city : this.sectionsForVoting) {
                rankingByClassGroup.put(type, new TreeMap<Integer, Candidate>((o1, o2) -> {
                    if (o1.equals(o2)) {
                        return 1;
                    }
                    return o2 - o1;
                }));
                for (Candidate candidate : this.candidates) {
                    int counterForVoteForCity = 0;
                    for (int i = 0; i < city.getBallotBox().size(); i++) {
                        if (city.getBallotBox().get(i).getVoteForCandidate().getNameOfCandidate().
                                equals(candidate.getNameOfCandidate())) {
                            counterForVoteForCity++;
                        }
                    }
                    rankingByClassGroup.get(type).put(counterForVoteForCity, candidate);
                }
            }
        }

        for (VoterType key : rankingByClassGroup.keySet()) {
            System.out.println(key + ":");
            for (Map.Entry<Integer, Candidate> entry : rankingByClassGroup.get(key).entrySet()) {
                System.out.println("\t" + entry.getValue().getNameOfCandidate());
            }
        }
    }

    private void printTheCityWithTheMostVoters() {
        TreeMap<Integer, String> rankingForTheCityWithMostVoters = new TreeMap<>((o1, o2) -> {
            if (o1.equals(o2)) {
                return 1;
            }
            return o2 - o1;
        });

        for (SectionForVoting keyCity : this.sectionsForVoting) {
            rankingForTheCityWithMostVoters.put(keyCity.getVotersOfCity().size(), keyCity.getNameOfCity());
        }

        System.out.printf("City with most voters is %s: %d voters\n",
                rankingForTheCityWithMostVoters.firstEntry().getValue(),
                rankingForTheCityWithMostVoters.firstKey());
    }

    private void printTheCityWithTheLeastInvalidVotes() {
        TreeMap<Integer, String> rankingForTheCityWithLeastInvalidVotes = new TreeMap<>((o1, o2) -> {
            if (o1.equals(o2)) {
                return 1;
            }
            return o1 - o2;
        });

        for (SectionForVoting keyCity : this.sectionsForVoting) {
            rankingForTheCityWithLeastInvalidVotes.put(keyCity.getNumberOfInvalidVote(), keyCity.getNameOfCity());
        }
        System.out.printf("City with least invalid votes is %s: %d voters\n",
                rankingForTheCityWithLeastInvalidVotes.firstEntry().getValue(),
                rankingForTheCityWithLeastInvalidVotes.firstKey());
    }

    private void printTheCityWithMostCorruptionVotes() {
        TreeMap<Integer, String> rankingForTheCityWithMostCorruptionVotes = new TreeMap<>((o1, o2) -> {
            if (o1.equals(o2)) {
                return 1;
            }
            return o2 - o1;
        });

        for (SectionForVoting keyCity : this.sectionsForVoting) {
            rankingForTheCityWithMostCorruptionVotes.put(keyCity.getNumberOfCorruptionVote(), keyCity.getNameOfCity());
        }
        System.out.printf("City with least invalid votes is %s: %d voters\n",
                rankingForTheCityWithMostCorruptionVotes.firstEntry().getValue(),
                rankingForTheCityWithMostCorruptionVotes.firstKey());
    }

    private void printNumberOfVotesForCandidatesForEducation() {
        TreeMap<CandidateEducation, Integer> rankingByNumberOfVotesForCandidatesByEducation = new TreeMap<>();
        TreeMap<Integer, Candidate> makeRanking = this.makeRankingTotalVoters();
        for (CandidateEducation key : CandidateEducation.values()) {
            int totalVotes = 0;
            for (Map.Entry<Integer, Candidate> keyEntry : makeRanking.entrySet()) {
                if (key.equals(keyEntry.getValue().getEducation())) {
                    totalVotes += keyEntry.getKey();
                }
            }
            rankingByNumberOfVotesForCandidatesByEducation.put(key, totalVotes);
        }

        for (Map.Entry<CandidateEducation, Integer> key : rankingByNumberOfVotesForCandidatesByEducation.entrySet()) {
            if (!key.getKey().equals(SECONDARY_EDUCATION)) {
                System.out.println("Candidates with " + key.getKey() + " has " + key.getValue() + " votes");
            }
        }
    }
}
