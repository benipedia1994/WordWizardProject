package edu.gatech.seclass.words6300;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    // Able to make maxTurns: 0
    @Test
    public void isOver() {
        try{
            String expected = "GAME OVER";
            String output;
            GameSettings is_over_test = new GameSettings(0);
            Game game_over = new Game(is_over_test);

        } catch (Exception ex){
            assertEquals( "GAME OVER", ex.getMessage() );
        }
    }

    @Test
    public void getScore() {
        GameSettings zero_score = new GameSettings(0);
        Game game_zero_score = new Game(zero_score);

        assertEquals(0 , game_zero_score.getScore() );

    }

    @Test
    public void getCurrentTurn() {
        GameSettings zero_turn_setting = new GameSettings(0);
        Game game_zero_score = new Game(zero_turn_setting);

        assertEquals(0, game_zero_score.getCurrentTurn());
    }
}