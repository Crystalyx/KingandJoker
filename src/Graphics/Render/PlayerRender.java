package Graphics.Render;

import org.lwjgl.opengl.GL11;

import Game.Entities.Entity;
import Game.Inventory.GuiContainer;
import Graphics.GUI;
import Graphics.Main;
import Graphics.Sprite;
import Utilities.Tessellator;

public class PlayerRender extends Render
{
	@Override
	public void render(Entity obj)
	{
		Tessellator t = Tessellator.instance;

		if (Main.p.life <= 0)
		{
			GL11.glColor3d(1d, 0, 0);
		}
		if (Main.p.needTrace)
		{
			Sprite.trace.getTexture().bind();

			for (int i = 0; i < Main.p.trace; i++)
			{
				GL11.glPushMatrix();
				GL11.glColor4d(1, 1, 1, 1d / Main.p.trace * i);

				t.start(GL11.GL_QUADS);
				t.addVertexWithUV(Main.p.xs[i] - GUI.PLAYER_WIDTH / 2, Main.p.ys[i], 0, 1);
				t.addVertexWithUV(Main.p.xs[i] + GUI.PLAYER_WIDTH / 2, Main.p.ys[i], 1, 1);
				t.addVertexWithUV(Main.p.xs[i] + GUI.PLAYER_WIDTH / 2, Main.p.ys[i] + GUI.PLAYER_HEIGHT, 1, 0);
				t.addVertexWithUV(Main.p.xs[i] - GUI.PLAYER_WIDTH / 2, Main.p.ys[i] + GUI.PLAYER_HEIGHT, 0, 0);
				t.draw();

				GL11.glColor4d(1, 1, 1, 1);
				GL11.glPopMatrix();
			}
		}
		Sprite.getSprite("bodies/" + Main.p.body).getTexture().bind();

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 0, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 1, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 1, 0);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 0, 0);
		t.draw();

		Sprite.getSprite("heads/" + Main.p.head).getTexture().bind();

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 0, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 1, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 1, 0);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 0, 0);
		t.draw();

		Sprite.getSprite("hats/" + Main.p.hat).getTexture().bind();

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 0, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y, 1, 1);
		t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 1, 0);
		t.addVertexWithUV(Main.p.pos.x - GUI.PLAYER_WIDTH / 2, Main.p.pos.y + GUI.PLAYER_HEIGHT, 0, 0);
		t.draw();

		if (Main.p.inv.getStackInSlot(Main.p.hotselect) != null)
		{
			Main.p.inv.getStackInSlot(Main.p.hotselect).item.icon.getTexture().bind();
			t.start(GL11.GL_QUADS);
			t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2 - GuiContainer.sside, Main.p.pos.y + GUI.PLAYER_HEIGHT, 0, 1);
			t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2 + GuiContainer.sside, Main.p.pos.y + GUI.PLAYER_HEIGHT, 1, 1);
			t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2 + GuiContainer.sside, Main.p.pos.y + GUI.PLAYER_HEIGHT + GuiContainer.sside * 2, 1, 0);
			t.addVertexWithUV(Main.p.pos.x + GUI.PLAYER_WIDTH / 2 - GuiContainer.sside, Main.p.pos.y + GUI.PLAYER_HEIGHT + GuiContainer.sside * 2, 0, 0);
			t.draw();
		}
		if (Main.p.life <= 0)
		{
			GL11.glColor3d(1d, 1, 1);
		}

	}

}
