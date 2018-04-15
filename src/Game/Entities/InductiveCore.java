package Game.Entities;

import Core.Screen;
import Game.Entities.AI.AIRangedAttack;
import Game.Entities.AI.AIRangedFollow;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.IRangedAttacker;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Game.Entities.Throwable.InducedMithrillium;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.Utils;

public class InductiveCore extends EntityLiving implements IRangedAttacker
{

	public InductiveCore(Vec2 pos)
	{
		super(pos, 48, 48, Icon.getIcon("mobs/induction_Core"));
		this.life = this.maxlife = 10000;

		Modifier fly = new Modifier(SharedModifiers.AbilityFly, "ICORE-BASE", 1, 0);
		this.applyModifier(fly);
		this.addAI(new AIRangedAttack(Player.class, 800, 100));
		this.addAI(new AIRangedFollow(Player.class, 1000, 800, 1000, 2));
	}

	public Vec2[] IMP = new Vec2[3];
	public InducedMithrillium[] IM = new InducedMithrillium[3];

	@Override
	public int getRenderType()
	{
		return 3;
	}

	public void updateIMPoses(long time)
	{
		for (int i = 0; i < IMP.length; i++)
		{
			this.IMP[i] = this.pos.add(new Vec2(0, this.height / 2)).add(Utils.getPosByAngle(time / 90d + this.rotation * Math.PI / 180d + i * Math.PI * 2 / IMP.length, 70));
		}

	}

	@Override
	public void onSpawned()
	{
		super.onSpawned();
		updateIMPoses(0);

		for (int i = 0; i < IMP.length; i++)
		{
			IM[i] = new InducedMithrillium(this, i, new Damage().setEntityDamage(this, 20), IMP[i]);

			Screen.room.addObj(IM[i]);
		}
	}

	@Override
	public boolean onDeath(EntityLiving p)
	{
		for (int i = 0; i < IM.length; i++)
		{
			IM[i].setDead(true);
		}
		return super.onDeath(p);
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		updateIMPoses(time);
		this.rotation += Math.PI / 10;
	}

	@Override
	public boolean performRangedAttack(EntityLiving target)
	{
		this.IM[Utils.r.nextInt(this.IM.length)].Attack(target.pos);
		return true;
	}

}
