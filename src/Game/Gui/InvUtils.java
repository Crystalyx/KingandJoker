package Game.Gui;

import java.util.ArrayList;
import java.util.List;

import Game.Item;
import Game.ItemStack;

public class InvUtils
{
	public static boolean contains(IInventory inv, Item i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					return true;
				}
			}
		}
		return false;
	}

	public static boolean contains(IInventory inv, Item i, int meta)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					return true;
				}
			}
		}
		return false;
	}

	public static boolean contains(IInventory inv, ItemStack i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static ItemStack get(IInventory inv, Item i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					return is;
				}
			}
		}
		return null;
	}

	public static ItemStack get(IInventory inv, Item i, int meta)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					return is;
				}
			}
		}
		return null;
	}

	public static ItemStack get(IInventory inv, ItemStack i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					return is;
				}
			}
		}
		return null;
	}

	public static int find(IInventory inv, Item i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					return j;
				}
			}
		}
		return -1;
	}

	public static int find(IInventory inv, Item i, int meta)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					return j;
				}
			}
		}
		return -1;
	}

	public static int find(IInventory inv, ItemStack i)
	{
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					return j;
				}
			}
		}
		return -1;
	}

	public static int count(IInventory inv, Item i)
	{
		int c = 0;
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					c += 1;
				}
			}
		}
		return c;
	}

	public static int count(IInventory inv, Item i, int meta)
	{
		int c = 0;
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					c += 1;
				}
			}
		}
		return c;
	}

	public static int count(IInventory inv, ItemStack i)
	{
		int c = 0;
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					c += 1;
				}
			}
		}
		return c;
	}

	public static List<Integer> findL(IInventory inv, Item i)
	{
		List<Integer> l = new ArrayList<Integer>();
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					l.add(j);
				}
			}
		}
		return l;
	}

	public static List<Integer> findL(IInventory inv, Item i, int meta)
	{
		List<Integer> l = new ArrayList<Integer>();

		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					l.add(j);
				}
			}
		}
		return l;
	}

	public static List<Integer> findL(IInventory inv, ItemStack i)
	{
		List<Integer> l = new ArrayList<Integer>();

		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					l.add(j);
				}
			}
		}
		return l;
	}

	public static List<ItemStack> getL(IInventory inv, Item i)
	{
		List<ItemStack> l = new ArrayList<ItemStack>();
		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i)
				{
					l.add(is);
				}
			}
		}
		return l;
	}

	public static List<ItemStack> getL(IInventory inv, Item i, int meta)
	{
		List<ItemStack> l = new ArrayList<ItemStack>();

		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.item == i && is.meta == meta)
				{
					l.add(is);
				}
			}
		}
		return l;
	}

	public static List<ItemStack> getL(IInventory inv, ItemStack i)
	{
		List<ItemStack> l = new ArrayList<ItemStack>();

		for (int j = 0; j < inv.getSizeInventory(); j++)
		{
			ItemStack is = inv.getStackInSlot(j);
			if (is != null)
			{
				if (is.equals(i))
				{
					l.add(is);
				}
			}
		}
		return l;
	}

}
