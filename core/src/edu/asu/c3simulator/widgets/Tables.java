package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Tables
{
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
	
	public static void padAllCells(Table table, Padding padding)
	{
		for (Cell<?> cell : table.getCells())
		{
			padCell(cell, padding);
		}
	}
	
	public static void padCell(Cell<?> cell, Padding padding)
	{
		float top = padding.get(Padding.TOP);
		float left = padding.get(Padding.LEFT);
		float bottom = padding.get(Padding.BOTTOM);
		float right = padding.get(Padding.RIGHT);
		
		cell.pad(top, left, bottom, right);
	}
}
