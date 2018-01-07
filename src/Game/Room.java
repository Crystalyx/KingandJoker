package Game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Core.GUI;
import Core.KIJCore;
import Game.Entities.Player;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.EntityThrowable;
import Graphics.Icon;
import Math.Vec.Vec2;
import Registry.Binds;
import Registry.BorderRenderRegistry;
import Registry.EntityRegistry;
import Registry.RenderRegistry;
import Utilities.AABB2;
import Utilities.Graph;
import Utilities.Logger;
import Utilities.Tessellator;

public class Room
{
	public List<Entity> objs = new ArrayList<Entity>();
	public List<Border> bords = new ArrayList<Border>();
	public Border[][] gunbords = new Border[1 + GUI.SCREEN_WIDTH / 50][1 + GUI.SCREEN_HEIGHT / 50];

	public int width = GUI.SCREEN_WIDTH;
	public int height = GUI.SCREEN_HEIGHT - 150;
	public int slowness = 100;

	public Room()
	{
		height = GUI.SCREEN_HEIGHT - 150;
	}

	public Room(int width, int height, int slowness)
	{
		this.height = height;
		this.width = width;
		this.slowness = slowness;
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

	public void addBorder(Border o)
	{
		this.bords.add(o);
	}

	public void leverBorder(int x, int y)
	{
		if (gunbords[x][y] != null)
		{
			this.bords.remove(gunbords[x][y]);
			gunbords[x][y] = null;
		}
		else
		{
			Border b = new Border(new Vec2(Math.floorDiv(x * 50 + 25, 50) * 50, Math.floorDiv(y * 50 + 25, 50) * 50), 50, 50, Icon.getIcon("border/metal"));
			GUI.room.addBorder(b);
			gunbords[x][y] = b;
		}
	}

	public void removeBorder(Border o)
	{
		this.bords.remove(o);
	}

	public void draw()
	{
		Tessellator t = GUI.t;

		// Icon.sky.getTexture().bind();

		Icon.getIcon("floor").bind();
		int h = height;
		int w = width;

		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(0, h, 0, 1);
		t.addVertexWithUV(w, h, 1, 1);
		t.addVertexWithUV(w, 0, 1, 0);
		t.addVertexWithUV(0, 0, 0, 0);
		t.draw();

		for (Entity obj : objs)
		{
			RenderRegistry.getRender(obj.getRenderType()).render(obj);

			if (Binds.pressed(KIJCore.SETTINGS.keyDebug))
			{
				this.drawDebug(obj);
			}
		}
		for (Border bord : bords)
		{
			BorderRenderRegistry.getRender(bord.getRenderType()).render(bord);
		}

		Icon.getIcon("UI/toolbox").bind();
		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(0, h, 0, 1);
		t.addVertexWithUV(w, h, 1, 1);
		t.addVertexWithUV(w, h + 150, 1, 0);
		t.addVertexWithUV(0, h + 150, 0, 0);
		t.draw();

		int barWidth = 530;
		int barHeight = 50;

		double barU = 1;
		double barV = 0.5;

		double Uborder = 5d / 1024d;
		double border = barWidth * 5d / 1024d;

		double l = KIJCore.p.life > 0 ? KIJCore.p.life : 5 * 5d / 256d + border / 2;
		double e = KIJCore.p.energy > 0 ? KIJCore.p.energy : 3 * 5d / 256d + border / 2;

		double health = barWidth * (l / (double) KIJCore.p.maxlife);
		double energy = barWidth * (e / (double) KIJCore.p.maxenergy);

		AABB2 uvhollow = new Vec2().extend(barU, barV);
		AABB2 uvfull = new Vec2(Uborder, barV).extend(barU - 2 * Uborder, barV);

		AABB2 healthbb = new Vec2(60, h + 90).extend(barWidth, barHeight);
		AABB2 chealthbb = new Vec2(60 + border, h + 90).extend(health - border * 2, barHeight);

		AABB2 energybb = new Vec2(60, h + 70 - barHeight).extend(barWidth, barHeight);
		AABB2 cenergybb = new Vec2(60 + border, h + 70 - barHeight).extend(energy - border * 2, barHeight);

		Icon.getIcon("UI/healthbar").bind();
		Graph.renderSqrWithUV(healthbb, uvhollow);
		Graph.renderSqrWithUV(chealthbb, uvfull);

		Icon.getIcon("UI/energybar").bind();
		Graph.renderSqrWithUV(energybb, uvhollow);
		Graph.renderSqrWithUV(cenergybb, uvfull);
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
					for (int i = 0; i < slowness; i++)
					{
						moveObj(obj, slowness);
					}
				}
				obj.updateModifiers();
			}
		}
	}

	public void moveObj(Entity obj, int slowness)
	{
		if (obj instanceof Player)
			obj.vel = obj.vel.mult(0.3, 0.3);

		Vec2 v = obj.vel.div(slowness);

		Vec2 vx = new Vec2(v.x, 0);
		Vec2 vy = new Vec2(0, v.y);

		obj.pos = obj.pos.add(vx);

		if (!obj.celestial)
		{
			boolean border = this.bords.stream().anyMatch(k -> obj.getBB().intersects(k.getBB()) || k.getBB().intersects(obj.getBB()));

			if (border)
			{
				obj.pos = obj.pos.sub(vx);
				obj.vel.x = 0;
			}
		}
		obj.pos = obj.pos.add(vy);
		if (!obj.celestial)
		{
			boolean border = this.bords.stream().anyMatch(k -> obj.getBB().intersects(k.getBB()) || k.getBB().intersects(obj.getBB()));

			if (border)
			{
				obj.pos = obj.pos.sub(vy);
				obj.vel.y = 0;

			}
		}

		if ((obj.pos.x - 2 - obj.width / 2 < 0 && v.x < 0))
		{
			// obj.pos.x = width - 1;
			obj.pos.x -= v.x;
			obj.vel.x = 0;
		}
		if ((obj.pos.x + 2 + obj.width / 2 > this.width && v.x > 0))
		{
			obj.pos.x -= v.x;
			obj.vel.x = 0;
		}
		if ((obj.pos.y - 2 - obj.height / 2 < 0 && v.y < 0))
		{
			obj.pos.y -= v.y;
			obj.vel.y = 0;
		}
		if ((obj.pos.y + 2 + obj.height / 2 > this.height && v.y > 0))
		{
			obj.pos.y -= v.y;
			obj.vel.y = 0;
		}
	}

	public List getEntitiesWithinSquare(Class e, AABB2 aabb)
	{
		List<Entity> l = new ArrayList<Entity>();
		objs.stream().filter(k -> k.isIn(aabb)).filter(k -> (k instanceof Entity)).forEach(k ->
		{
			if (e.isInstance(k))
			{
				l.add(k);
			}
		});
		return l;
	}

	public List getEntitiesWithinSquareExcluding(Class e, AABB2 aabb)
	{
		List<Entity> l = new ArrayList<Entity>();
		for (int j = 0; j < objs.size(); j++)
		{
			if (objs.get(j).isIn(aabb))
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

	public List getLivingEntitiesWithinSquareExcluding(Class<? extends Entity> e, AABB2 aabb)
	{
		List<EntityLiving> l = new ArrayList<EntityLiving>();
		for (int j = 0; j < objs.size(); j++)
		{
			if (objs.get(j).isIn(aabb))
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
		Icon.sqr.getTexture().bind();
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

		Vec2 vaabb = obj.pos.sub(new Vec2(obj.width / 2, obj.height / 2));
		AABB2 ab = vaabb.extend(new Vec2(obj.width, obj.height));

		Graph.renderAABB(ab);

		Vec2 vp = KIJCore.p.pos.add(new Vec2(0, 0));

		AABB2 range = vp.extendBoth(new Vec2(KIJCore.p.width, KIJCore.p.height));
		Graph.renderAABB(range);

		GL11.glColor3d(0, 1d, 0);

		if (obj instanceof EntityThrowable)
		{
			Graph.renderCircle(((EntityThrowable) obj).tgp, 10);
		}

		GL11.glColor3d(1, 1, 1);
	}

	public void setSlowness(int slowness)
	{
		this.slowness = slowness;
	}
}
