package Game.Items;

import org.lwjgl.input.Mouse;

import Game.Border;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.Throwable.Lightingball;
import Game.Items.API.ItemGun;
import Graphics.GUI;
import Graphics.Icon;
import Math.Vec.Vec2;

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

		Border b = new Border(new Vec2(Math.floorDiv(mx + 25, 50) * 50, Math.floorDiv(my + 25, 50) * 50), 50, 50, Icon.getIcon("metal"));

		GUI.room.leverBorder(Math.floorDiv(mx + 25, 50), Math.floorDiv(my + 25, 50));
	}

}
