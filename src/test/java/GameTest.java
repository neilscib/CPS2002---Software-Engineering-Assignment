/*
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class GameTest {

    Game game;
    public static final int HAZARD = 35;
    public static final int SAFE = 15;

    @Before
    public void setup(){
        game = new Game();
    }

    @After
    public void teardown(){
        game = null;
    }

    @Test
    public void startGameTest()
    {
        game.startGame(5, SAFE ,2);

        assertThat(game.getMap().getTreasurePos(), is(not(nullValue())));
        assertEquals(game.getMap().getSize(), 5);
    }

    @Test
    public void askDirectionPlayerValidDir()
    {
        //simulating user input
        String input = "u";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(game.askDirectionPlayer(0), anyOf(equalTo('u'), equalTo('d'), equalTo('l'), equalTo('r'), equalTo('e')));
    }

    @Test
    public void askDirectionPlayerInvalidDir()
    {
        //simulating user input
        String input = "s";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(game.askDirectionPlayer(0), anyOf(equalTo('u'), equalTo('d'), equalTo('l'), equalTo('r'), equalTo('e')));
    }

    @Test
    public void getNumPlayersTest()
    {
        game.startGame(5, SAFE, 2);
        assertEquals(game.getNumPlayers(), 2);
    }

}
*/
