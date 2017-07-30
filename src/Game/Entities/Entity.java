package Game.Entities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import Game.Action;
import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.ModifierList;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Color;
import Utilities.Tag;
import Utilities.Utils;
import Utilities.Vec2;

public class Entity
{
	public Vec2 pos = new Vec2(0, 90);
	public Vec2 velocity = new Vec2(0, 0);
	public Vec2 maxVelocity = new Vec2(16, 12);
	public Vec2 jt = new Vec2(0, -0.5), at = new Vec2(0.5, 0);
	public boolean g = true;
	public int maxLifeTime = -1;
	public int lifeTime = 0;
	public double rotation = 0;
	public ModifierList modifiers = new ModifierList();

	public void applyModifier(Modifier m)
	{
		this.modifiers.addModifier(m);
	}

	public void removeModifier(Modifier m)
	{
		for (Modifier mr : this.modifiers.list.get(m.base.name))
		{
			if (mr.uuid == m.uuid)
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

	public Sprite sprite;
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
			ei.velocity = new Vec2(Utils.getIntInRange(-8, 8), Utils.getIntInRange(1, 8));
			GUI.croom.addObj(ei);
		}
		return true;
	}

	public boolean interract(EntityLiving p)
	{
		this.velocity.add(p.velocity);
		return true;
	}

	public List<ItemStack> getDrops()
	{
		return new ArrayList<ItemStack>();
	}

	public Entity(Vec2 pos, double width, double height, Sprite s)
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
		this.velocity = vel;
	}

	public void setVelocity(double vx, double vy)
	{
		this.velocity = new Vec2(vx, vy);
	}

	public void addVelocity(Vec2 v)
	{
		this.velocity = this.velocity.add(v);
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
		this.g = this.pos.y <= 90;
		this.velocity = Utils.limit(this.velocity, new Vec2().extendBoth(this.maxVelocity));

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

	public Sprite getSprite()
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
}
