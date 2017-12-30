package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

public class BarType extends ObjType
{
	public BarType(int id, int px, int py, int sizex, int sizey)
	{
		super(Objs.Bar, id, px, py, sizex, sizey);
	}

	public void render(int k, int l, int x, int y, GuiBar bar)
	{

		int length = 10;
		// TODO
		GL11.glPushMatrix();
		GL11.glTranslated(k + x, l + y, 0);

		bar.bindTexture();

		GL11.glScaled(0.65, 0.65, 0);
		this.gui.drawTexturedModalRect(0, 0, this.px, this.py, this.sizex, this.sizey);

		this.gui.drawTexturedModalRect(0, this.sizey - length, this.px, this.py + this.sizey - length, this.sizex, length);

		GL11.glPopMatrix();
	}

}
