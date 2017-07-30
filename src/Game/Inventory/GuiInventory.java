package Game.Inventory;

import org.lwjgl.opengl.GL11;

import Game.Inventory.Base.ContainerBase;
import Game.Inventory.Base.GuiBase;

public class GuiInventory extends GuiBase
{
	public GuiInventory(ContainerBase c)
	{
		super(c);
	}

	public void drawBack(int k, int l)
	{
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		super.drawBack(k, l);
		
	}
}
