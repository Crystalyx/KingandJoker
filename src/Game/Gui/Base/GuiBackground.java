package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Utilities.Color;
import Utilities.Graph;

public class GuiBackground extends GuiOBJ
{
	public GuiBackground(double x, double y, double sizex, double sizey)
	{
		this.width = (int) (Math.ceil(sizex / 8d) * 8);
		this.height = (int) (Math.ceil(sizey / 8d) * 8);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(int k, int l)
	{
		double x = this.x;
		double y = this.y + this.height / 2;
		GL11.glPushMatrix();
		GL11.glTranslated(k, l, 0);
		GL11.glScaled(2.76, 2.76, 1);
		GL11.glTranslated(x + 2, y - 16, 0);

		this.bindTexture();

		Graph.drawSizedSqr(this.gui, 256, 256, 32, 32, this.width + 12, this.height, 224, 32, 8, 8);

		Graph.colorize(Color.Green);
		Graph.drawSizedSqr(this.gui, 256, 256, 32, 32, this.width + 12, this.gui.borderHeight, 224, 32, 8, 8);
		Graph.clearColor();

		GL11.glPopMatrix();
	}
}
