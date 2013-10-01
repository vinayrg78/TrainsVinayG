package com.tworks.trains;

import com.tworks.collections.utils.CostBasedGraphTraversalComparator;


/**Use case specific impl of GraphTraversalComparator
 * Comparator for LESSER_THAN_EQUAL_TO preset costLimit.
 * Helper for Graphs traversal.
 * 
 * @author VinayG
 */
public class CostBasedLTEGraphTraversalComparatorImpl extends CostBasedGraphTraversalComparator {
	
	public CostBasedLTEGraphTraversalComparatorImpl(int costLimit) {
		super();
		this.costLimit = costLimit;
	}

	private int costLimit;
	
	public boolean isCostWithinLimit(int currentCost) {
		return currentCost <= costLimit;
	}

}
