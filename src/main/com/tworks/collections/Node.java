package com.tworks.collections;

/**
 * Represents the node in the graph. 
 * Assumes the name to be unique.
 * 
 * @author VinayG
 */
public class Node {
	
	private String name;

	public Node(String name){
		this.name = name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof Node){
			Node other = (Node) object;
			if(this == other || this.name.equals(other.name)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}
}
