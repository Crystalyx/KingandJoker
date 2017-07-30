package Game.Entities;

import java.util.List;

import Game.Action;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Logger;
import Utilities.Vec2;

public class Sparkle extends EntityThrowable
{
	public Sparkle(Entity sender, Damage damage,Vec2 pos)
	{
		super(sender, damage, pos, 16, 16, Sprite.getSprite("mobs/sparkle"));
		this.ableToFly = true;
		Modifier fly = new Modifier(SharedModifiers.AbilityFly, "SPARK-LERBAS-E", 1, 0);
		this.applyModifier(fly);
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				p.damageBy(((EntityThrowable) obj).damage);
				p.velocity = p.velocity.add(obj.velocity.divide(6));
				obj.setDead(true);
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
		Vec2 v = this.pos.sub(new Vec2(this.width / 2, 0));
		List<EntityLiving> l = GUI.croom.getLivingEntitiesWithinSquareExcluding(Sparkler.class, v.extend(new Vec2(this.width, this.height)));
		l.remove(this);
		l.remove(this.sender);
		EntityLiving nrst = null;
		double min = 10000;
		Logger.info(l);
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
