package Game.Items;

import org.lwjgl.input.Mouse;

import Core.Screen;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.Throwable.LaserPart;
import Game.Items.API.ItemGun;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.Utils;

public class Laser extends ItemGun
{

	public Laser(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		if (p.consumeEnergy(20))
		{
			for (int i = 0; i < 100; i++)
			{
				LaserPart s = new LaserPart(p, new Damage().setEntityDamage(p, 50), p.pos);
				int mx = Mouse.getX();
				int my = Mouse.getY();
				double dx = mx - p.pos.x;
				double dy = my - p.pos.y;
				double a = Math.atan2(dy, dx);
				Vec2 np = Utils.getPosByAngle(a, 14 * i);
				s.pos = s.pos.add(np);

				Screen.room.addObj(s);
			}
		}
	}

	@Override
	public void onItemUse(Player p)
	{
		if (p.consumeEnergy(5))
		{
			for (int i = 0; i < 95; i++)
			{
				LaserPart s = new LaserPart(p, new Damage().setEntityDamage(p, 50), p.pos);
				int mx = Mouse.getX();
				int my = Mouse.getY();
				double dx = mx - p.pos.x;
				double dy = my - p.pos.y;
				double a = Math.atan2(dy, dx);
				Vec2 np = Utils.getPosByAngle(a, 14 * i);
				s.pos = s.pos.add(np);

				Screen.room.addObj(s);
			}
		}
	}
}
