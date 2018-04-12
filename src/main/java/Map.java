public class Map {
    private int size;
    private Cell myMap

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

    public void setSize(int size) {
        this.size = size;
    }

    public void generateMap(){
        private Cell myMap [this.size] [this.size];
    }
}
