package Game.Gui;

import java.util.ArrayList;
import java.util.List;

import Game.Gui.Base.GuiButton;

public class Container
{
	public List<Slot> slots = new ArrayList<Slot>();
	public List<GuiButton> buttons = new ArrayList<GuiButton>();

	public void addSlotToContainer(int id, int x, int y, IInventory inv)
	{
		slots.add(new Slot(id, x, y, inv));
	}
	
	public void addSlotToContainer(Slot s)
	{
		slots.add(s);
	}
}
