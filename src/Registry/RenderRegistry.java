package Registry;

import java.util.Hashtable;

import Graphics.Render.BasicRender;
import Graphics.Render.PlayerRender;
import Graphics.Render.Render;
import Graphics.Render.RotativeRender;
import Graphics.Render.SparklerRender;
import Registry.Registry.RegistrationException;

public class RenderRegistry
{
	public static Hashtable<Integer, Render> renders = new Hashtable<Integer, Render>();

	public static void register()
	{
		registerRenderer(new BasicRender(), 0);
		registerRenderer(new PlayerRender(), 1);
		registerRenderer(new SparklerRender(), 2);
		registerRenderer(new RotativeRender(), 3);
	}

	public static Render getRender(int id)
	{
		try
		{
			return renders.get(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return renders.get(0);
	}

	public static void registerRenderer(Render r, int id)
	{
		if (!renders.containsKey(id))
		{
			renders.put(id, r);
		}
		else
		{
			RegistrationException re = new RegistrationException(RegistrationException.Type.Entity, id, r);
			re.printStackTrace();
		}
	}
}
