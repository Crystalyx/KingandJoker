package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Game.Gui.GuiContainer;
import Utilities.Graph;

public class GuiSlot extends GuiOBJ
{
	public GuiSlot(double x, double y, double size, int type)
	{
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		this.type = type;
	}

	public GuiSlot(double x, double y, int type)
	{
		this(x, y, GuiContainer.sside, type);
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
			GL11.glTranslated(this.x + 2, this.y - 36, 0);
			this.bindTexture();

			Graph.drawSizedSqr(this.gui, 256, 256, 20, 20, this.width, this.height, 0, 0, 2, 2);

			GL11.glPopMatrix();
			if (this.type >= 0)
			{
				SlotType typ = ObjTypes.slots.get(this.type);
				typ.setGui(this.gui);
				typ.render(k, l, this.x+1.5, this.y - 46, this);
			}
		}
	}
}
