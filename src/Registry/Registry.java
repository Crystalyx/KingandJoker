package Registry;

import Game.Item;
import Game.Entities.Entity;
import Graphics.Render.Render;

public class Registry
{
	public static void init()
	{
		Items.register();
		Binds.register();
		EntityRegistry.register();
		RenderRegistry.register();

		EventHandlers.register();
	}

	@SuppressWarnings("serial")
	public static class RegistrationException extends Exception
	{
		public enum Type
		{
			Item, Entity, Render
		};

		public Type type;
		public int id;
		public Object obj;

		public RegistrationException(Type t, int id, Object obj)
		{
			this.type = t;
			this.id = id;
			this.obj = obj;
		}

		@Override
		public String getMessage()
		{
			switch (type)
			{
			case Item:
				return "Item register error when tried to register \"" + ((Item) obj).name + "\". Id " + id + " has been already occupied by \"" + GameRegistry.items.get(id).name + "\" ";
			case Entity:
				return "Entity register error when tried to register \"" + ((Entity) obj).getClass().getName() + "\". Id " + id + " has been already occupied by \"" + EntityRegistry.entities.get(id).getName() + "\" ";
			case Render:
				return "Render register error when tried to register \"" + ((Render) obj).getClass().getName() + "\". Id " + id + " has been already occupied by \"" + RenderRegistry.renders.get(id).getClass().getName() + "\" ";
			}
			return super.getMessage();
		}
	}
}
