package com.tworks.trains;

import com.tworks.collections.utils.HopBasedGraphTraversalComparator;

/**Use case specific impl of GraphTraversalComparator
 * Comparator for LESSER_THAN_EQUAL_TO preset costLimit.
 * Helper for Graphs traversal.
 * 
 * @author VinayG
 */
public class HopBasedLTEGraphTraversalComparatorImpl extends HopBasedGraphTraversalComparator {
	
	public HopBasedLTEGraphTraversalComparatorImpl(int hopLimit) {
		super();
		this.hopLimit = hopLimit;
	}

	private int hopLimit;
	
	public boolean isCostWithinLimit(int currentCost) {
		return currentCost <= hopLimit;
	}

}
