package TaskClubPoStrelba.Bow;

public class AluminievBow extends Bow {

    protected int bonusMernik = 1; // plus 1 tochka za mernik

    public AluminievBow(double tegloOfBow, int silaNaOpan) {
        super(tegloOfBow, silaNaOpan);
    }

    @Override
    protected int getMinSilaNaOpan() {
        return 25;
    }

    @Override
    protected int getMaxSilaNaOpan() {
        return 40;
    }

    @Override
    protected int getAveragSilaNaOpan() {
        return (getMinSilaNaOpan() + getMaxSilaNaOpan()) / 2;
    }

    public int getBonusMernik(){
        return this.bonusMernik;
    }
}
