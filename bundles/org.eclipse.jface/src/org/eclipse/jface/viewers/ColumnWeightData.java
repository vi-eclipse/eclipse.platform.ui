package org.eclipse.jface.viewers;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
import org.eclipse.jface.util.Assert;

/**
 * Describes the width of a table column in terms of a weight, 
 * a minimum width, and whether the column is resizable.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class ColumnWeightData extends ColumnLayoutData {

	/** 
	 * Default width of a column (in pixels).
	 */
	public static final int MINIMUM_WIDTH = 20;
	
	/**
	 * The column's minimum width in pixels.
	 */
	public int minimumWidth;
	
	/**
	 * The column's weight.
	 */
	public int weight;
/**
 * Creates a resizable column width with the given weight and a default
 * minimum width.
 *
 * @param weight the weight of the column
 */
public ColumnWeightData(int weight) {
	this(weight, true);
}
/**
 * Creates a resizable column width with the given weight and minimum width.
 *
 * @param weight the weight of the column
 * @param minimumWidth the minimum width of the column in pixels
 */
public ColumnWeightData(int weight, int minimumWidth) {
	this(weight, minimumWidth, true);
}
/**
 * Creates a column width with the given weight and minimum width.
 *
 * @param weight the weight of the column
 * @param minimumWidth the minimum width of the column in pixels
 * @param resizable <code>true</code> if the column is resizable,
 *   and <code>false</code> if size of the column is fixed
 */
public ColumnWeightData(int weight, int minimumWidth, boolean resizable) {
	super(resizable);
	Assert.isTrue(weight >= 0);
	Assert.isTrue(minimumWidth >= 0);
	this.weight = weight;
	this.minimumWidth = minimumWidth;
}
/**
 * Creates a column width with the given weight and a default
 * minimum width.
 *
 * @param weight the weight of the column
 * @param resizable <code>true</code> if the column is resizable,
 *   and <code>false</code> if size of the column is fixed
 */
public ColumnWeightData(int weight, boolean resizable) {
	this(weight, MINIMUM_WIDTH, resizable);
}
}
