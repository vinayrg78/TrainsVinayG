package com.tworks.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.GraphTraversalComparator;
import com.tworks.collections.utils.Path;

/**
 * Utility class with static methods for graph operations. 
 * Much like the Collections class from Java library.
 * 
 * @author VinayG
 */
public class Graphs {
	
	public static final int DEFAULT_COST_FOR_DIJKSTRAS = 1000;
	private static final int DEFAULT_COST_FOR_DIJKSTRAS_SOURCE = 0;
	
	public static List<Path> runDfs(Graph graph, GraphTraversalComparator graphTraversalComparator, Node sourceNode, Node destinationNode) throws NoSuchNodeException{
		List<Path> listOfPossiblePaths = new ArrayList<Path>();
		List<Node> currentPath = new ArrayList<Node>();
		currentPath.add(sourceNode);
		
		dfsVisit(graph, graphTraversalComparator, sourceNode, destinationNode, currentPath, 0, listOfPossiblePaths);
		return listOfPossiblePaths;
	}
	
	//FIXME: Try to reduce parameter list in method signature (without introducing state)! 
	private static void dfsVisit(Graph graph, GraphTraversalComparator graphTraversalComparator, Node sourceNode, Node destinationNode, 
											List<Node> currentPath, int currentCost, List<Path> listOfPossiblePaths) throws NoSuchNodeException{

		for(Node adjacentNode : graph.getAdjacentNodes(sourceNode)){
			int currentEdgeWeight = graph.getWeightForEdge(sourceNode, adjacentNode);
			currentCost = graphTraversalComparator.incrementCostBasedOnSetting(currentCost, currentEdgeWeight);
			
			if(!graphTraversalComparator.isCostWithinLimit(currentCost)){
				currentCost = graphTraversalComparator.decrementCostBasedOnSetting(currentCost, currentEdgeWeight);
				continue;
			} else {
				currentPath.add(adjacentNode);
				if(adjacentNode.equals(destinationNode)){
					//found valid path
					if(graphTraversalComparator.doesCostMatchLimit(currentCost)){
						listOfPossiblePaths.add(new Path(currentPath, currentCost));
						//System.out.println("Path found: "+ currentPath.toString() + " Cost: "+currentCost);
					}
				}
				//still not exceeded cost....continue recursively
				dfsVisit(graph, graphTraversalComparator, adjacentNode, destinationNode, currentPath, currentCost, listOfPossiblePaths);
				currentPath.remove(currentPath.lastIndexOf(adjacentNode));
				currentCost = graphTraversalComparator.decrementCostBasedOnSetting(currentCost, currentEdgeWeight);
			}
		}
	}
	
	
	public static Path runDijkstras(Graph graph, Node sourceNode, Node destinationNode) throws NoSuchNodeException{
		boolean isCyclic = false;
		if(sourceNode.equals(destinationNode)){
			isCyclic = true;
		}
		List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList = initializeDijkstrasFindings(graph, sourceNode, destinationNode);
		PriorityQueue<DijkstrasNodeWrapper> priorityQueue = getPriorityQueueInitializedForDijkstras(sourceNode, dijkstrasNodeWrapperList);
		while(!priorityQueue.isEmpty()){
			DijkstrasNodeWrapper currentNodeWrapper = priorityQueue.poll();
			List<Node> adjacentNodes = graph.getAdjacentNodes(currentNodeWrapper.getNode());
			for(Node newlyDiscoveredNode : adjacentNodes){
				DijkstrasNodeWrapper newlyDiscoveredNodeWrapper = getDijkstrasNodeWrapperForNode(dijkstrasNodeWrapperList, newlyDiscoveredNode, currentNodeWrapper.getNode());
				int weightOfNewlyDiscoveredEdge = graph.getWeightForEdge(currentNodeWrapper.getNode(), newlyDiscoveredNodeWrapper.getNode());
				if(isCyclic){
			    	relaxCyclic(currentNodeWrapper, newlyDiscoveredNodeWrapper, weightOfNewlyDiscoveredEdge, dijkstrasNodeWrapperList, priorityQueue);
				} else {
					relax(currentNodeWrapper, newlyDiscoveredNodeWrapper, weightOfNewlyDiscoveredEdge, dijkstrasNodeWrapperList, priorityQueue);
				}
			}
		}
    	return processDijkstrasResults(sourceNode, destinationNode, dijkstrasNodeWrapperList);
	}
	
	private static Path processDijkstrasResults(Node sourceNode, Node destinationNode, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList){
    	List<Node> nodesAlongShortestPath = getNodesForDetectedShortestPath(sourceNode, destinationNode, dijkstrasNodeWrapperList);
    	int cost = -1;
    	if(nodesAlongShortestPath.size() > 0){
    		cost = dijkstrasNodeWrapperList.get(dijkstrasNodeWrapperList.indexOf(new DijkstrasNodeWrapper(destinationNode))).getTotalCostFromSource();
    	}
    	Path discoveredShortestPath = new Path(nodesAlongShortestPath, cost);
    	return discoveredShortestPath;
	}
	
	private static List<Node> getNodesForDetectedShortestPath(Node sourceNode, Node destinationNode, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList) {
    	List<Node> nodePath = new ArrayList<Node>();
    	nodePath.add(new Node(destinationNode.getName()));
    	nodePath = getShortestPath(sourceNode, destinationNode, dijkstrasNodeWrapperList, nodePath);
    	return nodePath;
	}

	private static DijkstrasNodeWrapper getDijkstrasNodeWrapperForNode(List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList, Node node, Node parentNode){
		int index = dijkstrasNodeWrapperList.indexOf(new DijkstrasNodeWrapper(node, parentNode, 0));
		return dijkstrasNodeWrapperList.get(index);
	}
	
	/* Initializes the List of DijkstrasNodeWrappers, setting their parents as null and default distance to source as DEFAULT_COST_FOR_DIJKSTRAS
	 * We start with the source cost set to 0. */
	private static List<DijkstrasNodeWrapper> initializeDijkstrasFindings(Graph graph, Node sourceNode, Node destinationNode){
		List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList = new ArrayList<DijkstrasNodeWrapper>();
		DijkstrasNodeWrapper v;
		List<Node> nodeList = graph.getAllNodes();
		for(int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);
			if(node.equals(sourceNode)){
				v = new DijkstrasNodeWrapper(node, null, DEFAULT_COST_FOR_DIJKSTRAS_SOURCE);
			} else {
				v = new DijkstrasNodeWrapper(node, null, DEFAULT_COST_FOR_DIJKSTRAS);
			}
			dijkstrasNodeWrapperList.add(v);
		}
		return dijkstrasNodeWrapperList;
	}
	
	/* Initializes the PQ with all nodes, as in the DijkstrasNodeWrapperList */
	private static PriorityQueue<DijkstrasNodeWrapper> getPriorityQueueInitializedForDijkstras(Node srcNode, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList) {
		PriorityQueue<DijkstrasNodeWrapper> priorityQueue = new PriorityQueue<DijkstrasNodeWrapper>(dijkstrasNodeWrapperList.size());
		for(int i = 0; i < dijkstrasNodeWrapperList.size(); i++){
			priorityQueue.offer(dijkstrasNodeWrapperList.get(i));
		}
		return priorityQueue;
	}

	/* The famous Dijkstra's relax.*/
	private static void relax(DijkstrasNodeWrapper sourceNodeWrapper, DijkstrasNodeWrapper destinationNodeWrapper, int weightOfNewlyDiscoveredEdge, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList, PriorityQueue<DijkstrasNodeWrapper> pq){
		if(destinationNodeWrapper.getTotalCostFromSource() > sourceNodeWrapper.getTotalCostFromSource() + weightOfNewlyDiscoveredEdge){
			destinationNodeWrapper.setTotalCostFromSource(sourceNodeWrapper.getTotalCostFromSource() + weightOfNewlyDiscoveredEdge);
			destinationNodeWrapper.setParentNode(sourceNodeWrapper.getNode());
		    pq.remove(destinationNodeWrapper);
			pq.offer(destinationNodeWrapper);
		}
	}

	/* The famous Dijkstra's relax, tweaked for cyclic cases.*/
	private static void relaxCyclic(DijkstrasNodeWrapper sourceNodeWrapper, DijkstrasNodeWrapper destinationNodeWrapper, int weightOfNewlyDiscoveredEdge, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList, PriorityQueue<DijkstrasNodeWrapper> pq){
		if(destinationNodeWrapper.getTotalCostFromSource() > sourceNodeWrapper.getTotalCostFromSource() + weightOfNewlyDiscoveredEdge ||
				destinationNodeWrapper.getTotalCostFromSource() == 0){
			destinationNodeWrapper.setTotalCostFromSource(sourceNodeWrapper.getTotalCostFromSource() + weightOfNewlyDiscoveredEdge);
			destinationNodeWrapper.setParentNode(sourceNodeWrapper.getNode());
		    pq.remove(destinationNodeWrapper);
			pq.offer(destinationNodeWrapper);
		}
	}

	/* returns empty list in case there is no such path. */
	private static List<Node> getShortestPath(Node sourceNode, Node destinationNode, List<DijkstrasNodeWrapper> dijkstrasNodeWrapperList, List<Node> nodeList){
		int srcIndex = dijkstrasNodeWrapperList.indexOf(new DijkstrasNodeWrapper(sourceNode));
		int destIndex = dijkstrasNodeWrapperList.indexOf(new DijkstrasNodeWrapper(destinationNode));
		
		if(dijkstrasNodeWrapperList.get(srcIndex).getNode().equals(dijkstrasNodeWrapperList.get(destIndex).getParentNode())){
			nodeList.add(0, dijkstrasNodeWrapperList.get(srcIndex).getNode());
			return nodeList;
		}
		if(dijkstrasNodeWrapperList.get(destIndex).getParentNode() == null){
			//No such path exists.
			return new ArrayList<Node>();
		} else {
			nodeList.add(0, dijkstrasNodeWrapperList.get(destIndex).getParentNode());
			return getShortestPath(sourceNode, dijkstrasNodeWrapperList.get(destIndex).getParentNode(), dijkstrasNodeWrapperList, nodeList);
		}
	}

}
