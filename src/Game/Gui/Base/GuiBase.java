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

	public void drawString(String s, double i, double j, int font)
	{
		FontRenderer fr = new FontRenderer();
		fr.drawString(s, i, j, font);
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

		for (GuiButton b : this.base.buttons)
		{
			GL11.glPushMatrix();
			b.setGui(this);

			b.render(k, l);
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
	public void input()
	{
		this.moveBy(KIJCore.mvx, KIJCore.mvy);
	}

	@Override
	public AABB2 getMoveAABB(int k, int l)
	{
		return new Vec2(k + dx - (this.width - 30) * 2.76 / 2, l + dy - this.borderHeight * 4 + 16 + (this.height + this.borderHeight * 2) * 2.76 / 2).extend((this.width + 15) * 2.76, this.borderHeight * 2.76);
	}

	@Override
	public AABB2 getFocusAABB(int k, int l)
	{
		return new Vec2(k + dx - (this.width - 30) * 2.76 / 2, l + dy - this.borderHeight * 4 + 16 + (this.height + this.borderHeight * 4) * 2.76 / 2).extend((this.width + 15) * 2.76, 24 - this.borderHeight - this.height * 2.76);
	}

	@Override
	public void moveBy(double vx, double vy)
	{
		this.dx += vx;
		this.dy += vy;
	}

	@Override
	public void onFocusing()
	{
		this.base.buttons.stream().filter(s -> s instanceof GuiSwitch).forEach(k -> ((GuiSwitch) k).state = ((GuiSwitch) k).getState());
	}

}
