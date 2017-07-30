package Game.Inventory.Base;

import Game.Inventory.GuiContainer;
import Graphics.FontRenderer;

public class GuiBase extends GuiContainer
{
	public ContainerBase base;
	public int k;
	public int l;

	public GuiBase(ContainerBase c)
	{
		super(c.xSize, c.ySize, c);
		this.base = c;
	}

	public void drawString(String s, int i, int j)
	{
		FontRenderer fr = new FontRenderer();
		fr.drawString(s, i, j);
	}

	@Override
	public void drawBack(int k, int l)
	{
		for (GuiOBJ obj : this.base.objs)
		{
			obj.setGui(this);
			obj.render(k, l);
		}
	}

	@Override
	public void drawSlots(int k, int l)
	{
		super.drawSlots(k, l);
	}

}
