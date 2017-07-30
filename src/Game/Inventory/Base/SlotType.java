package Game.Inventory.Base;

import org.lwjgl.opengl.GL11;

import Game.Inventory.GuiContainer;

public class SlotType extends ObjType
{

	public SlotType(int id, int px, int py, int size)
	{
		super(Obj.Slot, id, px, py, size, size);
	}

	public void render(int k, int l, int x, int y, GuiSlot slot)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(k, l, 0);
		GL11.glScaled(2, 2, 1);
		GL11.glScaled(1.38, 1.38, 1);
		slot.bindTexture();
		this.gui.drawTexturedModalRect(x + 2 + (20 - GuiContainer.sside) / 2 + slot.sizex / 2 - this.sizex / 2, y - (int) (GuiContainer.sside * 2.1 + (20 - GuiContainer.sside) / 2) + slot.sizey / 2 - this.sizey / 2, this.px, this.py,
				this.sizex, this.sizey);
		GL11.glPopMatrix();
	}

}
