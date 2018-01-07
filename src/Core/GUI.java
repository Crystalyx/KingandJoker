package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import Game.Gui.Container;
import Game.Gui.ContainerEquipment;
import Game.Gui.InvUtils;
import Game.Gui.Base.ContainerBase;
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
	public static Container cont;

	public static List<Focusable> screenObjs = new ArrayList<Focusable>();

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
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		room.draw();

		screenObjs.stream().filter(k -> k != focus).forEach(f -> f.drawGui(K, L));

		/**
		 * Drawing debug stuff
		 */
		if (focus != null)
		{
			focus.drawGui(K, L);

			if (Binds.pressed(KIJCore.SETTINGS.keyDebug))
			{
				drawInterractionBBs(focus);

				cont.buttons.stream().forEach(b ->
				{
					AABB2 buab = new Vec2(b.x * 2.76 + K + focus.dx, (b.y - 13) * 2.76 + L + focus.dy).extend(b.width * 2.76, b.height * 2.76);
					Graph.renderAABB(buab);
				});

				// Drawing mouse
				AABB2 moab = new Vec2(KIJCore.mx, KIJCore.my).extend(20, 20);
				Graph.renderSqr(moab);
			}

			/**
			 * GuiButton Handling
			 */
			cont.buttons.stream().filter(b -> b.checkMouse()).peek(b -> b.mouseSelected(KIJCore.mx, KIJCore.my)).filter(b -> Binds.keyClick(KIJCore.SETTINGS.keyAttack)).forEach(b -> b.mousePressed(KIJCore.mx, KIJCore.my, 0));

			// dropping from inventory
			if (!inslot && Binds.leftClick && guiis != null)
			{
				EntityItem ei = new EntityItem(KIJCore.p.pos, guiis);
				ei.vel = new Vec2(Utils.getIntInRange(-4, 4), Utils.getIntInRange(1, 4));
				ei.setTimer(30);
				room.addObj(ei);
				guiis = null;
			}
		}
		else
		{
			screenObjs = Utils.reverseList(screenObjs);
			Optional<Focusable> of = screenObjs.stream().filter(k -> Utils.isInLimit(new Vec2(KIJCore.mx, KIJCore.my), k.getFocusAABB(K, L)) && Binds.pressed(KIJCore.SETTINGS.keyAttack)).findFirst();
			screenObjs = Utils.reverseList(screenObjs);
			if (of.isPresent())
			{
				focus = of.get();
				cont = focus.getContainer();
				screenObjs.remove(focus);
				screenObjs.add(focus);
				focus.onFocusing();
			}
		}

		GL11.glPopMatrix();
	}

	private static void drawInterractionBBs(Focusable foc)
	{
		AABB2 ab = foc.getMoveAABB(GUI.K, GUI.L);

		if (ab != null)
		{
			Graph.colorize(Color.Red);
			Icon.sqr.bind();
			Graph.renderAABB(ab);
			Graph.clearColor();
		}

		Icon.sqr.bind();
		Graph.renderAABB(foc.getFocusAABB(GUI.K, GUI.L));

	}

	public static void update()
	{
		updateOpenGL();
		inslot = false;

		// fox.addLast(arg0);

		if (Binds.keyClick(KIJCore.SETTINGS.keyWave))
		{
			if (!InvUtils.contains(KIJCore.p.inv, Items.Laser))
			{
				KIJCore.p.inv.addItemStack(new ItemStack(Items.Laser));
				KIJCore.p.inv.addItemStack(new ItemStack(Items.lightning_gun));
				KIJCore.p.inv.addItemStack(new ItemStack(Items.flamethrw));
				KIJCore.p.inv.addItemStack(new ItemStack(Items.cannon));
				KIJCore.p.inv.addItemStack(new ItemStack(Items.GlassGun));
			}

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
			if (focus != null && cont != null)
			{
				screenObjs.remove(focus);
				removeFocus();
			}
			else
			{
				cont = new ContainerEquipment();
				focus = new GuiEquipment((ContainerBase) cont);
				screenObjs.add(focus);
			}
		}

		time++;
	}

	public static void removeFocus()
	{
		focus = null;
		cont = null;
	}

	private static void updateOpenGL()
	{
		Display.update();
		SCREEN_WIDTH = Display.getWidth();
		SCREEN_HEIGHT = Display.getHeight();
		Display.sync(FPS);
	}
}
