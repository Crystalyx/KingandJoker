package Game.Gui;

import Game.ItemStack;
import Utilities.Tag;

public class PlayerInventory implements IInventory
{
	public ItemStack[] inv = new ItemStack[45];// 5-hot1 25 - inv 5 -hot2

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack setInventorySlotContent(int slot, ItemStack is)
	{
		ItemStack old = inv[slot];
		inv[slot] = is;
		return old;
	}

	@Override
	public int getSizeInventory()
	{
		return 45;
	}

	@Override
	public ItemStack decreaseStackSize(int slot, int ct)
	{
		ItemStack is = this.inv[slot].copy();
		is.size = ct;

		if (this.inv[slot].size - ct <= 0)
		{
			this.inv[slot] = null;
		}
		else
			this.inv[slot].size -= ct;

		return is;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return "PInv";
	}

	@Override
	public void read(Tag tag)
	{

	}

	@Override
	public void write(Tag tag)
	{

	}

	public ItemStack addItemStack(ItemStack i)
	{
		ItemStack ret = i.copy();
		for (int j = 0; j < this.getSizeInventory(); j++)
		{
			if (this.inv[j] == null)
			{
				ret = null;
				this.inv[j] = i;
				break;
			}
		}
		return ret;
	}

}
