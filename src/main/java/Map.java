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

    //map getter
    public Cell [] [] getMap(){
        return map;
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
    public Cell [] [] generateMap(int size, Position winningCell){
        Random rand = new Random();

        //create map 2d array with accepted size
        Cell [] [] myMap = new Cell[size][size];

        //get position of winning cell
        int winningXCoord = winningCell.getX();
        int winningYCoord = winningCell.getY();

        for(int i = 0; i<size; i++){
            for(int j = 0 ; j<size; j++){
                System.out.println("I:" + i + "J: " + j);
                //check if we are in coordinates of winning cell and act accordingly
                if(winningXCoord == i && winningYCoord == j){
                    System.out.println("b");
                    myMap[i][j] = new Cell(Type.TREASURE);
                    System.out.println("a");
                }else{
                    int randomNum = rand.nextInt(2);
                    if(randomNum == 0) {
                        System.out.println("bg");
                        myMap[i][j] = new Cell(Type.GREEN);
                        System.out.println("ag");
                    }else{
                        System.out.println("bb");
                        myMap[i][j] = new Cell(Type.BLUE);
                        System.out.println("ab");
                    }
                }

            }
        }

        return myMap;
    }

    //generates a random winning position and returns it
    public Position getWinningCell(int size){
        Random rand = new Random();

        int randomX = rand.nextInt(size);
        int randomY = rand.nextInt(size);

        Position p = new Position(randomX, randomY);

        return p;
    }

    //returns the tile type
    public Type getTileType(int x, int y){
        return map[x][y].type;
    }
}
