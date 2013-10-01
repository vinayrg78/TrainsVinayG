package com.tworks.trains.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tworks.collections.Graph;
import com.tworks.collections.Node;
import com.tworks.collections.exceptions.NoSuchNodeException;
import com.tworks.collections.utils.Path;
import com.tworks.trains.TrainClientGraphHelper;

/**
 * Tests the TrainClientGraphHelper and all the comparators.
 * @author VinayG
 */
public class TrainClientGraphHelperTest{
	
	protected Graph twGraph;
	protected Graph testGraph;
	protected Graph homeGrownGraph;
	protected Graph homeGrownGraph2;

	@Before
	public void setUp() throws Exception {
		String[] edgeArray = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
		twGraph = TrainClientGraphHelper.buildGraphFromFileContents(edgeArray);
		
		edgeArray = new String[]{"AB21", "AD3", "BC1", "BE4", "CD1", "DE4"};
		testGraph = TrainClientGraphHelper.buildGraphFromFileContents(edgeArray);
		
		edgeArray = new String[]{"AB2", "BA2", "AD4", "BD1", "DC3", "BE6", "EB6", "DH1", "HE1", "EF2", "FG3", "GF3", "GH8", "HG8"};
		homeGrownGraph = TrainClientGraphHelper.buildGraphFromFileContents(edgeArray);
		
		edgeArray = new String[]{"AB1", "BA1", "AC1", "CA2", "CD1", "DA3", "DF3", "FD3", "FA4", "AE7", "EA7", "FI2", "IF2", "EI1", "IE1", "EH1", "HE1", "EG2", "GE2"};
		homeGrownGraph2 = TrainClientGraphHelper.buildGraphFromFileContents(edgeArray);
	}
	
	//First 5 TW cases.
	@Test
	public void testFindCostOfGivenPathTW() {
		int cost = TrainClientGraphHelper.findCostOfGivenPath(twGraph, "ABC");
		assertEquals(9, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(twGraph, "AD");
		assertEquals(5, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(twGraph, "ADC");
		assertEquals(13, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(twGraph, "AEBCD");
		assertEquals(22, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(twGraph, "AED");
		assertEquals(-1, cost);
	}

	@Test
	public void testFindCostOfGivenPath() {
		int cost = TrainClientGraphHelper.findCostOfGivenPath(homeGrownGraph, "ABD");
		assertEquals(3, cost);
	}
	
	@Test
	public void testGetShortestPathBetweenTwoCities1() throws NoSuchNodeException {
		Path path = TrainClientGraphHelper.getShortestPathBetweenTwoCities(homeGrownGraph, "AG");
		assertEquals(10, path.getCost());
	}
	
	@Test
	public void testGetShortestPathBetweenTwoCities2() throws NoSuchNodeException {
		Path path = TrainClientGraphHelper.getShortestPathBetweenTwoCities(homeGrownGraph, "FC");
		assertEquals(22, path.getCost());
	}

	@Test
	public void testGetAllPathsUsingLTEHopLimit() throws NoSuchNodeException {
		int hopLimit = 5;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingLTEHopLimit(homeGrownGraph, hopLimit, "AH");
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(8, pathList.size());
	}

	@Test
	public void testGetAllPathsUsingLTECostLimit() throws NoSuchNodeException {
		int costLimit = 5;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingLTECostLimit(homeGrownGraph, costLimit, "AH");
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(2, pathList.size());
	}

	@Test
	public void testGetAllPathsUsingExactHopLimit() throws NoSuchNodeException {
		int hopLimit = 5;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingExactHopLimit(homeGrownGraph, hopLimit, "AH");	
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(4, pathList.size());
	}

	@Test
	public void testGetAllPathsUsingExactCostLimit() throws NoSuchNodeException {
		int costLimit = 5;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingExactCostLimit(homeGrownGraph, costLimit, "AH");
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(1, pathList.size());
	}

	@Test
	public void testGetAllPathsUsingLTHopLimit() throws NoSuchNodeException {
		int hopLimit = 4;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingLTHopLimit(homeGrownGraph, hopLimit, "AH");
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(2, pathList.size());
	}

	@Test
	public void testGetAllPathsUsingLTCostLimit() throws NoSuchNodeException {
		int costLimit = 12;
		List<Path> pathList = TrainClientGraphHelper.getAllPathsUsingLTCostLimit(homeGrownGraph, costLimit, "AG");
		for(Path path : pathList){
			System.out.println(path);
		}
		assertEquals(2, pathList.size());
	}
	
	@Test
	public void testGetNodesFromString(){
		String path = " ABCDE ";
		List<Node> nodeList = TrainClientGraphHelper.getNodesFromString(path);
		assertEquals(5, nodeList.size());
	}
	
	@Test
	public void testGetShortestPathBetweenTwoCitiesTestGraph() throws NoSuchNodeException {
		Path path = TrainClientGraphHelper.getShortestPathBetweenTwoCities(testGraph, "AE");
		assertEquals(7, path.getCost());
	}
	
	@Test
	public void testFindCostOfGivenPathTestGraph() {
		int cost = TrainClientGraphHelper.findCostOfGivenPath(testGraph, "AB");
		assertEquals(21, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(testGraph, "AD");
		assertEquals(3, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(testGraph, "ABC");
		assertEquals(22, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(testGraph, "ADE");
		assertEquals(7, cost);
		
		cost = TrainClientGraphHelper.findCostOfGivenPath(testGraph, "AED");
		assertEquals(-1, cost);
	}
	

}
