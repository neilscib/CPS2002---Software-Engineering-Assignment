/*import java.util.ArrayList;
import java.util.Set;

//this class should act as watcher part of the observer, however it was not completed due to time restrictions

public class FlagWatcher {

    boolean [] flagArray;
    ArrayList<Observer> observers;
    ArrayList<Set<Integer>> teamPlayers;
    Set<Integer> multipleWinners; //index of winner players

    public FlagWatcher(boolean[] flagArray)
    {
        this.flagArray = new boolean[flagArray.length];

        //arraycopy(src,srcPosition,des,desPosition,length)
        System.arraycopy(flagArray, 0, this.flagArray, 0, flagArray.length);

    }

    public void register(Observer newObserver, int teamNumber)
    {
        observers.add(newObserver);

        teamPlayers.get(teamNumber).add(observers.size()-1);
    }

    public void unregister(Observer observerToDelete)
    {
        int obsIndex = observers.indexOf(observerToDelete);
        observers.remove(obsIndex);
    }

    public void notifyObservers(Set<Integer> multipleWinners)
    {
        for(int i = 0; i < observers.size(); i++)
        {
            if (multipleWinners.contains(i) )
            {
                observers.get(i).update();
                //update() will set the local win from false to true
            }


        }
    }

}*/

