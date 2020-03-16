package TaskClubPoStrelba.Strelec;

import TaskClubPoStrelba.Bow.Bow;

import java.util.Random;

public abstract class Strelec {

    private static final int MAX_POINT = 10;
    private String nameOfStrelec;
    private boolean isMale; // pol na streleca
    private int age; // godini na streleca
    protected Bow bow; // lak nastreleca
    private int opit; // broi nagodinite opit nastreleca
    public int counterOfSastezaniq; // broqt na sastezaniqta v koito e uchastval streleca
    protected int maxBroiStreli; // broqt na strelite na streleca
    protected int shansZaPropusk; // shansa da propusne mishenata
    protected int MinPointForStreleca; // nai-malko tocki ot edna strelba
    protected int counterForPropuski; // broqt napropusnatite misheni
    private int pointsAfterShot; // tochkite sled kraq na strelbata
    private int counterOfTens = 0; // broi kolko desqtki ima streleca

    public Strelec(String nameOfStrelec, boolean isMale, int age, int opit) {
        this.nameOfStrelec = nameOfStrelec;
        this.isMale = isMale;
        this.age = age;
        this.opit = opit;
    }

//    public String getNameOfStrelec() {
//        return nameOfStrelec;
//    }

    public int getPointsAfterShot() {
        return pointsAfterShot;
    }

    public void shoot() {
        this.counterForPropuski = 0;
        this.pointsAfterShot = 0;
        for (int i = 0; i < this.maxBroiStreli; i++) {
            Random roll = new Random();
            int point = 0;
            if (roll.nextInt(100) < shansZaPropusk) {
                point = 0;
                this.counterForPropuski++;
            } else {
                point = roll.nextInt(MAX_POINT - 1) + MinPointForStreleca; // tochkite v mishenata sa ot 1 do 10
                if (point < MAX_POINT) {
                    point += this.bow.getBonusMernik();
                }
                if (point >= MAX_POINT) {
                    point = MAX_POINT;
                    this.counterOfTens++;
                }
            }
            this.pointsAfterShot += point;
        }
    }

    @Override
    public String toString() {
        return "Strelec{" +
                "nameOfStrelec='" + nameOfStrelec + '\'' +
                ", isMale=" + isMale +
                ", age=" + age +
                ", counterForPropuski=" + counterForPropuski +
                ", pointAfterShot=" + pointsAfterShot +
                ", counterOfTens=" + counterOfTens +
                "}\n";
    }

    public int comperToCounterOfZero(Strelec other) {
        if (this.counterForPropuski == other.counterForPropuski) {
            return 1;
        }
        return other.counterForPropuski - this.counterForPropuski;
    }

    public int comperToCounterOfTens(Strelec other) {
        if (this.counterOfTens == other.counterOfTens) {
            return 1;
        }
        return other.counterOfTens - this.counterOfTens;
    }

    public int compareToName(Strelec other) {
        if (this.nameOfStrelec.compareTo(other.nameOfStrelec) == 0) {
            return 1;
        }
        return this.nameOfStrelec.compareTo(other.nameOfStrelec);

    }

    public int compareToPoint(Strelec other) {
        if (this.pointsAfterShot == other.pointsAfterShot) {
            return 1;
        }
        return other.pointsAfterShot - this.pointsAfterShot;

    }
}
