package com.tworks.collections.utils;


public interface GraphTraversalComparator {
	
	/* only override for Exact comparison case */
	public abstract boolean doesCostMatchLimit(int currentCost);
	
	public abstract boolean isCostWithinLimit(int currentCost);

	/* Ignore additionalCost for hope based implementations*/
	public abstract int incrementCostBasedOnSetting(int currentCost, int additionalCost);

	/* Ignore additionalCost for hope based implementations*/
	public abstract int decrementCostBasedOnSetting(int currentCost, int additionalCost);
}
