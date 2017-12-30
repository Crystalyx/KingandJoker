package Graphics.Render;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Core.GUI;
import Core.KIJCore;
import Game.ItemStack;
import Game.Entities.API.Entity;
import Game.Items.Laser;
import Game.Items.API.ItemGun;
import Graphics.Icon;
import Math.Vec.Vec2;
import Registry.Binds;
import Utilities.AABB2;
import Utilities.Graph;
import Utilities.Tessellator;

public class PlayerRender extends Render
{
	@Override
	public void render(Entity obj)
	{
		Tessellator t = Tessellator.instance;

		if (KIJCore.p.life <= 0)
		{
			GL11.glColor3d(1d, 0, 0);
		}

		if (KIJCore.p.needTrace)
		{
			for (int i = 0; i < KIJCore.p.trace; i++)
			{
				GL11.glPushMatrix();
				GL11.glColor4d(1, 1, 1, (1d / KIJCore.p.trace) * i);

				drawBody(t);

				GL11.glPopMatrix();
			}
		}
		GL11.glColor4d(1, 1, 1, 1);

		drawBody(t);

		if (KIJCore.p.life <= 0)
		{
			GL11.glColor3d(1d, 1, 1);
		}

	}

	public void drawBody(Tessellator t)
	{
		GL11.glPushMatrix();
		Vec2 v = KIJCore.p.pos;
		GL11.glTranslated(v.x, v.y, 0);
		GL11.glScaled(1.5, 1.5, 1.5);
		AABB2 ab = new Vec2().extendBoth(GUI.PLAYER_WIDTH / 2, GUI.PLAYER_HEIGHT / 2);

		int mx = Mouse.getX();
		int my = Mouse.getY();
		double dx = mx - KIJCore.p.pos.x;
		double dy = my - KIJCore.p.pos.y;

		ItemStack is = KIJCore.p.getCurrentStack();

		if (is != null)
		{
			if (is.item instanceof ItemGun)
			{
				GL11.glPushMatrix();
				GL11.glRotated(Math.atan2(dy, dx) * 180 / Math.PI, 0, 0, 1);
				GL11.glTranslated(5, 0, 0);

				if (is.item instanceof Laser && Binds.pressed(KIJCore.SETTINGS.keyAttack) && KIJCore.p.energy >= 5)
				{
					AABB2 lb = new Vec2(0, -2).extend(1200, 4);
					Icon.getIcon("Laser").bind();
					Graph.renderSqr(lb);
				}

				((ItemGun) is.item).gun.bind();

				Graph.renderSqr(ab);

				GL11.glPopMatrix();
			}
		}

		Icon.getIcon("player/" + KIJCore.p.base).bind();
		Graph.renderSqr(ab);

		GL11.glPopMatrix();

	}

}
