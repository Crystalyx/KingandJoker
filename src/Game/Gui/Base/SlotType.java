package Game.Gui.Base;

import org.lwjgl.opengl.GL11;

import Math.Vec.Vec2;
import Utilities.AABB2;
import Utilities.Graph;

public class SlotType extends ObjType
{

	public SlotType(int id, int px, int py, int size)
	{
		super(Objs.Slot, id, px, py, size, size);
	}

	public void render(int k, int l, double x, double y, GuiSlot slot)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(k, l, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(1.38, 1.38, 1);
		GL11.glTranslated(x, y, 0);
		slot.bindTexture();

		AABB2 uvab = new Vec2(this.px, this.py).extend(this.sizex, this.sizey).multiply(1 / 256d);

		AABB2 rab = new Vec2().extend(slot.width, slot.height);

		Graph.renderSqrWithUV(rab, uvab);
		GL11.glPopMatrix();
	}

}
