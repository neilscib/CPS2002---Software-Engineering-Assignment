import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class GameTest {

    Map_Factory factory;
    Game game;
    Map map;
    private static final int HAZARD = 35;
    private static final int SAFE = 15;

    @Before
    public void setup(){
        game = new Game("hazardous");
        factory = new Map_Factory();

        //getMapInstance is the static method, thus it is called on the Map class directly.
        map = factory.getMap("hazardous");
    }

    @After
    public void teardown(){
        game = null;
    }

    @Test
    public void startGameTest()
    {
        game.startGame(5, SAFE ,map);

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
        game.startGame(5, 2, map);
        assertEquals(game.getNumPlayers(), 2);
    }

}
