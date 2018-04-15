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

public class EntityArrow extends EntityThrowable
{
	public static double j = -0.25;
	public EntityArrow(Entity sender, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 32, 32, Icon.getIcon("bow/metal_arrow"));
//		this.jt = new Vec2(0, j);
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				p.damageBy(((EntityThrowable) obj).damage);
				p.vel = p.vel.add(obj.vel.div(6));
				obj.setDead(true);
			}
		};
		this.setMaxLifeTime(180);
		this.da = 45;
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
		Vec2 v = this.pos;
		List<EntityLiving> l = Screen.room.getEntitiesWithinSquare(EntityLiving.class, v.extend(new Vec2(this.width, this.height)));
		l.remove(this.sender);
		EntityLiving nrst = null;
		double min = 10000;
		for (EntityLiving el : l)
		{
			Vec2 elv = el.pos;
			if (min > v.sub(elv).length())
			{
				min = v.sub(elv).length();
				nrst = el;
			}
		}

		if (min <= 50)
		{
			this.near.act(this, nrst);
		}
	}

}
