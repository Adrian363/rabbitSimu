/**
 * Class MonthlyPopulation
 * Class that represents a monthly population of rabbits
 */
public class MonthlyPopulation {

    private int  age;                /** Age of the rabbits                                           */
    private long rabbitNumber;       /** Number of rabbits in the population at the beginning         */
    private long aliveRabbitNumber;  /** Number of rabbits alive in the population                    */
    private long femaleNumber;       /** Number of female rabbits in the population at the beginning  */
    private long aliveFemaleNumber;  /** Number of females alive in the population                    */
    private int  monthsMaturity;     /** Number of months before maturity                             */
    private int  littersNumber;      /** Number of litters remaining in the year                      */

    /**
     * Default Constructor of the MonthlyPopulation class
     */
    public MonthlyPopulation() {
        this.age = 0;
    }

    /**
     * Constructor of the MonthlyPopulation class
     * @param age                Age of the rabbits
     * @param rabbitNumber       Number of rabbits in the population at the beginning
     * @param aliveRabbitNumber  Number of rabbits alive in the population
     * @param femaleNumber       Number of female rabbits in the population at the beginning
     * @param aliveFemaleNumber  Number of females alive in the population
     * @param monthsMaturity     Number of months before maturity
     * @param littersNumber      Number of litters remaining in the year
     */
    public MonthlyPopulation(int age, long rabbitNumber, long aliveRabbitNumber, long femaleNumber, long aliveFemaleNumber, int monthsMaturity, int littersNumber) {
        this.age = age;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = aliveRabbitNumber;
        this.femaleNumber = femaleNumber;
        this.aliveFemaleNumber = aliveFemaleNumber;
        this.monthsMaturity = monthsMaturity;
        this.littersNumber = littersNumber;
    }

    /**
     * Constructor of the MonthlyPopulation class
     * @param rabbitNumber    Number of rabbits in the population at the beginning
     * @param femaleNumber    Number of female rabbits in the population at the beginning
     * @param monthsMaturity  Number of months before maturity
     */
    public MonthlyPopulation(long rabbitNumber, long femaleNumber, int monthsMaturity) {

        this.age = 0;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = rabbitNumber;
        this.femaleNumber = femaleNumber;
        this.aliveFemaleNumber = femaleNumber;
        this.monthsMaturity = monthsMaturity;
        this.littersNumber = 0;

    }

    /**
     * Getter of the age of the rabbits
     * @return age Age of the rabbits
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter of the age of the rabbits
     * @param age Age of the rabbits
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter of the number of rabbits at the beginning
     * @return rabbitNumber Number of rabbits at the beginning
     */
    public long getRabbitNumber() {
        return rabbitNumber;
    }

    /**
     * Setter of the number of rabbits at the beginning
     * @param rabbitNumber Number of rabbits at the beginning
     */
    public void setRabbitNumber(long rabbitNumber) {
        this.rabbitNumber = rabbitNumber;
    }

    /**
     * Getter of the number of alive rabbits
     * @return aliveRabbitNumber Number of alive rabbits
     */
    public long getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    /**
     * Setter of the number of alive rabbits
     * @param aliveRabbitNumber Number of alive rabbits
     */
    public void setAliveRabbitNumber(long aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    /**
     * Getter of the number of female rabbits in the population at the beginning
     * @return femaleNumber Number of female rabbits in the population at the beginning
     */
    public long getFemaleNumber() {
        return femaleNumber;
    }

    /**
     * Setter of the number of female rabbits in the population at the beginning
     * @param femaleNumber Number of female rabbits in the population at the beginning
     */
    public void setFemaleNumber(long femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    /**
     * Getter of the number of females alive in the population
     * @return aliveFemaleNumber Number of females alive in the population
     */
    public long getAliveFemaleNumber() {
        return aliveFemaleNumber;
    }

    /**
     * Setter of the number of females alive in the population
     * @param aliveFemaleNumber Number of females alive in the population
     */
    public void setAliveFemaleNumber(long aliveFemaleNumber) {
        this.aliveFemaleNumber = aliveFemaleNumber;
    }

    /**
     * Getter of the number of months before maturity
     * @return monthsMaturity Number of months before maturity
     */
    public int getMonthsMaturity() {
        return monthsMaturity;
    }

    /**
     * Setter of the number of months before maturity
     * @param monthsMaturity Number of months before maturity
     */
    public void setMonthsMaturity(int monthsMaturity) {
        this.monthsMaturity = monthsMaturity;
    }

    /**
     * Getter of the number of litters remaining in the year
     * @return littersNumber Number of litters remaining in the year
     */
    public int getLittersNumber() {
        return littersNumber;
    }

    /**
     * Setter of the number of litters remaining in the year
     * @param littersNumber Number of litters remaining in the year
     */
    public void setLittersNumber(int littersNumber) {
        this.littersNumber = littersNumber;
    }

    /**
     * Method to evolve the subpopulation
     * @param population             Parent population
     * @param nextMonthlyPopulation  Next subpopulation
     */
    public void evolution(Population population, MonthlyPopulation nextMonthlyPopulation) {

        // Increment of the age of the rabbits
        this.age++;

        // Calculate the number of litters remaining in the year
        updateLittersNumber(population);

        // Calculate the number of dead rabbits
        updateAliveRabbits(population);

        // If at least one litter remains in the year,
        // the rabbits have reached maturity and at least one male remains in the subpopulation
        if (this.littersNumber > 0 && this.age >= this.monthsMaturity && this.aliveRabbitNumber > this.aliveFemaleNumber) {

            // We generate a new litter
            generateLitter(population, nextMonthlyPopulation);

        }

    }

    /**
     * Method to generate a new litter
     * @param population             Parent population
     * @param nextMonthlyPopulation  Next subpopulation
     */
    public void generateLitter(Population population, MonthlyPopulation nextMonthlyPopulation) {

        long newKittensNumber;
        long newFemaleKittensNumber = 0;
        long newMaleKittensNumber = 0;

        // We calculate the number of new kittens
        int kittensNumber = getMiddle(population.getMinKittens(), population.getMaxKittens());

        // We calculate the total of the number of new kittens
        newKittensNumber = (long) getApproximation(population, aliveFemaleNumber * kittensNumber,10);
        newMaleKittensNumber = (long) (getApproximation(population, population.getMaleProb(), 5) / 100 * newKittensNumber);
        newFemaleKittensNumber = newKittensNumber - newMaleKittensNumber;

        // We decrement the number of litters remaining in the year
        this.littersNumber--;

        // We update the number of rabbits in the prent population
        population.addAliveRabbits(newFemaleKittensNumber, newMaleKittensNumber, newKittensNumber);

        // We update the number of rabbits in the next subpopulation
        nextMonthlyPopulation.setRabbitNumber(newKittensNumber + nextMonthlyPopulation.getRabbitNumber());
        nextMonthlyPopulation.setAliveRabbitNumber(newKittensNumber + nextMonthlyPopulation.getAliveRabbitNumber());
        nextMonthlyPopulation.setFemaleNumber(newFemaleKittensNumber + nextMonthlyPopulation.getFemaleNumber());
        nextMonthlyPopulation.setAliveFemaleNumber(newFemaleKittensNumber + nextMonthlyPopulation.getAliveFemaleNumber());

    }

    /**
     * Method to calculate the number of litters remaining in the year
     * @param population  Parent population
     */
    public void updateLittersNumber(Population population) {

        // If the rabbits are mature since a completed year,
        // we generate the number of litters remaining in the year
        if ((this.age - this.monthsMaturity) % 12 == 0) {
            this.littersNumber = population.getPossibleLitters(getLitter(population.getCumulateProbas(), population),0);
        }

    }

    /**
     * Method to calculate the number of dead rabbits
     * @param population  Parent population
     */
    public void updateAliveRabbits(Population population) {

        // We calculate the survival probability of the rabbits
        double survivalProba = this.age >= this.monthsMaturity ? population.getAdultsSurvivalRate() : population.getKittensSurvivalRate();
        long newAliveRabbitNumber = this.aliveRabbitNumber;
        long newAliveFemaleNumber = this.aliveFemaleNumber;
        int monthsAge = age;

        // We calculate the survival probability of the rabbits if the age is greater than the number of years before least
        while (monthsAge >= 12 * (population.getYearsBeforeLeast() + 1)) {
            survivalProba -= population.getLeastProbaEachYear();
            monthsAge -= 12;
        }

        // We calculate the new number of alive rabbits
        newAliveRabbitNumber = (long) ((this.aliveRabbitNumber - this.aliveFemaleNumber) * getApproximation(population,  Math.pow((survivalProba / 100), (double) 1/12), 2));
        newAliveFemaleNumber = (long) (this.aliveFemaleNumber * getApproximation(population,  Math.pow((survivalProba / 100), (double) 1/12), 2));

        // We update the number of rabbits in the parent population
        population.updateAliveRabbitsPop((this.aliveFemaleNumber - newAliveFemaleNumber), (this.aliveRabbitNumber - (newAliveRabbitNumber + newAliveFemaleNumber)));

        // We update the number of rabbits in the subpopulation
        this.aliveRabbitNumber = newAliveRabbitNumber + newAliveFemaleNumber;
        this.aliveFemaleNumber = newAliveFemaleNumber;

        // If all the rabbits are dead
        if (this.aliveRabbitNumber <= 0) {

            // We delete the subpopulation of the parent population list
            population.getPopulations().remove(this);

        }

    }


    /**
     * Method to return a litter depending on the cumulate probabilities
     * @param cumulateProbas  Cumulated probabilities for litters
     * @param population      Parent population
     * @return litter The generated number of litters
     */
    public int getLitter(int[] cumulateProbas, Population population) {

        // We generate a random number between 0 and 100
        double random = population.rand(0,100);
        int j = 0;

        // We search the litter corresponding to the random number
        while(random > cumulateProbas[j]) {
            j++;
        }

        return j;

    }

    /**
     * Method to return the approximation of a number
     * @param population  Parent population
     * @param d           Number to approximate
     * @param percentage  Percentage of approximation
     * @return approximation The approximation of the number
     */
    public double getApproximation(Population population, double d, int percentage) {
        return population.rand(d - (d * percentage / 100),  d + (d * percentage / 100));
    }

    /**
     * Method to return the middle of two numbers
     * @param a First number
     * @param b Second number
     * @return middle The middle of the two numbers
     */
    public int getMiddle(int a, int b) {
        return (a + b) / 2;
    }

    @Override
    /**
     * Method to display the population
     * @return String The string to display
     */
    public String toString() {
        return "MonthlyPopulation {" +
                "age = " + age +
                ", rabbitNumber = " + rabbitNumber +
                ", aliveRabbitNumber = " + aliveRabbitNumber +
                ", femaleNumber = " + femaleNumber +
                ", aliveFemaleNumber = " + aliveFemaleNumber +
                ", monthsMaturity = " + monthsMaturity +
                ", littersNumber = " + littersNumber +
                " }";
    }

}
