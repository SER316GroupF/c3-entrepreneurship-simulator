package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Support class for formatting and maintaining {@link Table} objects
 * 
 * @author Moore, Zachary
 * 
 */
public class Tables
{
	/**
	 * Set the alignment of all cells in a particular column of a table
	 * 
	 * @param table
	 *            Target
	 * @param columnIndex
	 *            Column to set the width of
	 * @param alignment
	 *            New alignment for all items in the specified column
	 */
	public static void allignColumns(Table table, int columnIndex, int alignment)
	{
		for (Cell<?> cell : table.getCells())
		{
			if (cell.getColumn() == columnIndex)
			{
				cell.align(alignment);
			}
		}
	}
	
	/**
	 * Set the padding of all cells in the specified column of a table
	 * 
	 * @param table
	 *            Target table
	 * @param columnIndex
	 *            Target column
	 * @param padding
	 *            Padding specification
	 */
	public static void padColumn(Table table, int columnIndex, Padding padding)
	{
		for (Cell<?> cell : table.getCells())
		{
			if (cell.getColumn() == columnIndex)
			{
				padCell(cell, padding);
			}
		}
	}
	
	/**
	 * Set the padding of all cells in a table
	 * 
	 * @param cell
	 *            Target
	 * @param padding
	 *            Padding specifications
	 */
	public static void padAllCells(Table table, Padding padding)
	{
		for (Cell<?> cell : table.getCells())
		{
			padCell(cell, padding);
		}
	}
	
	/**
	 * Set the padding of a single table cell
	 * 
	 * @param cell
	 *            Target
	 * @param padding
	 *            Padding specifications
	 */
	public static void padCell(Cell<?> cell, Padding padding)
	{
		float top = padding.get(Padding.TOP);
		float left = padding.get(Padding.LEFT);
		float bottom = padding.get(Padding.BOTTOM);
		float right = padding.get(Padding.RIGHT);
		
		cell.pad(top, left, bottom, right);
	}
}
