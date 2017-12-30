package Game.Entities;

import java.util.List;

import Game.ItemStack;
import Game.Entities.AI.AIFollow;
import Game.Entities.API.EntityLiving;
import Graphics.Icon;
import Math.Vec.Vec2;
import Registry.GameRegistry;
import Utilities.Utils;

public class Manequen extends EntityLiving
{
	public Manequen(Vec2 pos)
	{
		super(pos, 64, 64, Icon.manequen);
		this.addAI(new AIFollow(Player.class, 100, 100, 0.5));
	}

	@Override
	public List<ItemStack> getDrops()
	{
		List<ItemStack> l = super.getDrops();
		l.add(new ItemStack(GameRegistry.getItem(Utils.r.nextInt(GameRegistry.items.size()))));
		return l;
	}

}
