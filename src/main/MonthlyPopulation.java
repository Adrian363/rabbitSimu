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

        age++;

        int survivalProba = age >= monthsMaturity ? population.getAdultsSurvivalRate() : population.getKittensSurvivalRate();
        int newAliveRabbitNumber = aliveRabbitNumber;
        int newAliveFemaleNumber = aliveFemaleNumber;

        for (int i = 0 ; i < aliveRabbitNumber ; i++) {

            if (population.rand(0, 100) > survivalProba) {

                newAliveRabbitNumber--;

                if (aliveFemaleNumber > 0) {
                    newAliveFemaleNumber--;
                }

            }

            aliveFemaleNumber--;

        }

        aliveRabbitNumber = newAliveRabbitNumber;
        aliveFemaleNumber = newAliveFemaleNumber;

        int newKittensNumber;
        int newFemaleKittensNumber = 0;
        int newMaleKittensNumber = 0;

        if (littersNumber > 0) {

            for (int i = 0 ; i < aliveFemaleNumber ; i++) {

                if (population.rand(0,100) > population.getMaleProb()) {
                    newFemaleKittensNumber++;
                } else {
                    newMaleKittensNumber++;
                }

            }

            newKittensNumber = newMaleKittensNumber + newFemaleKittensNumber;
            rabbitNumber = newKittensNumber;

        }

    }

}
