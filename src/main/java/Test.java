public class Test {
    public static void main(String [] args){
        Map m = new Map();

        m.setSize(5);
        System.out.println("Map");
        Cell [] [] myMap = m.getMap();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <5 ; j++) {
                Type myType = myMap[i][j].type;
                if (myType == Type.TREASURE){
                    System.out.println("T");
                }else if (myType == Type.BLUE){
                    System.out.println("B");
                }else{
                    System.out.println("G");
                }
            }

        }
    }
}
