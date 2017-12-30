package Graphics;

import org.lwjgl.opengl.GL11;

import Math.Vec.Vec2;
import Utilities.AABB2;
import Utilities.Tessellator;

public class RenderUtils
{
	public static void renderAABB(AABB2 aabb)
	{
		Tessellator t = GUI.t;

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(aabb.ox - 1, aabb.oy, 0, 0);
		t.addVertexWithUV(aabb.ox + 1, aabb.oy, 1, 0);
		t.addVertexWithUV(aabb.ox + 1, aabb.ty, 1, 1);
		t.addVertexWithUV(aabb.ox - 1, aabb.ty, 0, 1);

		t.addVertexWithUV(aabb.tx - 1, aabb.oy, 0, 0);
		t.addVertexWithUV(aabb.tx + 1, aabb.oy, 1, 0);
		t.addVertexWithUV(aabb.tx + 1, aabb.ty, 1, 1);
		t.addVertexWithUV(aabb.tx - 1, aabb.ty, 0, 1);

		t.addVertexWithUV(aabb.ox, aabb.oy - 1, 0, 0);
		t.addVertexWithUV(aabb.tx, aabb.oy - 1, 1, 0);
		t.addVertexWithUV(aabb.tx, aabb.oy + 1, 1, 1);
		t.addVertexWithUV(aabb.ox, aabb.oy + 1, 0, 1);

		t.addVertexWithUV(aabb.ox, aabb.ty - 1, 0, 0);
		t.addVertexWithUV(aabb.tx, aabb.ty - 1, 1, 0);
		t.addVertexWithUV(aabb.tx, aabb.ty + 1, 1, 1);
		t.addVertexWithUV(aabb.ox, aabb.ty + 1, 0, 1);
		t.draw();
	}

	public static void renderCircle(Vec2 v, double r)
	{
		Tessellator t = GUI.t;
		GL11.glPushMatrix();
		GL11.glTranslated(v.x, v.y, 0);
		int n = (int) (3 * r);
		double da = Math.PI / n;
		t.start(GL11.GL_QUADS);

		for (int i = 0; i < n; i++)
		{
			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 0);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 1, 0);
			t.addVertexWithUV(Math.cos((i + 1) * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 1, 1);
			t.addVertexWithUV(Math.cos(i * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 1);
			
			t.addVertexWithUV(Math.cos(i * da) * r, Math.sin(i * da) * r, 0, 1);
			t.addVertexWithUV(Math.cos((i + 1) * da) * r, Math.sin((i + 1) * da) * r, 1, 1);
			t.addVertexWithUV(Math.cos((i + 1) * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 1, 0);
			t.addVertexWithUV(Math.cos(i * da) * (r + 2), Math.sin((i + 1) * da) * (r + 2), 0, 0);
		}

		t.draw();
		GL11.glPopMatrix();
	}
}
