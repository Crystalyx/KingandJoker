package Game.Items;

import Game.Item;
import Graphics.Sprite;

public class ItemTool extends Item
{
	public ToolMaterial mat;

	public ItemTool(String name, int price, Sprite icon, ToolMaterial mat)
	{
		super(name, price, icon);
		this.mat = mat;
	}
}
