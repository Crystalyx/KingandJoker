package Game.Entities;

import java.util.ArrayList;
import java.util.List;

import Game.Action;
import Game.Entities.AI.AI;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Vec2;

public class EntityLiving extends Entity
{
	public List<AI> ais = new ArrayList<AI>();

	public int level = 1;
	public double strength = 15;
	public double life = 150;
	public double maxlife = 150;
	public double mana = 60;
	public double maxmana = 60;
	public double extendedAttackRange = 0;

	public boolean isOnFire = false;
	public int fireTick = 0;
	public double fireHeat = 0;

	/**
	 * equals to amount of percents of fire damage that will be blocked
	 */
	public double fireResist = 0;
	/**
	 * equals to amount of percents of damage that will be blocked
	 */
	public double armor = 0;
	/**
	 * equals to amount of percents of internal damage that will be blocked
	 */
	public double internalResist = 0;
	/**
	 * equals to amount of percents of any damage that will be blocked
	 */
	public double voidResist = 0;

	protected EntityAttribute attribute = EntityAttribute.Living;

	protected void setAttribute(EntityAttribute ea)
	{
		this.attribute = ea;
	}

	public EntityLiving(Vec2 pos, double width, double height)
	{
		super(pos, width, height);
	}

	public EntityLiving(Vec2 pos, double width, double height, Sprite sprite)
	{
		super(pos, width, height, sprite);
	}

	public void addAI(AI ai)
	{
		ais.add(ai);
	}

	public boolean damageBy(Damage d)
	{
		double cdam = 0;
		cdam += d.entityDam * (100d - this.armor) / 100d;
		cdam += d.fireDam * (100d - this.fireResist) / 100d;
		cdam += d.internalDam * (100d - this.internalResist) / 100d;
		cdam += d.unblockDam;

		cdam *= (100d - this.voidResist) / 100d;

		this.life -= cdam;
		return true;
	}

	public Action near = new Action();

	@Override
	public void update(long time)
	{
		super.update(time);

		if (GUI.time % 4 == 0)
		{
			if (this.isOnFire && this.fireTick > 0 && this.fireHeat > 0)
			{
				this.damageBy(new Damage().setFireDamage(this.fireHeat));
			}
			else
			{
				this.isOnFire = false;
				this.fireHeat = 0;
				this.fireTick = 0;
			}
		}

		this.ableToFly = this.modifiers.countValue(SharedModifiers.AbilityFly) > 0;
		this.maxlife = this.modifiers.countValue(SharedModifiers.Life);
		this.maxmana = this.modifiers.countValue(SharedModifiers.Mana);
		this.strength = this.modifiers.countValue(SharedModifiers.Damage);
		this.armor = this.modifiers.countValue(SharedModifiers.Armor);
		this.internalResist = this.modifiers.countValue(SharedModifiers.InternalResist);
		this.fireResist = this.modifiers.countValue(SharedModifiers.FireResist);
		this.voidResist = this.modifiers.countValue(SharedModifiers.VoidResist);

		for (int i = 0; i < ais.size(); i++)
		{
			if (ais.get(i).enabled)
				ais.get(i).updateAI(this);
		}

		if (this.life <= 0)
		{
			this.setDead(true);
		}
	}

	public void attackEntityFrom(EntityLiving e)
	{
		e.damageBy(new Damage().setEntityDamage(this, this.strength));
	}

	public void setArmor(double armor)
	{
		this.armor = armor;
	}

	public void setFireResist(double fireResist)
	{
		this.fireResist = fireResist;
	}

	public void setInternalResist(double internalResist)
	{
		this.internalResist = internalResist;
	}

	public void setVoidResist(double voidResist)
	{
		this.voidResist = voidResist;
	}

	public void addArmor(double armor)
	{
		this.armor += armor;
	}

	public void addFireResist(double fireResist)
	{
		this.fireResist += fireResist;
	}

	public void addInternalResist(double internalResist)
	{
		this.internalResist += internalResist;
	}

	public void addVoidResist(double voidResist)
	{
		this.voidResist += voidResist;
	}
}
