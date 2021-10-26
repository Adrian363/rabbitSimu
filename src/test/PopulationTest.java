import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {

    @Test
    public void rand() {

        Population population = new Population();

        assertEquals(0.5328330247200896, population.rand(0,1));
        assertEquals(53.41365935378446, population.rand(0,100));
        assertEquals(509.5530409196867, population.rand(0,1000));

    }

}