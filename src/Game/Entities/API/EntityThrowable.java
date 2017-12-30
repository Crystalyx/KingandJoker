package Game.Entities.API;

import Graphics.Icon;
import Math.Vec.Vec2;

public class EntityThrowable extends Entity
{
	public Entity sender;
	public Damage damage;
	public double da = 0;
	public Vec2 tgp = new Vec2();

	public EntityThrowable(Entity sender, Damage damage, Vec2 pos, double width, double height)
	{
		super(pos, width, height);
		this.sender = sender;
		this.damage = damage;
	}

	public EntityThrowable(Entity sender, Damage damage, Vec2 pos, double width, double height, Icon s)
	{
		super(pos, width, height, s);
		this.sender = sender;
		this.damage = damage;
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		this.rotation = Math.atan2(this.vel.y, this.vel.x) * 180 / Math.PI - da;
	}
}
