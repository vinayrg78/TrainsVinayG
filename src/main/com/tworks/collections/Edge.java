package com.tworks.collections;

/**
 * Represents a typical edge in the graph.
 * @author VinayG
 */
public class Edge {

	private Node srcNode;
	private Node destNode;
	private int edgeWeight;
	
	public Edge(Node srcNode, Node destNode, int edgeWeight){
		this.srcNode = srcNode;
		this.destNode = destNode;
		this.edgeWeight = edgeWeight;
	}
	
	public Node getSrcNode() {
		return srcNode;
	}
	public void setSrcNode(Node srcNode) {
		this.srcNode = srcNode;
	}
	public Node getDestNode() {
		return destNode;
	}
	public void setDestNode(Node destNode) {
		this.destNode = destNode;
	}
	public int getEdgeWeight() {
		return edgeWeight;
	}
	public void setEdgeWeight(int edgeWeight) {
		this.edgeWeight = edgeWeight;
	}
}
