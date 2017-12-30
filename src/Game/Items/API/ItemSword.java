package Game.Items.API;

import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Icon;

public class ItemSword extends ItemTool
{
	public ItemSword(String name, int price, Icon icon, ToolMaterial mat)
	{
		super(name, price, icon, mat);
	}

	@Override
	public void updateModifiers(ItemStack i, boolean equip, boolean inhand)
	{
		if (i != null)
		{
			if (i.item instanceof ItemTool)
			{
				ToolMaterial mat = ((ItemTool) i.item).mat;
				Modifier damag = new Modifier(SharedModifiers.Damage, "SWORD", mat.damage, 1);
				damag.setReqInHand(true);
				damag.setReqClear(true);
				this.addModifier(damag);
			}
		}
	}
}
