package Game.Inventory;

import Game.ItemStack;
import Utilities.Tag;

public class PlayerEquip implements IInventory
{
	public ItemStack[] equip = new ItemStack[14];

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return equip[slot];
	}

	@Override
	public ItemStack setInventorySlotContent(int slot, ItemStack is)
	{
		ItemStack old = equip[slot];
		equip[slot] = is;
		return old;
	}

	public ItemStack addItemStack(ItemStack i)
	{
		ItemStack ret = i.copy();
		for (int j = 0; j < this.getSizeInventory(); j++)
		{
			if (this.equip[j] == null)
			{
				ret = null;
				this.equip[j] = i;
				break;
			}
		}
		return ret;
	}

	@Override
	public int getSizeInventory()
	{
		return 14;
	}

	@Override
	public ItemStack decreaseStackSize(int slot, int ct)
	{
		ItemStack is = this.equip[slot].copy();
		is.size = ct;

		if (this.equip[slot].size - ct <= 0)
		{
			this.equip[slot] = null;
		}
		else
			this.equip[slot].size -= ct;

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

}
