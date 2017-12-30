package Game;

import java.util.Random;

public class MapCreator
{
	Random rand = new Random();

	public void fillMap(GMap map)
	{
		
	}
	
	public void fillRoom(Room map)
	{
		
	}

	public static float range(float a, float b, float c, float d)
	{
		return (float) Math.sqrt((a - c) * (a - c) + (b - d) * (b - d));
	}
}
