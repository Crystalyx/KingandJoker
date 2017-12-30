package Game.Items;

import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Game.Items.API.ItemBook;
import Graphics.Icon;

public class ItemFlyBook extends ItemBook
{

	public ItemFlyBook(String name, int price, Icon icon)
	{
		super(name, price, icon);
		this.addModifier(new Modifier(SharedModifiers.AbilityFly, "FLYBOO-K", 1, 1));
	}

}
