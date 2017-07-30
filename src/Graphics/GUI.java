package Graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Game.GMap;
import Game.ItemStack;
import Game.Room;
import Game.Entities.EntityItem;
import Game.Entities.InductiveCore;
import Game.Entities.Sparkler;
import Game.Inventory.ContainerEquipment;
import Game.Inventory.ContainerInventory;
import Game.Inventory.GuiEquipment;
import Game.Inventory.GuiInventory;
import Game.Inventory.InvUtils;
import Game.Inventory.Base.ContainerBase;
import Game.Inventory.Base.GuiBase;
import Game.Inventory.Base.ObjTypes;
import Registry.Binds;
import Registry.Items;
import Utilities.Tessellator;
import Utilities.Utils;
import Utilities.Vec2;

public class GUI
{
	public static int SCREEN_WIDTH = 800;
	public static int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH_BASE = 800;
	public static final int SCREEN_HEIGHT_BASE = 600;
	public static ItemStack guiis;
	public static boolean inslot = false;

	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 64;

	public static final String SCREEN_NAME = "King And Joker";

	public static GMap map;
	public static Room croom;
	public static MapCreator generator = new MapCreator();
	public static int dx = 0, dy = 0;
	public static int FPS = 60;
	public static Tessellator t = Tessellator.instance;
	public static GuiBase gui1;
	public static GuiBase gui2;
	public static ContainerBase cont1;
	public static ContainerBase cont2;

	public static long time = 0l;

	public static void init()
	{
		initializeOpenGL();
		croom = new Room();
		croom.addObj(Main.p);
	}

	private static void initializeOpenGL()
	{
		try
		{
			Display.setFullscreen(true);

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
		croom.draw();

		if (gui2 != null)
		{
			gui2.drawBack(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
			gui2.drawSlots(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		}

		if (gui1 != null)
		{
			gui1.drawBack(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
			gui1.drawSlots(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		}

		GL11.glPopMatrix();

		if (!inslot && Binds.leftClick)
		{
			if (guiis != null)
			{
				EntityItem ei = new EntityItem(Main.p.pos, guiis);
				ei.velocity = new Vec2(Utils.getIntInRange(-4, 4), Utils.getIntInRange(1, 4));
				ei.setTimer(30);
				croom.addObj(ei);
				guiis = null;
			}
		}
	}

	public static void update()
	{
		updateOpenGL();
		inslot = false;

		if (Binds.keyClick(Main.SETTINGS.keyWave))
		{
			if (!InvUtils.contains(Main.p.inv, Items.legendarySword))
			{
				Main.p.inv.addItemStack(new ItemStack(Items.legendarySword));
				Main.p.inv.addItemStack(new ItemStack(Items.flybook));
				Main.p.inv.addItemStack(new ItemStack(Items.invincRing));
				Main.p.inv.addItemStack(new ItemStack(Items.bow));

			}
			GUI.croom.addObj(new InductiveCore(new Vec2(180, 180)));
			GUI.croom.addObj(new Sparkler(new Vec2(SCREEN_WIDTH - 180, 180)));

		}

		if (Binds.keyClick(Main.SETTINGS.keyInventory))
		{
			if (cont1 == null)
			{
				cont1 = new ContainerInventory();
				gui1 = new GuiInventory(cont1);
			}
			else
			{
				cont1 = null;
				gui1 = null;
			}

		}

		if (Binds.keyClick(Main.SETTINGS.keyEquip))
		{

			if (cont2 == null)
			{
				cont2 = new ContainerEquipment();
				gui2 = new GuiEquipment(cont2);
			}
			else
			{
				cont2 = null;
				gui2 = null;
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
