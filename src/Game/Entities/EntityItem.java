package Game.Entities;

import Game.Action;
import Game.ItemStack;
import Graphics.Main;
import Utilities.Utils;
import Utilities.Vec2;

public class EntityItem extends Entity
{
	public ItemStack i;
	public int droptimer;

	public EntityItem(Vec2 pos, ItemStack i)
	{
		super(pos, 32, 32, i.item != null ? i.item.icon : null);
		this.i = i;
		this.near = new Action()
		{
			public void act(Entity obj, EntityLiving p)
			{
				if (p instanceof Player)
				{
					ItemStack is = ((EntityItem) obj).i.copy();
					if (((Player) p).inv.addItemStack(is) == null)
					{
						obj.setDead(true);
					}
				}
			}
		};
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		this.droptimer = Utils.limit(this.droptimer - 1, 0, Integer.MAX_VALUE);
		if (time % 20 == 0)
		{
			double d = this.pos.sub(Vec2.getCenteredPos(Main.p)).length();

			if (d <= 50)
			{
				if (this.droptimer <= 0)
				{
					this.near.act(this, Main.p);
				}
			}
		}
	}

	public void setTimer(int timer)
	{
		this.droptimer = timer;
	}

}
