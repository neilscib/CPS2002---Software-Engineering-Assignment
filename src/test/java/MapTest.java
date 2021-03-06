import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;

public class MapTest {

    Map m;
    Map_Factory factory;
    public static final int HAZARD = 35;
    public static final int SAFE = 15;

    @Before
    public void setup(){
        factory = new Map_Factory();

        //getMapInstance is the static method, thus it is called on the Map class directly.
        m = factory.getMap("hazardous");
    }


    @After
    public void teardown(){
        m = null;
    }

    @Test
    public void getInstanceTest()
    {
        Map.resetInstance();

        m = factory.getMap("hazardous");

        assertNotNull(m);
    }

    @Test
    public void minPlayerOutRangeSize(){

        boolean result = m.setMapSize(2, 4);
        assertEquals(false, result);
    }

    @Test
    public void maxPlayerOutRangeSize(){

        boolean result = m.setMapSize(6, 8);
        assertEquals(false, result);
    }

    @Test
    public void sizeOutOfRange(){

        boolean result = m.setMapSize(55, 4);
        assertEquals(false, result);
    }

    @Test
    public void minPlayerCorrectRangeSize(){

        boolean result = m.setMapSize(5, 2);
        assertEquals(true, result);
    }

    @Test
    public void maxPlayerCorrectRangeSize(){

        boolean result = m.setMapSize(8, 5);
        assertEquals(true, result);
    }

    @Test
    public void checkSizeSetter(){

        m.setSize(5);

        assertEquals(5,m.getSize());
    }

    @Test
    public void checkGenerateMap(){

        Position winPos = m.generateWinningCell(5);
        int x_coord = winPos.getX();
        int y_coord = winPos.getY();

        Cell [] [] generatedMap = m.generateMap(5, SAFE);

        assertEquals(generatedMap[x_coord][y_coord].type,Type.TREASURE);
    }

    @Test
    public void checkGetTile(){

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

