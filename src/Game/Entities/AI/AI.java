package Game.Entities.AI;

import Game.Entities.API.EntityLiving;

public abstract class AI
{
	public boolean enabled = true;

	public abstract void updateAI(EntityLiving e);
}
