package Registry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Game.Entities.Entity;
import Game.Entities.EntityArrow;
import Game.Entities.EntityItem;
import Game.Entities.InducedMithrillium;
import Game.Entities.InductiveCore;
import Game.Entities.Manequen;
import Game.Entities.Player;
import Game.Entities.Sparkle;
import Game.Entities.Sparkler;
import Utilities.Logger;

public class EntityRegistry
{
	public static void register()
	{
		addEntityMapping(Player.class, 0, true);
		addEntityMapping(EntityItem.class, 1, true);
		addEntityMapping(Manequen.class, 2, true);
		addEntityMapping(Sparkler.class, 3, true);
		addEntityMapping(Sparkle.class, 4, true);
		addEntityMapping(InductiveCore.class, 5, true);
		addEntityMapping(InducedMithrillium.class, 6, true);
		addEntityMapping(EntityArrow.class, 7, true);

	}

	public static Hashtable<Integer, Class<? extends Entity>> entities = new Hashtable<Integer, Class<? extends Entity>>();
	public static List<Class<? extends Entity>> movingEntities = new ArrayList<Class<? extends Entity>>();

	public static void addEntityMapping(Class<? extends Entity> e, int id, boolean sendVelUpd)
	{
		if (!entities.containsKey(id))
		{
			entities.put(id, e);
		}
		else
		{
			Logger.warn("Entity ID " + id + " has already been occupied by " + entities.get(id).getClass());
		}
		if (sendVelUpd)
		{
			movingEntities.add(e);
		}
	}

}
