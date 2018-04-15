package Game.Items;

import org.lwjgl.input.Mouse;

import Core.Screen;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.Throwable.Fireball;
import Game.Items.API.ItemGun;
import Graphics.Icon;
import Utilities.Utils;

public class Flamethrower extends ItemGun
{

	public Flamethrower(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		if (p.consumeEnergy(20))
		{
			Fireball s = new Fireball(p, new Damage().setEntityDamage(p, 50), p.pos);
			int mx = Mouse.getX();
			int my = Mouse.getY();
			double dx = mx - p.pos.x;
			double dy = my - p.pos.y;
			double a = Math.atan2(dy, dx);

			s.setVelocity(Utils.getPosByAngle(a + Math.sin(Utils.getTimedAngle(25)) * 0.2, 10));
			Screen.room.addObj(s);
		}
	}

	@Override
	public void onItemUse(Player p)
	{
		if (p.consumeEnergy(3))
		{
			Fireball s = new Fireball(p, new Damage().setEntityDamage(p, 50), p.pos);
			int mx = Mouse.getX();
			int my = Mouse.getY();
			double dx = mx - p.pos.x;
			double dy = my - p.pos.y;
			double a = Math.atan2(dy, dx);

			s.setVelocity(Utils.getPosByAngle(a + Math.sin(Utils.getTimedAngle(25)) * 0.2, 10));
			Screen.room.addObj(s);
		}
	}
}
