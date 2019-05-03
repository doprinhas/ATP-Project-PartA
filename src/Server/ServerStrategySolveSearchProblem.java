package Server;

import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchable;
import algorithms.search.Solution;

import java.io.*;
import java.time.chrono.IsoChronology;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private ASearchingAlgorithm algorithm;

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient){

        ISearchable prob = getProblem(inFromClient);
        ASearchingAlgorithm algorithm = new BestFirstSearch();
        Solution solution = algorithm.solve(prob);
        setSolutionToOutputStream(outToClient , solution);

    }

    private ISearchable getProblem (InputStream stream){
        try {
            ObjectInputStream fromClient = new ObjectInputStream(stream);
            ISearchable prob = (ISearchable) fromClient.readObject();
            fromClient.close();
            return prob;
        } catch (IOException e){
            System.out.println("The parameters aren't valid");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("The sent problem is not valid");
            e.printStackTrace();
        }
        return null;
    }

    private void setSolutionToOutputStream(OutputStream outToClient , Solution solution){
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.writeObject(solution);
            toClient.flush();
            toClient.close();
        } catch (IOException e){
            System.out.println("The parameters aren't valid");
            e.printStackTrace();
        }
    }
}
