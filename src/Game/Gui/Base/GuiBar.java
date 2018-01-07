package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Utilities.Color;
import Utilities.Utils;

public class GuiBar extends GuiOBJ
{
	public GuiBar(int x, int y, int sizex, int sizey, int type)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.type = type;
	}

	public GuiBar(int x, int y, int sizex, int sizey, int type, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.type = type;
		this.c = c;
	}

	public Color c = new Color(255, 255, 255);
	public int lastValue = 0;
	public int lastMaxValue = 1;

	public void updBar(int value, int maxValue)
	{
		this.lastValue = value;
		this.lastMaxValue = maxValue;
	}

	public int getLength()
	{
		return (int) (((double) this.lastValue) / ((double) this.lastMaxValue) * this.height);
	}

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			bindTexture();

			// EMPTY
			{
				// CORNER -,-
				this.gui.drawTexturedModalRect(k + this.x, l + this.y, 204, 0, 2, 2);

				// CORNER +,-
				this.gui.drawTexturedModalRect(k + this.x + this.width, l + this.y, 254, 0, 2, 2);

				// CORNER +,+
				this.gui.drawTexturedModalRect(k + this.x + this.width, l + this.y + this.height, 254, 22, 2, 2);

				// CORNER -,+
				this.gui.drawTexturedModalRect(k + this.x, l + this.y + this.height, 204, 22, 2, 2);

				int i = 0;
				// UPPER AND LOWER
				while (i < Utils.floorDiv(this.width, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y, 204 + Math.floorMod(i * 2, 48) + 2, 0, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + i * 2 + 2, l + this.y + this.height, 204 + Math.floorMod(i * 2, 48) + 2, 22, 2, 2);
					i++;
				}

				i = 0;
				// LEFT AND RIGHT
				while (i < Utils.floorDiv(this.height, 2) - 1)
				{
					this.gui.drawTexturedModalRect(k + this.x, l + this.y + i * 2 + 2, 204, Math.floorMod(i * 2, 20) + 2, 2, 2);
					this.gui.drawTexturedModalRect(k + this.x + this.width, l + this.y + i * 2 + 2, 254, Math.floorMod(i * 2, 20) + 2, 2, 2);
					i++;
				}

				int a = 0;
				while (a < Utils.floorDiv(this.width, 2) - 1)
				{
					int b = 0;
					while (b < Utils.floorDiv(this.height, 2) - 1)
					{
						this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2, Math.floorMod(a * 2, 32) + 2, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}

			GL11.glPopMatrix();

			GL11.glPushMatrix();
			int length = this.getLength();

			GL11.glColor3b((byte) (this.c.red / 2), (byte) (this.c.green / 2), (byte) (this.c.blue / 2));
			// FULL
			{
				int a = 0;
				while (a < Utils.floorDiv(this.width, 2) - 1)
				{
					int b = 0;
					while (b < Utils.floorDiv(length, 2) - Utils.floorMod(length + 1, 2))
					{
						this.gui.drawTexturedModalRect(k + this.x + a * 2 + 2, l + this.y + b * 2 + 2 - Math.floorMod(length, 2) + this.height - length, Math.floorMod(a * 2, 32) + 74, Math.floorMod(b * 2, 32) + 2, 2, 2);
						b++;
					}
					a++;
				}
			}

			this.gui.drawString(this.lastValue + "/" + this.lastMaxValue, this.x + k - this.width / 2, this.y + l - 16,14);

			GL11.glPopMatrix();
		}
	}
}
