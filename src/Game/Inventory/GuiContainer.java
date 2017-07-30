package Game.Inventory;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Game.ItemStack;
import Game.Entities.EntityItem;
import Graphics.FontRenderer;
import Graphics.GUI;
import Graphics.Main;
import Graphics.Sprite;
import Registry.Binds;
import Utilities.Tessellator;
import Utilities.Utils;
import Utilities.Vec2;

public class GuiContainer extends Gui
{
	public Container c;
	public static int sside = 20;
	public double x = 0;
	public double y = 0;

	public GuiContainer(int width, int height, Container c)
	{
		super(width, height);
		this.c = c;
	}

	@Override
	public void drawGui(int k, int l)
	{
		this.drawBack(k, l);
	}

	public void drawBack(int k, int l)
	{
	}

	public void drawSlots(int k, int l)
	{
		Tessellator t = GUI.t;

		GL11.glPushMatrix();

		GL11.glTranslated(k, l - 100, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(1.38, 1.38, 1);
		GL11.glTranslated(0, (20 - GuiContainer.sside), 0);

		int mx = (int) Math.round((Mouse.getX() - k) / (2 * 1.38));
		int my = (int) (Math.round((Mouse.getY() + 100 - l) / (2 * 1.38)));

		for (Slot slot : c.slots)
		{
			if (slot.getStack() != null)
			{
				slot.getStack().item.icon.getTexture().bind();

				double d = 1 / (2 * 1.38);

				t.start(GL11.GL_QUADS);

				t.addVertexWithUV(slot.x + d, slot.y - d + sside, 0, 0);
				t.addVertexWithUV(slot.x - d + sside, slot.y - d + sside, 1, 0);
				t.addVertexWithUV(slot.x - d + sside, slot.y + d, 1, 1);
				t.addVertexWithUV(slot.x + d, slot.y + d, 0, 1);

				t.draw();
			}
		}
		GL11.glPushMatrix();
		for (Slot slot : c.slots)
		{
			if (Utils.isInLimit(mx, slot.x, slot.x + sside))
			{
				if (Utils.isInLimit(my, slot.y, slot.y + sside))
				{
					GL11.glColor4d(1, 1, 1, 0.5);

					GUI.inslot = true;
					Sprite.sqr.getTexture().bind();
					t.start(GL11.GL_QUADS);

					t.addVertexWithUV(slot.x, slot.y + sside, 0, 0);
					t.addVertexWithUV(slot.x + sside, slot.y + sside, 1, 0);
					t.addVertexWithUV(slot.x + sside, slot.y, 1, 1);
					t.addVertexWithUV(slot.x, slot.y, 0, 1);

					t.draw();

					if (Binds.leftClick)
					{
						if (slot.inv.isItemValidForSlot(slot.id, GUI.guiis))
						{
							ItemStack ps = slot.getStack();
							slot.setStack(GUI.guiis);
							GUI.guiis = ps;
						}
					}
					GL11.glColor4d(1, 1, 1, 1);

					if (slot.getStack() != null)
					{
						List<String> ttl = new ArrayList<String>();
						ttl.add(slot.getStack().item.name);
						slot.getStack().item.addInformation(slot.getStack(), Main.p, ttl);

						int count = 0;
						for (String s : ttl)
						{
							if (s.length() > count)
							{
								count = s.length();
							}
						}
						int twidth = 8 * (count + 2);
						int theight = 6 * (ttl.size() + 2);

						// -+
						t.drawTexturedModalRect(mx + 2, my - 16, 0, 0, 8, 8, Sprite.getSprite("tooltip"));
						// ++
						t.drawTexturedModalRect(mx - 6 + twidth, my - 16, 24, 0, 8, 8, Sprite.getSprite("tooltip"));
						// --
						t.drawTexturedModalRect(mx + 2, my - 16 - theight, 0, 24, 8, 8, Sprite.getSprite("tooltip"));
						// +-
						t.drawTexturedModalRect(mx - 6 + twidth, my - 16 - theight, 24, 24, 8, 8, Sprite.getSprite("tooltip"));

						// horiz
						for (int i = 0; i < count; i++)
						{
							t.drawTexturedModalRect(mx + 10 + i * 8, my - 16, 8, 0, 8, 8, Sprite.getSprite("tooltip"));
							t.drawTexturedModalRect(mx + 10 + i * 8, my - 16 - theight, 8, 24, 8, 8, Sprite.getSprite("tooltip"));
						}
						// vert
						for (int i = 0; i < ttl.size() + 1; i++)
						{
							t.drawTexturedModalRect(mx + 2, my - 22 - 6 * i, 0, 8, 8, 6, Sprite.getSprite("tooltip"));
							t.drawTexturedModalRect(mx - 6 + twidth, my - 22 - 6 * i, 24, 8, 8, 6, Sprite.getSprite("tooltip"));
						}

						for (int i = 0; i < count; i++)
						{
							for (int j = 0; j < ttl.size() + 1; j++)
							{
								t.drawTexturedModalRect(mx + 10 + i * 8, my - 22 - j * 6, 8, 8, 8, 6, Sprite.getSprite("tooltip"));
							}
						}

						GL11.glPushMatrix();
						GL11.glTranslated(mx + 10, my - 16, 0);
						GL11.glRotated(180, 1, 0, 0);
						FontRenderer fr = new FontRenderer();
						int i = 0;
						for (String s : ttl)
						{
							fr.drawString(s, 0, 9 * i, 12);
							i += 1;
						}
						GL11.glPopMatrix();
					}
				}
			}
		}

		if (!GUI.inslot && Binds.leftClick)
		{
			// TODO
			if (GUI.guiis != null)
			{
				EntityItem ei = new EntityItem(Main.p.pos, GUI.guiis);
				ei.velocity = new Vec2(Utils.getIntInRange(-4, 4), Utils.getIntInRange(1, 4));
				ei.setTimer(30);
				GUI.croom.addObj(ei);
				GUI.guiis = null;
			}
		}

		GL11.glPopMatrix();
		if (GUI.guiis != null)
		{
			GUI.guiis.item.icon.getTexture().bind();

			t.start(GL11.GL_QUADS);

			t.addVertexWithUV(mx, my + sside, 0, 0);
			t.addVertexWithUV(mx + sside, my + sside, 1, 0);
			t.addVertexWithUV(mx + sside, my, 1, 1);
			t.addVertexWithUV(mx, my, 0, 1);

			t.draw();
		}
		GL11.glPopMatrix();
	}
}
