package TaskClubPoStrelba;

import TaskClubPoStrelba.Strelec.MladshiStrelec;
import TaskClubPoStrelba.Strelec.StarshiStrelec;
import TaskClubPoStrelba.Strelec.Strelec;
import TaskClubPoStrelba.Strelec.VeteranStrelec;

import java.util.ArrayList;
import java.util.TreeSet;

public class ClubPoStrelba {

    private String nameNaCluba; // ime na cluba po strelba
    private String adresNaCluba; // adres na cluba po strelba
    public static String nameOfTreniuor; // ime na treniuora w cluba po strelba
    public ArrayList<Strelec> strelci; // spisak ot strelcite v cluba po strelba
    private TreeSet<MladshiStrelec> spisakCategoriqMladshi = new TreeSet<>((o1, o2) -> o1.compareToPoint(o2));
    private TreeSet<StarshiStrelec> spisakCategoriqStarshi = new TreeSet<>((o1, o2) -> o1.compareToPoint(o2));
    private TreeSet<VeteranStrelec> spisakCategoriqVeteran = new TreeSet<>((o1, o2) -> o1.compareToPoint(o2));




    public ClubPoStrelba(String nameNaCluba, String adresNaCluba) {
        this.nameNaCluba = nameNaCluba;
        this.adresNaCluba = adresNaCluba;
        this.strelci = new ArrayList<>();
    }

    public void strartNaSastezanieto() {
        System.out.println(this.nameNaCluba + " organisira sastezanie");
        System.out.println("Glaven sadia e treniuora na cluba " + ClubPoStrelba.nameOfTreniuor);
        System.out.println("-------------------Spisak po azbuchen red-----------------------");
        spisakPoAzbuchenRed();
        strelciteStrelqt();
        System.out.println("-------------------Spisak po categorii-----------------------");
        System.out.println("---categoriq MladshiStrelec--");
        categoriaMladshiStrelec();
        System.out.println("---categoriq StarshiStrelec--");
        categoriaStarshiStrelec();
        System.out.println("---categoriq VeteranStrelec--");
        categoriaVeteranStrelec();
        System.out.println("-------------------Pobediteli po categorii-----------------------");
        pobeditelOtVsqkaCategoriq();
        System.out.println("-------------------Sreden rezultat na vsqka categoriq-----------------------");
        printAverageResultOfCategoriaMladshiStrelec(this.spisakCategoriqMladshi);
        printAverageResultOfCategoriaStarshi(this.spisakCategoriqStarshi);
        printAverageResultOfCategoriaVeteran(this.spisakCategoriqVeteran);
        System.out.println("-------------------Nai-tochen strelec-----------------------");
        printStrelesWhitMaxTens(this.strelci);
        System.out.println("-------------------Nai-nekadaren strelec-----------------------");
        printStrelesWhitMaxZero(this.strelci);
    }

    private void printStrelesWhitMaxZero(ArrayList<Strelec> strelci) {
        TreeSet<Strelec> spisakPoZero =
                new TreeSet<>((o1, o2) -> o1.comperToCounterOfZero(o2));
        spisakPoZero.addAll(this.strelci);
        System.out.println(spisakPoZero.first());
    }

    private void printStrelesWhitMaxTens(ArrayList<Strelec> strelci) {
        TreeSet<Strelec> spisakPoTens =
                new TreeSet<>((o1, o2) -> o1.comperToCounterOfTens(o2));
        spisakPoTens.addAll(this.strelci);
        System.out.println(spisakPoTens.first());
    }

    private void printAverageResultOfCategoriaVeteran(TreeSet<VeteranStrelec> spisakCategoriqVeteran) {
        double averageresultOfPoints = 0;
        for (VeteranStrelec element : spisakCategoriqVeteran) {
            averageresultOfPoints += element.getPointsAfterShot();
        }
        averageresultOfPoints /= spisakCategoriqVeteran.size();
        System.out.printf("%.2f e sredniqt resultat na categoriq Veteran\n",averageresultOfPoints);
    }

    private void printAverageResultOfCategoriaStarshi(TreeSet<StarshiStrelec> spisakCategoriqStarshi) {
        double averageresultOfPoints = 0;
        for (StarshiStrelec element : spisakCategoriqStarshi) {
            averageresultOfPoints += element.getPointsAfterShot();
        }
        averageresultOfPoints /= spisakCategoriqStarshi.size();
        System.out.printf("%.2f e sredniqt resultat na categoriq Starshi\n",averageresultOfPoints);
    }

    private void printAverageResultOfCategoriaMladshiStrelec(TreeSet<MladshiStrelec> spisakCategoriqMladshi) {
        double averageresultOfPoints = 0;
        for (MladshiStrelec element : spisakCategoriqMladshi) {
            averageresultOfPoints += element.getPointsAfterShot();
        }
        averageresultOfPoints /= spisakCategoriqMladshi.size();
        System.out.printf("%.2f e sredniqt resultat na categoriq Mladshi\n",averageresultOfPoints);
    }

    private void pobeditelOtVsqkaCategoriq() {
        System.out.println("---categoriq MladshiStrelec--");
        System.out.println(this.spisakCategoriqMladshi.first());
        System.out.println("---categoriq StarshiStrelec--");
        System.out.println(this.spisakCategoriqStarshi.first());
        System.out.println("---categoriq VeteranStrelec--");
        System.out.println(this.spisakCategoriqVeteran.first());
    }

    private void categoriaVeteranStrelec() {
        for (int i = 0; i < this.strelci.size(); i++) {
            if (this.strelci.get(i) instanceof VeteranStrelec) {
                this.spisakCategoriqVeteran.add((VeteranStrelec) this.strelci.get(i));
            }
        }
        System.out.println(this.spisakCategoriqVeteran);
    }

    private void categoriaStarshiStrelec() {
        for (int i = 0; i < this.strelci.size(); i++) {
            if (this.strelci.get(i) instanceof StarshiStrelec) {
                this.spisakCategoriqStarshi.add((StarshiStrelec) this.strelci.get(i));
            }
        }
        System.out.println(this.spisakCategoriqStarshi);
    }

    private void categoriaMladshiStrelec() {
        for (int i = 0; i < this.strelci.size(); i++) {
            if (this.strelci.get(i) instanceof MladshiStrelec) {
                this.spisakCategoriqMladshi.add((MladshiStrelec) this.strelci.get(i));
            }
        }
        System.out.println(this.spisakCategoriqMladshi);
    }

    private void strelciteStrelqt() {
        for (int i = 0; i < this.strelci.size(); i++) {
            this.strelci.get(i).shoot();
        }
    }

    private void spisakPoAzbuchenRed() {
        for (int i = 0; i < this.strelci.size(); i++) {
            this.strelci.get(i).counterOfSastezaniq++;
        }
        TreeSet<Strelec> spisakZaPechat =
                new TreeSet<>((o1, o2) -> o1.compareToName(o2));
        spisakZaPechat.addAll(this.strelci);
        System.out.println(spisakZaPechat);
    }
}
