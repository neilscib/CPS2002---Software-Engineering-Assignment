import java.io.*;

public class HTMLBuilder {

    BufferedWriter bw = null;
    FileWriter fw = null;

    HTMLBuilder(int numPlayers, Cell [] [] map) {
        String fileName;
        Position tempPos = new Position(-1,-1);

        for(int i = 0; i<numPlayers; i++){
            fileName = "Player"+i+".html";

            writeMapToFile(fileName,map,i,tempPos);
        }

    }

    public void writeMapToFile(String fileName, Cell [] [] map, int playerNumber, Position p){
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "    border: 1px solid black;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>");
            bw.write("<body>\n");
            bw.write("<table>\n");
            bw.write("<tr>\n"+"<th colspan=\"" + map[0].length + "\">" +"Player " + playerNumber + "</th>\n"+"</tr>\n");

            for(int i = 0; i<map[0].length; i++){
                bw.write("<tr>\n");
                for(int j=0;j<map[0].length;j++){
                    //check if the player is in the current position - if he is print salmon and X
                    if(p.getX() == i && p.getY() == j){
                        if(map[i][j].type == Type.TREASURE){
                            bw.write("<td height = 100 width = 100 bgcolor=\"#FFD700\">X</td>\n");
                        }else {
                            bw.write("<td height = 100 width = 100 bgcolor=\"#ff6666\">X</td>\n");
                        }
                    }else {
                        // check if the box is visited or not - if not visited check what type - if visited print gray
                        if (map[i][j].visited) {
                            if (map[i][j].type == Type.TREASURE){
                                bw.write("<td height = 100 width = 100 bgcolor=\"#FFD700\"></td>\n");
                            }else if (map[i][j].type == Type.BLUE){
                                bw.write("<td height = 100 width = 100 bgcolor=\"#0040ff\"></td>\n");
                            }else{
                                bw.write("<td height = 100 width = 100 bgcolor=\"#00b33c\"></td>\n");
                            }
                        }else{
                            bw.write("<td height = 100 width = 100 bgcolor=\"#bfbfbf\"></td>\n");
                        }
                    }
                }
                bw.write("<tr>\n");
            }
            bw.write("</table>\n");
            bw.write("</body>\n");
            bw.write("</html>\n");
        }catch(IOException ioe){
            ioe.printStackTrace();
            System.out.println("ErrorInReadingWritingFile: " + fileName );
        }finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ioe) {

                ioe.printStackTrace();

            }
        }
    }

}
