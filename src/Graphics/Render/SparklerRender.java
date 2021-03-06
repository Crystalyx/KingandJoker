package Graphics.Render;

import org.lwjgl.opengl.GL11;

import Core.Screen;
import Game.Entities.API.Entity;
import Graphics.Icon;
import Utilities.Tessellator;

public class SparklerRender extends Render
{

	@Override
	public void render(Entity obj)
	{
		GL11.glPushMatrix();
		Tessellator t = Screen.t;
		Icon s0 = Icon.getIcon("mobs/sparkler_0");
		Icon s1 = Icon.getIcon("mobs/sparkler_1");
		Icon s2 = Icon.getIcon("mobs/sparkler_2");

		double life = obj.lifeTime;

		double dh = obj.height;
		double dw = obj.width / 2;
		double dx0 = 10 * Math.sin(-(Screen.time + life) / 16d);
		double dx1 = 10 * Math.sin(-(Screen.time + life) / 16d + Math.PI / 3);
		double dx2 = 10 * Math.sin(-(Screen.time + life) / 16d + 2 * Math.PI / 3);

		GL11.glTranslated(obj.pos.x, obj.pos.y, 0);
		GL11.glScaled(1.5, 1.5, 1);

		GL11.glPushMatrix();

		s0.getTexture().bind();
		GL11.glTranslated(dx0, 0, 0);
		GL11.glScaled(0.5, 0.5, 1);
		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(+dw, +dh, 0, 0);
		t.addVertexWithUV(-dw, +dh, 1, 0);
		t.addVertexWithUV(-dw, 0, 1, 1);
		t.addVertexWithUV(+dw, 0, 0, 1);
		t.draw();
		GL11.glPopMatrix();

		GL11.glTranslated(0, -obj.height / 5, 0);

		GL11.glPushMatrix();
		GL11.glTranslated(dx1, 0, 0);
		GL11.glScaled(0.5, 0.5, 1);

		s1.getTexture().bind();

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(dw, dh, 0, 0);
		t.addVertexWithUV(-dw, +dh, 1, 0);
		t.addVertexWithUV(-dw, 0, 1, 1);
		t.addVertexWithUV(+dw, 0, 0, 1);
		t.draw();
		GL11.glPopMatrix();

		GL11.glTranslated(0, -obj.height / 5, 0);

		GL11.glPushMatrix();
		GL11.glTranslated(dx2, 0, 0);
		GL11.glScaled(0.5, 0.5, 1);
		s2.getTexture().bind();

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(+dw, +dh, 0, 0);
		t.addVertexWithUV(-dw, +dh, 1, 0);
		t.addVertexWithUV(-dw, 0, 1, 1);
		t.addVertexWithUV(+dw, 0, 0, 1);
		t.draw();
		GL11.glPopMatrix();

		GL11.glPopMatrix();
	}

}
