package Game.Entities;

import java.util.List;

import Game.Action;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Logger;
import Utilities.Vec2;

public class EntityArrow extends EntityThrowable
{

	public EntityArrow(Entity sender, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 16, 16, Sprite.getSprite("bow/metal_arrow"));
		this.jt = new Vec2(0, -0.25);
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				Logger.info(((EntityThrowable) obj).damage.entityDam);
				p.damageBy(((EntityThrowable) obj).damage);
				p.velocity = p.velocity.add(obj.velocity.divide(6));
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
		Vec2 v = this.pos.sub(new Vec2(this.width / 2, 0));
		List<EntityLiving> l = GUI.croom.getEntitiesWithinSquare(EntityLiving.class, v.extend(new Vec2(this.width, this.height)));
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
