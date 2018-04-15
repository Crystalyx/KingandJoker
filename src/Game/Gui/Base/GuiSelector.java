package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Core.Screen;
import Core.KIJCore;
import Utilities.Graph;
import Utilities.Utils;

public class GuiSelector extends GuiButton
{
	public GuiSelector(double x, double y, int sizex, int sizey, int statecount)
	{
		super(x, y, sizex, sizey);
		this.statecount = statecount;
	}

	public int state = 0;
	public int statecount = 0;

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

			GL11.glTranslated(this.state / ((double) this.statecount) * (this.width - this.height), -2 + this.height / 2, 0);
			Graph.drawSizedSqr(this.gui, 256, 256, 20, 20, this.height, this.height, 79, 0, 1, 1);

			GL11.glPopMatrix();
		}
	}

	public boolean getState()
	{
		return false;
	}

	public void updateState()
	{
		this.state = (int) Utils.floorDiv(KIJCore.mx - Screen.L - this.x * 2.76, this.width / this.statecount);
	}
	
	@Override
	public boolean mousePressed(double x, double y, int button)
	{
		updateState();
		return super.mousePressed(x, y, button);
	}

	public boolean mouseSelected(double x, double y)
	{

		return true;
	}

}
