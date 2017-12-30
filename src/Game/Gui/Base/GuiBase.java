package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Core.KIJCore;
import Game.Gui.GuiContainer;
import Graphics.FontRenderer;
import Math.Vec.Vec2;
import Utilities.AABB2;

public class GuiBase extends GuiContainer
{
	public ContainerBase base;
	public int k;
	public int l;

	public GuiBase(ContainerBase c)
	{
		super(c.xSize, c.ySize, c);
		this.base = c;
		c.objs.stream().forEach(k ->
		{
			if (k instanceof GuiText)
				this.fox.add((GuiText) k);
		});
	}

	public void drawString(String s, int i, int j)
	{
		FontRenderer fr = new FontRenderer();
		fr.drawString(s, i, j);
	}

	@Override
	public void drawGui(int k, int l)
	{
		super.drawGui(k+dx, l+dy);
	}

	@Override
	public void drawBack(int k, int l)
	{
		for (GuiOBJ obj : this.base.objs)
		{
			GL11.glPushMatrix();
			obj.setGui(this);

			obj.render(k, l);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void drawSlots(int k, int l)
	{
		super.drawSlots(k, l);
	}

	@Override
	public boolean canFocus()
	{
		return true;
	}

	@Override
	public boolean canMove()
	{
		return true;
	}

	@Override
	public AABB2 getFocusAABB(int k, int l)
	{
		return super.getMoveAABB(k, l);
	}

	@Override
	public void input()
	{
		this.moveBy(KIJCore.mvx, KIJCore.mvy);
	}

	@Override
	public AABB2 getMoveAABB(int k, int l)
	{
		if (this.base.objs.get(0) instanceof GuiBackground)
		{
			GuiBackground gbg = (GuiBackground) this.base.objs.get(0);
			return new Vec2(k + (gbg.x + 2) * 2 * 1.38, l + (gbg.y - 16) * 2 * 1.38).extend(this.width * 2 * 1.38, this.borderHeight * 2 * 1.38).move(new Vec2(dx, dy));
		}
		return null;
	}

	@Override
	public void moveBy(double vx, double vy)
	{
		this.dx += vx;
		this.dy += vy;
	}

}
