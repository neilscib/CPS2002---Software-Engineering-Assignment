public class Cell{
    //Holds whether the current cell has been visited by the player or not.
    public boolean visited;
    //This holds whether the cell is a grass, water, or treasure cell.
    public Type type;

    Cell(Type type){
        this.type = type;
        visited = false;
    }
}