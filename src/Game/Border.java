package Game;

import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.AABB2;

public class Border
{

	public Border(Vec2 pos, double width, double height, Icon s)
	{
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.sprite = s;
	}

	public Vec2 pos = new Vec2(0, 90);
	public Icon sprite;

	public double height = 64;
	public double width = 64;

	public AABB2 getBB()
	{
		return this.pos.extendBoth(this.width / 2, this.height / 2);
	}

	public int getRenderType()
	{
		return 0;
	}

}
