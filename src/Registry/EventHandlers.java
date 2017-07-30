package Registry;

import Graphics.Main;
import Graphics.RenderHandler;

public class EventHandlers
{
	public static void register()
	{
		Main.EVENT_BUS.addHandler(RenderHandler.class);
	}
}
