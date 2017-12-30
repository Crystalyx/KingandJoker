package Game.Inventory.Base;

import org.lwjgl.opengl.GL11;

import Graphics.FontRenderer;
import Graphics.Icon;

public class GuiTooltip extends GuiOBJ
{
	public boolean background = true;
	public String[] text;
	public int sx, sy, tx, ty;
	public boolean alwaysRender = false;

	public GuiTooltip(int sx, int sy, int tx, int ty, String... text)
	{
		this.x = sx;
		this.y = sy;
		this.sizex = tx - sx;
		this.sizey = ty - sy;
		this.text = text;
	}

	public GuiTooltip(int sx, int sy, int tx, int ty, boolean back, String... text)
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
		this.sizex = 8 * (max + 2);
		this.sizey = 8 * (text.length + 2);
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
		this.gui.drawTexturedModalRect(k - 6 + this.sizex, l - 16, 24, 0, 8, 8, Icon.getIcon("tooltip"));
		// --
		this.gui.drawTexturedModalRect(k + 2, l - 16 - this.sizey, 0, 24, 8, 8, Icon.getIcon("tooltip"));
		// +-
		this.gui.drawTexturedModalRect(k - 6 + this.sizex, l - 16 - this.sizey, 24, 24, 8, 8, Icon.getIcon("tooltip"));

		// horiz
		for (int i = 0; i < count; i++)
		{
			this.gui.drawTexturedModalRect(k + 10 + i * 8, l - 16, 8, 0, 8, 8, Icon.getIcon("tooltip"));
			this.gui.drawTexturedModalRect(k + 10 + i * 8, l - 16 - this.sizey, 8, 24, 8, 8, Icon.getIcon("tooltip"));
		}
		// vert
		for (int i = 0; i < text.length + 1; i++)
		{
			this.gui.drawTexturedModalRect(k + 2, l - 24 - i * 8, 0, 8, 8, 8, Icon.getIcon("tooltip"));
			this.gui.drawTexturedModalRect(k - 6 + this.sizex, l - 24 - i * 8, 24, 8, 8, 8, Icon.getIcon("tooltip"));
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
