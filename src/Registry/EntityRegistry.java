package Registry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Game.Entities.InductiveCore;
import Game.Entities.Manequen;
import Game.Entities.Player;
import Game.Entities.Sparkler;
import Game.Entities.Swarm;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityArrow;
import Game.Entities.API.EntityItem;
import Game.Entities.Throwable.Cannonball;
import Game.Entities.Throwable.Fireball;
import Game.Entities.Throwable.InducedMithrillium;
import Game.Entities.Throwable.LaserPart;
import Game.Entities.Throwable.Lightingball;
import Game.Entities.Throwable.Sparkle;
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
		addEntityMapping(Swarm.class, 8, true);
		addEntityMapping(Cannonball.class, 9, true);
		addEntityMapping(Lightingball.class, 10, true);
		addEntityMapping(Fireball.class, 11, true);
		addEntityMapping(LaserPart.class, 12, true);

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
