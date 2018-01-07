package API;

import java.util.ArrayList;
import java.util.List;

import Game.Gui.Container;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.AABB2;
import Utilities.Tessellator;

public class Focusable implements IFocusable, IMovable
{
	public double width;
	public double height;
	public int dx = 0, dy = 0;
	public double borderHeight = 32;
	public boolean pause = false;
	public List<IFocusable> fox = new ArrayList<IFocusable>();

	public Icon drawingTexture;

	public boolean hasContainer = false;

	public Container getContainer()
	{
		return null;
	}

	public Focusable(double width, double height)
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

	public void setDrawingTexture(Icon drawingTexture)
	{
		this.drawingTexture = drawingTexture;
	}

	public void drawTexturedModalRect(double x, double y, double u, double v, double w, double h, Icon icon)
	{
		Tessellator.instance.drawTexturedModalRect(x, y, u, v, w, h, icon);
	}

	public void drawTexturedModalRect(double x, double y, double u, double v, double w, double h)
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
		return new Vec2(this.dx + k / 2, this.dy + l / 2).extend(width * 2.76, height * 2.76);
	}

	@Override
	public boolean canMove()
	{
		return true;
	}

	@Override
	public AABB2 getMoveAABB(int k, int l)
	{
		return new Vec2(k + dx - (this.width - 30) * 2.76 / 2, l + dy + (this.height + this.borderHeight * 2) * 2.76 / 2).extend((this.width + 15) * 2.76, this.borderHeight * 2.76);
	}

	@Override
	public void moveBy(double vx, double vy)
	{
		this.dx += vx;
		this.dy += vy;
	}

	public void onFocusing()
	{
		
	}

	public void onClosing()
	{

	}
}
