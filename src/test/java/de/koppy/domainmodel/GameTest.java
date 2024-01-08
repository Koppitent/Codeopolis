package de.koppy.domainmodel;

import de.koppy.presentation.TextInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void createAndResetCity() {
        Game game = new Game("UwUCity", DifficultyLevel.EASY, new TextInterface());
        assertEquals(game.getCity().getName(), "UwUCity");
        game.resetCity("NewTown");
        assertEquals(game.getCity().getName(), "NewTown");
    }

}