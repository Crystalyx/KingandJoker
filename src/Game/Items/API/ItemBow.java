package Game.Items.API;

import org.lwjgl.input.Mouse;

import Functions.EMath;
import Functions.Expression;
import Functions.Var;
import Game.Item;
import Game.Entities.Player;
import Game.Entities.API.Damage;
import Game.Entities.API.EntityArrow;
import Game.Entities.API.EntityLiving;
import Graphics.GUI;
import Graphics.Icon;
import Math.Vec.Vec2;
import Utilities.Utils;

public class ItemBow extends Item
{
	public ItemBow(String name, int price, Icon icon)
	{
		super(name, price, icon);
	}

	public static double v = 10;

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		super.onItemAttack(p, e);
		EntityArrow ea = new EntityArrow(p, new Damage().setEntityDamage(p, p.strength + 30), p.pos);
		int mx = Mouse.getX();
		int my = Mouse.getY();
		double dx = mx - p.pos.x;
		double dy = my - p.pos.y;

//		v = Math.sqrt(dx * dx + dy * dy);

		double alpha = Math.atan2(dy * 1.0d, dx * 1.0d);
		Vec2 lv = Utils.getPosByAngle(alpha, v);
		ea.setVelocity(lv);
		GUI.room.addObj(ea);
	}

}
