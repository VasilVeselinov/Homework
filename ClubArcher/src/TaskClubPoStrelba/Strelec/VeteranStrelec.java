package TaskClubPoStrelba.Strelec;

import TaskClubPoStrelba.Bow.CarbonBow;

public class VeteranStrelec extends Strelec {

    public VeteranStrelec(String nameOfStrelec, boolean isMale, int age, int opit, int teglo, int silaNaOpan) {
        super(nameOfStrelec, isMale, age, opit);
        this.bow = new CarbonBow(teglo, silaNaOpan);
        this.maxBroiStreli = 60; // sterlia samo s 30 streli
        this.shansZaPropusk = 0; // 5% da ne oceli
        this.MinPointForStreleca = 6; // celi ot 6 do 10
    }
}
