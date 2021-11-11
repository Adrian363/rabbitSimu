import java.sql.SQLOutput;

public class PopulationManager {

    public static void main(String[] args) {

        int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };
        int yearsSimulation = 12;

        Population rabbitPopulation = new Population(10,10,3,6,50,4,8,60,35,10,10,possibleLitters);

        for (int i = 0 ; i < yearsSimulation * 12 ; i++) {
            rabbitPopulation.evolution();
        }

        /*

        for(int i = 0; i < 13 ; i++) {

            rabbitPopulation.evolution();
            System.out.println(rabbitPopulation.toString());
            for (MonthlyPopulation mp : rabbitPopulation.getPopulations()) {
                System.out.println(mp.toString());
            }
            System.out.println("----------------------------------------");
        }

        */

        System.out.println(rabbitPopulation.toString());

    }

}
