package TaskClubPoStrelba;

import TaskClubPoStrelba.Strelec.MladshiStrelec;
import TaskClubPoStrelba.Strelec.StarshiStrelec;
import TaskClubPoStrelba.Strelec.Strelec;
import TaskClubPoStrelba.Strelec.VeteranStrelec;

import java.util.Random;

public class DemoClubArcher {

    public static final int BROQT_NA_STRELCITE_KOITO_TRQBVA_DA_SE_DOBAVQT = 40;

    public static void main(String[] args) {

        ClubPoStrelba iTTalanti = new ClubPoStrelba("IT Archar", "j.k.\"Borovo\"");
        ClubPoStrelba.nameOfTreniuor = "Krasimir Stoev";

        String[] maleName = {"Vasko","Vesko","Borko","Toni","Pesho","Misho","Tosho","Gosho","Nikola","Zaprqn"};
        String[] femaleName = {"Veska","Veronika","Borqna","Tedi","Pepi","Moni","Teodora","Gina","Nina","Zara"};
        Random roll = new Random();
        for (int i = 0; i < BROQT_NA_STRELCITE_KOITO_TRQBVA_DA_SE_DOBAVQT; i++) {
            String   nameOfStrelec = ""; // imenata mogat da budat 10 majki i 10 jenski
            boolean isMale = true; // pola e maj ili jena
            int age = 0; // godinite sa ot 12 do 52
            int opit = 0; // opita e ot 1 do 40, izchislqva se: godinite - 11, za da se poluchi nai-malko resultat 1
            int teglo = 0; // teglo e 1 za carbonov lak, 2 za aluminiev lak i 3 za darven lak
            int silaNaOpan = 0; // chislo mejdu 20 i 48
            isMale = roll.nextBoolean();
            if (isMale){
                nameOfStrelec = maleName[roll.nextInt(9)];
            }else {
                nameOfStrelec = femaleName[roll.nextInt(9)];
            }
            age = roll.nextInt(39) + 12;
            opit = age - 11;
            int shansZaVidStrelec = roll.nextInt(100);
            Strelec strelec = null;
            if (shansZaVidStrelec < 33){
                teglo = 3;
                silaNaOpan = roll.nextInt(10) + 21;
                strelec = new MladshiStrelec(nameOfStrelec,isMale,age,opit,teglo,silaNaOpan);
            }else {
                if (shansZaVidStrelec < 66){
                    if (roll.nextBoolean()){
                        teglo = 1; // za carbonov lak
                        silaNaOpan = roll.nextInt(20) + 29;
                    }else {
                        teglo = 2; // za aluminiev lak
                        silaNaOpan = roll.nextInt(15) + 26;
                    }
                    strelec = new StarshiStrelec(nameOfStrelec,isMale,age,opit,teglo,silaNaOpan);
                }else {
                    teglo = 1;
                    silaNaOpan = roll.nextInt(20) + 29;
                    strelec = new VeteranStrelec(nameOfStrelec,isMale,age,opit,teglo,silaNaOpan);
                }
            }
            iTTalanti.strelci.add(strelec);
        }
        iTTalanti.strartNaSastezanieto();
    }
}
