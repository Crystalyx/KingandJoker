package Graphics.Render;

import org.lwjgl.opengl.GL11;

import Game.Border;
import Game.Entities.API.Entity;
import Graphics.GUI;
import Math.Vec.Vec2;
import Utilities.AABB2;
import Utilities.Graph;
import Utilities.Tessellator;

public class BasicRender extends Render
{

	@Override
	public void render(Entity obj)
	{
		Tessellator t = GUI.t;

		if (obj.getSprite() != null)
		{
			GL11.glPushMatrix();

			GL11.glTranslated(obj.pos.x, obj.pos.y, 0);

			obj.getSprite().getTexture().bind();
			AABB2 bb = new Vec2().extendBoth(obj.width / 2, obj.height / 2);

			Graph.renderSqr(bb);
			GL11.glPopMatrix();
		}

	}

	@Override
	public void render(Border bord)
	{
		Tessellator t = GUI.t;

		if (bord.sprite != null)
		{
			GL11.glPushMatrix();

			GL11.glTranslated(bord.pos.x, bord.pos.y, 0);

			bord.sprite.getTexture().bind();
			AABB2 bb = new Vec2().extendBoth(bord.width / 2, bord.height / 2);

			Graph.renderSqr(bb);
			GL11.glPopMatrix();
		}

	}

}
