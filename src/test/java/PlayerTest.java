///*
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//import org.junit.After;
//import org.junit.Before;
//
//public class PlayerTest {
//
//    Player player1;
//    Map map;
//    public static final int HAZARD = 35;
//    public static final int SAFE = 15;
//
//    @Before
//    public void setup(){
//        map = new Map();
//        map.setMapSize(5,2);
//        map.setSize(5, SAFE);
//        player1 = new Player(map.getMap());
//    }
//
//    @After
//    public void teardown(){
//        player1 = null;
//        map = null;
//    }
//
//    @Test
//    public void testSetPosition()
//    {
//
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), 5);
//
//        int x = player1.getPosition().getX();
//        int y = player1.getPosition().getY();
//
//        //when initialised, x and y values are equal to -1, so when given an initial
//        //position, then they should not be -1 anymore.
//        assertThat(x, is(not(-1)));
//        assertThat(y, is(not(-1)));
//
//    }
//
//    @Test
//    public void testMoveValid()
//    {
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), 5);
//        int x_old = player1.getPosition().getX();
//        int y_old = player1.getPosition().getY();
//
//        char direction = 'd';
//        int counter = 0;
//
//        //Since the initial position assigned to the player is random,
//        //'d' might be an invalid move, in that case the while loop is
//        //entered and all the possible moves are tried. Else the test might not be
//        //effective.
//        while(!player1.move(direction, 5))
//        {
//            switch(counter)
//            {
//                case 0:
//                    direction = 'u';
//                    break;
//                case 1:
//                    direction = 'd';
//                    break;
//                case 2:
//                    direction = 'l';
//                    break;
//                case 3:
//                    direction = 'r';
//                    break;
//            }
//
//            counter++;
//        }
//
//        int x_new = player1.getPosition().getX();
//        int y_new = player1.getPosition().getY();
//
//        //when the counter is 1 or 2, it means that the direction of movement was either left or right, so we
//        //test on the y value
//        if((counter == 2) || (counter == 3))
//            assertThat(y_new, anyOf(equalTo(y_old+1), equalTo(y_old-1)));
//        else
//            assertThat(x_new, anyOf(equalTo(x_old+1), equalTo(x_old-1)));
//
//
//
//    }
//
//    @Test
//    public void testMoveInvalid()
//    {
//        int mapSize = 5;
//
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), mapSize);
//
//        boolean validityOfLastMove = true;
//
//        //if we move in the same direction for an amount of times equal to the size of the map,
//        //then we will go out of bounds for sure.
//        for(int i = 0; i < mapSize; i++)
//        {
//            validityOfLastMove = player1.move('u', mapSize);
//        }
//
//        assertEquals(false, validityOfLastMove);
//
//    }
//
//    @Test
//    public void getPositionTest()
//    {
//        //this is null for now. But when we set it equal to the Position returned by the
//        //getPosition method, it won't be null anymore.
//        Position pos;
//
//        int mapSize = 5;
//
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), mapSize);
//
//        pos = player1.getPosition();
//
//        assertThat(pos, is(not(nullValue())));
//    }
//
//    @Test
//    public void moveToInitialTest()
//    {
//        Position initPos;
//
//        int mapSize = 5;
//
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), mapSize);
//        player1.setInitialPos();
//
//        player1.move('d', mapSize);
//        player1.move('r', mapSize);
//
//
//        player1.moveToInitial();
//        Position movedPos = player1.getPosition();
//
//        assertEquals(movedPos,player1.getPosition());
//    }
//
//    @Test
//    public void setInitialPosTest()
//    {
//        //testing that setInitial actually sets the initialPos field.
//        //Verifying by calling the setPosition and then setInitialPos.
//
//        player1 = new Player(map.getMap());
//        player1.setPosition(map.getTreasurePos(), 5);
//        player1.setInitialPos();
//
//        assertEquals(player1.getPosition().getX(), player1.getInitial().getX());
//        assertEquals(player1.getPosition().getY(), player1.getInitial().getY());
//    }
//
//
//}
//*/
