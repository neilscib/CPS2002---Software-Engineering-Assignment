import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

public class HelloWorldTesting {
    HelloWorld hw;

    @Before
    public void setup(){
        hw = new HelloWorld();
    }

    @After
    public void teardown(){
        hw = null;
    }

    @Test
    public void getMessTest() {

        String s = hw.getMess();

        assertEquals("Hello World!", s);
    }
}
