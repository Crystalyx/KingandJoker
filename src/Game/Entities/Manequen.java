package Game.Entities;

import java.util.List;

import Game.ItemStack;
import Game.Entities.AI.AIFollow;
import Graphics.Sprite;
import Registry.GameRegistry;
import Utilities.Utils;
import Utilities.Vec2;

public class Manequen extends EntityLiving
{
	public Manequen(Vec2 pos)
	{
		super(pos, 64, 64, Sprite.manequen);
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
