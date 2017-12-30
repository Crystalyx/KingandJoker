package Game.Items.API;

import Game.Item;
import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Graphics.Icon;

public class ItemEquippable extends Item
{

	public ItemEquippable(String name, int price, Icon icon)
	{
		super(name, price, icon);
	}

	@Override
	public void setupModifiers()
	{
		this.modifiers.keySet().stream().forEach(key ->
		{
			Modifier mf = this.modifiers.get(key);
			mf.setReqEquip(true);
			mf.setReqClear(true);
			this.addModifier(mf);
		});

	}

	@Override
	public void updateModifiers(ItemStack i, boolean equip, boolean inhand)
	{
		this.modifiers.keySet().stream().forEach(key ->
		{
			Modifier mf = this.modifiers.get(key);
			mf.setReqEquip(true);
			mf.setReqClear(true);
			this.addModifier(mf);
		});
	}
}
