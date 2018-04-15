package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Core.Screen;
import Core.KIJCore;
import Math.Vec.Vec2;
import Utilities.Graph;
import Utilities.Utils;

public class GuiButton extends GuiOBJ
{
	public GuiButton(double x, double y, int size)
	{
		this(x, y, size, size);
	}

	public GuiButton(double x, double y, int sizex, int sizey)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
	}

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

			Graph.drawSizedSqr(this.gui, 256, 256, 20, 20, this.width, this.height, 79, 0, 3, 3);

			GL11.glPopMatrix();
		}
	}

	public boolean mouseSelected(double x, double y)
	{
		return true;
	}

	public boolean mouseClicked(double x, double y, int button)
	{
		return true;
	}

	public boolean mousePressed(double x, double y, int button)
	{
		return true;
	}

	public boolean checkMouse()
	{
		return Utils.isInLimit(new Vec2(KIJCore.mx, KIJCore.my), new Vec2(this.x * 2.76 + Screen.K + Screen.focus.dx, (this.y - 13) * 2.76 + Screen.L + Screen.focus.dy).extend(this.width * 2.76, this.height * 2.76));
	}
}
