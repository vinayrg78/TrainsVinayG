package com.tworks.collections.utils;

public abstract class HopBasedGraphTraversalComparator implements
		GraphTraversalComparator {

	
	@Override
	public abstract boolean isCostWithinLimit(int currentCost);

	//Default impl for non exact cases.
	public boolean doesCostMatchLimit(int currentCost){
		return true;
	}

	/* Ignore additionalCost as this is hop based impl. Shouldn't be overridden, hence final.*/
	@Override
	public final int incrementCostBasedOnSetting(int currentCost, int additionalCost) {
		return currentCost + 1;
	}

	/* Ignore additionalCost as this is hop based impl. Shouldn't be overridden, hence final.*/
	@Override
	public final int decrementCostBasedOnSetting(int currentCost, int additionalCost) {
		return currentCost - 1;
	}

}
