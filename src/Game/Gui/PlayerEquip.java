package Game.Gui;

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
//		if (is != null)
//		{
//			switch (slot)
//			{
//			case (0):
//				return is.item instanceof ItemBelt;
//			case (1):
//				return is.item instanceof ItemRing;
//			case (2):
//				return is.item instanceof ItemRing;
//			case (3):
//				return is.item instanceof ItemAmulet;
//			case (4):
//				return is.item instanceof ItemBoots;
//			case (5):
//				return is.item instanceof ItemChestplate;
//			case (6):
//				return is.item instanceof ItemHelmet;
//			case (7):
//				return is.item instanceof ItemGlove;
//			case (8):
//				return is.item instanceof ItemShield;
//			case (9):
//				return is.item instanceof ItemCape;
//			case (10):
//				return is.item instanceof ItemGem;
//			case (11):
//				return is.item instanceof ItemBook;
//			case (12):
//				return is.item instanceof ItemArtifact;
//			case (13):
//				return is.item instanceof ItemGemPlate;
//			}
//		}
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
