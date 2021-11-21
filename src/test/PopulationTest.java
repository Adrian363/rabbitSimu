import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {

    @Test
    /**
     * Test if the random number generation is correct
     */
    public void rand() {

        Population population = new Population();

        assertEquals(0.5328330247200896, population.rand(0,1));
        assertEquals(53.41365935378446, population.rand(0,100));
        assertEquals(509.5530409196867, population.rand(0,1000));

    }

    @Test
    /**
     * Test if the add of rabbits is correct
     */
    public void addRabbits() {

        Population population = new Population();

        population.addAliveRabbits(5, 8, 13);

        assertEquals(population.getFemaleNumber(), 5);
        assertEquals(population.getMaleNumber(), 8);
        assertEquals(population.getAliveRabbitNumber(), 13);

    }

    @Test
    /**
     * Test if the remove of rabbits is correct
     */
    public void removeRabbits() {

        Population population = new Population();

        population.addAliveRabbits(5, 8, 13);
        population.updateAliveRabbitsPop(4, 6);

        assertEquals(population.getFemaleNumber(), 1);
        assertEquals(population.getMaleNumber(), 6);
        assertEquals(population.getAliveRabbitNumber(), 7);

        // Testing when the number of death rabbits is superior than the number of rabbits alive
        population.updateAliveRabbitsPop(2, 2);

        assertEquals(population.getFemaleNumber(), 1);
        assertEquals(population.getAliveRabbitNumber(), 7);
    }

}