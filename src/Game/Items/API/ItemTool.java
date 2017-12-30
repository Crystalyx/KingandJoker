package Game.Items.API;

import Game.Item;
import Graphics.Icon;

public class ItemTool extends Item
{
	public ToolMaterial mat;

	public ItemTool(String name, int price, Icon icon, ToolMaterial mat)
	{
		super(name, price, icon);
		this.mat = mat;
	}
}
