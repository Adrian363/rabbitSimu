import java.sql.Timestamp;
import java.util.ArrayList;

public class Population {

    private int      age;
    private long      aliveRabbitNumber;
    private long      femaleNumber;
    private long      maleNumber;
    private int      minKittens;
    private int      maxKittens;
    private int      minSexualMaturity;
    private int      maxSexualMaturity;
    private double      adultsSurvivalRate;
    private double      kittensSurvivalRate;
    private int      yearsBeforeLeast;
    private int      leastProbaEachYear;
    private int  []  cumulateProbas;
    private int [][] possibleLitters;


    private double  maleProb;
    private MersenneTwister random;

    private ArrayList<MonthlyPopulation> populations;

    public Population() {
        //this.cumulateProbas =  getCumulateLittersProbas(possibleLitters);
        this.age = 0;
        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();
        random.setSeed(123456789);
    }

    public Population(long femaleNumber, long maleNumber, int minKittens, int maxKittens, double maleProb,
                      int minSexualMaturity, int maxSexualMaturity, double adultsSurvivalRate,
                      double kittensSurvivalRate,
                      int yearsBeforeLeast, int leastProbaEachYear, int[][] possibleLitters ) {

        this.age                    = 0;
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
        this.possibleLitters        = possibleLitters;
        this.cumulateProbas         = getCumulateLittersProbas(possibleLitters);

        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        random.setSeed(timestamp.getTime());
        initPopulation();
    }

    /**
     * Generation of a random number between min and max
     * @param min Minimum value
     * @param max Maximum value
     * @return Random number between min and max
     */
    public double rand(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    public void setAliveRabbitNumber(long aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    public long getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(long femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    public long getMaleNumber() {
        return maleNumber;
    }

    public void setMaleNumber(long maleNumber) {
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

    public double getAdultsSurvivalRate() {
        return adultsSurvivalRate;
    }

    public void setAdultsSurvivalRate(int adultsSurvivalRate) {
        this.adultsSurvivalRate = adultsSurvivalRate;
    }

    public double getKittensSurvivalRate() {
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

    public void updateAliveRabbitsPop(long deathFemale, long deathRabbits) {

        try {

            if((this.femaleNumber - deathFemale < 0) || ( this.maleNumber - (deathRabbits - deathFemale) < 0  ) || (this.aliveRabbitNumber - deathRabbits < 0)) {
                throw new IllegalArgumentException();
            } else {

                this.femaleNumber       -= deathFemale;
                this.maleNumber         -= (deathRabbits - deathFemale);
                this.aliveRabbitNumber  -= deathRabbits;

            }
        } catch (IllegalArgumentException e) {
            System.err.println("The number of death rabbit is too high.");
        }

    }

    public void addAliveRabbits(long newFemaleNumber, long newMaleNumber, long newRabbitsNumber ){

        this.maleNumber        += newMaleNumber;
        this.femaleNumber      += newFemaleNumber;
        this.aliveRabbitNumber += newRabbitsNumber;

        // System.out.println( this.toString() );
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

    public void initPopulation() {

        MonthlyPopulation monthlyPopulation = new MonthlyPopulation((int) this.aliveRabbitNumber, (int) this.femaleNumber, (int) this.rand(this.getMinSexualMaturity(), this.getMaxSexualMaturity() + 1));
        this.populations.add(monthlyPopulation);

    }

    public void evolution() {

        this.age++;
        MonthlyPopulation nextMonthlyPopulation = new MonthlyPopulation(0,0, (int) this.rand(this.getMinSexualMaturity(), this.getMaxSexualMaturity() + 1));

        for (int i = 0; i < populations.size() ; i++) {
            MonthlyPopulation mp = populations.get(i);
            mp.evolution(this, nextMonthlyPopulation);
        }

        if (nextMonthlyPopulation.getRabbitNumber() > 0) {
            this.populations.add(nextMonthlyPopulation);
        }

    }

    @Override
    public String toString() {
        return "Population { " +
               "age = " + this.age +
               ", aliveRabbitNumber = " + this.aliveRabbitNumber +
               ", femaleNumber = " + this.femaleNumber +
               ", maleNumber = " + this.maleNumber +
               ", nbMonthlyPopulation = " + this.populations.size() +
               "  }";
    }
}
