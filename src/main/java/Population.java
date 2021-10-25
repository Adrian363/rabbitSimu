package java;

import java.util.ArrayList;

public class Population {

    int     aliveRabbitNumber;
    int     femaleNumber;
    int     maleNumber;
    int     minKittens;
    int     maxKittens;
    int     minSexualMaturity;
    int     maxSexualMaturity;
    int     adultsSurvivalRate;
    int     kittensSurvivalRate;
    double  maleProb;

    ArrayList<MonthlyPopulation> populations;

    public Population(int femaleNumber, int maleNumber, int minKittens, int maxKittens, double maleProb,
                      int minSexualMaturity, int maxSexualMaturity, int adultsSurvivalRate, int kittensSurvivalRate) {

        this.femaleNumber = femaleNumber;
        this.maleNumber   = maleNumber;
        this.aliveRabbitNumber = maleNumber + femaleNumber;
        this.minKittens = minKittens;
        this.maxKittens = maxKittens;
        this.maleProb = maleProb;
        this.minSexualMaturity = minSexualMaturity;
        this.maxSexualMaturity = maxSexualMaturity;

        this.populations = new ArrayList<MonthlyPopulation>();
    }









}
