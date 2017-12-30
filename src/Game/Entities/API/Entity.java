package Game.Entities.API;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import Core.GUI;
import Game.Action;
import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.ModifierList;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.AABB2;
import Utilities.Color;
import Utilities.Tag;
import Utilities.Utils;

public class Entity
{
	public Vec2 pos = new Vec2(0, 90);
	public Vec2 vel = new Vec2(0, 0);
	public Vec2 maxVelocity = new Vec2(8, 6);
	// public Vec2 jt = new Vec2(0, -0.5), at = new Vec2(0.75, 0);
	// public boolean g = true;
	public int maxLifeTime = -1;
	public int lifeTime = 0;
	public double rotation = 0;
	public ModifierList modifiers = new ModifierList();

	public boolean celestial = false;

	public void applyModifier(Modifier m)
	{
		this.modifiers.addModifier(m);
	}

	public void removeModifier(Modifier m)
	{
		for (Modifier mr : this.modifiers.list.get(m.base.name))
		{
			if (mr.uuid.equals(m.uuid))
			{
				this.modifiers.list.get(m.base.name).remove(mr);
			}
		}
	}

	public void onSpawned()
	{

	}

	public int getRenderType()
	{
		return 0;
	}

	public boolean ableToFly = false;

	public EntityLiving lastAttacked = null;

	public Icon sprite;
	private Color c = new Color(255, 255, 255);

	public boolean isDead = false;

	public void setDead(boolean dead)
	{
		this.isDead = dead;
		if (dead)
		{
			if (!this.onDeath(this.lastAttacked))
			{
				this.setDead(false);
			}
		}
	}

	public Action near = new Action()
	{
		public void act(Entity obj, EntityLiving p)
		{

		}
	};

	public void onLeftClick(EntityLiving p)
	{

	}

	public boolean onDeath(EntityLiving p)
	{
		List<ItemStack> drop = this.getDrops();

		for (ItemStack i : drop)
		{
			EntityItem ei = new EntityItem(this.pos, i);
			ei.vel = new Vec2(Utils.getIntInRange(-8, 8), Utils.getIntInRange(1, 8));
			GUI.room.addObj(ei);
		}
		return true;
	}

	public boolean interract(EntityLiving p)
	{
		this.vel.add(p.vel);
		return true;
	}

	public List<ItemStack> getDrops()
	{
		return new ArrayList<ItemStack>();
	}

	public Entity(Vec2 pos, double width, double height, Icon s)
	{
		this.pos = pos;
		this.height = height;
		this.width = width;
		this.sprite = s;
	}

	public Entity(Vec2 pos, double width, double height)
	{
		this.pos = pos;
		this.height = height;
		this.width = width;
	}

	public void setPosition(Vec2 pos)
	{
		this.pos = pos;
	}

	public void setVelocity(Vec2 vel)
	{
		this.vel = vel;
	}

	public void setVelocity(double vx, double vy)
	{
		this.vel = new Vec2(vx, vy);
	}

	public void addVelocity(Vec2 v)
	{
		this.vel = this.vel.add(v);
	}

	public double height = 64;
	public double width = 64;

	public double getHeight()
	{
		return this.height;
	}

	public double getWidth()
	{
		return this.width;
	}

	public void update(long time)
	{
		// this.g = this.pos.y <= (90 + this.height / 2);
		this.vel = Utils.limit(this.vel, new Vec2().extendBoth(this.maxVelocity));

		if (this.lifeTime >= this.maxLifeTime && this.maxLifeTime != -1)
		{
			this.setDead(true);
		}
		else
		{
			this.lifeTime += 1;
		}
	}

	public void updateModifiers()
	{
		Enumeration<String> enm = this.modifiers.list.keys();
		while (enm.hasMoreElements())
		{
			String key = enm.nextElement();
			List<Modifier> mdfs = this.modifiers.list.get(key);
			if (!mdfs.isEmpty())
			{
				for (int i = 0; i < mdfs.size(); i++)
				{
					Modifier mr = mdfs.get(i);
					if (mr.reqClear)
					{
						mdfs.remove(mr);
					}
				}
			}
		}
	}

	public Icon getSprite()
	{
		return sprite;
	}

	public Color getColor()
	{
		return this.c;
	}

	public void setLifeTime(int lifeTime)
	{
		this.lifeTime = lifeTime;
	}

	public void setMaxLifeTime(int maxLifeTime)
	{
		this.maxLifeTime = maxLifeTime;
	}

	public void setAbleToFly(boolean ableToFly)
	{
		this.ableToFly = ableToFly;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void setMaxVelocity(Vec2 v)
	{
		this.maxVelocity = v;
	}

	public void write(Tag tag)
	{
		tag.add("modifiers", this.modifiers);
	}

	public void read(Tag tag)
	{
		this.modifiers = tag.get("modifiers");
	}

	public AABB2 getBB()
	{
		return this.pos.extendBoth(this.width / 2, this.height / 2);
	}

	public boolean isIn(AABB2 aabb)
	{
		return aabb.intersects(this.pos.extendBoth(0.01));
	}
}
