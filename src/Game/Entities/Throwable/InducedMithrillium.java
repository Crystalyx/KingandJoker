package Game.Entities.Throwable;

import java.util.List;

import Game.Action;
import Game.Entities.InductiveCore;
import Game.Entities.API.Damage;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.EntityThrowable;
import Graphics.GUI;
import Graphics.Icon;
import Math.Vec.Vec2;

public class InducedMithrillium extends EntityThrowable
{
	public int id;
	public boolean trown = false;

	public InducedMithrillium(InductiveCore sender, int id, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 32, 32, Icon.getIcon("materials/mithrillium"));
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				p.damageBy(((EntityThrowable) obj).damage);
				p.vel = p.vel.add(obj.vel.div(6));
				((InducedMithrillium) obj).target = null;
			}
		};
		this.id = id;
	}

	public int attTime = 0;
	public int retTime = 0;
	public int maxAttTime = 80;
	public Vec2 target = null;

	@Override
	public void update(long time)
	{
		super.update(time);
		Vec2 v = this.pos.sub(new Vec2(this.width / 2, 0));
		List<EntityLiving> l = GUI.room.getLivingEntitiesWithinSquareExcluding(InductiveCore.class, v.extend(new Vec2(this.width, this.height)));
		l.remove(this);
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

		if (id != -1 && this.target == null && this.retTime == -1)
		{
			Vec2 vel = this.pos.sub(((InductiveCore) this.sender).IMP[this.id]);

			this.setVelocity(vel.mult(-0.5));
		}
		if (this.target != null)
		{
			Vec2 vel = this.pos.sub(this.target);
			vel.normalize();

			this.setVelocity(vel.mult(-20));
		}
		if (this.target != null)
		{
			if (this.attTime >= this.maxAttTime)
			{
				this.target = null;
				this.retTime = 0;
				this.attTime = 0;
			}
			else
			{
				this.attTime += 1;
			}
		}
		else
		{
			if (this.retTime != -1)
			{
				if (this.retTime >= this.maxAttTime)
				{
					this.retTime = -1;
				}
				else
				{
					this.retTime += 1;
					Vec2 vel = this.pos.sub(((InductiveCore) this.sender).IMP[this.id]);

					this.setVelocity(vel.mult(-1));
				}
			}
		}
		// this.setPosition(vel)

		this.rotation += Math.PI / 20;
	}

	public void Attack(Vec2 t)
	{
		this.target = t;
		this.tgp = t;
	}

	@Override
	public int getRenderType()
	{
		return 3;
	}

}
