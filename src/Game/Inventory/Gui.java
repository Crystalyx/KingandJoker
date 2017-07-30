package Game.Inventory;

import Graphics.Sprite;
import Utilities.Tessellator;

public class Gui
{
	public int width;
	public int height;
	public boolean pause = false;

	public Sprite drawingTexture;

	public Gui(int width, int height)
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

	public void onClosing()
	{

	}
	
	public void setDrawingTexture(Sprite drawingTexture)
	{
		this.drawingTexture = drawingTexture;
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h, Sprite icon)
	{
		Tessellator.instance.drawTexturedModalRect(x, y, u, v, w, h, icon);
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h)
	{
		Tessellator.instance.drawTexturedModalRect(x, y, u, v, w, h,this.drawingTexture);
	}
}
