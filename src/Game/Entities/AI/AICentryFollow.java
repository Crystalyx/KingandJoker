package Game.Entities.AI;

import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Math.Matrix.Matrix2;
import Math.Vec.Vec2;
import Utilities.Utils;

public class AICentryFollow extends AI
{

	public AICentryFollow(Vec2 centry, double range, double velocity)
	{
		this.centry = centry;
		this.range = range;
		this.velocity = velocity;
	}

	public AICentryFollow(Entity centry, Vec2 d, double range, double velocity)
	{
		this.targ = centry;
		this.dp = d;
		this.entity = true;
		this.range = range;
		this.velocity = velocity;
	}

	public void setCentry(Vec2 centry)
	{
		this.centry = centry;
	}

	public void setCentry(Entity centry, Vec2 d)
	{
		this.targ = centry;
		this.dp = d;
		this.entity = true;
	}

	public void setCentry(Entity centry)
	{
		this.targ = centry;
		this.entity = true;
	}

	public boolean entity = false;

	public Vec2 centry;

	public Vec2 dp;
	public Entity targ;

	public double range;
	public double velocity = 1;

	@Override
	public void updateAI(EntityLiving e)
	{
		Vec2 point = this.entity ? this.targ.pos.add(this.dp) : this.centry;

		Vec2 n = point.sub(e.pos);

		double g = n.length() - this.range;
		double c = (Math.signum(g));

		Vec2 tg = n.crossProduct(Matrix2.i).mult(Utils.boolToNum(Utils.isInLimit(point.x - e.pos.x, -1.1 * this.range, 1. * this.range), 1, Math.signum(point.x - e.pos.x))).mult(Utils.isInLimit(g, -10, 10) ? 1 : 0);
		tg.normalize();

		n.normalize();
		e.setVelocity(n.mult(c).add(tg).mult(2 * this.velocity));
	}

}
