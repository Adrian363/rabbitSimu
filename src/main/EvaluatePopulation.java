/**
 * Class EvaluatePopulation
 * Allows the evaluation of the population
 */
public class EvaluatePopulation {

    private int      yearsSimulation;       /** Number of years to simulate                     */
    private int      nbPopSim;              /** Number of populations to simulate               */
    private int      femaleNumberBeginning; /** Number of female at the begining                */
    private int maleNumberBeginning;        /** Number of male at the begining                  */
    private int [][] possibleLitters;       /** Possible litter probas for each amount litter   */
    private double   averageRabbitsEnd;     /** Number of rabbits at the end of the simulation  */
    private MersenneTwister random;         /** Random generator                                */

    /**
     * Constructor of the class EvaluatePopulation
     * @param yearsSimulation       Number of years to simulate
     * @param nbPopSim              Number of populations to simulate
     * @param femaleNumberBegining  Number of female at the begining
     * @param maleNumberBegining    Number of male at the begining
     * @param possibleLitters       Possible litter probas for each amount litter
     * @param random                Random generator
     */
    public EvaluatePopulation(int yearsSimulation, int nbPopSim, int femaleNumberBegining, int maleNumberBegining, int [][] possibleLitters, MersenneTwister random){

        this.yearsSimulation = yearsSimulation;
        this.nbPopSim = nbPopSim;
        this.femaleNumberBeginning = femaleNumberBegining;
        this.maleNumberBeginning = maleNumberBegining;
        this.possibleLitters = possibleLitters;
        this.random = random;

    }

    /**
     * Setting the number of rabbits at the end of the simulation
     * @param averageRabbitsEnd Number of rabbits at the end of the simulation
     */
    public void setAverageRabbitsEnd(double averageRabbitsEnd) {
        this.averageRabbitsEnd = averageRabbitsEnd;
    }

    /**
     * Getter of the the number of rabbits at the end of the simulation
     * @return averageRabbitsEnd Number of rabbits at the end of the simulation
     */
    public double getAverageRabbitsEnd() {
        return this.averageRabbitsEnd;
    }

    /**
     * Method to evaluate the population
     */
    public void simulationPop(){

        // For each population
        for (int i = 0 ;  i < this.nbPopSim ; i++) {

            // Initialize the population
            Population population = new Population(this.femaleNumberBeginning, this.maleNumberBeginning, 3,6,50,4,8,60,35,10,10, this.possibleLitters, random);

            // For each month, evolve the population
            for(int j = 0 ; j < this.yearsSimulation * 12 ; j++) {
                    population.evolution();
            }

            // Display the population
            population.detailsDisplay(i);

            // Update the average number of rabbits at the end of the simulation
            this.averageRabbitsEnd += (double) population.getAliveRabbitNumber();

        }

        // Calculate the average number of rabbits at the end of the simulation
        this.averageRabbitsEnd /= nbPopSim;

        // Display the average number of rabbits at the end of the simulation
        System.out.println("\nAverage number of rabbits after " + this.yearsSimulation + " years: " + this.averageRabbitsEnd);

    }

}
