package Game;

import java.util.ArrayList;
import java.util.List;

public class GMap
{
	public List<Room> map= new ArrayList<Room>();

	public void addRoom(Room r)
	{
		this.map.add(r);
	}
}
