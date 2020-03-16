package TaskClubPoStrelba.Bow;

public class DarvenBow extends Bow {

    public DarvenBow(double tegloOfBow, int silaNaOpan) {
        super(tegloOfBow, silaNaOpan);
    }

    @Override
    protected int getMinSilaNaOpan() {
        return 20;
    }

    @Override
    protected int getMaxSilaNaOpan() {
        return 30;
    }

    @Override
    protected int getAveragSilaNaOpan() {
        return (getMinSilaNaOpan() + getMaxSilaNaOpan()) / 2;
    }

    @Override
    public int getBonusMernik() {
        return 0;
    }

}
