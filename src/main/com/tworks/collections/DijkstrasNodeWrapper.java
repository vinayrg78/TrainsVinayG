package com.tworks.collections;

/*
 * Used exclusively by Graphs for running Dijkstras. Hence package scoped.
 */
class DijkstrasNodeWrapper implements Comparable<DijkstrasNodeWrapper>{
	private int totalCostFromSource;
	private Node node;
	private Node parentNode;
	
	public DijkstrasNodeWrapper(Node node, Node parentNode, int totalCostFromSource){
		this.node = node;
		this.parentNode = parentNode;
		this.totalCostFromSource = totalCostFromSource;
	}
	
	public DijkstrasNodeWrapper(DijkstrasNodeWrapper dijkstrasNodeWrapper) {
		this.node = dijkstrasNodeWrapper.node;
		this.parentNode = dijkstrasNodeWrapper.parentNode;
		this.totalCostFromSource = dijkstrasNodeWrapper.totalCostFromSource;
	}

	public DijkstrasNodeWrapper(Node sourceNode) {
		this.node = sourceNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DijkstrasNodeWrapper other = (DijkstrasNodeWrapper) obj;
		if (!node.equals(other.node))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(DijkstrasNodeWrapper other) {
		return this.totalCostFromSource - other.totalCostFromSource;
	}

	public int getTotalCostFromSource() {
		return totalCostFromSource;
	}

	public void setTotalCostFromSource(int totalCostFromSource) {
		this.totalCostFromSource = totalCostFromSource;
	}

	public Node getNode() {
		return node;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public String toString(){
		return node.getName() + parentNode.getName() + totalCostFromSource;
	}
} 
