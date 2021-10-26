import org.junit.jupiter.api.Test;

public class MonthlyPopulation {

    int age;
    int rabbitNumber;
    int aliveRabbitNumber;
    int femaleNumber;
    int monthsMaturity;
    int littersNumber;

    public MonthlyPopulation() {
        this.age = 0;
    }

    public MonthlyPopulation(int age, int rabbitNumber, int aliveRabbitNumber, int femaleNumber, int monthsMaturity, int littersNumber) {
        this.age = age;
        this.rabbitNumber = rabbitNumber;
        this.aliveRabbitNumber = aliveRabbitNumber;
        this.femaleNumber = femaleNumber;
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



    }

}
