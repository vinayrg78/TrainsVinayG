package com.tworks.collections.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.tworks.collections.DirectedGraph;
import com.tworks.collections.Edge;
import com.tworks.collections.Graph;
import com.tworks.collections.Node;

public class AbstractGraphsTest {

	protected Graph twGraph;
	protected Graph testGraph;
	protected Graph homeGrownGraph;
	protected Graph homeGrownGraph2;

	
	@Before
	public void setUp() throws Exception {
		String[] edgeArray = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
		List<Edge> edgeList = buildEdgeListForGraph(edgeArray);
		twGraph = DirectedGraph.getGraphFromEdges(edgeList);
		
		edgeArray = new String[]{"AB2", "AD3", "BC1", "BE4", "CD1", "DE4"};
		edgeList = buildEdgeListForGraph(edgeArray);
		testGraph = DirectedGraph.getGraphFromEdges(edgeList);
		
		edgeArray = new String[]{"AB2", "BA2", "AD4", "BD1", "DC3", "BE6", "EB6", "DH1", "HE1", "EF2", "FG3", "GF3", "GH8", "HG8"};
		edgeList = buildEdgeListForGraph(edgeArray);
		homeGrownGraph = DirectedGraph.getGraphFromEdges(edgeList);
		
		edgeArray = new String[]{"AB1", "BA1", "AC1", "CA2", "CD1", "DA3", "DF3", "FD3", "FA4", "AE7", "EA7", "FI2", "IF2", "EI1", "IE1", "EH1", "HE1", "EG2", "GE2"};
		edgeList = buildEdgeListForGraph(edgeArray);
		homeGrownGraph2 = DirectedGraph.getGraphFromEdges(edgeList);
	}

	private List<Edge> buildEdgeListForGraph(String[] edgeArray) {
		List<Edge> edgeList = new ArrayList<Edge>();
		for(String edgeWithWeight : edgeArray){
			String[] partsOfEdgeData = edgeWithWeight.split("");
			
			//String leadingSpace = partsOfEdgeData[0];
			String src = partsOfEdgeData[1];
			String dest = partsOfEdgeData[2];
			int edgeWeight = new Integer(partsOfEdgeData[3]).intValue();
			
			edgeList.add(new Edge(new Node(src), new Node(dest), edgeWeight));
		}
		return edgeList;
	}

	@After
	public void tearDown() throws Exception {
	}

}
