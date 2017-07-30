package Game.Entities;

import java.util.List;

import Game.Action;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Vec2;

public class InducedMithrillium extends EntityThrowable
{
	public int id;
	public boolean trown = false;

	public InducedMithrillium(InductiveCore sender, int id, Damage damage, Vec2 pos)
	{
		super(sender, damage, pos, 16, 16, Sprite.getSprite("materials/mithrillium"));
		this.near = new Action()
		{
			@Override
			public void act(Entity obj, EntityLiving p)
			{
				p.damageBy(((EntityThrowable) obj).damage);
				p.velocity = p.velocity.add(obj.velocity.divide(6));
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
		List<EntityLiving> l = GUI.croom.getLivingEntitiesWithinSquareExcluding(InductiveCore.class, v.extend(new Vec2(this.width, this.height)));
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

		if (id != -1 && this.target == null && this.retTime == -1)
		{
			Vec2 vel = this.pos.sub(((InductiveCore) this.sender).IMP[this.id]);

			this.setVelocity(vel.modif(-0.5));
		}
		if (this.target != null)
		{
			Vec2 vel = this.pos.sub(this.target);
			vel.normalize();

			this.setVelocity(vel.modif(-20));
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

					this.setVelocity(vel.modif(-1));
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
