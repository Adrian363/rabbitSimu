import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Class Population
 * This class represents a population of rabbits
 */
public class Population {

    private int                          age;                   /** Age of the population                          */
    private long                         aliveRabbitNumber;     /** Number of rabbits alive                        */
    private long                         femaleNumber;          /** Number of female rabbits                       */
    private long                         maleNumber;            /** Number of male rabbits                         */
    private int                          minKittens;            /** Number of minimum kittens in a litter          */
    private int                          maxKittens;            /** Number of maximum kittens in a litter          */
    private int                          minSexualMaturity;     /** Minimum age to get maturity                    */
    private int                          maxSexualMaturity;     /** Maximum age to get maturity                    */
    private double                       adultsSurvivalRate;    /** Survival rate of adults                        */
    private double                       kittensSurvivalRate;   /** Survival rate of kittens                       */
    private int                          yearsBeforeLeast;      /** Number of years before the least               */
    private int                          leastProbaEachYear;    /** Probability of the least appearing each year   */
    private int  []                      cumulateProbas;        /** Cumulated probabilities for litters            */
    private int [][]                     possibleLitters;       /** Possible litter probas for each amount litter  */
    private double                       maleProb;              /** Probability of giving birth to a male          */
    private MersenneTwister              random;                /** Random generator                               */
    private ArrayList<MonthlyPopulation> populations;           /** List of all subpopulations                     */

    /**
     * Default Constructor of a population
     */
    public Population() {
        this.age = 0;
        this.random = new MersenneTwister();
        this.populations = new ArrayList<MonthlyPopulation>();
        random.setSeed(123456789);
    }

    /**
     * Constructor of a population
     * @param femaleNumber         Number of female rabbits
     * @param maleNumber           Number of male rabbits
     * @param minKittens           Number of minimum kittens in a litter
     * @param maxKittens           Number of maximum kittens in a litter
     * @param maleProb             Probability of giving birth to a male
     * @param minSexualMaturity    Minimum age to get maturity
     * @param maxSexualMaturity    Maximum age to get maturity
     * @param adultsSurvivalRate   Survival rate of adults
     * @param kittensSurvivalRate  Survival rate of kittens
     * @param yearsBeforeLeast     Number of years before the least
     * @param leastProbaEachYear   Probability of the least appearing each year
     * @param possibleLitters      Possible litter probas for each amount litter
     * @param random               Random generator
     */
    public Population(long femaleNumber, long maleNumber, int minKittens, int maxKittens, double maleProb, int minSexualMaturity, int maxSexualMaturity, double adultsSurvivalRate, double kittensSurvivalRate, int yearsBeforeLeast, int leastProbaEachYear, int[][] possibleLitters, MersenneTwister random ) {

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
        this.random = random;
        this.populations = new ArrayList<>();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
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

    /**
     * Getter of the age
     * @return age Age of the population
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter of the age
     * @param age Age of the population
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter of the alive rabbits number
     * @return aliveRabbitNumber Number of alive rabbits
     */
    public long getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    /**
     * Setter of the alive rabbits number
     * @param aliveRabbitNumber Number of alive rabbits
     */
    public void setAliveRabbitNumber(long aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    /**
     * Getter of the female number
     * @return femaleNumber Number of female rabbits
     */
    public long getFemaleNumber() {
        return femaleNumber;
    }

    /**
     * Setter of the female number
     * @param femaleNumber Number of female rabbits
     */
    public void setFemaleNumber(long femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    /**
     * Getter of the male number
     * @return maleNumber Number of male rabbits
     */
    public long getMaleNumber() {
        return maleNumber;
    }

    /**
     * Setter of the male number
     * @param maleNumber Number of male rabbits
     */
    public void setMaleNumber(long maleNumber) {
        this.maleNumber = maleNumber;
    }

    /**
     * Getter of the number of minimum kittens in a litter
     * @return minKittens Number of minimum kittens in a litter
     */
    public int getMinKittens() {
        return minKittens;
    }

    /**
     * Setter of the number of minimum kittens in a litter
     * @param minKittens Number of minimum kittens in a litter
     */
    public void setMinKittens(int minKittens) {
        this.minKittens = minKittens;
    }

    /**
     * Getter of the number of maximum kittens in a litter
     * @return maxKittens Number of maximum kittens in a litter
     */
    public int getMaxKittens() {
        return maxKittens;
    }

    /**
     * Setter of the number of maximum kittens in a litter
     * @param maxKittens Number of maximum kittens in a litter
     */
    public void setMaxKittens(int maxKittens) {
        this.maxKittens = maxKittens;
    }

    /**
     * Getter of the minimum age to get maturity
     * @return minSexualMaturity Minimum age to get maturity
     */
    public int getMinSexualMaturity() {
        return minSexualMaturity;
    }

    /**
     * Setter of the minimum age to get maturity
     * @param minSexualMaturity Minimum age to get maturity
     */
    public void setMinSexualMaturity(int minSexualMaturity) {
        this.minSexualMaturity = minSexualMaturity;
    }

    /**
     * Getter of the maximum age to get maturity
     * @return maxSexualMaturity Maximum age to get maturity
     */
    public int getMaxSexualMaturity() {
        return maxSexualMaturity;
    }

    /**
     * Setter of the maximum age to get maturity
     * @param maxSexualMaturity Maximum age to get maturity
     */
    public void setMaxSexualMaturity(int maxSexualMaturity) {
        this.maxSexualMaturity = maxSexualMaturity;
    }

    /**
     * Getter of the survival rate of adults
     * @return adultSurvivalRate Survival rate of adults
     */
    public double getAdultsSurvivalRate() {
        return adultsSurvivalRate;
    }

    /**
     * Setter of the survival rate of adults
     * @param adultsSurvivalRate Survival rate of adults
     */
    public void setAdultsSurvivalRate(int adultsSurvivalRate) {
        this.adultsSurvivalRate = adultsSurvivalRate;
    }

    /**
     * Getter of the survival rate of kittens
     * @return kittensSurvivalRate Survival rate of kittens
     */
    public double getKittensSurvivalRate() {
        return kittensSurvivalRate;
    }

    /**
     * Setter of the survival rate of kittens
     * @param kittensSurvivalRate Survival rate of kittens
     */
    public void setKittensSurvivalRate(int kittensSurvivalRate) {
        this.kittensSurvivalRate = kittensSurvivalRate;
    }

    /**
     * Getter of the probability of giving birth to a male
     * @return maleProb Probability of giving birth to a male
     */
    public double getMaleProb() {
        return maleProb;
    }

    /**
     * Setter of the probability of giving birth to a male
     * @param maleProb Probability of giving birth to a male
     */
    public void setMaleProb(double maleProb) {
        this.maleProb = maleProb;
    }

    /**
     * Getter of the number of years before the least
     * @return yearsBeforeLeast Number of years before the least
     */
    public int getYearsBeforeLeast() {
        return this.yearsBeforeLeast;
    }

    /**
     * Setter of the number of years before the least
     * @param yearsBeforeLeast Number of years before the least
     */
    public void setYearsBeforeLeast(int yearsBeforeLeast) {
        this.yearsBeforeLeast = yearsBeforeLeast;
    }

    /**
     * Getter of the probability of the least appearing each year
     * @return leastProb Probability of the least appearing each year
     */
    public int  getLeastProbaEachYear() {
        return this.leastProbaEachYear;
    }

    /**
     * Setter of the probability of the least appearing each year
     * @param leastProbaEachYear Probability of the least appearing each year
     */
    public void setLeastProbaEachYear(int leastProbaEachYear) {
        this.leastProbaEachYear = leastProbaEachYear;
    }

    /**
     * Getter of the random number generator
     * @return random Random number generator
     */
    public MersenneTwister getRandom() {
        return random;
    }

    /**
     * Setter of the random number generator
     * @param random Random number generator
     */
    public void setRandom(MersenneTwister random) {
        this.random = random;
    }

    /**
     * Getter of the list of all subpopulations
     * @return populations List of all subpopulations
     */
    public ArrayList<MonthlyPopulation> getPopulations() {
        return populations;
    }

    /**
     * Setter of the list of all subpopulations
     * @param populations List of all subpopulations
     */
    public void setPopulations(ArrayList<MonthlyPopulation> populations) {
        this.populations = populations;
    }

    /**
     * Getter of the cumulated probabilities for litters
     * @return cumulatedProbabilities Cumulated probabilities for litters
     */
    public int[] getCumulateProbas() {
        return this.cumulateProbas;
    }

    /**
     * Setter of the cumulated probabilities for litters
     * @param cumulateProbas Cumulated probabilities for litters
     */
    public void setCumulateProbas(int[] cumulateProbas) {
        this.cumulateProbas = cumulateProbas;
    }

    /**
     * Getter of a specific possibleLitters
     * @return possibleLitters[i][j] Possible litters asked for litter i
     */
    public int getPossibleLitters(int i , int j) {
        return this.possibleLitters[i][j] ;
    }

    /**
     * Getter of the number of possible litters table
     * @return possibleLitters Number of possible litters table
     */
    public int [][] getPossibleLittersTab() {
        return this.possibleLitters ;
    }

    /**
     * Method to set the number of alive rabbits in the population
     * @param deathFemale  Number of dead female
     * @param deathRabbits Number of dead rabbits
     */
    public void updateAliveRabbitsPop(long deathFemale, long deathRabbits) {

        try {

            // If all the dead numbers of rabbits are lower than the actual number of rabbits
            if((this.femaleNumber - deathFemale < 0) || ( this.maleNumber - (deathRabbits - deathFemale) < 0  ) || (this.aliveRabbitNumber - deathRabbits < 0)) {
                throw new IllegalArgumentException();
            } else {

                // Update the number of rabbits
                this.femaleNumber       -= deathFemale;
                this.maleNumber         -= (deathRabbits - deathFemale);
                this.aliveRabbitNumber  -= deathRabbits;

            }

        } catch (IllegalArgumentException e) {
            System.err.println("The number of death rabbit is too high.");
        }

    }

    /**
     * Method to set the number of alive rabbits in the population
     * @param newFemaleNumber   Number of new female rabbits
     * @param newMaleNumber     Number of new male rabbits
     * @param newRabbitsNumber  Number of new rabbits
     */
    public void addAliveRabbits(long newFemaleNumber, long newMaleNumber, long newRabbitsNumber ){

        // Update all the rabbits numbers
        this.maleNumber        += newMaleNumber;
        this.femaleNumber      += newFemaleNumber;
        this.aliveRabbitNumber += newRabbitsNumber;

    }

    /**
     * Method to calculate the cumulated probabilities for litters
     * @param possibleLitters Possible litter probas for each amount litter
     * @return cumulatedProbas Cumulated probabilities for litters
     */
    public int[] getCumulateLittersProbas(int[][] possibleLitters) {

        int[] cumulateProbas = new int[possibleLitters.length];
        int cumulateProba = 0;

        // For each litter possibility
        for (int i = 0 ; i < possibleLitters.length ; i++) {
            cumulateProba += possibleLitters[i][1];
            cumulateProbas[i] = cumulateProba;
        }

        return cumulateProbas;

    }

    /**
     * Method to initialize the population with a new MonthlyPopulation
     */
    public void initPopulation() {

        // Initialize the population
        MonthlyPopulation monthlyPopulation = new MonthlyPopulation((int) this.aliveRabbitNumber, (int) this.femaleNumber, (int) this.rand(this.getMinSexualMaturity(), this.getMaxSexualMaturity() + 1));

        // Add the population to the list of subpopulations
        this.populations.add(monthlyPopulation);

    }

    /**
     * Method to evolve the population
     */
    public void evolution() {

        // Increment the age of the population
        this.age++;

        // Create a new subpopulation
        MonthlyPopulation nextMonthlyPopulation = new MonthlyPopulation(0,0, (int) this.rand(this.getMinSexualMaturity(), this.getMaxSexualMaturity() + 1));

        // For each subpopulation
        for (int i = 0; i < populations.size() ; i++) {

            // Get the current subpopulation
            MonthlyPopulation mp = populations.get(i);

            // Evolve the subpopulation
            mp.evolution(this, nextMonthlyPopulation);

        }

        // If the subpopulation is not empty, add it to the list
        if (nextMonthlyPopulation.getRabbitNumber() > 0) {
            this.populations.add(nextMonthlyPopulation);
        }

    }

    /**
     * Method to display the population
     * @param populationNumber
     */
    public void detailsDisplay(int populationNumber){

        System.out.println("----------------------------------------");
        System.out.println("Population n°"+ populationNumber + " after " + this.age + " months");

        // If the population is not empty
        if (this.aliveRabbitNumber > 0) {

            System.out.println("Number of alive rabbits: " + this.aliveRabbitNumber);
            System.out.println("Number of alive females: " + this.femaleNumber + " Percentage: " + ((float)this.femaleNumber/this.aliveRabbitNumber) * 100 + "%");
            System.out.println("Number of alive males: " + this.maleNumber + " Percentage: " + ((float)this.maleNumber/this.aliveRabbitNumber) * 100 + "%");

        } else {
            System.out.println("All the rabbits in the population are dead !");
        }

    }

    @Override
    /**
     * Method to display the population
     * @return String The string to display
     */
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
