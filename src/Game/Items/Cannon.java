package Game.Items;

import org.lwjgl.input.Mouse;

import Core.Screen;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.Throwable.Cannonball;
import Game.Items.API.ItemGun;
import Graphics.Icon;
import Utilities.Utils;

public class Cannon extends ItemGun
{

	public Cannon(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		if (p.consumeEnergy(10))
		{
			Cannonball s = new Cannonball(p, new Damage().setEntityDamage(p, 150), p.pos);
			int mx = Mouse.getX();
			int my = Mouse.getY();
			double dx = mx - p.pos.x;
			double dy = my - p.pos.y;
			double a = Math.atan2(dy, dx);

			s.setVelocity(Utils.getPosByAngle(a, 10));
			Screen.room.addObj(s);
		}
	}

}
