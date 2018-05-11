import java.util.Scanner;

//REMAINING THINGS TO DO:
//	UNIT TESTING FOR GAME.JAVA
// Water Tile Bug - After passing over it the first time the game lets you go over it in the next iteration

public class Game{

    private int turns;
    private Player [] players;
    private Map map;
    private boolean [] treasureFlags; // a boolean for every player, which is true when a certain player has reached the treasure
    private boolean won; //signals whether the whole game has been won or not
    //construct to build map
    private Map_Factory map_factory = new Map_Factory();


    //constructor
    public Game(String map_type)
    {
        map = map_factory.getMap(map_type);
        won = false;
    }

    public Map getMap()
    {
        return map;
    }

    public  void startGame(int size, int numPlayers, Map map_instance)
    {
        //on startup the map with the given size is generated 
        map.setSize(size);

        //an array of players is created
        players = new Player[numPlayers];

        treasureFlags = new boolean[numPlayers];
        won = false;

        //creating the new position for every player
        for(int i = 0; i < numPlayers; i++)
        {
            players[i] = new Player(map.getCopyOfMap(), map_instance);
            players[i].setPosition(map.getTreasurePos(),size);
            players[i].setInitialPos();

            //at startup all the flags are false
            treasureFlags[i] = false;
        }
    }

    //asks player for direction and return the character
    public char askDirectionPlayer(int playerNum)
    {
        Scanner in = new Scanner(System.in);

        char direction = in.next().charAt(0);

        //since the Player.move() only recognizes u,d,l,r
        switch(direction)
        {
            case 'U':
            case 'u':
                return 'u';
            case 'D':
            case 'd':
                return 'd';
            case 'L':
            case 'l':
                return 'l';
            case 'R':
            case 'r':
                return 'r';
        }

        return 'e';
    }

    public int getNumPlayers()
    {
        return players.length;
    }


    /*
    public boolean setNumPlayers(int n)
    {

    }
    */

    //generates an HTML File per player
    private void generateHTMLFiles(HTMLBuilder builder, Player[] listOfPlayers)
    {
        int counter = 0;
        for(Player player : listOfPlayers){
            builder.writeMapToFile("Player"+counter+".html", player.getMap(), counter, player.getPosition());
            counter+=1;
        }
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        //game.map = new Map();

        int numPlayers;
        int size;
        //1 = safe || 2 hazardous
        int map_type;
        //will hold type of map
        String type_of_map;

        //The user will keep on being prompted to enter the size and the number of players until they are valid.
        System.out.println("Enter number of players:");
        numPlayers = in.nextInt();
        System.out.println("Enter size of map (n x n):");
        size = in.nextInt();
        System.out.println("Enter type of map:\n");
        System.out.println("Press 1 if safe or 2 if hazardous:");
        map_type = in.nextInt();

        switch(map_type){
            case 1:
                type_of_map = "hazardous";
                break;
            case 2:
                type_of_map = "safe";
                break;
            default:
                System.out.println("Invalid choice! Safe was automatically chosen for you.");
                type_of_map = "safe";
                break;
        }

        Game game = new Game(type_of_map);

        //while they are not valid
        while (!game.map.setMapSize(size, numPlayers))
        {
            System.out.println("Try again:");

            System.out.println("Enter number of players:");
            numPlayers = in.nextInt();
            System.out.println("Enter size of map (n x n):");
            size = in.nextInt();
        }

        game.startGame(size,numPlayers, game.map);

        //starting HTML Builder
        HTMLBuilder builder = new HTMLBuilder(numPlayers, game.map.getMap());

        //System.out.println("Treasure is at: " + game.map.getTreasurePos());

        //while the game has not been won
        while (!game.won)
        {
            char direction;

            //prompting every user to enter 'u','d','l' or 'r', and then tell him whether it was a valid move or not
            for (int i = 0; i < game.getNumPlayers(); i++)
            {
                System.out.println();

                //System.out.println("Player " + i + ", your position is: " + players[i].getPosition());
                System.out.println("Enter direction:");
                System.out.println("Up (u) Down (d) Left (l) Right (r)");
                direction = game.askDirectionPlayer(i);

                //setting the boolean 'visited' of the new position as true, in the private copy of the map (this is happening in move())
                if (!game.players[i].move(direction, size))
                    System.out.println("That was an invalid move");
                else
                    System.out.println("Your new position is: " + game.players[i].getPosition());
            }

            //for each player we are uncovering the tile, checking whether the tile is the winning tile, a water tile or just a grass tile
            Position tempPos;
            for (int i = 0; i < game.getNumPlayers(); i++)
            {
                tempPos = game.players[i].getPosition();

                //
                //move returns false when the move ends up out of bounds of the map.
                //if(!game.players[i].move(direction, size))
                    //System.out.println("That move is invalid, out of bounds of the map!");


                //if the position of this player is the same as the winning position, then set his treasure flag as true
                if ((game.map.getTreasurePos().getX() == tempPos.getX()) && (game.map.getTreasurePos().getY() == tempPos.getY()))
                    game.treasureFlags[i] = true;
                else if (game.map.getTileType(tempPos.getX(), tempPos.getY(), game.players[i].getMap()) == Type.BLUE)
                {
                    game.players[i].moveToInitial();
                    System.out.println("Player " + i + " you moved on a water tile " + tempPos +" ! You have been sent back to the initial position " + game.players[i].getInitial());
                }

                //updating the HTML files
                game.generateHTMLFiles(builder, game.players);
            }

            //then after we uncovered the tiles of the players, we are going to check declare the winners and stop the game.
            for (int i = 0; i < game.getNumPlayers(); i++) {
                if (game.treasureFlags[i]) {
                    System.out.println("Well done 'Player " + i + "', you have won!");
                    game.won = true;
                }
            }

        }




    }
}