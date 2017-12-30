package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Game.Gui.GuiContainer;

public class GuiSlot extends GuiOBJ
{
	public GuiSlot(int x, int y, int size, int type)
	{
		this.x = x;
		this.y = y;
		this.sizex = size;
		this.sizey = size;
		this.type = type;
	}

	public GuiSlot(int x, int y, int type)
	{
		this(x, y, GuiContainer.sside - 2, type);
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
			this.bindTexture();

			// -+
			this.gui.drawTexturedModalRect(this.x + 2, this.y - 16, 0, 0, 2, 2);
			// ++
			this.gui.drawTexturedModalRect(this.x + 2 + this.sizex, this.y - 16, 18, 0, 2, 2);
			// --
			this.gui.drawTexturedModalRect(this.x + 2, this.y - 16 - this.sizey, 0, 18, 2, 2);
			// +-
			this.gui.drawTexturedModalRect(this.x + 2 + this.sizex, this.y - 16 - this.sizey, 18, 18, 2, 2);

			// horiz
			for (int i = 0; i < Math.ceil(this.sizex / 2d) - 1; i++)
			{
				this.gui.drawTexturedModalRect(this.x + 4 + i * 2, this.y - 16, 2, 0, 2, 2);
				this.gui.drawTexturedModalRect(this.x + 4 + i * 2, this.y - 16 - this.sizey, 2, 18, 2, 2);
			}
			// vert
			for (int i = 0; i < Math.ceil(this.sizey / 2d) - 1; i++)
			{
				this.gui.drawTexturedModalRect(this.x + 2, this.y - 18 - i * 2, 0, 2, 2, 2);
				this.gui.drawTexturedModalRect(this.x + 2 + this.sizex, this.y - 18 - i * 2, 18, 2, 2, 2);
			}

			for (int i = 0; i < Math.ceil(this.sizex / 2d) - 1; i++)
			{
				for (int j = 0; j < Math.ceil(this.sizey / 2d) - 1; j++)
				{
					this.gui.drawTexturedModalRect(this.x + 4 + i * 2, this.y - 18 - j * 2, 2, 2, 2, 2);
				}
			}

			GL11.glPopMatrix();
			if (this.type >= 0)
			{
				SlotType typ = ObjTypes.slots.get(this.type);
				typ.setGui(this.gui);
				typ.render(k, l, this.x + 2 + this.sizex / 2 - typ.sizex / 2, 10 + this.y + this.sizey / 2 - typ.sizey / 2, this);
			}
		}
	}
}
