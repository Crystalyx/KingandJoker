package API;

import Core.KIJCore;
import Utilities.AABB2;

public interface IMovable
{
	public default void input()
	{
		this.moveBy(KIJCore.mvx, KIJCore.mvy);
	}

	public boolean canMove();

	public AABB2 getMoveAABB(int k, int l);

	public void moveBy(double x, double y);
}
