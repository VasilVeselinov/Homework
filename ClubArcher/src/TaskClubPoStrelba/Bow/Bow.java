package TaskClubPoStrelba.Bow;

public abstract class Bow {

    protected static final String proizvoditelOfBow = "ITOlimpiec"; // Ime na proizvoditelq na Bows
    public double tegloOfBow; // teglo na TaskClubPoStrelba.Bow
    public int silaNaOpan; // sila na opan na TaskClubPoStrelba.Bow

    protected Bow(double tegloOfBow, int silaNaOpan) {
        this.tegloOfBow = tegloOfBow;
        if (silaNaOpan >= getMinSilaNaOpan() && silaNaOpan <= getMaxSilaNaOpan()) {
            this.silaNaOpan = silaNaOpan;
        }else {
            this.silaNaOpan = getAveragSilaNaOpan();
        }
    }

    protected abstract int getMinSilaNaOpan();
    protected abstract int getMaxSilaNaOpan();
    protected abstract int getAveragSilaNaOpan();

    public abstract int getBonusMernik();

    @Override
    public String toString() {
        return "Bow{" +
                "tegloOfBow=" + tegloOfBow +
                ", silaNaOpan=" + silaNaOpan +
                '}';
    }
}
