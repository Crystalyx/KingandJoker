package UCS;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Utilities.Configuration;
import Utilities.Logger;

public class EventBus
{
	/**
	 * The bigger value - Lower priority
	 */
	public List<Class<? extends Object>> ehs = new ArrayList<Class<? extends Object>>();

	public void addHandler(Class<? extends Object> eh)
	{
		if (eh.isAnnotationPresent(EventHandler.class))
			this.ehs.add(eh);
	}

	public void raiseEvent(Event e)
	{
		for (Class<? extends Object> eh : this.ehs)
		{
			Class<?> clazz = eh;

			if (clazz.isAnnotationPresent(EventHandler.class))
			{
				Method[] methods = clazz.getDeclaredMethods();

				for (int i = 0; i < methods.length; i++)
				{
					if (!e.canceled)
					{
						Method m = methods[i];
						if (m.isAnnotationPresent(SubscribeEvent.class))
						{
							Type[] param = m.getGenericParameterTypes();
							Type par = param[0];
							if (par.getTypeName() == e.getClass().getTypeName())
							{
								try
								{
									m.invoke(eh, e);
								}
								catch (Exception e1)
								{
									e1.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		if (Configuration.getBoolean("System", "eventLogging", false))
			if (e.canceled)
				Logger.addToLog("Canceled");
	}

}
