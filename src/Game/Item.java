package Game;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import Game.Entities.Player;
import Game.Entities.API.EntityLiving;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Icon;

public class Item
{
	public String name;
	public int uid = -1;
	public int price;
	public Icon icon;
	public int maxSize;

	public HashMap<String, Modifier> modifiers = new HashMap<String, Modifier>();

	public void addModifiers(List<Modifier> mods)
	{
		mods.stream().forEach(key -> this.modifiers.put(key.base.name, key));
	}

	public Item(String name, int price, Icon icon)
	{
		this.name = name;
		this.price = price;
		this.icon = icon;
		this.setupModifiers();
	}

	public void update(ItemStack is, EntityLiving p, boolean equip, boolean inhand)
	{
		this.updateModifiers(is, equip, inhand);
		Set<String> enm = this.modifiers.keySet();
		enm.stream().forEach(key ->
		{
			Modifier v = this.modifiers.get(key);
			if (((v.reqInHand && inhand) || !v.reqInHand) && ((v.reqEquip && equip) || !v.reqEquip))
				p.applyModifier(v);
		});
	}

	public void onItemUse(Player p)
	{

	}

	public void onItemAttack(Player p, EntityLiving e)
	{

	}

	public void setupModifiers()
	{
		this.modifiers.keySet().stream().forEach(key -> this.addModifier(this.modifiers.get(key)));
	}

	public void updateModifiers(ItemStack i, boolean equip, boolean inhand)
	{
		this.modifiers.keySet().stream().forEach(key -> this.addModifier(this.modifiers.get(key)));
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
		Modifier dmana = this.modifiers.get(SharedModifiers.Energy.name);
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
