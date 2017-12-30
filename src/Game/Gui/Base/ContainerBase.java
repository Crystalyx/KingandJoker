package Game.Gui.Base;

import java.util.ArrayList;
import java.util.List;

import Core.KIJCore;
import Game.Gui.Container;
import Game.Gui.GuiContainer;
import Game.Gui.IInventory;
import Game.Gui.PlayerInventory;
import Game.Gui.Slot;
import Utilities.Color;

public class ContainerBase extends Container
{
	public List<GuiOBJ> objs = new ArrayList<GuiOBJ>();
	public int xSize;
	public int ySize;

	public IInventory tile;
	public PlayerInventory p;

	public void add(GuiOBJ obj)
	{
		this.objs.add(obj);
	}

	public void addPlayerSlots(int k, int l)
	{
		for (int i = 0; i < 5; i++)
		{
			this.addSlot(i, KIJCore.p.inv, 9 + k, (int) (20 * (4 - i)) + l - this.ySize / 4);
		}
		for (int i = 0; i < 5; i++)
		{
			this.addSlot(i + 5, KIJCore.p.inv, 48 + (int) (20 * 7) + k, (int) (20 * (4 - i)) + l - this.ySize / 4);
		}

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				this.addSlot(38 + j - 7 * i, KIJCore.p.inv, 39 + (int) (20 * j) + k, (int) (20 * i) + l - this.ySize / 4);
			}
		}
	}

	// =============PICTURE==============
	public void addPicture(int x, int y, int sizex, int sizey, int type, Color c)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c));
	}

	public void addHiddenPicture(int x, int y, int sizex, int sizey, int type, Color c, boolean hide)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c).setHidden(hide));
	}

	// =============BACKGROUND==============
	public void addBackground(int x, int y, int sizex, int sizey)
	{
		this.xSize = sizex;
		this.ySize = sizey;
		this.add(new GuiBackground(x, y, sizex, sizey));
	}

	// =============SLOT==============
	public Slot addSlot(int id, IInventory inv, int x, int y, int size)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, int x, int y, int size, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, IInventory inv, int x, int y)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, int x, int y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, IInventory inv, int x, int y, int size, int type)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, int x, int y, int size, int type, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, int type, IInventory inv, int x, int y)
	{
		this.add(new GuiSlot(x - 2, y - 2, GuiContainer.sside - 2, type));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, int type, IInventory inv, int x, int y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, GuiContainer.sside - 2, type).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	// =============TEXT==============
	public void addText(String text, int x, int y, int sizex, int sizey)
	{
		GuiText gt = new GuiText(text, x, y, sizex, sizey);
		this.add(gt);
	}

	public void addText(String text, int x, int y, int sizex, int sizey, boolean back)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back));
	}

	public void addHiddenText(String text, int x, int y, int sizex, int sizey, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey).setHidden(hide));
	}

	public void addHiddenText(String text, int x, int y, int sizex, int sizey, boolean back, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back).setHidden(hide));
	}

	// =============TOOLTIP==============
	public void addTooltip(int x, int y, int sizex, int sizey, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text));
	}

	public void addTooltip(int x, int y, int sizex, int sizey, boolean back, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text));
	}

	public void addHiddenTooltip(int x, int y, int sizex, int sizey, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text).setHidden(hide));
	}

	public void addHiddenTooltip(int x, int y, int sizex, int sizey, boolean back, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text).setHidden(hide));
	}
}
