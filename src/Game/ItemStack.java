package Game;

import Registry.GameRegistry;
import Utilities.Tag;

public class ItemStack
{
	public Item item;
	public int meta;
	public int size;
	public Tag nbt;

	public ItemStack(Item i)
	{
		this.item = i;
		this.size = 1;
	}

	public ItemStack(Item i, int size)
	{
		this.item = i;
		this.size = size;
	}

	public ItemStack(Item i, int meta, int size)
	{
		this.item = i;
		this.meta = meta;
		this.size = 1;
	}

	public ItemStack setTag(Tag tg)
	{
		nbt = tg;
		return this;
	}
	
	public boolean equals(ItemStack is)
	{
		return this.item == is.item && this.meta == is.meta && this.size == is.size && ((this.nbt == null && is.nbt == null) || this.nbt.equals(is.nbt));
	}

	public void write(Tag equip, String base)
	{
		equip.add(base + "_ItemID", this.item.uid);
		equip.add(base + "_stacksize", this.size);
		equip.add(base + "_meta", this.meta);
		equip.appendTag(base + "_Tag", this.nbt);
	}

	public static ItemStack read(Tag equip, String base)
	{
		Item item = GameRegistry.getItem(equip.getInt(base + "_ItemID"));
		int size = equip.getInt(base + "_stacksize");
		int meta = equip.getInt(base + "_meta");
		Tag nbt = equip.getTagAt(base + "_Tag");
		return new ItemStack(item, meta, size).setTag(nbt);
	}

	public ItemStack copy()
	{
		ItemStack ret = new ItemStack(this.item, this.meta, this.size);
		if (this.nbt != null)
			ret.setTag(this.nbt.copy());
		return ret;
	}
}
