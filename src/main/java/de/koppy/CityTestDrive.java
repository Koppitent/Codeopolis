package de.koppy;

import de.koppy.domainmodel.City;
import de.koppy.domainmodel.Game;
import de.koppy.presentation.TextInterface;

public class CityTestDrive {

    public static void startTest() {

        City city = new City("EdgarTown");

        Assert(city.getName().equals("EdgarTown") && city.getYear() == 0
                && city.getAcres() == 1000 && city.getBushles() == 2800
                && city.getPopulation() == 100, "correct initialisation.");

        city.setPriceperacre(100);
        Assert(!city.kaufen(40), "not enough bushles check.");

        city.setPriceperacre(2);
        Assert(!city.verkaufen(2000), "not enough acres check.");

        city.kaufen(10);
        Assert(city.getAcres() == 1010 && city.getBushles() == 2780, "kaufen richtiges ergebnis.");
        city.verkaufen(10);
        Assert(city.getAcres() == 1000 && city.getBushles() == 2800, "verkaufen richtiges ergebnis.");

        city.ernähren(100*20);
        Assert(city.getPopulation() == 100 && city.getBushles() == 2800-(100*20), "right answer ernähren (enough bushles for all).");


        city.setYear(1);
        Assert(city.getYear() == 1, "Jahre inkrement");

        Assert(city.ernähren(0), "ernähren > 0");
        Assert(city.pflanzen(0), "pflanzen > 0");

        //* NEW CITY
        city = new City("EdgarTown");
        int bushles = city.getBushles() - 100*20;
        int acres = city.getAcres();
        city.kaufen(10);
        city.verkaufen(10);
        city.pflanzen(20);
        city.ernähren(100*20);
        TurnResult tr = city.runTurn();
        bushles = bushles - tr.getAteByRates();
        int resident = 100 + tr.getNewResidents();

        System.out.println("Some Tests fpr runTurn() Method");
        Assert(city.getYear() == 1, "correct year");
        Assert(city.getBushles() == bushles, "correct bushles actual: " + city.getBushles() + ", expected: " + bushles);
        Assert(city.getPopulation() == resident, "correct people (noone died) acutal: " + city.getPopulation() + ", expected: " + resident);
        Assert(city.getAcres() == acres, "correct acres");

        Game game = new Game("UwUCity", DifficultyLevel.EASY, new TextInterface());
        Assert(game.getCity().getName().equals("UwUCity"), "correct name init game");
        game.resetCity("NewTown");
        Assert(game.getCity().getName().equals("NewTown"), "reset neue City");

    }

    public static void Assert(boolean bool, String description) {
        if(bool) System.out.println("[PASS] " + description);
        else System.out.println("[    ] " + description);
    }

}