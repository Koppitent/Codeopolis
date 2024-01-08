package de.koppy.domainmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    City city = new City("EdgarTown");

    @Test
    public void initiationTest() {
        assertEquals(city.getName(), "EdgarTown");
        assertEquals(city.getAcres(), 1000);
        assertEquals(city.getBushles(), 2800);
        assertEquals(city.getPopulation(), 100);
    }

    @Test
    public void kaufenverkaufenTest() {
        city.setPriceperacre(100);
        assertFalse(city.kaufen(40));
        city.setPriceperacre(2);
        assertFalse(city.verkaufen(2000));
        city.kaufen(10);
        assertEquals(city.getAcres(), 1010);
        assertEquals(city.getBushles(), 2780);
        city.verkaufen(10);
        assertEquals(city.getAcres(), 1000);
        assertEquals(city.getBushles(), 2800);
    }

    @Test
    public void ernährenTest() {
        city.ernähren(100*20);
        assertEquals(city.getPopulation(), 100);
        assertEquals(city.getBushles(), 2800-(100*20));
        assertTrue(city.ernähren(0));
    }

    @Test
    public void pflanzenTest() {
        assertTrue(city.pflanzen(0));
    }
    @Test
    public void yearTest() {
        city.setYear(1);
        assertEquals(city.getYear(), 1);
    }

    City newcity = new City("EdgarTown");

    @Test
    public void runTurnTest() {
        int bushles = city.getBushles() - 100*20;
        int acres = city.getAcres();
        city.kaufen(10);
        city.verkaufen(10);
        city.pflanzen(20);
        city.ernähren(100*20);
        TurnResult tr = city.runTurn();
        bushles = bushles - tr.getAteByRates();
        int resident = 100 + tr.getNewResidents();

        assertEquals(city.getYear(), 1);
        assertEquals(city.getBushles(), bushles);
        assertEquals(city.getPopulation(), resident);
        assertEquals(city.getAcres(), acres);
    }

}