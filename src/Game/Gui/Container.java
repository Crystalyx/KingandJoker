package Game.Gui;

import java.util.ArrayList;
import java.util.List;

public class Container
{
	public List<Slot> slots = new ArrayList<Slot>();

	public void addSlotToContainer(int id, int x, int y, IInventory inv)
	{
		slots.add(new Slot(id, x, y, inv));
	}
	
	public void addSlotToContainer(Slot s)
	{
		slots.add(s);
	}
}
