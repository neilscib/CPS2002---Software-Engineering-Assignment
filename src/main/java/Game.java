import java.util.Scanner;
//import Player.java;
//import Map.java;

public class Game{

    private int turns;
    private Player [] players;
    public Map map;
    public boolean [] treasureFlags; // a boolean for every player, which is true when a certain player has reached the treasure
    public boolean won; //signals whether the whole game has been won or not

    public void startGame(int size, int numPlayers)
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
            players[i] = new Player();
            players[i].setPosition(map.getTreasurePos(), size, map);

            //at startup all the flags are false
            treasureFlags[i] = false;
        }
    }

    public char askDirectionPlayer(int playerNum)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Player " + playerNum + ", your position is: " + players[playerNum].getPosition());
        System.out.println("enter direction:");
        System.out.println("Up (u) Down (d) Left (l) Right (r)");

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

    public Player getPlayer(int arrayIndex)
    {
        return players[arrayIndex];
    }


    /*
    public boolean setNumPlayers(int n)
    {

    }
    */

    public void generateHTMLFiles()
    {

    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.map = new Map();

        int numPlayers = 0;
        int size = 0;

        //The user will keep on being prompted to enter the size and the number of players until they are valid.
        System.out.println("Enter number of players:");
        numPlayers = in.nextInt();
        System.out.println("Enter size of map (n x n):");
        size = in.nextInt();

        //while they are not valid
        while (!game.map.setMapSize(size, numPlayers))
        {
            System.out.println("Try again:");

            System.out.println("Enter number of players:");
            numPlayers = in.nextInt();
            System.out.println("Enter size of map (n x n):");
            size = in.nextInt();
        }

        game.startGame(size, numPlayers);

        System.out.println("Treasure is at: " + game.map.getTreasurePos());

        //while the game has not been won
        while (!game.won)
        {
            char direction = 'e';

            //prompting every user to enter 'u','d','l' or 'r', and then tell him whether it was a valid move or not
            for (int i = 0; i < game.getNumPlayers(); i++)
            {
                System.out.println();

                direction = game.askDirectionPlayer(i);

                //setting the boolean 'visited' of the new position as true, in the private copy of the map (this is happening in move())
                if (!game.getPlayer(i).move(direction, size))
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


                //updating the HTML files
                game.generateHTMLFiles();

                //if the position of this player is the same as the winning position, then set his treasure flag as true
                if ((game.map.getTreasurePos().getX() == tempPos.getX()) && (game.map.getTreasurePos().getY() == tempPos.getY()))
                    game.treasureFlags[i] = true;
                else if (game.map.getTileType(tempPos.getX(), tempPos.getY()) == Type.BLUE)
                {
                    game.players[i].moveToInitial();
                    System.out.println("Player " + i + " you moved on a water tile " + tempPos +" ! You have been sent back to the initial position " + game.players[i].getInitial());
                }
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