import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyPopulationTest {

    /*
     *    10 First random numbers (0,100)
     *    -------------------------------
     *    53.28330247200896
     *    53.41365935378446
     *    50.95530409196867
     *    71.35640253888509
     *    25.699895331049028
     *    75.26936093410164
     *    88.38791826561871
     *    15.489908943926423
     *    67.05464344275741
     *    64.3445153214518
     */

    @Test
    /**
     * Test if the update of the alive rabbits is correct
     */
    void updateAliveRabbits() {

        int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };

        MersenneTwister random = new MersenneTwister();
        random.setSeed(123456789);

        Population population = new Population(1,1,3,6,50,4,8,60,35, 10, 10, possibleLitters, random);
        MonthlyPopulation monthlyPopulation = new MonthlyPopulation(1000,500,5);

        monthlyPopulation.updateAliveRabbits(population);
        assertEquals(920, monthlyPopulation.getAliveRabbitNumber(),20);
        assertEquals(450, monthlyPopulation.getAliveFemaleNumber(),20);

        monthlyPopulation.updateAliveRabbits(population);
        assertEquals(840, monthlyPopulation.getAliveRabbitNumber(),20);
        assertEquals(420, monthlyPopulation.getAliveFemaleNumber(),20);

    }

    @Test
    /**
     * Test if the calculation of the cumulated probabilities is correct
     */
    void getCumulateLittersProbas() {
        int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };

        MersenneTwister random = new MersenneTwister();
        random.setSeed(123456789);

        MonthlyPopulation monthlyPopulation = new MonthlyPopulation();
        Population population = new Population(1,1,3,6,50,4,8,60,35, 10, 10, possibleLitters, random);

        assertTrue(Arrays.equals(population.getCumulateProbas(),
                                 population.getCumulateLittersProbas(population.getPossibleLittersTab())));
    }

    @Test
    /**
     * Test if the generation of a litter is correct
     */
    void getLitter() {


        int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };

        MersenneTwister random = new MersenneTwister();
        random.setSeed(123456789);

        Population population = new Population(1,1,3,6,50,4,8,60,35, 10, 10, possibleLitters, random);
        MonthlyPopulation monthlyPopulation = new MonthlyPopulation();
        int[] cumulateProbas = { 10, 20, 50, 70, 100 };

        assertEquals(3,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(3,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(4,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(2,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(4,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(4,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(1,monthlyPopulation.getLitter(cumulateProbas, population));
        assertEquals(3,monthlyPopulation.getLitter(cumulateProbas, population));

    }

}