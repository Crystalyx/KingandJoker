package Registry;

import java.util.Hashtable;

import Graphics.Render.BasicRender;
import Graphics.Render.PlayerRender;
import Graphics.Render.Render;
import Graphics.Render.RotativeRender;
import Graphics.Render.SparklerRender;
import Registry.Registry.RegistrationException;

public class BorderRenderRegistry
{
	public static Hashtable<Integer, Render> renders = new Hashtable<Integer, Render>();

	public static void register()
	{
		try
		{
			registerRenderer(new BasicRender(), 0);
		}
		catch (RegistrationException e)
		{
			e.printStackTrace();
		}
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

	public static void registerRenderer(Render r, int id) throws RegistrationException
	{
		if (!renders.containsKey(id))
		{
			renders.put(id, r);
		}
		else
		{
			throw new RegistrationException(RegistrationException.Type.Entity, id, r);
		}
	}
}
