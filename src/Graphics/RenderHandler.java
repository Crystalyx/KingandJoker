package Graphics;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Game.Inventory.GuiContainer;
import Game.Inventory.Slot;
import UCS.EventHandler;
import UCS.GuiEvent;
import UCS.SubscribeEvent;
import Utilities.Tessellator;
import Utilities.Utils;

@EventHandler
public class RenderHandler
{
	@SubscribeEvent
	public static void renderHud(GuiEvent.HudRenderEvent e)
	{
		Tessellator t = GUI.t;

		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 0);
		GL11.glTranslated(0, e.l, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(0.38, 0.38, 1);

		t.drawTexturedModalRect(0, 0, 0, 0, 112, 487, Icon.hot);
		t.drawTexturedModalRect(e.k * 2 + 320, 0, 400, 0, 112, 487, Icon.hot);

		GL11.glPopMatrix();

		// for (int i = 0; i < 5; i++)
		// {
		// drawSlot(-12, e.l - 12, i);
		// }
		// for (int i = 0; i < 5; i++)
		// {
		// drawSlot(e.k + 165, e.l - 12, i + 5);
		// }
	}

//	protected static void drawSlot(int k, int l, int id)
//	{
//		Tessellator t = GUI.t;
//
//		GL11.glPushMatrix();
//		GL11.glTranslated(k, l, 0);
//		GL11.glScaled(2, 2, 1);
//
//		int mx = (int) Math.round((Mouse.getX() - k) / 2);
//		int my = (int) Math.round((Mouse.getY() - l) / 2);
//
//		Slot slot = GUI.cont1.slots.get(id);
//
//		if (slot.inv.getStackInSlot(slot.id) != null)
//		{
//			slot.inv.getStackInSlot(slot.id).item.icon.getTexture().bind();
//
//			t.start(GL11.GL_QUADS);
//
//			t.addVertexWithUV(slot.x, slot.y + GuiContainer.sside, 0, 0);
//			t.addVertexWithUV(slot.x + GuiContainer.sside, slot.y + GuiContainer.sside, 1, 0);
//			t.addVertexWithUV(slot.x + GuiContainer.sside, slot.y, 1, 1);
//			t.addVertexWithUV(slot.x, slot.y, 0, 1);
//
//			t.draw();
//		}
//		GL11.glPushMatrix();
//		GL11.glColor4d(1, 1, 1, 0.5);
//		if (Utils.isInLimit(mx + 2, slot.x, slot.x + GuiContainer.sside))
//		{
//			if (Utils.isInLimit(my + 2, slot.y, slot.y + GuiContainer.sside))
//			{
//				Sprite.sqr.getTexture().bind();
//				t.start(GL11.GL_QUADS);
//
//				t.addVertexWithUV(slot.x, slot.y + GuiContainer.sside, 0, 0);
//				t.addVertexWithUV(slot.x + GuiContainer.sside, slot.y + GuiContainer.sside, 1, 0);
//				t.addVertexWithUV(slot.x + GuiContainer.sside, slot.y, 1, 1);
//				t.addVertexWithUV(slot.x, slot.y, 0, 1);
//
//				t.draw();
//			}
//		}
//		GL11.glColor4d(1, 1, 1, 1);
//		GL11.glPopMatrix();
//
//		if (id == Main.p.hotselect)
//		{
//			Sprite.frame.getTexture().bind();
//			t.start(GL11.GL_QUADS);
//
//			t.addVertexWithUV(slot.x - GuiContainer.sside * 0.1, slot.y + GuiContainer.sside * 1.1, 0, 0);
//			t.addVertexWithUV(slot.x + GuiContainer.sside * 1.1, slot.y + GuiContainer.sside * 1.1, 1, 0);
//			t.addVertexWithUV(slot.x + GuiContainer.sside * 1.1, slot.y - GuiContainer.sside * 0.1, 1, 1);
//			t.addVertexWithUV(slot.x - GuiContainer.sside * 0.1, slot.y - GuiContainer.sside * 0.1, 0, 1);
//
//			t.draw();
//		}
//		GL11.glPopMatrix();
//	}
}
