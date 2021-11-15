import org.junit.jupiter.api.Test;

public class MonthlyPopulation {

    private int age;
    private long rabbitNumber;
    private long aliveRabbitNumber;
    private long femaleNumber;
    private long aliveFemaleNumber;
    private int monthsMaturity;
    private int littersNumber;

    public MonthlyPopulation() {
        this.age = 0;
    }

    public MonthlyPopulation(int age, long rabbitNumber, long aliveRabbitNumber, long femaleNumber, long aliveFemaleNumber, int monthsMaturity, int littersNumber) {
        this.age = age;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = aliveRabbitNumber;
        this.femaleNumber = femaleNumber;
        this.aliveFemaleNumber = aliveFemaleNumber;
        this.monthsMaturity = monthsMaturity;
        this.littersNumber = littersNumber;
    }

    public MonthlyPopulation(long rabbitNumber, long femaleNumber, int monthsMaturity) {
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

    public long getRabbitNumber() {
        return rabbitNumber;
    }

    public void setRabbitNumber(long rabbitNumber) {
        this.rabbitNumber = rabbitNumber;
    }

    public long getAliveRabbitNumber() {
        return aliveRabbitNumber;
    }

    public void setAliveRabbitNumber(long aliveRabbitNumber) {
        this.aliveRabbitNumber = aliveRabbitNumber;
    }

    public long getFemaleNumber() {
        return femaleNumber;
    }

    public void setFemaleNumber(long femaleNumber) {
        this.femaleNumber = femaleNumber;
    }

    public long getAliveFemaleNumber() {
        return aliveFemaleNumber;
    }

    public void setAliveFemaleNumber(long aliveFemaleNumber) {
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

    public void evolution(Population population, MonthlyPopulation nextMonthlyPopulation) {

        this.age++;

        updateLittersNumber(population);

        updateAliveRabbits(population);

        if (this.littersNumber > 0 && this.age >= this.monthsMaturity && this.aliveRabbitNumber > this.aliveFemaleNumber) {
            generateLitter(population, nextMonthlyPopulation);
        }

    }

    public void generateLitter(Population population, MonthlyPopulation nextMonthlyPopulation) {

        long newKittensNumber;
        long newFemaleKittensNumber = 0;
        long newMaleKittensNumber = 0;

        int kittensNumber = getMiddle(population.getMinKittens(), population.getMaxKittens());

        newKittensNumber = (long) getApproximation(population, aliveFemaleNumber * kittensNumber,10);
        newMaleKittensNumber = (long) (getApproximation(population, population.getMaleProb(), 5) / 100 * newKittensNumber);
        newFemaleKittensNumber = newKittensNumber - newMaleKittensNumber;

        /*for (int i = 0 ; i < this.aliveFemaleNumber ; i++) {

            int kittensNumber = (int) population.rand(population.getMinKittens(), population.getMaxKittens() + 1);

            for (int j = 0 ; j < kittensNumber ; j++) {
                if (population.rand(0,100) > population.getMaleProb()) {
                    newFemaleKittensNumber++;
                } else {
                    newMaleKittensNumber++;
                }
            }

        }

        newKittensNumber = newMaleKittensNumber + newFemaleKittensNumber;*/

        this.littersNumber--;

        population.addAliveRabbits(newFemaleKittensNumber, newMaleKittensNumber, newKittensNumber);

        nextMonthlyPopulation.setRabbitNumber(newKittensNumber + nextMonthlyPopulation.getRabbitNumber());
        nextMonthlyPopulation.setAliveRabbitNumber(newKittensNumber + nextMonthlyPopulation.getAliveRabbitNumber());
        nextMonthlyPopulation.setFemaleNumber(newFemaleKittensNumber + nextMonthlyPopulation.getFemaleNumber());
        nextMonthlyPopulation.setAliveFemaleNumber(newFemaleKittensNumber + nextMonthlyPopulation.getAliveFemaleNumber());

    }

    public void updateLittersNumber(Population population) {

        if ((this.age - this.monthsMaturity) % 12 == 0) {
            this.littersNumber = population.getPossibleLitters(getLitter(population.getCumulateProbas(), population),0);
        }

    }

    public void updateAliveRabbits(Population population) {

        double survivalProba = this.age >= this.monthsMaturity ? population.getAdultsSurvivalRate() :
                          population.getKittensSurvivalRate();
        long newAliveRabbitNumber = this.aliveRabbitNumber;
        long newAliveFemaleNumber = this.aliveFemaleNumber;
        int monthsAge = age;

        while (monthsAge >= 12 * (population.getYearsBeforeLeast() + 1)) {
            survivalProba -= population.getLeastProbaEachYear();
            monthsAge -= 12;
        }

        newAliveRabbitNumber =
                (long) ((this.aliveRabbitNumber - this.aliveFemaleNumber) * getApproximation(population,  Math.pow((survivalProba / 100), (double) 1/12), 2));
        newAliveFemaleNumber =
                (long) (this.aliveFemaleNumber * getApproximation(population,  Math.pow((survivalProba / 100), (double) 1/12), 2));


        /*for (int i = 0 ; i < this.aliveRabbitNumber ; i++) {

            if (population.rand(0, 100) > survivalProba) {

                newAliveRabbitNumber--;

                if (this.aliveFemaleNumber > 0) {
                    newAliveFemaleNumber--;
                }

            }

            this.aliveFemaleNumber--;

        }*/

        population.updateAliveRabbitsPop((this.aliveFemaleNumber - newAliveFemaleNumber),
                                         (this.aliveRabbitNumber - (newAliveRabbitNumber + newAliveFemaleNumber)));

        this.aliveRabbitNumber = newAliveRabbitNumber + newAliveFemaleNumber;
        this.aliveFemaleNumber = newAliveFemaleNumber;

        if (this.aliveRabbitNumber <= 0) {
            population.getPopulations().remove(this);
        }

    }


    public int getLitter(int[] cumulateProbas, Population population) {

        double random = population.rand(0,100);
        int j = 0;

        while(random > cumulateProbas[j]) {
            j++;
        }

        return j;

    }

    public double getApproximation(Population population, double d, int percentage) {
        return population.rand(d - (d * percentage / 100),  d + (d * percentage / 100));
    }

    public int getMiddle(int a, int b) {
        return (a + b) / 2;
    }

    @Override
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
