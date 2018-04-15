package Game.Entities.Throwable;

import java.util.List;

import Core.Screen;
import Game.Action;
import Game.Entities.Sparkler;
import Game.Entities.API.Damage;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.EntityThrowable;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.AABB2;

public class Sparkle extends EntityThrowable
{
	public Sparkle(Entity sender, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 32, 32, Icon.getIcon("mobs/sparkle"));
		this.ableToFly = true;
		Modifier fly = new Modifier(SharedModifiers.AbilityFly, "SPARK-LERBAS-E", 1, 0);
		this.applyModifier(fly);
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				if (p != obj)
				{
					p.damageBy(((EntityThrowable) obj).damage);
					p.vel = p.vel.add(obj.vel.div(6));
					obj.setDead(true);
				}
			}
		};
		this.setMaxLifeTime(80);
	}

	@Override
	public int getRenderType()
	{
		return 3;
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		AABB2 v = this.pos.extendBoth(new Vec2(this.width / 2, this.height / 2));
		List<EntityLiving> l = Screen.room.getLivingEntitiesWithinSquareExcluding(Sparkler.class, v);
		l.remove(this.sender);
		EntityLiving nrst = null;
		double min = 10000;
		for (EntityLiving el : l)
		{
			Vec2 elv = el.pos;
			if (min > this.pos.sub(elv).length())
			{
				min = this.pos.sub(elv).length();
				nrst = el;
			}
		}

		if (min <= 50)
		{
			this.near.act(this, nrst);
		}

	}

}
