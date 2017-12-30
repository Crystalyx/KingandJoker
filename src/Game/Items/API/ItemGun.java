package Game.Items.API;

import Game.Item;
import Graphics.Icon;

public class ItemGun extends Item
{

	public ItemGun(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon);
		this.gun = icon;
	}

	public Icon gun;
}
