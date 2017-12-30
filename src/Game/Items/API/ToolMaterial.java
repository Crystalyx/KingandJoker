package Game.Items.API;

public class ToolMaterial
{
	public String name;
	public double damage;
	public int enchantability;

	public ToolMaterial(String name, double damage, int enchant)
	{
		this.name = name;
		this.damage = damage;
		this.enchantability = enchant;
	}
}
