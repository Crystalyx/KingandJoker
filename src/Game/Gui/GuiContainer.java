package Game.Gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import API.Focusable;
import Core.Screen;
import Core.KIJCore;
import Game.ItemStack;
import Game.Entities.API.EntityItem;
import Game.Gui.Base.GuiOBJ;
import Graphics.FontRenderer;
import Graphics.Icon;
import Math.Vec.Vec2;
import Registry.Binds;
import Utilities.AABB2;
import Utilities.Graph;
import Utilities.Tessellator;
import Utilities.Utils;

public class GuiContainer extends Focusable
{
	public Container c;
	public static int sside = 12;
	public double x = 0;
	public double y = 0;

	public GuiContainer(double width, double height, Container c)
	{
		super(width, height);
		this.c = c;
		this.hasContainer = true;
	}

	@Override
	public Container getContainer()
	{
		return this.c;
	}

	@Override
	public void drawGui(int k, int l)
	{
		this.drawBack(k + this.dx, l + this.dy);
		this.drawSlots(k + this.dx, l + this.dy);
	}

	public void drawBack(int k, int l)
	{
	}

	public void drawSlots(int k, int l)
	{
		Tessellator t = Screen.t;

		GL11.glPushMatrix();

		GL11.glTranslated(k, l - 100, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(1.38, 1.38, 1);
		GL11.glTranslated(0, (0 - GuiContainer.sside), 0);

		int mx = (int) Math.round((Mouse.getX() - k) / (2 * 1.38));
		int my = (int) (Math.round((Mouse.getY() + 100 - l) / (2 * 1.38)));

		for (Slot slot : c.slots)
		{
			if (slot.getStack() != null)
			{
				slot.getStack().item.icon.getTexture().bind();

				double d = 0;// 1 / (2 * 1.38);

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
				if (Utils.isInLimit(my, slot.y - sside, slot.y))
				{
					GL11.glColor4d(1, 1, 1, 0.5);

					Screen.inslot = true;
					Icon.sqr.getTexture().bind();
					t.start(GL11.GL_QUADS);

					t.addVertexWithUV(slot.x, slot.y + sside, 0, 0);
					t.addVertexWithUV(slot.x + sside, slot.y + sside, 1, 0);
					t.addVertexWithUV(slot.x + sside, slot.y, 1, 1);
					t.addVertexWithUV(slot.x, slot.y, 0, 1);

					t.draw();

					if (Binds.leftClick)
					{
						if (slot.inv.isItemValidForSlot(slot.id, Screen.guiis))
						{
							ItemStack ps = slot.getStack();
							slot.setStack(Screen.guiis);
							Screen.guiis = ps;
						}
					}
					GL11.glColor4d(1, 1, 1, 1);

					if (slot.getStack() != null)
					{
						List<String> ttl = new ArrayList<String>();
						ttl.add(slot.getStack().item.name);
						slot.getStack().item.addInformation(slot.getStack(), KIJCore.p, ttl);

						int count = 0;
						for (String s : ttl)
						{
							if (s.length() > count)
							{
								count = s.length();
							}
						}
						int twidth = 7 * (count + 2);
						int theight = 8 * (ttl.size() + 2);

						GuiOBJ.vanillaTexture.bind();
						GL11.glPushMatrix();
						GL11.glTranslated(mx, my - 4, 0);

						Graph.drawSizedSqr(this, 256, 256, 32, 32, twidth, theight, 224, 0, 8, 8);

						GL11.glPopMatrix();

						GL11.glPushMatrix();
						GL11.glTranslated(mx + 10, my - 4, 0);
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

		if (!Screen.inslot && Binds.leftClick)
		{
			// TODO
			if (Screen.guiis != null)
			{
				EntityItem ei = new EntityItem(KIJCore.p.pos, Screen.guiis);
				ei.vel = new Vec2(Utils.getIntInRange(-4, 4), Utils.getIntInRange(1, 4));
				ei.setTimer(30);
				Screen.room.addObj(ei);
				Screen.guiis = null;
			}
		}

		GL11.glPopMatrix();
		if (Screen.guiis != null)
		{
			Screen.guiis.item.icon.getTexture().bind();

			AABB2 ab = new Vec2(mx, my + sside).extendBoth(sside / 2);
			Graph.renderSqr(ab);
		}
		GL11.glPopMatrix();
	}
}
