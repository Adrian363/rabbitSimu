import java.util.ArrayList;

public class Population {

    private int      aliveRabbitNumber;
    private int      femaleNumber;
    private int      maleNumber;
    private int      minKittens;
    private int      maxKittens;
    private int      minSexualMaturity;
    private int      maxSexualMaturity;
    private int      adultsSurvivalRate;
    private int      kittensSurvivalRate;
    private int      yearsBeforeLeast;
    private int      leastProbaEachYear;
    private int  []  cumulateProbas;
    private int [][] possibleLitters  = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };


    private double  maleProb;
    private MersenneTwister random;

    private ArrayList<MonthlyPopulation> populations;

    public Population() {
        this.cumulateProbas =  getCumulateLittersProbas(possibleLitters);
        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();
        random.setSeed(123456789);
    }

    public Population(int femaleNumber, int maleNumber, int minKittens, int maxKittens, double maleProb,
                      int minSexualMaturity, int maxSexualMaturity, int adultsSurvivalRate, int kittensSurvivalRate,
                      int yearsBeforeLeast, int leastProbaEachYear ) {

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
        this.yearsBeforeLeast       = yearsBeforeLeast;
        this.leastProbaEachYear     = leastProbaEachYear;
        this.cumulateProbas         = getCumulateLittersProbas(possibleLitters);

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

    public int getYearsBeforeLeast() { return this.yearsBeforeLeast; }

    public void setYearsBeforeLeast(int yearsBeforeLeast) { this.yearsBeforeLeast = yearsBeforeLeast; }

    public int  getLeastProbaEachYear() { return this.leastProbaEachYear; }

    public void setLeastProbaEachYear(int leastProbaEachYear) { this.leastProbaEachYear = leastProbaEachYear; }

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

    public int[] getCumulateProbas(){ return this.cumulateProbas; }

    public int getPossibleLitters( int i , int j) { return this.possibleLitters[i][j] ;}

    public int [][] getPossibleLittersTab(){ return this.possibleLitters ;}

    public void updateAliveRabbitsPop(int deathFemale, int deathRabbits){

        this.femaleNumber       -= deathFemale;
        this.maleNumber         -= deathRabbits - deathFemale;
        this.aliveRabbitNumber  -= deathRabbits;
    }

    public int[] getCumulateLittersProbas(int[][] possibleLitters) {

        int[] cumulateProbas = new int[possibleLitters.length];
        int cumulateProba = 0;

        for (int i = 0 ; i < possibleLitters.length ; i++) {
                                cumulateProba += possibleLitters[i][1];
            cumulateProbas[i] = cumulateProba;
        }

        return cumulateProbas;

    }

    @Override
    public String toString() {
        return "Population{" +
               "aliveRabbitNumber=" + this.aliveRabbitNumber +
               ", femaleNumber" + this.femaleNumber +
               ", maleNumber=" + this.maleNumber +
               ", femaleNumber=" + femaleNumber +
               '}';
    }
}
