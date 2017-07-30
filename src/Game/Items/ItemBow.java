package Game.Items;

import org.lwjgl.input.Mouse;

import Game.Item;
import Game.Entities.Damage;
import Game.Entities.EntityArrow;
import Game.Entities.EntityLiving;
import Game.Entities.Player;
import Graphics.GUI;
import Graphics.Sprite;
import Utilities.Vec2;

public class ItemBow extends Item
{
	public ItemBow(String name, int price, Sprite icon)
	{
		super(name, price, icon);
	}

	@Override
	public void onItemAttack(Player p, EntityLiving e)
	{
		super.onItemAttack(p, e);
		EntityArrow ea = new EntityArrow(p, new Damage().setEntityDamage(p, p.strength + 30), Vec2.getCenteredPos(p));
		int mx = (int) Math.round(Mouse.getX());
		int my = (int) Math.round(Mouse.getY());
		Vec2 lv = new Vec2(mx, my).sub(p.pos);
		lv.normalize();
		ea.setVelocity(lv.modif(25));
		GUI.croom.addObj(ea);
	}

}
