package Core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import API.Focusable;
import GUI.GuiEquipment;
import Game.Border;
import Game.GMap;
import Game.ItemStack;
import Game.MapCreator;
import Game.Room;
import Game.Entities.InductiveCore;
import Game.Entities.Sparkler;
import Game.Entities.Swarm;
import Game.Entities.AI.AICentryFollow;
import Game.Entities.API.EntityItem;
import Game.Gui.ContainerEquipment;
import Game.Gui.Base.ContainerBase;
import Game.Gui.Base.GuiBase;
import Game.Gui.Base.ObjTypes;
import Graphics.FontRenderer;
import Graphics.Icon;
import Math.Vec.Vec2;
import Registry.Binds;
import Registry.Items;
import Utilities.AABB2;
import Utilities.Color;
import Utilities.Graph;
import Utilities.Tessellator;
import Utilities.Utils;

public class GUI
{

	public static final String SCREEN_NAME = "King And Joker";

	public static final int SCREEN_WIDTH_BASE = 800;
	public static final int SCREEN_HEIGHT_BASE = 600;
	public static final int PLAYER_WIDTH = 40;
	public static final int PLAYER_HEIGHT = 40;

	public static int K = SCREEN_WIDTH_BASE / 2;
	public static int L = SCREEN_HEIGHT_BASE / 2;
	public static int SCREEN_WIDTH = 800;
	public static int SCREEN_HEIGHT = 600;

	public static ItemStack guiis;
	public static boolean inslot = false;

	public static Focusable focus;

	public static GMap map;
	public static Room room;
	public static MapCreator generator = new MapCreator();
	public static int dx = 0, dy = 0;
	public static int FPS = 60;
	public static Tessellator t = Tessellator.instance;
	public static GuiBase gui;
	public static ContainerBase cont;

	public static long time = 0l;

	public static void init()
	{
		initializeOpenGL();
		room = new Room();
		room.setSlowness(KIJCore.slowness);
		room.addObj(KIJCore.p);
	}

	private static void initializeOpenGL()
	{
		try
		{
			Display.setResizable(true);

			Display.setTitle(SCREEN_NAME);

			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		FontRenderer.load();
		ObjTypes.load();
		SCREEN_WIDTH = Display.getWidth();
		SCREEN_HEIGHT = Display.getHeight();
		K = SCREEN_WIDTH / 2;
		L = SCREEN_HEIGHT / 2;

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glClearColor(1, 1, 1, 1);

	}

	public static void draw()
	{
		GL11.glPushMatrix();

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		// GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
		// GL11.GL_NEAREST);
		room.draw();

		if (focus != null)
		{
			focus.drawGui(K, L);

			// if (gui != null)
			// {
			// gui.drawGui(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
			// }
			AABB2 ab = GUI.focus.getMoveAABB(GUI.K, GUI.L);
			// Logger.info(ab);
			// Logger.info(new Vec2(Main.mpx, Main.mpy));

			if (ab != null)
			{
				Graph.colorize(Color.Red);
				Icon.sqr.bind();
				Graph.renderAABB(ab);
				Graph.clearColor();
			}
			GL11.glPopMatrix();

			if (!inslot && Binds.leftClick)
			{
				if (guiis != null)
				{
					EntityItem ei = new EntityItem(KIJCore.p.pos, guiis);
					ei.vel = new Vec2(Utils.getIntInRange(-4, 4), Utils.getIntInRange(1, 4));
					ei.setTimer(30);
					room.addObj(ei);
					guiis = null;
				}
			}
		}
	}

	public static void update()
	{
		updateOpenGL();
		inslot = false;

		// fox.addLast(arg0);

		if (Binds.keyClick(KIJCore.SETTINGS.keyWave))
		{
			KIJCore.p.inv.addItemStack(new ItemStack(Items.Laser));
			KIJCore.p.inv.addItemStack(new ItemStack(Items.lightning_gun));
			KIJCore.p.inv.addItemStack(new ItemStack(Items.flamethrw));
			KIJCore.p.inv.addItemStack(new ItemStack(Items.cannon));
			KIJCore.p.inv.addItemStack(new ItemStack(Items.GlassGun));

			Border b = new Border(new Vec2(room.width / 2, room.height / 2), 50, 50, Icon.getIcon("border/glass"));
			room.addBorder(b);

			for (int i = 0; i < Utils.r.nextInt(20); i++)
			{
				Sparkler s = new Sparkler(new Vec2(180, 280 + i * 6));
				GUI.room.addObj(s);
			}
			for (int i = 0; i < Utils.r.nextInt(20); i++)
			{
				InductiveCore s = new InductiveCore(new Vec2(180, 280 + i * 6));
				GUI.room.addObj(s);
			}
			for (int i = 0; i < Utils.r.nextInt(20); i++)
			{
				Swarm s = new Swarm(new Vec2(180, 280 + i * 6));
				GUI.room.addObj(s);
				((AICentryFollow) s.ais.get(1)).setCentry(KIJCore.p, new Vec2(0, 32));
			}
		}

		if (Binds.keyClick(KIJCore.SETTINGS.keyEquip))
		{
			if (cont != null)
			{
				focus = null;
				cont = null;
				gui = null;
			}
			else
			{
				cont = new ContainerEquipment();
				gui = new GuiEquipment(cont);
				focus = gui;
			}
		}

		time++;
	}

	private static void updateOpenGL()
	{
		Display.update();
		SCREEN_WIDTH = Display.getWidth();
		SCREEN_HEIGHT = Display.getHeight();
		Display.sync(FPS);

	}
}
