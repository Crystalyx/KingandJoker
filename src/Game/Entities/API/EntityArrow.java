package Game.Entities.API;

import java.util.List;

import Game.Action;
import Graphics.GUI;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.Logger;

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
		List<EntityLiving> l = GUI.room.getEntitiesWithinSquare(EntityLiving.class, v.extend(new Vec2(this.width, this.height)));
		l.remove(this);
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
