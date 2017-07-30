package Graphics.Render;

import org.lwjgl.opengl.GL11;

import Game.Entities.Entity;
import Game.Entities.EntityItem;
import Graphics.GUI;
import Graphics.Main;
import Graphics.Sprite;
import Utilities.Tessellator;
import Utilities.Utils;
import Utilities.Vec2;

public class BasicRender extends Render
{

	@Override
	public void render(Entity obj)
	{
		Tessellator t = GUI.t;

		if (obj.getSprite() != null)
		{
			obj.getSprite().getTexture().bind();
			t.start(GL11.GL_QUADS);

			t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height, 0, 0);
			t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y + obj.height, 1, 0);
			t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y, 1, 1);
			t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y, 0, 1);

			t.draw();

			if (!(obj instanceof EntityItem))
			{
				double d = obj.pos.sub(Vec2.getCenteredPos(Main.p)).length();
				if (d <= 50)
				{
					int as = 32;

					Sprite.arrinter.getTexture().bind();
					t.start(GL11.GL_QUADS);

					t.addVertexWithUV(obj.pos.x - as / 2, obj.pos.y + obj.height + as, 0, 0);
					t.addVertexWithUV(obj.pos.x + as / 2, obj.pos.y + obj.height + as, 1, 0);
					t.addVertexWithUV(obj.pos.x + as / 2, obj.pos.y + obj.height, 1, 1);
					t.addVertexWithUV(obj.pos.x - as / 2, obj.pos.y + obj.height, 0, 1);

					t.draw();
				}

			}
		}

	}

}
