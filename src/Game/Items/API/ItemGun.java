package Game.Items.API;

import Game.Item;
import Game.Entities.Player;
import Game.Entities.API.EntityLiving;
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
