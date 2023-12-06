package de.koppy;

public class CityTestDrive {

    public static void startTest() {

        City city = new City("EdgarTown");

        Assert(city.getName().equals("EdgarTown") && city.getYear() == 0
                && city.getAcres() == 1000 && city.getBushles() == 2800
                && city.getPopulation() == 100, "correct initialisation.");

        city.setPriceperacre(100);
        Assert(city.kaufen(40) == false, "not enough bushles check.");

        city.setPriceperacre(2);
        Assert(city.verkaufen(2000) == false, "not enough acres check.");

        city.kaufen(10);
        Assert(city.getAcres() == 1010 && city.getBushles() == 2780, "kaufen richtiges ergebnis.");
        city.verkaufen(10);
        Assert(city.getAcres() == 1000 && city.getBushles() == 2800, "verkaufen richtiges ergebnis.");

        city.ernähren(100*20);
        Assert(city.getPopulation() == 100 && city.getBushles() == 2800-(100*20), "right answer ernähren (enough bushles for all).");


        city.setBushles(10000);
        city.ernähren(50*20);
        Assert(city.getPopulation() == 50, "Hälfte tot weil 50*20 bushles nur.");

        city.setYear(1);
        Assert(city.getYear() == 1, "Jahre inkrement");

        Assert(city.ernähren(0), "ernähren > 0");
        Assert(city.pflanzen(0), "pflanzen > 0");


    }

    public static void Assert(boolean bool, String description) {
        if(bool) System.out.println("[PASS] " + description);
        else System.out.println("[    ] " + description);
    }

}