package Graphics;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import Game.Entities.Player;
import Registry.Binds;
import Registry.Registry;
import Registry.Settings;
import UCS.EventBus;
import Utilities.Configuration;
import Utilities.Logger;
import Utilities.Utils;
import Utilities.Vec2;

public class Main
{
	public static boolean isExitRequested = false;
	public static boolean pause = false;
	public static Player p;
	public static final EventBus EVENT_BUS = new EventBus();
	public static final Settings SETTINGS = new Settings();

	public static void main(String[] args)
	{
		Configuration.readConfig();
		SETTINGS.load();

		p = new Player();
		GUI.init();
		Registry.init();
		Keyboard.enableRepeatEvents(true);

		while (!isExitRequested)
		{
			GUI.draw();
			GUI.update();
			Binds.update();

			pause = (GUI.gui1 != null && GUI.gui1.pause) || (GUI.gui2 != null && GUI.gui2.pause);
			if (!pause)
			{
				input();
				GUI.croom.updateRoom();
				// p.update();
			}
			isExitRequested = Binds.pressed(Keyboard.KEY_LSHIFT) && Binds.pressed(Keyboard.KEY_ESCAPE);
		}
		Logger.info("Exit Requested");
		Logger.outputLog("World");
		Configuration.setupConfig();
	}

	private static void input()
	{
		if (Binds.pressed(SETTINGS.keyJump) && (p.g || p.ableToFly))
			p.velocity.y = p.maxVelocity.y;

		if (Binds.pressed(SETTINGS.keyRight))
			p.velocity = Utils.limit(p.velocity.add(p.at), new Vec2().extendBoth(p.maxVelocity));

		if (Binds.pressed(SETTINGS.keyStrafe))
			p.velocity.y = -p.maxVelocity.y;

		if (Binds.pressed(SETTINGS.keyLeft))
			p.velocity = Utils.limit(p.velocity.sub(p.at), new Vec2().extendBoth(p.maxVelocity));

		isExitRequested = isExitRequested || Display.isCloseRequested();
	}

}
