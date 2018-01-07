package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import API.IFocusable;
import Utilities.AABB2;
import Utilities.Graph;

public class GuiText extends GuiOBJ implements IFocusable
{
	public boolean background = true;

	public GuiText(String text, double x, double y, double sizex, double sizey)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.text = text;
	}

	public GuiText(String text, double x, double y, double sizex, double sizey, boolean back)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.text = text;
		this.background = back;
	}

	public GuiText(String text, double x, double y, int sizex, int sizey, boolean back, int font)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.text = text;
		this.background = back;
		this.font = font;
	}

	public GuiText(String text, double x, double y, int sizex, int sizey, int font)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.text = text;
		this.font = font;
	}

	public void updText(String text)
	{
		this.text = text;
	}

	public String text = "";
	public int font = 14;

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

			if (this.background)
			{
				Graph.drawSizedSqr(this.gui, 256, 256, 20, 20, this.width, this.height, 0, 0, 2, 2);
			}
			this.gui.drawString(this.text, 3, 1, this.font);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void input()
	{

	}

	@Override
	public boolean canFocus()
	{
		return false;
	}

	@Override
	public AABB2 getFocusAABB(int k, int l)
	{
		return null;
	}
}
