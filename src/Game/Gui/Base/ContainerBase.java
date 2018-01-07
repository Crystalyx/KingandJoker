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

	public double xSize;
	public double ySize;

	public IInventory tile;
	public PlayerInventory p;

	public void add(GuiOBJ obj)
	{
		this.objs.add(obj);
	}

	public void addPlayerSlots(int k, int l)
	{
		double d = 1 / 1.50d;
		for (int i = 0; i < 5; i++)
		{
			this.addSlot(i, KIJCore.p.inv, 9 + k, (GuiContainer.sside + d) * (4 - i) + l - this.ySize / 4);
		}
		for (int i = 0; i < 5; i++)
		{
			this.addSlot(i + 5, KIJCore.p.inv, 48 + (GuiContainer.sside + d) * 7 + k, (GuiContainer.sside + d) * (4 - i) + l - this.ySize / 4);
		}

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				this.addSlot(38 + j - 7 * i, KIJCore.p.inv, 39 + (GuiContainer.sside + d) * j + k, (GuiContainer.sside + d) * i + l - this.ySize / 4);
			}
		}
	}

	// =============PICTURE==============
	public void addPicture(double x, double y, int sizex, int sizey, int type, Color c)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c));
	}

	public void addHiddenPicture(double x, double y, int sizex, int sizey, int type, Color c, boolean hide)
	{
		this.add(new GuiPicture(x, y, sizex, sizey, type, c).setHidden(hide));
	}

	// =============BACKGROUND==============
	public void addBackground(double x, double y, int sizex, int sizey)
	{
		this.xSize = sizex;
		this.ySize = sizey;
		this.add(new GuiBackground(x, y, sizex, sizey));
	}

	// =============SLOT==============
	public Slot addSlot(int id, IInventory inv, double x, double y, int size)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, double x, double y, int size, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, -1).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, IInventory inv, double x, double y)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, double x, double y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, -1).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, IInventory inv, double x, double y, int size, int type)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, IInventory inv, double x, double y, int size, int type, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, size, type).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addSlot(int id, int type, IInventory inv, double x, double y)
	{
		this.add(new GuiSlot(x - 2, y - 2, GuiContainer.sside, type));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	public Slot addHiddenSlot(int id, int type, IInventory inv, double x, double y, boolean hidden)
	{
		this.add(new GuiSlot(x - 2, y - 2, GuiContainer.sside, type).setHidden(hidden));
		Slot s = new Slot(id, x, y, inv);
		this.addSlotToContainer(s);
		return s;
	}

	// =============TEXT==============
	public void addText(String text, double x, double y, int sizex, int sizey)
	{
		GuiText gt = new GuiText(text, x, y, sizex, sizey);
		this.add(gt);
	}

	public void addText(String text, double x, double y, int sizex, int sizey, boolean back)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back));
	}

	public void addText(String text, double x, double y, int sizex, int sizey, boolean back, int font)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back, font));
	}

	public void addText(String text, double x, double y, int sizex, int sizey, int font)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, font));
	}

	public void addHiddenText(String text, double x, double y, int sizex, int sizey, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey).setHidden(hide));
	}

	public void addHiddenText(String text, double x, double y, int sizex, int sizey, boolean back, boolean hide)
	{
		this.add(new GuiText(text, x, y, sizex, sizey, back).setHidden(hide));
	}

	// =============TOOLTIP==============
	public void addTooltip(double x, double y, int sizex, int sizey, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text));
	}

	public void addTooltip(double x, double y, int sizex, int sizey, boolean back, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text));
	}

	public void addHiddenTooltip(double x, double y, int sizex, int sizey, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, text).setHidden(hide));
	}

	public void addHiddenTooltip(double x, double y, int sizex, int sizey, boolean back, boolean hide, String... text)
	{
		this.add(new GuiTooltip(x, y, sizex, sizey, back, text).setHidden(hide));
	}

	// ===============BUTTON==================
	public void addButton(GuiButton gb)
	{
		this.buttons.add(gb);
	}
}
