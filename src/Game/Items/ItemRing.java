package Game.Items;

import java.util.List;

import Game.Item;
import Game.ItemStack;
import Game.Entities.EntityLiving;
import Game.Entities.Player;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Sprite;

public class ItemRing extends Item
{
	public ItemRing(String name, int price, Sprite icon)
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
			this.addModifier(new Modifier(SharedModifiers.Mana, "MANAR-ING", 20, 2));
		}
	}

}
