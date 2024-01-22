package de.koppy.domainmodel.Plants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionsTest {

    @Test
    public void testGenerate() {
        Conditions conditions = Conditions.generateRandomConitions();
        Assertions.assertTrue(conditions.getSoilConditions() <= 1.0f);
        Assertions.assertTrue(conditions.getSoilConditions() >= 0.0f);
        Assertions.assertTrue(conditions.getAvergeTemperatureSummer() <= 30f);
        Assertions.assertTrue(conditions.getAvergeTemperatureSummer() >= 0f);
        Assertions.assertTrue(conditions.getAvergeTemperatureWinter() <= 10f);
        Assertions.assertTrue(conditions.getAvergeTemperatureWinter() >= -10f);
    }

}