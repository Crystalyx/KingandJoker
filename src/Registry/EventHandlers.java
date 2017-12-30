package Registry;

import Core.KIJCore;
import Graphics.RenderHandler;

public class EventHandlers
{
	public static void register()
	{
		KIJCore.EVENT_BUS.addHandler(RenderHandler.class);
	}
}
