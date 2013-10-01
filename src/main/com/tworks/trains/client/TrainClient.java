package com.tworks.trains.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tworks.collections.Graph;
import com.tworks.collections.exceptions.DuplicateNodeException;
import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.Path;
import com.tworks.trains.TrainClientFileHelper;
import com.tworks.trains.TrainClientGraphHelper;

/**
 * The console based interactive UI for trying checking the various options provided by the entered map.
 * 
 * @author VinayG
 */
public class TrainClient {
	
	Graph graph = null;

	public static void main(String args[]) throws IOException, DuplicateNodeException {
		TrainClient trainClient = new TrainClient();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try{
			trainClient.runPreliminary(bufferedReader);
			boolean dontStop = true;
			while(dontStop){
				trainClient.showMenu();
				String selectedOption = null;
	
				selectedOption = bufferedReader.readLine();
				int selectedOptionValue = Integer.valueOf(selectedOption).intValue();
				switch(selectedOptionValue){
				
					case 1: trainClient.specificRouteProblem(bufferedReader);
							dontStop = trainClient.continueExploring(bufferedReader);
							break;
					case 2: trainClient.shortestPathProblem(bufferedReader);
							dontStop = trainClient.continueExploring(bufferedReader);
							break;
					case 3: trainClient.numberOfTripsProblem(bufferedReader);
							dontStop = trainClient.continueExploring(bufferedReader);
							break;
					default:System.out.println("Invalid option....please try again.");
							break;
				}
			}
		} catch(NumberFormatException nfe){
			System.out.println("Invalid entry....please start again.");
			bufferedReader.close();
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.out.println("No such file....please start again.");
			bufferedReader.close();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("IO error....please start again.");
			bufferedReader.close();
			System.exit(1);
		} catch (NoSuchNodeException e) {
			System.out.println("No such node....please start again.");
			bufferedReader.close();
			System.exit(1);
		} 
	}

	private boolean continueExploring(BufferedReader bufferedReader) throws IOException{
		System.out.println("If you would like to continue exploring hit 'y' else hit any key.");
		String selectedRoute = bufferedReader.readLine();
		if(selectedRoute.trim().equalsIgnoreCase("y")){
			return true;
		}
		return false;
	}

	private void specificRouteProblem(BufferedReader bufferedReader) throws IOException{
		System.out.println("\n You chose: Find distance of a specific route.");
		System.out.println(" Please enter the cities as a single word e.g: ABC ");

		String selectedRoute = bufferedReader.readLine();
		int cost = TrainClientGraphHelper.findCostOfGivenPath(graph, selectedRoute.trim());
		if(cost == -1){
			System.out.println("No such path: " + selectedRoute);
		} else {
			System.out.println("Cost of " + selectedRoute +": " + cost);
		}
	}

	private void shortestPathProblem(BufferedReader bufferedReader) throws IOException, NoSuchNodeException{
		System.out.println("\n You chose: Find length of shortest path netween 2 cities.");
		System.out.println(" Please enter the cities as a single word e.g: ABC ");

		String selectedRoute = bufferedReader.readLine();
		if(selectedRoute.trim().length() != 2){
			System.out.println("Invalid option....please start again.");
			return;
		}
		Path path = TrainClientGraphHelper.getShortestPathBetweenTwoCities(graph, selectedRoute.trim());
		if(path.getCost() == -1){
			System.out.println("No such path: " + selectedRoute);
		} else {
			System.out.println("Shortest Path: " +path.toString());
		}
	}

	private void numberOfTripsProblem(BufferedReader bufferedReader) throws IOException, NumberFormatException, NoSuchNodeException{
		showNumberOfTripsMenu();

		String selectedOption = bufferedReader.readLine();
		int selectedOptionValue = Integer.valueOf(selectedOption).intValue();
		
		System.out.println(" Please enter the value for cost/hops: ");
		String selectedLimitValue = bufferedReader.readLine();
		int selectedLimitIntValue = Integer.valueOf(selectedLimitValue).intValue();
		
		System.out.println(" Please enter the cities as a single word e.g: ABC ");
		String selectedRoute = bufferedReader.readLine();
		if(selectedRoute.trim().length() != 2){
			selectedOptionValue = -1;
		}
		
		List<Path> pathList = new ArrayList<Path>();
		switch(selectedOptionValue){
			case 1: pathList = TrainClientGraphHelper.getAllPathsUsingLTEHopLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;
			case 2: pathList = TrainClientGraphHelper.getAllPathsUsingLTECostLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;
			case 3: pathList = TrainClientGraphHelper.getAllPathsUsingExactHopLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;
			case 4: pathList = TrainClientGraphHelper.getAllPathsUsingExactCostLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;	
			case 5: pathList = TrainClientGraphHelper.getAllPathsUsingLTHopLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;
			case 6: pathList = TrainClientGraphHelper.getAllPathsUsingLTCostLimit(graph, selectedLimitIntValue, selectedRoute.trim());
					break;
			default:System.out.println("Invalid option....please start again.");
					return;
		}
		
		if(pathList.size() > 0){
			System.out.println(" Number of paths: " + pathList.size());
			for(int i = 0; i < pathList.size(); i++){
				System.out.println("Path " + (i + 1) + ":" + pathList.get(i).toString());
			}
		} else {
			System.out.println(" No such path: " + selectedRoute);
		}
	}

	private void runPreliminary(BufferedReader bufferedReader) throws FileNotFoundException, IOException, DuplicateNodeException {
		System.out.print("\n Enter file name: ");
		String fileName = bufferedReader.readLine();
		String fileContents = TrainClientFileHelper.getFileContents(fileName);
		
		String[] arrayOfFileContents = TrainClientFileHelper.processFileContents(fileContents);
		graph = TrainClientGraphHelper.buildGraphFromFileContents(arrayOfFileContents);
		
		if(graph != null){
			System.out.println("\n Map was successfully created! You map has the following cities: " + graph.getAllNodes());
		} else {
			System.out.println("You've reached where no man has reached before...please report this issue!");
			System.exit(1);
		}
	}

	private void showMenu() {
		System.out.println("\n Choose an operation by entering its number: ");
		System.out.println("  1. Find distance of a specific route ");
		System.out.println("  2. Find length of shortest path netween 2 cities ");
		System.out.println("  3. Find number of trips between cities");		
	}
	
	private void showNumberOfTripsMenu() {
		System.out.println(" You chose: Find number of trips between cities.");
		System.out.println(" Choose a condition by entering its number: ");
		System.out.println("  1. Max number of hops ");
		System.out.println("  2. Max cost ");
		System.out.println("  3. Exact number of hops ");	
		System.out.println("  4. Exact cost ");	
		System.out.println("  5. Lesser than given number of hops ");	
		System.out.println("  6. Lesser than given cost ");			
	}
	
}
