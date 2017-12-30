package Graphics;

import org.lwjgl.opengl.GL11;

import UCS.EventHandler;
import UCS.GuiEvent;
import UCS.SubscribeEvent;

@EventHandler
public class RenderHandler
{
	@SubscribeEvent
	public static void renderHud(GuiEvent.HudRenderEvent e)
	{
//		Tessellator t = GUI.t;

		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 0);
		GL11.glTranslated(0, e.l, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(0.38, 0.38, 1);

		GL11.glPopMatrix();
	}

}
