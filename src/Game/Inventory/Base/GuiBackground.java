package Game.Inventory.Base;

import org.lwjgl.opengl.GL11;

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
		l += 32;

		this.bindTexture();

		// -+
		this.gui.drawTexturedModalRect(x + 2, y - 16, 224, 32, 8, 8);
		// ++
		this.gui.drawTexturedModalRect(x + 10 + this.sizex, y - 16, 224 + 24, 32, 8, 8);
		// --
		this.gui.drawTexturedModalRect(x + 2, y - 16 - this.sizey, 224, 32 + 24, 8, 8);
		// +-
		this.gui.drawTexturedModalRect(x + 10 + this.sizex, y - 16 - this.sizey, 224 + 24, 32 + 24, 8, 8);

		// horiz
		for (int i = 0; i < Math.ceil(this.sizex / 8); i++)
		{
			this.gui.drawTexturedModalRect(x + 10 + i * 8, y - 16, 224 + 8, 32, 8, 8);
			this.gui.drawTexturedModalRect(x + 10 + i * 8, y - 16 - this.sizey, 224 + 8, 32 + 24, 8, 8);
		}
		// vert
		for (int i = 0; i < Math.ceil(this.sizey / 8) - 1; i++)
		{
			this.gui.drawTexturedModalRect(x + 2, y - 24 - i * 8, 224, 32 + 8, 8, 8);
			this.gui.drawTexturedModalRect(x + 10 + this.sizex, y - 24 - i * 8, 224 + 24, 32 + 8, 8, 8);
		}

		for (int i = 0; i < Math.ceil(this.sizex / 8); i++)
		{
			for (int j = 0; j < Math.ceil(this.sizey / 8)-1; j++)
			{
				this.gui.drawTexturedModalRect(x + 10 + i * 8, y - 24 - j * 8, 224 + 8, 32 + 8, 8, 8);
			}
		}

		GL11.glPopMatrix();
	}
}
