package TaskClubPoStrelba.Bow;

public class CarbonBow extends AluminievBow {

    protected int bonusMernik = super.bonusMernik + 1; // plus 2 tochki za mernik
    protected  int bonusStabilizator = 1; // plus 1 tochka za stabilizator

    public CarbonBow(double tegloOfBow, int silaNaOpan) {
        super(tegloOfBow, silaNaOpan);
    }

    @Override
    protected int getMinSilaNaOpan() {
        return 28;
    }

    @Override
    protected int getMaxSilaNaOpan() {
        return 48;
    }

    @Override
    protected int getAveragSilaNaOpan() {
        return (getMinSilaNaOpan() + getMaxSilaNaOpan()) / 2;
    }

    public int getBonusMernik(){
        return this.bonusMernik + this.bonusStabilizator;
    }
}
