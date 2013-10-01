package com.tworks.collections.utils;

import java.util.ArrayList;
import java.util.List;

import com.tworks.collections.Node;

/**
 * POJO Class used to capture graph traversal results.
 * This is designed to be directly used by the end clients.
 * 
 * @author VinayG
 */
public class Path{
	private int cost;
	private List<Node> nodeList = new ArrayList<Node>();

	public Path(List<Node> listOfNodes, int cost){
		for(Node node: listOfNodes){
			this.nodeList.add(node);
		}
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Node node: nodeList){
			sb.append(node);
		}
		sb.append("   Distance: " + cost);
		return sb.toString();
	}
}
