package Game.Items.API;

import java.util.List;

import Game.ItemStack;
import Game.Entities.Player;
import Game.Entities.API.EntityLiving;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Icon;

public class ItemRing extends ItemEquippable
{
	public ItemRing(String name, int price, Icon icon)
	{
		super(name, price, icon);
	}

	@Override
	public void update(ItemStack is, EntityLiving p, boolean equip, boolean inhand)
	{
		super.update(is, p, equip, inhand);
	}

	@Override
	public void addInformation(ItemStack is, Player p, List<String> l)
	{
		super.addInformation(is, p, l);
	}

	@Override
	public void setupModifiers()
	{
		if (this.name == "Armor Ring")
		{
			this.addModifier(new Modifier(SharedModifiers.Armor, "ARMOR-RING", 20, 2));
		}
		if (this.name == "Damage Ring")
		{
			this.addModifier(new Modifier(SharedModifiers.Damage, "DAMAG-ERING", 20, 2));
		}
		if (this.name == "Life Ring")
		{
			this.addModifier(new Modifier(SharedModifiers.Life, "LIFER-ING", 20, 2));
		}
		if (this.name == "Mana Ring")
		{
			this.addModifier(new Modifier(SharedModifiers.Energy, "MANAR-ING", 20, 2));
		}
	}

}
