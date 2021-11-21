import java.sql.SQLOutput;

public class PopulationManager {

    public static void main(String[] args) {

        int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };
        int     yearsSimulation = 10;

        MersenneTwister random = new MersenneTwister();
        random.setSeed(123456789);

        //Population rabbitPopulation = new Population(10,10, 3,6,50,4,8,60,35,10,10,possibleLitters, random);

        /*
        for (int i = 0 ; i < yearsSimulation * 12 ; i++) {
            rabbitPopulation.evolution();
        }*/

        //System.out.println(rabbitPopulation.toString());
        /*
        for(int i = 0; i < 36 ; i++) {

            rabbitPopulation.evolution();

            rabbitPopulation.detailsDisplay();
            //System.out.println(rabbitPopulation.toString());

            //System.out.println("----------------------------------------");

        }*/

        EvaluatePopulation evalPop = new EvaluatePopulation(1, 50,10, 10,possibleLitters, random);
        evalPop.simulationPop();





    }

}
