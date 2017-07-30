package Game.Inventory;

import Game.ItemStack;

public class Slot
{
	public int id;

	public int x;
	public int y;
	public IInventory inv;
	public Container cont;

	public Slot(int id, int x, int y, IInventory inv)
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
