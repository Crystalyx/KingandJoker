package Game.Entities.Throwable;

import java.util.List;

import Core.Screen;
import Game.Action;
import Game.Entities.API.Damage;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.EntityThrowable;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.AABB2;

public class LaserPart extends EntityThrowable
{
	public LaserPart(Entity sender, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 12, 12, Icon.getIcon("bars"));// charged_coin
		this.celestial = true;
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
		this.setMaxLifeTime(1);
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
		List<EntityLiving> l = Screen.room.getEntitiesWithinSquare(EntityLiving.class, v);
		l.remove(this);
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
