import java.util.Random;


public class Player
{
    private Position position;
    private Position initialPos;
    private Map myMap;
    private Cell [] [] map; //the private copy of the map

    //constrcutor to be able to initialize the 'position pointer'
    public Player(Cell [] [] map, Map instance_map)
    {
        //just for the sake of having an object attached to the 'position' pointer, else it will lead to
        //null pointer exception.
        position = new Position(-1,-1);

        //passing generated map to the player
        this.map = map;

        //initialising map instance
        myMap = instance_map;

    }


    //This function takes direction as a character (u,d,l,r) and the size of the map, 
    //and if the move is valid (within bounds), the position is updated and returns true,
    // else the position is left intact and the function returns false.
    public boolean move(char direction, int size)
    {
        int tempX = position.getX();
        int tempY = position.getY();

        switch(direction)
        {
            case 'u':
                tempX--;
                break;
            case 'd':
                tempX++;
                break;
            case 'r':
                tempY++;
                break;
            case 'l':
                tempY--;
                break;
        }

        //Assuming that the indexing of the cells start from 0.
        //So for example if the size is 5 of the map, then valid positions will be 0,1,2,3,4 only, while 5 onwards will be invalid. 
        
        //When either the x or y coordinate went out of bounds
        //example: X should be greater or equal to 0 AND smaller than 5. Now if this is false, then it is out of bounds.
        if(!((tempX >= 0) && (tempX < size)) || !((tempY >= 0) && (tempY < size)))
            return false;
        else
        {
            position.setX(tempX);
            position.setY(tempY);

            myMap.visitCoord(tempX, tempY, map);
        }

        return true;
    }

    //Sets the random initial position of the player.
    //This returns true when the random position happens to be the winning cell, else it returns false. 
    public boolean setPosition(Position winning, int size)
    {
        //this is not really required for setting the initial position, but we need to set this.map pointer to
        //point to somewhere, and now is a good chance to do it, because setPosition() is always called when starting the game.

        Random rand = new Random();

        int initX = rand.nextInt(size);
        int initY = rand.nextInt(size);

        position.setX(initX);
        position.setY(initY);

        //when the randomly generated position is not green, re-generate a random position
        while(myMap.getTileType(initX, initY, map) != Type.GREEN)
        {
            initX = rand.nextInt(size);
            initY = rand.nextInt(size);

            position.setX(initX);
            position.setY(initY);
        }


        /*initialPos = new Position(position.getX(), position.getY());*/


        //if the initial random position is the winning position or a water tile then return true, else return false.
        if(((winning.getX() == initX) && (winning.getY() == initY))||(map[initX][initY].type == Type.BLUE))
            return true;
        else
            return false;

    }

    public Position getPosition()
    {
        return position;
    }

    //when the player moves on a water tile, he is sent back to his initial position.
    public void moveToInitial()
    {
        position = new Position(initialPos.getX(), initialPos.getY());
    }

    public Position getInitial()
    {
        return initialPos;
    }

    //returns private map of player
    public Cell [] [] getMap()
    {
        return map;
    }

    //method used to set initial position
    public void setInitialPos()
    {
        initialPos = new Position(position.getX(),position.getY());
    }
}