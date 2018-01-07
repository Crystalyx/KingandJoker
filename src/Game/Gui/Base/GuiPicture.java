package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Utilities.Color;

public class GuiPicture extends GuiOBJ
{
	public GuiPicture(double x, double y, double sizex, double sizey, int type, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = sizex;
		this.height = sizey;
		this.type = type;
	}

	public float rotation = 0;
	public float scale = 1;

	@Override
	public void render(int k, int l)
	{
		if (!this.hidden)
		{
			GL11.glPushMatrix();
			this.setCustomTexture("guibases/pictures.png");
			GL11.glTranslated(this.x + k, this.y + l, 0);
			GL11.glRotated(this.rotation, 0, 0, -1);
			GL11.glScaled(this.scale, this.scale, this.scale);
			this.bindTexture();

			int m = Math.floorMod(this.type, 8);
			int d = Math.floorDiv(this.type, 8);

			this.gui.drawTexturedModalRect((int) (-this.width / Math.PI * 2), (int) (-this.height / Math.PI * 2), m * 32, d * 32, this.width, this.height);
			this.disableCustomTexture();
			GL11.glPopMatrix();
		}
	}
}
