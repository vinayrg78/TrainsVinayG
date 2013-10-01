package com.tworks.trains;

import java.util.ArrayList;
import java.util.List;

import com.tworks.collections.DirectedGraph;
import com.tworks.collections.Edge;
import com.tworks.collections.Graph;
import com.tworks.collections.Graphs;
import com.tworks.collections.Node;
import com.tworks.collections.exceptions.DuplicateNodeException;
import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.GraphTraversalComparator;
import com.tworks.collections.utils.Path;

/**
 * This is the helper class to help the TrainClient with all the Graph related activities.
 * 
 * @author VinayG
 */
public class TrainClientGraphHelper {

	public static Graph buildGraphFromEdgeArray(List<Edge> edgeList) throws DuplicateNodeException{
		Graph kiwiLandGraph = DirectedGraph.getGraphFromEdges(edgeList);
		return kiwiLandGraph;
	}
	
	public static List<Edge> buildEdgeListForGraph(String[] edgeArray) throws NumberFormatException{
		List<Edge> edgeList = new ArrayList<Edge>();
		for(String edgeWithWeight : edgeArray){
			edgeWithWeight = edgeWithWeight.trim();
			String[] partsOfEdgeData = edgeWithWeight.split("");
			
			//String leadingSpace = partsOfEdgeData[0];
			String src = partsOfEdgeData[1];
			String dest = partsOfEdgeData[2];
			//int edgeWeight = new Integer(partsOfEdgeData[3]).intValue();
			//critical assumption that cities are always single letters.
			int edgeWeight = new Integer(edgeWithWeight.substring(2)).intValue();

			edgeList.add(new Edge(new Node(src), new Node(dest), edgeWeight));
		}
		return edgeList;
	}
	
	public static Graph buildGraphFromFileContents(String[] edgeArray) throws DuplicateNodeException{
		List<Edge> edgeList = buildEdgeListForGraph(edgeArray);
	    return buildGraphFromEdgeArray(edgeList);
	}
	
	public static int findCostOfGivenPath(Graph graph, String path){
		int cost = 0;
		List<Node> nodeList = getNodesFromString(path);
		
		for(int i = 0 ; i < nodeList.size()-1; i++){
			Node src = nodeList.get(i);
			Node dest = nodeList.get(i+1);
			
			if(graph.getWeightForEdge(src, dest) == -1){
				return -1;
			} 
			cost += graph.getWeightForEdge(src, dest);
		}
		return cost;
	}

	public static Path getShortestPathBetweenTwoCities(Graph graph, String path) throws NoSuchNodeException{
		List<Node> nodeList = getNodesFromString(path);

		Path shortestPath = Graphs.runDijkstras(graph, nodeList.get(0), nodeList.get(1));
		return shortestPath;
	}
	
	
	public static List<Path> getAllPathsUsingLTEHopLimit(Graph graph, int hopLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new HopBasedLTEGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}
	
	public static List<Path> getAllPathsUsingLTECostLimit(Graph graph, int costLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new CostBasedLTEGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}
	
	public static List<Path> getAllPathsUsingExactHopLimit(Graph graph, int hopLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new HopBasedExactGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}
	
	public static List<Path> getAllPathsUsingExactCostLimit(Graph graph, int costLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new CostBasedExactGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}

	public static List<Path> getAllPathsUsingLTHopLimit(Graph graph, int costLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new HopBasedLTGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}
	
	public static List<Path> getAllPathsUsingLTCostLimit(Graph graph, int costLimit, String path) throws NoSuchNodeException {
		List<Node> nodeList = getNodesFromString(path);

		GraphTraversalComparator gtc = new CostBasedLTGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(graph, gtc, nodeList.get(0), nodeList.get(1));
		return pList;
	}
	
	public static List<Node> getNodesFromString(String path){
		String[] partsOfEdgeData = path.trim().split("");
		List<Node> nodeList = new ArrayList<Node>();
		for(int i = 1 ; i < partsOfEdgeData.length; i++){
			nodeList.add(new Node(partsOfEdgeData[i]));
		}
		return nodeList;
	}

}
