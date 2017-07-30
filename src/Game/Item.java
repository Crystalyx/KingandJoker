package Game;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import Game.Entities.EntityLiving;
import Game.Entities.Player;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Sprite;

public class Item
{
	public String name;
	public int uid = -1;
	public int price;
	public Sprite icon;
	public int maxSize;

	public Hashtable<String, Modifier> modifiers = new Hashtable<String, Modifier>();

	public Item(String name, int price, Sprite icon)
	{
		this.name = name;
		this.price = price;
		this.icon = icon;
		this.setupModifiers();
	}

	public void update(ItemStack is, EntityLiving p, boolean equip, boolean inhand)
	{
		this.updateModifiers(is, equip, inhand);
		Enumeration<String> enm = this.modifiers.keys();
		while (enm.hasMoreElements())
		{
			Modifier v = (Modifier) modifiers.get(enm.nextElement());
			if (((v.reqInHand && inhand) || !v.reqInHand) && ((v.reqEquip && equip) || !v.reqEquip))
				p.applyModifier(v);
		}
	}

	public void onItemUse()
	{

	}

	public void onItemAttack(Player p, EntityLiving e)
	{

	}

	public void setupModifiers()
	{

	}

	public void updateModifiers(ItemStack i, boolean equip, boolean inhand)
	{

	}

	public void addModifier(Modifier m)
	{
		if (this.modifiers.containsKey(m.base.name))
		{
			this.modifiers.replace(m.base.name, this.modifiers.get(m.base.name), m);
		}
		this.modifiers.put(m.base.name, m);
	}

	public int getPrice(ItemStack is)
	{
		return is != null ? is.item.price : 0;
	}

	public void addInformation(ItemStack is, Player p, List<String> l)
	{
		l.add("");

		Modifier dlife = this.modifiers.get(SharedModifiers.Life.name);
		addLine(l, dlife);
		Modifier dmana = this.modifiers.get(SharedModifiers.Mana.name);
		addLine(l, dmana);
		Modifier ddamage = this.modifiers.get(SharedModifiers.Damage.name);
		addLine(l, ddamage);
		Modifier darmor = this.modifiers.get(SharedModifiers.Armor.name);
		addLine(l, darmor);
		Modifier dfire = this.modifiers.get(SharedModifiers.FireResist.name);
		addLine(l, dfire);
		Modifier dintern = this.modifiers.get(SharedModifiers.InternalResist.name);
		addLine(l, dintern);
		Modifier dvoid = this.modifiers.get(SharedModifiers.VoidResist.name);
		addLine(l, dvoid);
		Modifier dfly = this.modifiers.get(SharedModifiers.AbilityFly.name);
		if (dfly != null)
		{
			if (dfly.value >= 0)
			{
				l.add("ÞlGives you ability to fly");
			}
		}
		if (this.getPrice(is) > 0)
			l.add("ÞG" + this.getPrice(is) + " Coins");
	}

	public void addLine(List<String> l, Modifier dm)
	{
		if (dm != null)
		{
			if (dm.value > 0)
			{
				l.add("Þl" + (dm.operation != 0 ? ("+" + dm.value + (dm.operation == 2 ? "%" : "") + " to " + dm.base.affValue) : ("Set " + dm.base.affValue + " to " + dm.value)));
			}
		}
	}
}
