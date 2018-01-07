package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Utilities.Graph;

public class GuiSwitch extends GuiButton
{
	public GuiSwitch(double x, double y, int sizex, int sizey)
	{
		super(x, y, sizex, sizey);
	}

	public boolean state = false;

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(k, l, 0);
			GL11.glScaled(2, 2, 1);
			GL11.glScaled(1.38, 1.38, 1);
			GL11.glTranslated(this.x, this.y, 0);
			this.bindTexture();
			GL11.glTranslated(0, -9, 0);
			Graph.drawSizedSqr(this.gui, 256, 256, 20, 3, this.width, 4, 99, 0, 1, 1);

			GL11.glTranslated(this.state ? this.width - this.height : 0, -2 + this.height / 2, 0);
			Graph.drawSizedSqr(this.gui, 256, 256, 20, 20, this.height, this.height, 79, 0, 1, 1);

			GL11.glPopMatrix();
		}
	}

	public boolean getState()
	{
		return false;
	}

	public void switchState()
	{
		this.state = !this.state;
	}

	public boolean mouseSelected(double x, double y)
	{

		return true;
	}

	public boolean mousePressed(double x, double y, int button)
	{

		return true;
	}
}
