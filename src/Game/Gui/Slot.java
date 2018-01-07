package Game.Gui;

import Game.ItemStack;

public class Slot
{
	public int id;

	public double x;
	public double y;
	public IInventory inv;
	public Container cont;

	public Slot(int id, double x, double y, IInventory inv)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.inv = inv;
	}

	public ItemStack getStack()
	{
		return this.inv.getStackInSlot(id);
	}

	public void setStack(ItemStack is)
	{
		this.inv.setInventorySlotContent(id, is);
	}
}
