package TaskClubPoStrelba.Strelec;

import TaskClubPoStrelba.Bow.AluminievBow;
import TaskClubPoStrelba.Bow.CarbonBow;

public class StarshiStrelec extends Strelec {


    public StarshiStrelec(String nameOfStrelec, boolean isMale, int age, int opit, int teglo, int silaNaOpan) {
        super(nameOfStrelec, isMale, age, opit);
        if (teglo == 2){
            this.bow = new AluminievBow(teglo,silaNaOpan);
        }else {
            this.bow = new CarbonBow(teglo,silaNaOpan);
        }
        this.maxBroiStreli = 60; // sterlia samo s 30 streli
        this.shansZaPropusk = 5; // 5% da ne oceli
        this.MinPointForStreleca = 6; // celi ot 6 do 10
    }
}
