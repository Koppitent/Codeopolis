package de.koppy.lager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepotTest {

    Depot depot = new Depot(4);

    @Test
    public void testInitiation() {
        assertEquals(depot.getCapacity(), 4);
    }

}