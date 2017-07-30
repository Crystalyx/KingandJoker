package Registry;

import java.util.Hashtable;

import Game.Item;
import Registry.Registry.RegistrationException;

public class GameRegistry
{
	public static Hashtable<Integer, Item> items = new Hashtable<Integer, Item>();

	public static void registerItem(int id, Item i) throws RegistrationException
	{
		if (!items.containsKey(id))
		{
			i.uid = id;
			items.put(id, i);
		}
		else
		{
			RegistrationException re = new RegistrationException(RegistrationException.Type.Item, id, i);
			re.printStackTrace();
		}
	}

	public static Item getItem(int id)
	{
		if (items.containsKey(id))
		{
			return items.get(id);
		}
		else
			return null;
	}
}
