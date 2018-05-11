import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;

public class MapTest {

    Map m;
    public static final int WATERPROB = 25;

    /*
    @Before
    public void setup(){
        m = new Map();
    }
    */

    @After
    public void teardown(){
        m = null;
    }

    @Test
    public void getInstanceTest()
    {
        assertThat(Map.getMapInstance(WATERPROB), is(not(null)));
    }

    @Test
    public void minPlayerOutRangeSize(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        boolean result = m.setMapSize(2, 4);
        assertEquals(false, result);
    }

    @Test
    public void maxPlayerOutRangeSize(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        boolean result = m.setMapSize(6, 8);
        assertEquals(false, result);
    }

    @Test
    public void sizeOutOfRange(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        boolean result = m.setMapSize(55, 4);
        assertEquals(false, result);
    }

    @Test
    public void minPlayerCorrectRangeSize(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        boolean result = m.setMapSize(5, 2);
        assertEquals(true, result);
    }

    @Test
    public void maxPlayerCorrectRangeSize(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        boolean result = m.setMapSize(8, 5);
        assertEquals(true, result);
    }

    @Test
    public void checkSizeSetter(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        m.setSize(5);
        assertEquals(5,m.getSize());
    }

    @Test
    public void checkGenerateMap(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        Position winPos = m.generateWinningCell(5);
        int x_coord = winPos.getX();
        int y_coord = winPos.getY();

        Cell [] [] generatedMap = m.generateMap(5);

        assertEquals(generatedMap[x_coord][y_coord].type,Type.TREASURE);
    }

    @Test
    public void checkGetTile(){
        //getMapInstance is the static method, thus it is called on the Map class directly.
        Map m = Map.getMapInstance();

        m.setSize(5);
        Cell [] [] myMap = m.getMap();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j<5; j++){
                if (myMap[i][j].type == Type.TREASURE ){
                    assertEquals(m.getTileType(i,j, myMap),Type.TREASURE);
                }
            }
        }
    }


}
