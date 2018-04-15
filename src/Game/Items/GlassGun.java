package Game.Items;

import org.lwjgl.input.Mouse;

import Core.Screen;
import Game.Entities.Player;
import Game.Entities.API.EntityLiving;
import Game.Items.API.ItemGun;
import Graphics.Icon;

public class GlassGun extends ItemGun
{

	public GlassGun(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		int mx = Mouse.getX();
		int my = Mouse.getY();

		Screen.room.leverBorder(Math.floorDiv(mx + 25, 50), Math.floorDiv(my + 25, 50));
	}

}
