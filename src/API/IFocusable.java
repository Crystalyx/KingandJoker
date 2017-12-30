package API;

import Utilities.AABB2;

public interface IFocusable
{
	public void input();

	public boolean canFocus();
	
	public AABB2 getFocusAABB(int k, int l);
}
