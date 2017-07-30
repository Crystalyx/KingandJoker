package Game.Items;

import Game.Item;
import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Sprite;
import Utilities.Utils;

public class ItemFlyBook extends Item
{

	public ItemFlyBook(String name, int price, Sprite icon)
	{
		super(name, price, icon);
	}

	@Override
	public void setupModifiers()
	{
		Modifier mf = new Modifier(SharedModifiers.AbilityFly, "FLYBOO-K", 1, 1);
		mf.setReqEquip(true);
		mf.setReqClear(true);
		this.addModifier(mf);
	}

	@Override
	public void updateModifiers(ItemStack i, boolean equip, boolean inhand)
	{
		Modifier mf = new Modifier(SharedModifiers.AbilityFly, "FLYBOO-K", Utils.boolToNum(equip, 1, -1), 1);
		mf.setReqEquip(true);
		mf.setReqClear(true);
		this.addModifier(mf);
	}

}
