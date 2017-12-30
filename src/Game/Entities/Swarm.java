package Game.Entities;

import Game.Entities.AI.AIAttackOnCollide;
import Game.Entities.AI.AICentryFollow;
import Game.Entities.API.EntityLiving;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.Logger;

public class Swarm extends EntityLiving
{

	public Swarm(Vec2 pos)
	{
		super(pos, 16, 16, Icon.getIcon("mobs/swarm"));
		Modifier fly = new Modifier(SharedModifiers.AbilityFly, "SWARM-BASE", 1, 0);
		this.applyModifier(fly);
		this.life = this.maxlife = 30;
		this.addAI(new AIAttackOnCollide(Player.class, 1000, 20, 1000, 3));
		this.addAI(new AICentryFollow(pos, 50, 1));
	}

	@Override
	public boolean onDeath(EntityLiving p)
	{
		Logger.info("Dead");
		return super.onDeath(p);
	}

	@Override
	public void attackEntityFrom(EntityLiving e)
	{
		this.strength = 1;

		super.attackEntityFrom(e);
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		// Logger.info(this.life);
		// this.life = this.maxlife;
		this.rotation += Math.PI / 6;
	}
}
