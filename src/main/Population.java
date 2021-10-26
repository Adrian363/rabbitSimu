import java.util.ArrayList;

public class Population {

    private int     aliveRabbitNumber;
    private int     femaleNumber;
    private int     maleNumber;
    private int     minKittens;
    private int     maxKittens;
    private int     minSexualMaturity;
    private int     maxSexualMaturity;
    private int     adultsSurvivalRate;
    private int     kittensSurvivalRate;
    private double  maleProb;
    private MersenneTwister random;

    private ArrayList<MonthlyPopulation> populations;

    public Population() {
        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();
        random.setSeed(123456789);
    }

    public Population(int femaleNumber, int maleNumber, int minKittens, int maxKittens, double maleProb,
                      int minSexualMaturity, int maxSexualMaturity, int adultsSurvivalRate, int kittensSurvivalRate) {

        this.femaleNumber           = femaleNumber;
        this.maleNumber             = maleNumber;
        this.aliveRabbitNumber      = maleNumber + femaleNumber;
        this.minKittens             = minKittens;
        this.maxKittens             = maxKittens;
        this.maleProb               = maleProb;
        this.minSexualMaturity      = minSexualMaturity;
        this.maxSexualMaturity      = maxSexualMaturity;
        this.adultsSurvivalRate     = adultsSurvivalRate;
        this.kittensSurvivalRate    = kittensSurvivalRate;

        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();
        random.setSeed(123456789);
    }

    /**
     * Generation of a random number between min and max
     * @param min Minimum value
     * @param max Maximum value
     * @return Random number between min and max
     */
    public double rand(int min, int max) {
        return min + (max - min) * random.nextDouble();
    }

    public int getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    public void setAliveRabbitNumber(int aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    public int getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(int femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    public int getMaleNumber() {
        return maleNumber;
    }

    public void setMaleNumber(int maleNumber) {
        this.maleNumber = maleNumber;
    }

    public int getMinKittens() {
        return minKittens;
    }

    public void setMinKittens(int minKittens) {
        this.minKittens = minKittens;
    }

    public int getMaxKittens() {
        return maxKittens;
    }

    public void setMaxKittens(int maxKittens) {
        this.maxKittens = maxKittens;
    }

    public int getMinSexualMaturity() {
        return minSexualMaturity;
    }

    public void setMinSexualMaturity(int minSexualMaturity) {
        this.minSexualMaturity = minSexualMaturity;
    }

    public int getMaxSexualMaturity() {
        return maxSexualMaturity;
    }

    public void setMaxSexualMaturity(int maxSexualMaturity) {
        this.maxSexualMaturity = maxSexualMaturity;
    }

    public int getAdultsSurvivalRate() {
        return adultsSurvivalRate;
    }

    public void setAdultsSurvivalRate(int adultsSurvivalRate) {
        this.adultsSurvivalRate = adultsSurvivalRate;
    }

    public int getKittensSurvivalRate() {
        return kittensSurvivalRate;
    }

    public void setKittensSurvivalRate(int kittensSurvivalRate) {
        this.kittensSurvivalRate = kittensSurvivalRate;
    }

    public double getMaleProb() {
        return maleProb;
    }

    public void setMaleProb(double maleProb) {
        this.maleProb = maleProb;
    }

    public MersenneTwister getRandom() {
        return random;
    }

    public void setRandom(MersenneTwister random) {
        this.random = random;
    }

    public ArrayList<MonthlyPopulation> getPopulations() {
        return populations;
    }

    public void setPopulations(ArrayList<MonthlyPopulation> populations) {
        this.populations = populations;
    }

}
