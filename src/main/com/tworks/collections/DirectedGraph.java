package com.tworks.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tworks.collections.exceptions.DuplicateNodeException;
import com.tworks.collections.exceptions.NoSuchNodeException;

/**
 * The data structure for DirectedGraph. Maintains the adjacency Matrix and allows operations on it.
 * 
 * @author VinayG
 */
public class DirectedGraph implements Graph {
	
	//Node name -> matrix index Map. Helps keep track of the index of each Node.
	private Map<String, Integer> nodeIndexMap;
	private int[][] adjacencyMatrix;
	
	/** static factory methods to build directedGraph objects.*/
	public static Graph getGraphFromNodes(List<Node> nodesList) throws DuplicateNodeException{
		return new DirectedGraph(nodesList);
	}
	
	/** static factory methods to build directedGraph objects.*/
	public static Graph getGraphFromEdges(List<Edge> edgeList) throws DuplicateNodeException{
		Set<Node> nodes = new HashSet<Node>();
		for(Edge edge : edgeList){
			nodes.add(edge.getSrcNode());
			nodes.add(edge.getDestNode());
		}
		List<Node> nodesList = new ArrayList<Node>(nodes);
		return new DirectedGraph(nodesList, edgeList);
	}
	
	private DirectedGraph(List<Node> nodesList) throws DuplicateNodeException{
		nodeIndexMap = new HashMap<String, Integer>();
		for(int index = 0; index < nodesList.size(); index++){
			Node node = nodesList.get(index);
			if(nodeIndexMap.containsKey(node.getName())){
				throw new DuplicateNodeException("Node names in the graph must be unique.");
			}
			nodeIndexMap.put(node.getName(), index);
		}
		adjacencyMatrix = new int[nodesList.size()][nodesList.size()];
	}
	
	private DirectedGraph(List<Node> nodesList, List<Edge> edgeList) throws DuplicateNodeException{
		this(nodesList);
		for(Edge edge: edgeList){
			this.addEdgeToGraph(edge.getSrcNode(), edge.getDestNode(), edge.getEdgeWeight());
		}
	}

	@Override
	public void addEdgeToGraph(Edge edge) {
		addEdgeToGraph(edge.getSrcNode(), edge.getDestNode(), edge.getEdgeWeight());
	}
	
	@Override
	public void addEdgeToGraph(Node srcNode, Node destNode, int edgeWeight) {
		int i = getIndexForNode(srcNode);
		int j = getIndexForNode(destNode);
		
		adjacencyMatrix[i][j] = edgeWeight;
	}

	/* Only removes if both nodes are connected by an edge with the specified weight */
	@Override
	public boolean removeEdgeFromGraph(Node srcNode, Node destNode, int edgeWeight) {
		//TODO:  Add validation?
		int i = getIndexForNode(srcNode);
		int j = getIndexForNode(destNode);
		
		if(adjacencyMatrix[i][j] == edgeWeight){
			adjacencyMatrix[i][j] = 0;
			return true;
		}
		return false;
	}

	@Override
	public List<Node> getAdjacentNodes(Node srcNode) throws NoSuchNodeException {
		List<Node> adjacentNodes = new ArrayList<Node>();
		int srcIndex = getIndexForNode(srcNode);
		
		for(int i = 0; i < adjacencyMatrix[srcIndex].length; i++){
			int edgeWeight = adjacencyMatrix[srcIndex][i];
			if(edgeWeight != 0){
				adjacentNodes.add(getNodeForIndex(i));
			}
		}
		return adjacentNodes;
	}
	
	@Override
	public int getWeightForEdge(Node src, Node dest) {
		int srcIndex = getIndexForNode(src);
		int adjacentNodeIndex = getIndexForNode(dest);
		
		if(adjacencyMatrix[srcIndex][adjacentNodeIndex] == 0){
			return -1;
		}
		return adjacencyMatrix[srcIndex][adjacentNodeIndex];
	}
	
	@Override
	public List<Node> getAllNodes() {
		List<Node> nodeList = new ArrayList<Node>();
		for(Entry<String, Integer> entry : nodeIndexMap.entrySet()){
			nodeList.add(new Node(entry.getKey()));
		}
		return nodeList;
	}
	
	private int getIndexForNode(Node node){
		return nodeIndexMap.get(node.getName());
	}
	
	private Node getNodeForIndex(int index) throws NoSuchNodeException{
		if(!nodeIndexMap.containsValue(index)){
			throw new NoSuchNodeException("No node found for index " + index);
		}
		for (Entry<String, Integer> entry : nodeIndexMap.entrySet()) {
	        if (entry.getValue() == index) {
	            return new Node(entry.getKey());
	        }
	    }
	    return null;
	}

	@Override
	public void addNode(Node node) {
		// TODO Nice to have feature
	}

	@Override
	public boolean removeNode() {
		// TODO Nice to have feature
		return false;
	}

}
