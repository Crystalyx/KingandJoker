package Game.Entities;

import Game.Entities.AI.AIRangedAttack;
import Game.Entities.AI.AIRangedFollow;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Utils;
import Utilities.Vec2;

public class InductiveCore extends EntityLiving implements IRangedAttacker
{

	public InductiveCore(Vec2 pos)
	{
		super(pos, 48, 48, Sprite.getSprite("mobs/induction_Core"));
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

			GUI.croom.addObj(IM[i]);
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
		this.IM[Utils.r.nextInt(this.IM.length)].Attack(Vec2.getCenteredPos(target));
		return true;
	}

}
