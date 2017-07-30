package Game.Entities;

import java.util.ArrayList;
import java.util.List;

import Game.ItemStack;

public class Damage
{
	public List<String> modif = new ArrayList<String>();
	public EntityLiving attacker;
	public double entityDam;
	public ItemStack item;
	public double internalDam;
	public boolean unblock;
	public double unblockDam;
	public boolean fire;
	public double fireDam;

	public Damage setEntityDamage(EntityLiving e, double dam)
	{
		this.attacker = e;
		this.entityDam = dam;
		return this;
	}

	public Damage setFireDamage(double dam)
	{
		this.fireDam = dam;
		return this;
	}

	public Damage setInternalDamage(ItemStack item, double dam)
	{
		this.item = item;
		this.internalDam = dam;
		return this;
	}

	public Damage setUnblockableDamage(double dam)
	{
		this.unblock = true;
		this.unblockDam = dam;
		return this;
	}
}
