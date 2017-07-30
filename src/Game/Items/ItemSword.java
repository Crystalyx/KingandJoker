package Game.Items;

import Game.ItemStack;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Graphics.Sprite;

public class ItemSword extends ItemTool
{
	public ItemSword(String name, int price, Sprite icon, ToolMaterial mat)
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
