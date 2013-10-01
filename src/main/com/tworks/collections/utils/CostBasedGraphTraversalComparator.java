package com.tworks.collections.utils;

public abstract class CostBasedGraphTraversalComparator implements
		GraphTraversalComparator {

	
	@Override
	public abstract boolean isCostWithinLimit(int currentCost);

	//Default impl for non exact cases.
	public boolean doesCostMatchLimit(int currentCost){
		return true;
	}

	@Override
	public final int incrementCostBasedOnSetting(int currentCost, int additionalCost) {
		return currentCost + additionalCost;
	}

	@Override
	public final int decrementCostBasedOnSetting(int currentCost, int additionalCost) {
		return currentCost - additionalCost;
	}

}
