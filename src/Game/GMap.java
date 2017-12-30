package Game;

public class GMap
{
	public Room[][] map = new Room[10][10];

	public void addRoom(Room r)
	{
		this.map[0][0] = r;
	}
}
