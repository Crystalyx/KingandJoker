package Game.Entities.AI;

import java.util.List;

import Core.Screen;
import Game.Entities.API.EntityLiving;
import Math.Vec.Vec2;

public class AIAttackOnCollide extends AI
{
	public AIAttackOnCollide(Class<? extends EntityLiving> e, double sRange, double aRange, double eRange, double velocity)
	{
		this.target = e;
		this.sRange = sRange;
		this.aRange = aRange;
		this.eRange = eRange;
		this.velocity = velocity;
	}

	public Class<? extends EntityLiving> target;
	public double sRange;
	public double aRange;
	public double eRange;
	public EntityLiving currTarget = null;
	public double velocity = 1;

	@Override
	public void updateAI(EntityLiving e)
	{
		if (this.currTarget == null)
		{
			Vec2 ps = e.pos;
			List<EntityLiving> l = Screen.room.getEntitiesWithinSquare(this.target, ps.extendBoth(this.sRange));
			double min = 10000;
			EntityLiving t = null;
			for (int i = 0; i < l.size(); i++)
			{
				EntityLiving v = l.get(i);
				Vec2 ev = v.pos.sub(e.pos);
				if (ev.length() < min)
				{
					min = ev.length();
					t = v;
				}
			}

			if (t != null)
			{
				this.currTarget = t;
			}
		}
		else
		{
			Vec2 ev = this.currTarget.pos.sub(e.pos);
			if (ev.length() > this.eRange || this.currTarget.isDead)
			{
				this.currTarget = null;
			}
			else
			{
				if (ev.length() <= this.aRange)
				{
					e.attackEntityFrom(this.currTarget);
				}
			}
		}
		// if (this.currTarget != null)
		// {
		// Vec2 ev = this.currTarget.pos.sub(e.pos);
		// ev.normalize();
		// e.vel = e.vel.add(ev.multiply(this.velocity / 10d, e.ableToFly ?
		// this.velocity / 10d : 0));
		// }
	}

}
