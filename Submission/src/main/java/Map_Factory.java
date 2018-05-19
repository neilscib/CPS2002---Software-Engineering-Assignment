import java.util.Random;

public class Map_Factory {

    private Random rand = new Random();

    private int HAZARDOUS = rand.nextInt(11)+25;
    private static final int SAFE = 10;

    //pass string type
    public Map getMap(String map_type){
        //return type of map according to parameter
        //probablities are listed as static variables
        if(map_type.equals("hazardous")){
            return Map.getMapInstance(HAZARDOUS);
        }else if(map_type.equals("safe")){
            return Map.getMapInstance(SAFE);
        }else{
            return null;
        }
    }

}
