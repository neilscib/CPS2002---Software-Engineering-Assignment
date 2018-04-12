import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

public class MapTest {

    Map m;

    @Before
    public void setup(){
        m = new Map();
    }

    @After
    public void teardown(){
        m = null;
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
}
