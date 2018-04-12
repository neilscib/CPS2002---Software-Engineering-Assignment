import java.util.Random;

public class Map {
    private int size;
    private Cell [] [] map;

    //check whether map size and number of players are compatible according to given restrictions
    public boolean setMapSize(int size, int numPlayers){
        if(size <=50) {
            if (numPlayers >= 5 && size >= 8) {
                return true;
            } else if (numPlayers < 5 && size >= 5) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //size setter
    public void setSize(int size) {
        this.size = size;
        map = generateMap(size, getWinningCell(size));
    }

    //size getter
    public int getSize(){
        return size;
    }

    //generates the map and returns it
    private Cell [] [] generateMap(int size, Position winningCell){
        Random rand = new Random();

        //create map 2d array with accepted size
        Cell [] [] myMap = new Cell[size][size];

        //get position of winning cell
        int winningXCoord = winningCell.getX();
        int winningYCoord = winningCell.getY();

        for(int i = 0; i<size; i++){
            for(int j = 0 ; j<size; j++){
                //check if we are in coordinates of winning cell and act accordingly
                if(winningXCoord == i && winningYCoord == j){
                    myMap[i][j].type = Type.TREASURE;
                }else{
                    int randomNum = rand.nextInt((2 - 1) + 1) + 1;
                    if(randomNum == 0) {
                        myMap[i][j].type = Type.GREEEN;
                    }else{
                        myMap[i][j].type = Type.BLUE;
                    }
                }

                //set each cell to unvisited
                myMap[i][j].visited = false;
            }
        }

        return myMap;
    }

    //used to test the generateMap function
    public Cell [] [] test_generateMap(int size,Position p){
        return generateMap(size,p);
    }

    //used to test the getWinningCell function
    public Position test_getWinningCell(int size){
        return getWinningCell(size);
    }

    //generates a random winning position and returns it
    private Position getWinningCell(int size){
        Random rand = new Random();

        int randomX = rand.nextInt((size)+1)+1;
        int randomY = rand.nextInt((size)+1)+1;

        Position p = new Position(randomX, randomY);

        return p;
    }

    //returns the tile type
    public Type getTileType(int x, int y){
        return map[x][y].type;
    }
}
