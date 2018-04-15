package Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Game.Entities.Player;
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

	public static void main(String[] args)
	{
		init(args);
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
		Screen.init();
		Registry.init();
		Keyboard.enableRepeatEvents(true);
	}

	public static void update()
	{
		Screen.draw();
		Screen.update();
		Binds.update();
		updateMouse();

		if (Binds.pressed(SETTINGS.keyAttack))
		{
			if (Screen.focus != null)
			{
				if (Utils.isInLimit(new Vec2(mx, my), Screen.focus.getMoveAABB(Screen.K, Screen.L)))
				{
					Screen.focus.input();
				}
				if (Utils.isInLimit(new Vec2(mx, my), Screen.focus.getFocusAABB(Screen.K, Screen.L)))
				{
					Screen.removeFocus();
				}
			}
			else
				input();
		}
		else
			if (Screen.focus == null)
				input();

		pause = (Screen.focus != null && Screen.focus.pause);
		if (!pause)
		{
			Screen.room.updateRoom();

		}
		if (!Screen.room.objs.contains(p))
		{
			if (Binds.keyClick(Keyboard.KEY_L))
			{
				p.life = p.maxlife;
				p.energy = p.maxenergy;
				p.lifeTime = 0;
				p.setDead(false);
				Screen.room.objs.add(p);
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
