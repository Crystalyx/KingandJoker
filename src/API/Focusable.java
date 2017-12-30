package API;

import java.util.ArrayList;
import java.util.List;

import Graphics.Icon;
import Utilities.AABB2;
import Utilities.Tessellator;

public class Focusable implements IFocusable, IMovable
{
	public int width;
	public int height;
	public int dx = 20, dy = 0;
	public int borderHeight = 12;
	public boolean pause = false;
	public List<IFocusable> fox = new ArrayList<IFocusable>();

	public Icon drawingTexture;

	public Focusable(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	/**
	 * k and l - center coords
	 */
	public void drawGui(int k, int l)
	{
	}

	public Focusable setBorderHeight(int borderHeight)
	{
		this.borderHeight = borderHeight;
		return this;
	}

	public void onClosing()
	{

	}

	public void setDrawingTexture(Icon drawingTexture)
	{
		this.drawingTexture = drawingTexture;
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h, Icon icon)
	{
		Tessellator.instance.drawTexturedModalRect(x, y, u, v, w, h, icon);
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h)
	{
		Tessellator.instance.drawTexturedModalRect(x, y, u, v, w, h, this.drawingTexture);
	}

	@Override
	public void input()
	{
	}

	@Override
	public boolean canFocus()
	{
		return true;
	}

	@Override
	public AABB2 getFocusAABB(int k, int l)
	{
		return null;
	}

	@Override
	public boolean canMove()
	{
		return true;
	}

	@Override
	public AABB2 getMoveAABB(int k, int l)
	{
		return null;
	}

	@Override
	public void moveBy(double vx, double vy)
	{
		this.dx += vx;
		this.dy += vy;
	}
}
