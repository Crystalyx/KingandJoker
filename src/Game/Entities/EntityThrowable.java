package Game.Entities;

import Graphics.Sprite;
import Utilities.Vec2;

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

	public EntityThrowable(Entity sender, Damage damage, Vec2 pos, double width, double height, Sprite s)
	{
		super(pos, width, height, s);
		this.sender = sender;
		this.damage = damage;
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		if (this.pos.y > 90)
		{
			this.rotation = Math.atan2(this.velocity.y, this.velocity.x) * 180 / Math.PI - da;
		}
		else
		{
			this.setVelocity(new Vec2());
		}
	}
}
