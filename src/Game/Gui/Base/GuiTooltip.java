package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Graphics.FontRenderer;
import Graphics.Icon;

public class GuiTooltip extends GuiOBJ
{
	public boolean background = true;
	public String[] text;
	public double sx, sy, tx, ty;
	public boolean alwaysRender = false;

	public GuiTooltip(double sx, double sy, double tx, double ty, String... text)
	{
		this.x = sx;
		this.y = sy;
		this.width = tx - sx;
		this.height = ty - sy;
		this.text = text;
	}

	public GuiTooltip(double sx, double sy, double tx, double ty, boolean back, String... text)
	{
		this.x = sx;
		this.y = sy;
		this.tx = tx;
		this.ty = ty;
		this.text = text;
		int max = 0;
		for (int i = 0; i < text.length; i++)
		{
			if (text.length > max)
			{
				max = text.length;
			}
		}
		this.width = 8 * (max + 2);
		this.height = 8 * (text.length + 2);
		this.background = back;
	}

	@Override
	public void render(int k, int l)
	{
		// check if mouse in aabb(sx,sy,tx,ty) or alwaysRender

		int count = 0;

		// -+
		this.gui.drawTexturedModalRect(k + 2, l - 16, 0, 0, 8, 8, Icon.getIcon("tooltip"));
		// ++
		this.gui.drawTexturedModalRect(k - 6 + this.width, l - 16, 24, 0, 8, 8, Icon.getIcon("tooltip"));
		// --
		this.gui.drawTexturedModalRect(k + 2, l - 16 - this.height, 0, 24, 8, 8, Icon.getIcon("tooltip"));
		// +-
		this.gui.drawTexturedModalRect(k - 6 + this.width, l - 16 - this.height, 24, 24, 8, 8, Icon.getIcon("tooltip"));

		// horiz
		for (int i = 0; i < count; i++)
		{
			this.gui.drawTexturedModalRect(k + 10 + i * 8, l - 16, 8, 0, 8, 8, Icon.getIcon("tooltip"));
			this.gui.drawTexturedModalRect(k + 10 + i * 8, l - 16 - this.height, 8, 24, 8, 8, Icon.getIcon("tooltip"));
		}
		// vert
		for (int i = 0; i < text.length + 1; i++)
		{
			this.gui.drawTexturedModalRect(k + 2, l - 24 - i * 8, 0, 8, 8, 8, Icon.getIcon("tooltip"));
			this.gui.drawTexturedModalRect(k - 6 + this.width, l - 24 - i * 8, 24, 8, 8, 8, Icon.getIcon("tooltip"));
		}

		for (int i = 0; i < count; i++)
		{
			for (int j = 0; j < text.length + 1; j++)
			{
				this.gui.drawTexturedModalRect(k + 10 + i * 8, l - 24 - j * 8, 8, 8, 8, 8, Icon.getIcon("tooltip"));
			}
		}

		GL11.glPushMatrix();
		GL11.glTranslated(k + 10, l - 16, 0);
		GL11.glRotated(180, 1, 0, 0);
		FontRenderer fr = new FontRenderer();
		int i = 0;
		for (String s : text)
		{
			fr.drawString(s, 0, 12 * i);
			i += 1;
		}
		GL11.glPopMatrix();
	}

	public GuiTooltip setAlwaysRender(boolean alwaysRender)
	{
		this.alwaysRender = alwaysRender;
		return this;
	}
}
