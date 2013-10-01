package com.tworks.collections.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.tworks.collections.Graphs;
import com.tworks.collections.Node;
import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.GraphTraversalComparator;
import com.tworks.collections.utils.Path;
import com.tworks.trains.CostBasedLTEGraphTraversalComparatorImpl;
import com.tworks.trains.CostBasedLTGraphTraversalComparatorImpl;
import com.tworks.trains.HopBasedExactGraphTraversalComparatorImpl;
import com.tworks.trains.HopBasedLTEGraphTraversalComparatorImpl;

public class GraphsDFSTest extends AbstractGraphsTest {

	private int hopLimit = 0;
	private int costLimit = 0;
	
	@Test
	public void testNoCyclesAllPathsNoLimits() throws NoSuchNodeException {
		costLimit = Integer.MAX_VALUE;
		GraphTraversalComparator gtc = new CostBasedLTGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(testGraph, gtc, new Node("A"), new Node("E"));
		
		assertEquals(3, pList.size());
	}

	@Test
	public void testNoCyclesAllPathsUsingCostLimit() throws NoSuchNodeException {
		costLimit = 7;
		GraphTraversalComparator gtc = new CostBasedLTEGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(testGraph, gtc, new Node("A"), new Node("E"));
		
		assertEquals(2, pList.size());
	}
	
	@Test
	public void testNoCyclesAllPathsUsingHopLimit() throws NoSuchNodeException {
		hopLimit = 3;
		GraphTraversalComparator gtc = new HopBasedLTEGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(testGraph, gtc, new Node("A"), new Node("E"));
		
		assertEquals(2, pList.size());
	}
	
	//Case 6
	@Test
	public void testAllPathsToSelfWithCycleUsingHopLimit() throws NoSuchNodeException {
		hopLimit = 3;
		GraphTraversalComparator gtc = new HopBasedLTEGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(twGraph, gtc, new Node("C"), new Node("C"));
		
		assertEquals(2, pList.size());
	}
	
	//Case 7
	@Test
	public void testAllPathsUsingExactHopLimit() throws NoSuchNodeException {
		hopLimit = 4;
		GraphTraversalComparator gtc = new HopBasedExactGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(twGraph, gtc, new Node("A"), new Node("C"));
		
		assertEquals(3, pList.size());
	}
	
	//Case 10.
	@Test
	public void testAllPathsToSelfUsingHopLimit() throws NoSuchNodeException {
		costLimit = 30;
		GraphTraversalComparator gtc = new CostBasedLTGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(twGraph, gtc, new Node("C"), new Node("C"));
		
		assertEquals(7, pList.size());
	}
	
	//Homegrown1
	@Test
	public void testAllPathsBetweenNodesfUsingExactHopLimitHG() throws NoSuchNodeException {
		hopLimit = 2;
		GraphTraversalComparator gtc = new HopBasedExactGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(homeGrownGraph, gtc, new Node("A"), new Node("H"));
		for(Path path: pList){
			System.out.println(path);
		}
		assertEquals(1, pList.size());
	}
	
	@Test
	public void testAllPathsBetweenNodesfUsingLTCostLimitHG() throws NoSuchNodeException {
		costLimit = 13;
		GraphTraversalComparator gtc = new CostBasedLTGraphTraversalComparatorImpl(costLimit);
		List<Path> pList = Graphs.runDfs(homeGrownGraph, gtc, new Node("A"), new Node("G"));
		for(Path path: pList){
			System.out.println(path);
		}
		assertEquals(3, pList.size());
	}
	
	//Homegrown2
	@Test
	public void testAllPathsBetweenNodesfUsingLTEHopLimitHG2() throws NoSuchNodeException {
		hopLimit = 4;
		GraphTraversalComparator gtc = new HopBasedExactGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(homeGrownGraph2, gtc, new Node("A"), new Node("H"));
		for(Path path: pList){
			System.out.println(path);
		}
		assertEquals(6, pList.size());
	}
	
	@Test
	public void testAllPathsBetweenNonExistantNodesUsingLTEHopLimitHG2() throws NoSuchNodeException {
		hopLimit = 4;
		GraphTraversalComparator gtc = new HopBasedExactGraphTraversalComparatorImpl(hopLimit);
		List<Path> pList = Graphs.runDfs(homeGrownGraph2, gtc, new Node("A"), new Node("X"));
		for(Path path: pList){
			System.out.println(path);
		}
		assertEquals(0, pList.size());
	}
}
