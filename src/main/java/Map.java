import java.util.Random;

public class Map {
    private int size;
    private Cell [] [] map;
    private Position treasure;
    private int probability_water;

    /*
    This sets the constructor(even if there is no implementation for it) as private, so no one can call new Map()
    from another class, and the new object of class can only be created using getMapInstance().
     */
    //map constructor
    public Map(int probability_water)
    {
        this.probability_water = probability_water;
    }

    /*
    This will initially be set to null.
    */
    private static Map mapObj;

    /*
    This method will create and return the instance of the map object.
    */
    public static Map getMapInstance(int prob)
    {
        if(mapObj == null)
            return mapObj = new Map(prob);
        else return mapObj;
    }

    /*
    This will be called if the map instance needs to be deleted, for any reason whatsoever.
     */
    public static void resetInstance()
    {
        mapObj = null;
    }

    //check whether map size and number of players are compatible according to given restrictions
    public boolean setMapSize(int size, int numPlayers){
        if(size <=50) {
            if (numPlayers >= 5 && size >= 8) {
                return true;
            }else if (numPlayers < 5 && size >= 5) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //map getter
    public Cell [] [] getMap(){
        return map;
    }

    //get copy of map
    public Cell [] [] getCopyOfMap(){
        Cell[] [] myCopy = new Cell [map[0].length][map[0].length];
        for (int i = 0 ; i < map[0].length;i++){
            for (int j = 0 ; j < map[0].length;j++){
                myCopy[i][j] = new Cell ((map[i][j]).type);
                myCopy[i][j].visited = false;
            }
        }
        return myCopy;
    }


    //size setter
    public void setSize(int size) {
        this.size = size;
        generateWinningCell(size);
        map = generateMap(size, probability_water);
    }

    //size getter
    public int getSize(){
        return size;
    }

    //generates the map and returns it
    public Cell [] [] generateMap(int size, int percentage_water){
        Random rand = new Random();

        //create map 2d array with accepted size
        Cell [][] myMap = new Cell[size][size];

        //get position of winning cell
        int winningXCoord = treasure.getX();
        int winningYCoord = treasure.getY();

        for(int i = 0; i<size; i++)
        {
            for(int j = 0 ; j<size; j++)
            {
                //System.out.println("I:" + i + " J: " + j);

                //check if we are in coordinates of winning cell and act accordingly
                if(winningXCoord == i && winningYCoord == j)
                {
                    //System.out.println("b");
                    myMap[i][j] = new Cell(Type.TREASURE);
                    //System.out.println("a");
                }else {
                    int randomNum = rand.nextInt(100);
                    if(randomNum >percentage_water) {
                        //System.out.println("bg");
                        myMap[i][j] = new Cell(Type.GREEN);
                        //System.out.println("ag");
                    }else{
                        //System.out.println("bb");
                        myMap[i][j] = new Cell(Type.BLUE);
                        //System.out.println("ab");
                    }
                }

            }
        }

        return myMap;
    }

    //returns the winning coordinate without generating a new one every time being called
    public Position getTreasurePos()
    {
        return treasure;
    }

    //generates a random winning position and returns it
    public Position generateWinningCell(int size){
        Random rand = new Random();

        int randomX = rand.nextInt(size);
        int randomY = rand.nextInt(size);

        Position p = new Position(randomX, randomY);
        treasure = p;

        return p;
    }

    //returns the tile type
    public Type getTileType(int x, int y, Cell[][] passedMap){
        return passedMap[x][y].type;
    }

    //changes status of the current position in the passed map
    public void visitCoord(int x, int y, Cell[][] passedMap)
    {
        passedMap[x][y].visited = true;
    }


}
