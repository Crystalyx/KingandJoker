package Game.Entities.AI;

import java.util.List;

import Game.Entities.EntityLiving;
import Game.Entities.IRangedAttacker;
import Graphics.GUI;
import Utilities.Vec2;

public class AIRangedAttack extends AI
{
	@SuppressWarnings("unchecked")
	@Override
	public void updateAI(EntityLiving e)
	{
		if (e instanceof IRangedAttacker)
		{
			if (this.currTarget == null)
			{
				Vec2 ps = e.pos;
				List<EntityLiving> l = GUI.croom.getEntitiesWithinSquare(this.target, ps.extendBoth(this.range));
				l.remove(e);
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
				if (ev.length() > this.range || this.currTarget.isDead)
				{
					this.currTarget = null;
				}
			}
			if (this.currTarget != null)
			{
				if ((e.lifeTime) % Math.max(this.slowness, 1) == 0)
				{
					((IRangedAttacker) e).performRangedAttack(this.currTarget);
				}
			}
		}
	}

	public AIRangedAttack(Class<? extends EntityLiving> e, double range, int slowness)
	{
		this.target = e;
		this.range = range;
		this.slowness = slowness;
	}

	public Class<? extends EntityLiving> target;
	public double range;
	public int slowness = 1;
	public EntityLiving currTarget;

}
