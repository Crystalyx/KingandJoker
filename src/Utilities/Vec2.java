package Utilities;

import Game.Entities.Entity;

public class Vec2
{
	public double x;
	public double y;

	public Vec2()
	{
		this.x = 0;
		this.y = 0;
	}

	public Vec2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public static Vec2 getCenteredPos(Entity o)
	{
		return o.pos.add(new Vec2(0, o.height / 2));
	}

	public double length()
	{
		return Math.hypot(this.x, this.y);
	}

	public Vec2 add(Vec2 v)
	{
		return new Vec2(this.x + v.x, this.y + v.y);
	}

	public Vec2 sub(Vec2 v)
	{
		return new Vec2(this.x - v.x, this.y - v.y);
	}

	public Vec2 modif(double l)
	{
		return new Vec2(this.x * l, this.y * l);
	}

	public Vec2 modif(double lx, double ly)
	{
		return new Vec2(this.x * lx, this.y * ly);
	}

	public Vec2 divide(double l)
	{
		return new Vec2(this.x / l, this.y / l);
	}

	public Vec2 divide(double lx, double ly)
	{
		return new Vec2(this.x / lx, this.y / ly);
	}

	public void normalize()
	{
		double l = this.length();
		if (l == 0)
		{
			this.x = 0;
			this.y = 0;
			return;
		}
		this.x /= l;
		this.y /= l;
	}

	public Vec2 dotProduct(Vec2 v)
	{
		return new Vec2(this.x * v.x, this.y * v.y);
	}

	public AABB extend(double l)
	{
		return new AABB(this, this.add(new Vec2(l, l)));
	}

	public AABB extend(Vec2 v)
	{
		return new AABB(this, this.add(v));
	}

	public AABB extendBoth(double l)
	{
		Vec2 el = new Vec2(l, l);
		return new AABB(this.sub(el), this.add(el));
	}

	public AABB extendBoth(Vec2 v)
	{
		return new AABB(this.sub(v), this.add(v));
	}

	@Override
	public String toString()
	{
		return "[" + this.x + "; " + this.y + "]";
	}

	public Vec2 copy()
	{
		return new Vec2(this.x, this.y);
	}
}
