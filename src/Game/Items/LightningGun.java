package Game.Items;

import org.lwjgl.input.Mouse;

import Core.GUI;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityLiving;
import Game.Entities.Throwable.Lightingball;
import Game.Items.API.ItemGun;
import Graphics.Icon;
import Utilities.Utils;

public class LightningGun extends ItemGun
{

	public LightningGun(String name, int price, Icon invicon, Icon icon)
	{
		super(name, price, invicon, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		Lightingball s = new Lightingball(p, new Damage().setEntityDamage(p, 250), p.pos);
		int mx = Mouse.getX();
		int my = Mouse.getY();
		double dx = mx - p.pos.x;
		double dy = my - p.pos.y;
		double a = Math.atan2(dy, dx);

		s.setVelocity(Utils.getPosByAngle(a, 10));
		GUI.room.addObj(s);
	}

}
