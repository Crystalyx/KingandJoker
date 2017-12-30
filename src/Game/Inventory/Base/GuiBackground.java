package Game.Inventory.Base;

import org.lwjgl.opengl.GL11;

import Utilities.Color;
import Utilities.Graph;

public class GuiBackground extends GuiOBJ
{
	public GuiBackground(int x, int y, int sizex, int sizey)
	{
		this.sizex = (int) (Math.ceil(sizex / 8) * 8);
		this.sizey = (int) (Math.ceil(sizey / 8) * 8);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(int k, int l)
	{
		int x = this.x;
		int y = this.y + this.sizey / 2;
		GL11.glPushMatrix();
		GL11.glTranslated(k, l, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(1.38, 1.38, 1);
		GL11.glTranslated(x + 2, y - 16, 0);

		this.bindTexture();

		Graph.drawSizedSqr(this.gui, 256, 256, 32, 32, this.sizex + 12, this.sizey, 224, 32, 8, 8);

		Graph.colorize(Color.Green);
		Graph.drawSizedSqr(this.gui, 256, 256, 32, 32, this.sizex + 12, this.gui.borderHeight, 224, 32, 8, 8);
		Graph.clearColor();

		GL11.glPopMatrix();
	}
}
