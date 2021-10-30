import org.junit.jupiter.api.Test;

public class MonthlyPopulation {

    int age;
    int rabbitNumber;
    int aliveRabbitNumber;
    int femaleNumber;
    int aliveFemaleNumber;
    int monthsMaturity;
    int littersNumber;

    public MonthlyPopulation() {
        this.age = 0;
    }

    public MonthlyPopulation(int age, int rabbitNumber, int aliveRabbitNumber, int femaleNumber, int aliveFemaleNumber, int monthsMaturity, int littersNumber) {
        this.age = age;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = aliveRabbitNumber;
        this.femaleNumber = femaleNumber;
        this.aliveFemaleNumber = aliveFemaleNumber;
        this.monthsMaturity = monthsMaturity;
        this.littersNumber = littersNumber;
    }

    public MonthlyPopulation(int rabbitNumber, int femaleNumber, int monthsMaturity) {
        this.age = 0;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = rabbitNumber;
        this.femaleNumber = femaleNumber;
        this.aliveFemaleNumber = femaleNumber;
        this.monthsMaturity = monthsMaturity;
        this.littersNumber = 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRabbitNumber() {
        return rabbitNumber;
    }

    public void setRabbitNumber(int rabbitNumber) {
        this.rabbitNumber = rabbitNumber;
    }

    public int getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    public void setAliveRabbitNumber(int aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    public int getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(int femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    public int getAliveFemaleNumber() {
        return aliveFemaleNumber;
    }

    public void setAliveFemaleNumber(int aliveFemaleNumber) {
        this.aliveFemaleNumber = aliveFemaleNumber;
    }

    public int getMonthsMaturity() {
        return monthsMaturity;
    }

    public void setMonthsMaturity(int monthsMaturity) {
        this.monthsMaturity = monthsMaturity;
    }

    public int getLittersNumber() {
        return littersNumber;
    }

    public void setLittersNumber(int littersNumber) {
        this.littersNumber = littersNumber;
    }

    public void evolution(Population population) {

        this.age++;

        updateLittersNumber(population);

        updateAliveRabbits(population);

        if (this.littersNumber > 0 && this.age >= this.monthsMaturity) {
            population.getPopulations().add(generateLitter(population));
        }

    }

    public MonthlyPopulation generateLitter(Population population) {

        MonthlyPopulation litter;

        int newKittensNumber;
        int newFemaleKittensNumber = 0;
        int newMaleKittensNumber = 0;

        for (int i = 0 ; i < this.aliveFemaleNumber ; i++) {

            int kittensNumber = (int) population.rand(population.getMinKittens(), population.getMaxKittens() + 1);

            for (int j = 0 ; j < kittensNumber ; j++) {
                if (population.rand(0,100) > population.getMaleProb()) {
                    newFemaleKittensNumber++;
                } else {
                    newMaleKittensNumber++;
                }
            }

        }

        newKittensNumber = newMaleKittensNumber + newFemaleKittensNumber;

        this.littersNumber--;

        litter = new MonthlyPopulation(newKittensNumber,newFemaleKittensNumber,(int) population.rand(population.getMinSexualMaturity(), population.getMaxSexualMaturity() + 1));

        return litter;

    }

    public void updateLittersNumber(Population population) {


        if ((this.age - this.monthsMaturity) % 12 == 0) {

            // TODO Population values
            int[][] possibleLitters = { { 4, 5 }, { 5, 30 }, { 6, 30 }, { 7, 30 }, { 8, 5 } };
            int[] cumulateProbas = getCumulateLittersProbas(possibleLitters);

            this.littersNumber = possibleLitters[getLitter(cumulateProbas, population)][0];

        }

    }

    public void updateAliveRabbits(Population population) {

        int survivalProba = this.age >= this.monthsMaturity ? population.getAdultsSurvivalRate() : population.getKittensSurvivalRate();
        int newAliveRabbitNumber = this.aliveRabbitNumber;
        int newAliveFemaleNumber = this.aliveFemaleNumber;
        int monthsAge = age;

        // TODO Population values
        int yearsBeforeLeast = 10;
        int leastProbaEachYear = 10;

        while (monthsAge >= 12 * (yearsBeforeLeast + 1)) {
            survivalProba -= leastProbaEachYear;
            monthsAge -= 12;
        }

        for (int i = 0 ; i < this.aliveRabbitNumber ; i++) {

            if (population.rand(0, 100) > survivalProba) {

                newAliveRabbitNumber--;

                if (this.aliveFemaleNumber > 0) {
                    newAliveFemaleNumber--;
                }

            }

            this.aliveFemaleNumber--;

        }

        this.aliveRabbitNumber = newAliveRabbitNumber;
        this.aliveFemaleNumber = newAliveFemaleNumber;

    }

    public int[] getCumulateLittersProbas(int[][] possibleLitters) {

        int[] cumulateProbas = new int[possibleLitters.length];
        int cumulateProba = 0;

        for (int i = 0 ; i < possibleLitters.length ; i++) {
            cumulateProba += possibleLitters[i][1];
            cumulateProbas[i] = cumulateProba;
        }

        return cumulateProbas;

    }

    public int getLitter(int[] cumulateProbas, Population population) {

        double random = population.rand(0,100);
        int j = 0;

        while(random > cumulateProbas[j]) {
            j++;
        }

        return j;

    }

    @Override
    public String toString() {
        return "MonthlyPopulation{" +
                "age=" + age +
                ", rabbitNumber=" + rabbitNumber +
                ", aliveRabbitNumber=" + aliveRabbitNumber +
                ", femaleNumber=" + femaleNumber +
                ", aliveFemaleNumber=" + aliveFemaleNumber +
                ", monthsMaturity=" + monthsMaturity +
                ", littersNumber=" + littersNumber +
                '}';
    }
}
