package Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Game.Entities.Player;
import Game.Items.API.ItemBow;
import Graphics.GUI;
import Math.Complex.Quaternion;
import Math.Vec.Vec2;
import Registry.Binds;
import Registry.Registry;
import Registry.Settings;
import UCS.EventBus;
import Utilities.Configuration;
import Utilities.Logger;
import Utilities.TypeUtils;
import Utilities.Utils;

public class KIJCore
{
	public static boolean isExitRequested = false;
	public static boolean pause = false;
	public static Player p;
	public static final EventBus EVENT_BUS = new EventBus();
	public static final Settings SETTINGS = new Settings();
	public static final Configuration cfg = new Configuration();
	public static final int slowness = 20;
	public static boolean tickFox = false;

	public static void main(String[] args)
	{
		init(args);
		TypeUtils.initStr();

		while (!isExitRequested)
		{
			update();
		}

		destruct();
	}

	public static void init(String[] args)
	{
		TypeUtils.initStr();

		cfg.readConfig();
		SETTINGS.load();
		p = new Player();
		GUI.init();
		Registry.init();
		Keyboard.enableRepeatEvents(true);
	}

	public static void update()
	{
		GUI.draw();
		GUI.update();
		Binds.update();
		updateMouse();

		if (Binds.pressed(SETTINGS.keyAttack))
		{
			if (GUI.focus != null)
			{
				if (GUI.focus.canFocus())
				{
					GUI.focus.input();
				}
				else
				{
					GUI.focus = null;
				}
			}
			else
				input();
			tickFox = false;

			if (GUI.fox != null && Utils.isInLimit(new Vec2(mx, my), GUI.fox.getMoveAABB(GUI.K, GUI.L)))
			{
				if (!tickFox)
				{
					tickFox = true;
					GUI.focus = GUI.fox;
				}
			}
			if (!tickFox)
				GUI.focus = null;
		}
		else
			if (GUI.focus == null)
				input();

		pause = (GUI.gui != null && GUI.gui.pause);
		if (!pause)
		{
			GUI.room.updateRoom();

			// p.update();
		}
		if (!GUI.room.objs.contains(p))
		{
			if (Binds.keyClick(Keyboard.KEY_L))
			{
				p.life = p.maxlife;
				p.energy = p.maxenergy;
				p.lifeTime = 0;
				p.setDead(false);
				GUI.room.objs.add(p);
			}
		}
		isExitRequested = isExitRequested || Display.isCloseRequested() || (Binds.pressed(Keyboard.KEY_LSHIFT) && Binds.pressed(Keyboard.KEY_ESCAPE));

	}

	public static void destruct()
	{
		Logger.info("Exit Requested");
		Logger.outputLog("World");
		cfg.setupConfig();
	}

	public static double mvx = 0;
	public static double mvy = 0;
	public static double mpx = -1;
	public static double mpy = -1;
	public static double mx = -1;
	public static double my = -1;

	public static void updateMouse()
	{
		mx = Mouse.getX();
		my = Mouse.getY();

		if (mpx == -1)
		{
			mpx = Mouse.getX();
		}
		if (mpy == -1)
		{
			mpy = Mouse.getY();
		}

		mvx = Mouse.getX() - mpx;
		mvy = Mouse.getY() - mpy;

		mpx = Mouse.getX();
		mpy = Mouse.getY();
	}

	private static void input()
	{
		if (Binds.pressed(SETTINGS.keyUp))
		{
			p.vel.y = p.maxVelocity.y;
		}
		if (Binds.pressed(SETTINGS.keyRight))
		{
			p.vel.x = p.maxVelocity.x;
		}
		if (Binds.pressed(SETTINGS.keyDown))
		{
			p.vel.y = -p.maxVelocity.y;
		}
		if (Binds.pressed(SETTINGS.keyLeft))
		{
			p.vel.x = -p.maxVelocity.x;
		}
	}

}
