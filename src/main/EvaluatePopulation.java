public class EvaluatePopulation {

        private int      yearsSimulation ;
        private int      nbPopSim;
        private int      femaleNumberBegining;
        private int      maleNumberBegining;
        private int [][] possibleLitters;

        private double   averageRabbitsEnd;

        private MersenneTwister random;



        public EvaluatePopulation(int yearsSimulation, int nbPopSim, int femaleNumberBegining, int maleNumberBegining
                , int [][] possibleLitters, MersenneTwister random){

                this.yearsSimulation = yearsSimulation;
                this.nbPopSim = nbPopSim;
                this.femaleNumberBegining = femaleNumberBegining;
                this.maleNumberBegining = maleNumberBegining;
                this.possibleLitters = possibleLitters;
                this.random = random;

        }

        public void setAverageRabbitsEnd(double averageRabbitsEnd){

                this.averageRabbitsEnd = averageRabbitsEnd;

        }

        public double getAverageRabbitsEnd(){

                return this.averageRabbitsEnd;

        }

        public void simulationPop(){

                for (int i = 0;  i < this.nbPopSim; i++){

                        Population population = new Population(this.femaleNumberBegining, this.maleNumberBegining, 3,6,50,4,8,60,35,10,10,
                                                               this.possibleLitters, random);


                        for(int j = 0; j < this.yearsSimulation * 12; j++){

                                population.evolution();

                        }

                        population.detailsDisplay(i);
                        this.averageRabbitsEnd += (double) population.getAliveRabbitNumber();

                }

                this.averageRabbitsEnd /= nbPopSim;
                System.out.println("\nAverage number of rabbits after " + this.yearsSimulation + " years: " + this.averageRabbitsEnd);

        }
}
