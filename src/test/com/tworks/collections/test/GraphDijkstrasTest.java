package com.tworks.collections.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tworks.collections.Graphs;
import com.tworks.collections.Node;
import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.Path;


public class GraphDijkstrasTest extends AbstractGraphsTest{

	//TW graph tests.
	//Case 8
	@Test
	public void testShortestPathBetweenNodes() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("A"), new Node("C"));
		assertEquals(9, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	//Case 9
	@Test
	public void testStillAnotherPathToSelf() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("B"), new Node("B"));
		assertEquals(9, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	//Case 10
	@Test
	public void testShortestPathToSelf() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("E"), new Node("E"));
		assertEquals(9, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testAnotherPathToSelf() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("D"), new Node("D"));
		assertEquals(16, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testNonExistantShortestPathToSelf() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("A"), new Node("A"));
		assertEquals(-1, shortestPath.getCost());
		if(shortestPath.getCost() == -1 && shortestPath.getNodeList().size() == 0){
			System.out.println("No such path.");
		} else {
			fail("Should have returned -1.");
		}
	}
	
	@Test
	public void testShortestPathBetweenAdjacentNodes() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("E"), new Node("B"));
		assertEquals(3, shortestPath.getCost());
		System.out.println(shortestPath);
	}

	@Test
	public void testShortestPathBetweenNonAdjacentNodes() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("E"), new Node("C"));
		assertEquals(7, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenRatherDistantNodes() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("E"), new Node("D"));
		assertEquals(15, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testNonExistantShortestPathBetweenNodes() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(twGraph, new Node("E"), new Node("A"));
		assertEquals(-1, shortestPath.getCost());
		if(shortestPath.getCost() == -1 && shortestPath.getNodeList().size() == 0){
			System.out.println("No such path.");
		} else {
			fail("Should have returned -1.");
		}
	}
	
	//HomeGrownGraphTests
	@Test
	public void testShortestPathBetweenAdjacentNodesForHG() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph, new Node("A"), new Node("G"));
		assertEquals(10, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenNodesForHG() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph, new Node("F"), new Node("C"));
		assertEquals(22, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenRandomNodesForHG() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph, new Node("E"), new Node("H"));
		assertEquals(8, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenTwoNodesForHG() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph, new Node("H"), new Node("C"));
		assertEquals(11, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	//HomeGrownGraphTest2
	@Test
	public void testShortestPathBetweenNodesForHG2() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph2, new Node("B"), new Node("I"));
		assertEquals(8, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenTwoNodesForHG2() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph2, new Node("B"), new Node("I"));
		assertEquals(8, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testShortestPathBetweenToSelfForHG2() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph2, new Node("A"), new Node("A"));
		assertEquals(2, shortestPath.getCost());
		System.out.println(shortestPath);
	}
	
	@Test
	public void testAnotherShortestPathBetweenToSelfForHG2() throws NoSuchNodeException {
		Path shortestPath = Graphs.runDijkstras(homeGrownGraph2, new Node("C"), new Node("C"));
		assertEquals(3, shortestPath.getCost());
		System.out.println(shortestPath);
	}
}


