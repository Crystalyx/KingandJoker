package GUI;

import Game.Inventory.Base.ContainerBase;
import Game.Inventory.Base.GuiBase;

public class GuiEquipment extends GuiBase
{

	public GuiEquipment(ContainerBase c)
	{
		super(c);
		this.pause = true;
	}

	public void drawBack(int k, int l)
	{
		super.drawBack(k, l);
		// GL11.glPushMatrix();
		//
		// Tessellator t = GUI.t;
		//
		// GL11.glTranslated(k, l, 0);
		// GL11.glScaled(2, 2, 1);
		//
		// int mx = (int) Math.round((Mouse.getX() - k) / 2);
		// int my = (int) Math.round((Mouse.getY() - l) / 2);
		//
		// Sprite.arrinter.getTexture().bind();
		// t.start(GL11.GL_QUADS);
		//
		// t.addVertexWithUV(-4 + mx, 4 + my, 0, 0);
		// t.addVertexWithUV(4 + mx, 4 + my, 1, 0);
		// t.addVertexWithUV(4 + mx, -4 + my, 1, 1);
		// t.addVertexWithUV(-4 + mx, -4 + my, 0, 1);
		//
		// t.draw();
		//
		// GL11.glPushMatrix();
		// GL11.glScaled(0.38, 0.38, 1);
		//
		// t.drawTexturedModalRect(-39 - 375, -89, 0, 0, 375, 666, Sprite.equ);
		//
		// GL11.glPopMatrix();
		// GL11.glPopMatrix();
	}
}
