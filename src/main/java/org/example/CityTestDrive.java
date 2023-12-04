package org.example;

public class CityTestDrive {

    public static void startTest() {

        City city = new City("EdgarTown");

        Assert(city.getName().equals("EdgarTown") && city.getYear() == 0
                && city.getAcres() == 1000 && city.getBushles() == 2800
                && city.getPopulation() == 100, "correct initialisation.");

        Assert(city.kaufen(3, 4000) == false, "not enough bushles check.");
        Assert(city.kaufen(2, 3) == false, "bushles divisible check.");

        Assert(city.verkaufen(2, 2000) == false, "not enough acres check.");

        city.kaufen(2, 10);
        Assert(city.getAcres() == 1005 && city.getBushles() == 2790, "kaufen richtiges ergebnis.");
        city.verkaufen(2, 5);
        Assert(city.getAcres() == 1000 && city.getBushles() == 2800, "verkaufen richtiges ergebnis.");

        city.ernähren(100*20);
        Assert(city.getPopulation() == 1000 && city.getBushles() == 2800-(100*20), "right answer ernähren (enough bushles for all).");

        city.ernähren(50*20);
        Assert(city.getPopulation() == 500, "Hälfte tot weil 50*20 bushles nur.");

        city.setYear(1);
        Assert(city.getYear() == 1, "Jahre inkrement");

        //TODO: test .pflanzen() Method

        Assert(city.ernähren(0), "ernähren > 0");
        Assert(city.pflanzen(0), "pflanzen > 0");


    }

    public static void Assert(boolean bool, String description) {
        if(bool) System.out.println("[PASS] " + description);
        else System.out.println("[    ] " + description);
    }

}