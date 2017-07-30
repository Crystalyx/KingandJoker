package Utilities;

import Game.Entities.Entity;

public class AABB
{
	public double ox;
	public double oy;
	public double tx;
	public double ty;

	public AABB(double ox, double oy, double tx, double ty)
	{
		this.ox = ox;
		this.oy = oy;
		this.tx = tx;
		this.ty = ty;
		arrange();
	}

	public AABB(Entity e)
	{
		this.ox = e.pos.x - e.width / 2;
		this.oy = e.pos.y;
		this.tx = e.pos.x + e.width / 2;
		this.ty = e.pos.y + e.height;
		arrange();
	}

	public AABB(Vec2 o, Vec2 t)
	{
		this.ox = o.x;
		this.oy = o.y;
		this.tx = t.x;
		this.ty = t.y;
		arrange();
	}

	public void arrange()
	{
		if (ox > tx)
		{
			double dx = ox;
			ox = tx;
			tx = dx;
		}
		if (oy > ty)
		{
			double dy = oy;
			oy = ty;
			ty = dy;
		}
	}

	public boolean intersects(AABB aabb)
	{
		return (Utils.isInLimit(aabb.ox, this.ox, this.tx) && Utils.isInLimit(aabb.oy, this.oy, this.ty)) || (Utils.isInLimit(aabb.tx, this.ox, this.tx) && Utils.isInLimit(aabb.oy, this.oy, this.ty))
				|| (Utils.isInLimit(aabb.tx, this.ox, this.tx) && Utils.isInLimit(aabb.ty, this.oy, this.ty)) || (Utils.isInLimit(aabb.ty, this.oy, this.ty) && Utils.isInLimit(aabb.ox, this.ox, this.tx));
	}

	public Vec2 getOrigin()
	{
		return new Vec2(this.ox, this.oy);
	}

	public Vec2 getTarget()
	{
		return new Vec2(this.tx, this.ty);
	}

	public boolean contains(Entity e)
	{
		return Utils.isInLimit(e.pos.x, this.ox, this.tx) && Utils.isInLimit(e.pos.y + e.height / 2, this.oy, this.ty);
	}

	@Override
	public String toString()
	{
		return "[" + this.ox + ";" + this.oy + ";" + this.tx + ";" + this.ty + ";" + "]";
	}
}
