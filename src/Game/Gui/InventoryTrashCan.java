package Game.Gui;

import Game.ItemStack;
import Utilities.Tag;

public class InventoryTrashCan implements IInventory
{

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return null;
	}

	@Override
	public ItemStack setInventorySlotContent(int slot, ItemStack is)
	{
		return null;
	}

	@Override
	public ItemStack addItemStack(ItemStack is)
	{
		return null;
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack decreaseStackSize(int slot, int ct)
	{
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return "trash";
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
