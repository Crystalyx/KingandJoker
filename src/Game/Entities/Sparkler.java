package Game.Entities;

import java.util.List;

import Core.Screen;
import Game.ItemStack;
import Game.Entities.AI.AIRangedAttack;
import Game.Entities.AI.AIRangedFollow;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityAttribute;
import Game.Entities.API.EntityLiving;
import Game.Entities.API.IRangedAttacker;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Game.Entities.Throwable.Sparkle;
import Graphics.Icon;
import Math.Vec.Vec2;

public class Sparkler extends EntityLiving implements IRangedAttacker
{
	public Sparkler(Vec2 pos)
	{
		super(pos, 64, 64, Icon.getIcon("mobs/sparkler"));
		this.maxVelocity = new Vec2(8, 6);
		this.ableToFly = true;
		this.setAttribute(EntityAttribute.Energy);
		Modifier fly = new Modifier(SharedModifiers.AbilityFly, "SPARK-LERBAS-E", 1, 0);
		this.applyModifier(fly);
		this.addAI(new AIRangedAttack(Player.class, 400, 100));
		this.addAI(new AIRangedFollow(Player.class, 800, 400, 800, 1));
	}

	@Override
	public List<ItemStack> getDrops()
	{
		List<ItemStack> l = super.getDrops();
//		l.add(new ItemStack(GameRegistry.getItem(Utils.r.nextInt(GameRegistry.items.size()))));
		return l;
	}

	@Override
	public int getRenderType()
	{
		return 2;
	}

	@Override
	public boolean performRangedAttack(EntityLiving target)
	{
		Vec2 ev = target.pos.sub(this.pos);
		Sparkle b = new Sparkle(this, new Damage().setEntityDamage(this, 20), this.pos);
		b.tgp = target.pos;
		ev.normalize();
		b.setVelocity(ev.mult(8));
		Screen.room.addObj(b);
		return true;
	}

}
