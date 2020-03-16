package TaskClubPoStrelba.Strelec;

import TaskClubPoStrelba.Bow.DarvenBow;

public class MladshiStrelec extends Strelec{

    public MladshiStrelec(String nameOfStrelec, boolean isMale, int age, int opit, int teglo, int silaNaOpan) {
        super(nameOfStrelec, isMale, age, opit);
        this.bow = new DarvenBow(teglo , silaNaOpan);
        this.maxBroiStreli = 30; // sterlia samo s 30 streli
        this.shansZaPropusk = 10; // 10% da ne oceli
        this.MinPointForStreleca = 1; // celi ot 1 do 10
    }
}
