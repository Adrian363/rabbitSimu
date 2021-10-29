public class PopulationManager {

    public static void main(String[] args) {

        Population rabbitPopulation = new Population(1,1,3,6,50,5,8,60,35);

        MonthlyPopulation monthly = new MonthlyPopulation(0,10000,10000,5000,5000,5,6);

        for (int i = 0 ; i < 20 ; i++) {

            monthly.evolution(rabbitPopulation);
            System.out.println("Alive : " + monthly.getAliveRabbitNumber() + ", Female : " + monthly.getAliveFemaleNumber() + ", Litters : " + monthly.getRabbitNumber());

        }

    }

}
