public class Map_Factory {

    private static final int HAZARDOUS = 35;
    private static final int SAFE = 25;

    //pass string type
    public Map getMap(String map_type){
        //return type of map according to parameter
        //prbablities are listed as static variables
        if(map_type.equals("hazardous")){
            return new Map(HAZARDOUS);
        }else if(map_type.equals("safe")){
            return new Map(SAFE);
        }else{
            return null;
        }
    }

}
