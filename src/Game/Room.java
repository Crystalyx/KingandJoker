package Game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Game.Entities.Entity;
import Game.Entities.EntityLiving;
import Game.Entities.EntityThrowable;
import Game.Entities.Player;
import Graphics.GUI;
import Graphics.Main;
import Graphics.RenderUtils;
import Graphics.Sprite;
import Registry.Binds;
import Registry.EntityRegistry;
import Registry.RenderRegistry;
import Utilities.AABB;
import Utilities.Logger;
import Utilities.Tessellator;
import Utilities.Utils;
import Utilities.Vec2;

public class Room
{
	public List<Entity> objs = new ArrayList<Entity>();

	public int height = 500;
	public int width = GUI.SCREEN_WIDTH;

	public Room()
	{
	}

	public Room(int width, int height)
	{
		this.height = height;
		this.width = width;
	}

	public void addObj(Entity o)
	{
		this.objs.add(o);
		o.onSpawned();
	}

	public void removeObj(Entity o)
	{
		this.objs.remove(o);
	}

	public Sprite top = Sprite.grass;
	public Sprite under = Sprite.dirt;
	public Sprite back = Sprite.sky;

	public void draw()
	{
		Tessellator t = GUI.t;

		Sprite.sky.getTexture().bind();
		int h = height;
		int w = width;

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(0, 0, 0, 1);
		t.addVertexWithUV(w, 0, 1, 1);
		t.addVertexWithUV(w, h * 2, 1, 0);
		t.addVertexWithUV(0, h * 2, 0, 0);
		t.draw();

		int s = 32;
		int n = w / s + 1;
		int gh = 58;

		top.getTexture().bind();
		for (int i = 0; i < n; i++)
		{
			t.start(GL11.GL_QUADS);

			t.addVertexWithUV(0 + s * i, 0 + gh, 0, 1);
			t.addVertexWithUV(s + s * i, 0 + gh, 1, 1);
			t.addVertexWithUV(s + s * i, s + gh, 1, 0);
			t.addVertexWithUV(0 + s * i, s + gh, 0, 0);

			t.draw();
		}
		int m = h / s + 1;
		under.getTexture().bind();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				t.start(GL11.GL_QUADS);

				t.addVertexWithUV(0 + s * i, 0 + gh - s - j * s, 0, 1);
				t.addVertexWithUV(s + s * i, 0 + gh - s - j * s, 1, 1);
				t.addVertexWithUV(s + s * i, s + gh - s - j * s, 1, 0);
				t.addVertexWithUV(0 + s * i, s + gh - s - j * s, 0, 0);

				t.draw();
			}
		}

		for (Entity obj : objs)
		{
			RenderRegistry.getRender(obj.getRenderType()).render(obj);

			if (obj instanceof EntityLiving)
			{
				drawHealthBar(obj);
				drawManaBar(obj);
			}

			if (Binds.pressed(Main.SETTINGS.keyDebug))
			{
				this.drawDebug(obj);
			}
		}
	}

	public void updateRoom()
	{
		for (int j = 0; j < objs.size(); j++)
		{
			Entity obj = objs.get(j);
			if (!EntityRegistry.entities.containsValue(obj.getClass()))
			{
				Logger.warn("Entity class " + obj.getClass() + " hasn't been registered.");
				Logger.warn("Entity will be removed");
				obj.setDead(true);
			}
			if (obj.isDead)
			{
				objs.remove(obj);
				obj = null;
			}
			else
			{
				obj.update(GUI.time);
				if (EntityRegistry.movingEntities.contains(obj.getClass()))
				{
					moveObj(obj);
				}
				obj.updateModifiers();
			}
		}
	}

	public static void moveObj(Entity obj)
	{
		if (!obj.g && !obj.ableToFly)
		{
			obj.velocity = obj.velocity.add(obj.jt);
		}

		if (!obj.g && obj.ableToFly)
		{
			if (obj instanceof Player)
			{
				double lx = 1;
				double ly = 1;
				if (Binds.unpressed(Main.SETTINGS.keyJump) && Binds.unpressed(Main.SETTINGS.keyStrafe))
				{
					ly = 0.5;
				}
				if (Binds.unpressed(Main.SETTINGS.keyLeft) && Binds.unpressed(Main.SETTINGS.keyRight))
				{
					lx = 0.3;
				}
				obj.velocity = obj.velocity.modif(lx, ly);

			}
		}

		if (obj.g)
		{
			obj.pos.y = 90;
			if (obj.velocity.y < 0)
				obj.velocity.y = 0;
		}

		if ((obj instanceof Player) && Binds.unpressed(Main.SETTINGS.keyLeft) && Binds.unpressed(Main.SETTINGS.keyRight) && !obj.ableToFly)
		{
			obj.velocity = obj.velocity.modif(0.3 + Utils.boolToNum(!obj.g, 0.7, 0), 1);
		}

		obj.pos = obj.pos.add(obj.velocity);

		if ((obj.pos.x - obj.width / 2 < 0 && obj.velocity.x < 0) || (obj.pos.x + obj.width / 2 > GUI.croom.width && obj.velocity.x > 0))
		{
			obj.pos.x -= obj.velocity.x;
			obj.velocity.x = 0;
		}
	}

	public List getEntitiesWithinSquare(Class<? extends Entity> e, AABB aabb)
	{
		List<Entity> l = new ArrayList<Entity>();
		for (int j = 0; j < objs.size(); j++)
		{
			if (aabb.contains(objs.get(j)))
			{
				if (objs.get(j) instanceof Entity)
				{
					Entity obj = (Entity) objs.get(j);
					if (e.isInstance(obj))
					{
						l.add(obj);
					}
				}
			}
		}
		return l;
	}

	public List getEntitiesWithinSquareExcluding(Class<? extends Entity> e, AABB aabb)
	{
		List<Entity> l = new ArrayList<Entity>();
		for (int j = 0; j < objs.size(); j++)
		{
			if (aabb.contains(objs.get(j)))
			{
				if (objs.get(j) instanceof Entity)
				{
					Entity obj = (Entity) objs.get(j);
					if (!e.isInstance(obj))
					{
						l.add(obj);
					}
				}
			}
		}
		return l;
	}

	public List getLivingEntitiesWithinSquareExcluding(Class<? extends Entity> e, AABB aabb)
	{
		List<EntityLiving> l = new ArrayList<EntityLiving>();
		for (int j = 0; j < objs.size(); j++)
		{
			if (aabb.contains(objs.get(j)))
			{
				if (objs.get(j) instanceof EntityLiving)
				{
					EntityLiving obj = (EntityLiving) objs.get(j);
					if (!e.isInstance(obj))
					{
						l.add(obj);
					}
				}
			}
		}
		return l;
	}

	public void drawDebug(Entity obj)
	{
		Tessellator t = Tessellator.instance;
		Sprite.sqr.getTexture().bind();
		GL11.glColor3d(1, 0, 0);
		Vec2 v = obj.pos;

		t.start(GL11.GL_QUAD_STRIP);
		t.addVertexWithUV(v.x - 1, 0, 0, 0);
		t.addVertexWithUV(v.x + 1, 0, 1, 0);
		t.addVertexWithUV(v.x + 1, GUI.SCREEN_HEIGHT, 1, 1);
		t.addVertexWithUV(v.x - 1, GUI.SCREEN_HEIGHT, 0, 1);
		t.draw();

		int mx = (Mouse.getX());
		int my = (Mouse.getY());

		t.start(GL11.GL_QUAD_STRIP);
		t.addVertexWithUV(mx - 1, my - 1, 0, 0);
		t.addVertexWithUV(mx + 1, my - 1, 1, 0);
		t.addVertexWithUV(mx + 1, my + 1, 1, 1);
		t.addVertexWithUV(mx - 1, my + 1, 0, 1);
		t.draw();

		t.start(GL11.GL_QUAD_STRIP);
		t.addVertexWithUV(0, v.y - 1 + obj.height / 2, 0, 0);
		t.addVertexWithUV(1, v.y + 1 + obj.height / 2, 0, 0);
		t.addVertexWithUV(GUI.SCREEN_WIDTH, v.y + 1 + obj.height / 2, 1, 1);
		t.addVertexWithUV(GUI.SCREEN_WIDTH, v.y - 1 + obj.height / 2, 0, 1);
		t.draw();

		Vec2 vaabb = obj.pos.sub(new Vec2(obj.width / 2, 0));
		AABB ab = vaabb.extend(new Vec2(obj.width, obj.height));

		RenderUtils.renderAABB(ab);

		GL11.glColor3d(0, 1d, 0);

		if (obj instanceof EntityThrowable)
		{
			RenderUtils.renderCircle(((EntityThrowable) obj).tgp, 10);
		}

		GL11.glColor3d(1, 1, 1);
	}

	public void drawHealthBar(Entity obj)
	{
		Tessellator t = Tessellator.instance;
		Sprite.getSprite("health_bar").getTexture().bind();

		double part = ((EntityLiving) obj).life / ((EntityLiving) obj).maxlife;
		double length = part * obj.width;
		double pot = obj.width * 0.1;
		t.start(GL11.GL_QUADS);

		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height, 0, 0);
		t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y + obj.height, 1, 0);
		t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y + obj.height + pot, 1, 0.5);
		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot, 0, 0.5);

		t.draw();
		t.start(GL11.GL_QUADS);

		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height, 0, 1);
		t.addVertexWithUV(obj.pos.x - obj.width / 2 + length, obj.pos.y + obj.height, part, 1);
		t.addVertexWithUV(obj.pos.x - obj.width / 2 + length, obj.pos.y + obj.height + pot, part, 0.5);
		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot, 0, 0.5);

		t.draw();
	}

	public void drawManaBar(Entity obj)
	{
		double part = ((EntityLiving) obj).mana / ((EntityLiving) obj).maxmana;
		double length = part * obj.width;
		double pot = obj.width * 0.1;

		Tessellator t = Tessellator.instance;
		Sprite.getSprite("mana_bar").getTexture().bind();

		t.start(GL11.GL_QUADS);

		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot, 0, 0);
		t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y + obj.height + pot, 1, 0);
		t.addVertexWithUV(obj.pos.x + obj.width / 2, obj.pos.y + obj.height + pot * 2, 1, 0.5);
		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot * 2, 0, 0.5);

		t.draw();
		t.start(GL11.GL_QUADS);

		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot, 0, 1);
		t.addVertexWithUV(obj.pos.x - obj.width / 2 + length, obj.pos.y + obj.height + pot, part, 1);
		t.addVertexWithUV(obj.pos.x - obj.width / 2 + length, obj.pos.y + obj.height + pot * 2, part, 0.5);
		t.addVertexWithUV(obj.pos.x - obj.width / 2, obj.pos.y + obj.height + pot * 2, 0, 0.5);

		t.draw();
	}
}
