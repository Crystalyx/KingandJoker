package Game.Inventory;

import Game.ItemStack;
import Utilities.Tag;

public interface IInventory
{
	public ItemStack getStackInSlot(int slot);

	public ItemStack setInventorySlotContent(int slot, ItemStack is);

	/**
	 * 
	 * @param i
	 * @return given Itemstack if there is no place else null
	 */
	public ItemStack addItemStack(ItemStack is);

	public int getSizeInventory();

	public ItemStack decreaseStackSize(int slot, int ct);

	public boolean isItemValidForSlot(int slot, ItemStack is);

	public String getInventoryName();

	public void read(Tag tag);

	public void write(Tag tag);
}
