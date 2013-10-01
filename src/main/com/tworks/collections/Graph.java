package com.tworks.collections;

import java.util.List;

import com.tworks.collections.exceptions.NoSuchNodeException;

public interface Graph {

	public abstract void addNode(Node node);
	
	public abstract void addEdgeToGraph(Edge edge);
	
	public abstract void addEdgeToGraph(Node srcNode, Node destNode, int edgeWeight);
	
	public abstract boolean removeNode();
	
	public abstract boolean removeEdgeFromGraph(Node srcNode, Node destNode, int edgeWeight);
	
	public abstract List<Node> getAdjacentNodes(Node srcNode) throws NoSuchNodeException;
	
	public abstract int getWeightForEdge(Node src, Node dest);
	
	public abstract List<Node> getAllNodes();

}
