package Game.Gui.Base;

import java.util.ArrayList;
import java.util.List;

public class ObjTypes
{
	public static List<SlotType> slots = new ArrayList<SlotType>();
	public static List<BarType> bars = new ArrayList<BarType>();

	public static void load()
	{
		for (int i = 0; i < 11; i++)
		{
			addSlotType(i, 0, 20 * (i + 1), 20);
		}
		addSlotType(11, 20, 0, 20);
		addSlotType(12, 20, 20, 20);

	}

	public static void addSlotType(int id, int px, int py, int size)
	{
		slots.add(new SlotType(id, px, py, size));
	}

	public static void addBarType(int id, int px, int py, int sizex, int sizey)
	{
		bars.add(new BarType(id, px, py, sizex, sizey));
	}
}
