package Game.Entities.AI;

import java.util.List;

import Game.Entities.API.EntityLiving;
import Graphics.GUI;
import Math.Vec.Vec2;

public class AIRangedFollow extends AI
{
	public AIRangedFollow(Class<? extends EntityLiving> e, double sRange, double mRange, double eRange, double velocity)
	{
		this.target = e;
		this.sRange = sRange;
		this.mRange = mRange;
		this.eRange = eRange;
		this.velocity = velocity;
	}

	public Class<? extends EntityLiving> target;
	public double sRange;
	public double mRange;
	public double eRange;
	public EntityLiving currTarget;
	public double velocity = 1;

	@SuppressWarnings("unchecked")
	@Override
	public void updateAI(EntityLiving e)
	{
		if (this.currTarget == null)
		{
			Vec2 ps = e.pos;
			List<EntityLiving> l = GUI.room.getEntitiesWithinSquare(this.target, ps.extendBoth(this.sRange));
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
			else
			{
				e.setVelocity(e.vel.mult(0.95));
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
				if (ev.length() > this.mRange)
				{
					ev.normalize();
					e.vel = e.vel.add(ev.mult(this.velocity / 10d, e.ableToFly ? this.velocity / 10d : 0));
				}
				else
				{
					e.setVelocity(e.vel.mult(0.95));
				}
			}
		}
	}
}
