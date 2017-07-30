package Registry;

import Game.Item;
import Game.Items.ItemBelt;
import Game.Items.ItemBow;
import Game.Items.ItemFlyBook;
import Game.Items.ItemRing;
import Game.Items.ItemSword;
import Game.Items.ToolMaterial;
import Graphics.Sprite;
import Registry.Registry.RegistrationException;

public class Items
{
	public static ToolMaterial rust = new ToolMaterial("Rust", 25, 0);
	public static Item rustSword = new ItemSword("Rust Sword", 25, Sprite.getSprite("sword/rust"), rust);
	public static ToolMaterial metal = new ToolMaterial("Rust", 35, 0);
	public static Item metalSword = new ItemSword("Metal Sword", 35, Sprite.getSprite("sword/metal"), metal);
	public static ToolMaterial broad = new ToolMaterial("Broad", 40, 0);
	public static Item broadSword = new ItemSword("Broad Sword", 40, Sprite.getSprite("sword/broad"), broad);
	public static ToolMaterial poison = new ToolMaterial("Poison", 35, 0);
	public static Item poisonSword = new ItemSword("Poison Sword", 35, Sprite.getSprite("sword/poison"), poison);
	public static ToolMaterial legendary = new ToolMaterial("Legendary", 50, 0);
	public static Item legendarySword = new ItemSword("ÞmLegendary Sword", 50, Sprite.getSprite("sword/legendary"), legendary);
	public static ToolMaterial seashell = new ToolMaterial("Seashell", 35, 0);
	public static Item seashellSword = new ItemSword("Seashell Sword", 35, Sprite.getSprite("sword/seashell"), seashell);
	public static ToolMaterial plant = new ToolMaterial("Plant", 35, 0);
	public static Item plantSword = new ItemSword("Plant Sword", 35, Sprite.getSprite("sword/plant"), plant);
	public static ToolMaterial fiery = new ToolMaterial("Fiery", 35, 0);
	public static Item fierySword = new ItemSword("Fiery Sword", 35, Sprite.getSprite("sword/fiery"), fiery);
	public static ToolMaterial icy = new ToolMaterial("Icy", 35, 0);
	public static Item icySword = new ItemSword("Icy Sword", 35, Sprite.getSprite("sword/icy"), icy);
	public static ToolMaterial dark = new ToolMaterial("Dark", 25, 0);
	public static Item darkSword = new ItemSword("Dark Sword", 25, Sprite.getSprite("sword/dark"), dark);
	public static ToolMaterial dragon = new ToolMaterial("Dragon", 35, 0);
	public static Item dragonSword = new ItemSword("Dragon Sword", 35, Sprite.getSprite("sword/dragon"), dragon);
	public static ToolMaterial gem = new ToolMaterial("Gem", 35, 0);
	public static Item gemSword = new ItemSword("Gem Sword", 35, Sprite.getSprite("sword/gem"), gem);
	public static ToolMaterial heat = new ToolMaterial("Heat", 35, 0);
	public static Item heatSword = new ItemSword("Heat Sword", 35, Sprite.getSprite("sword/heat"), heat);

	public static Item manaRing = new ItemRing("Mana Ring", 0, Sprite.getSprite("ring/mana"));
	public static Item lifeRing = new ItemRing("Life Ring", 0, Sprite.getSprite("ring/life"));
	public static Item damageRing = new ItemRing("Damage Ring", 0, Sprite.getSprite("ring/damage"));
	public static Item armorRing = new ItemRing("Armor Ring", 0, Sprite.getSprite("ring/armor"));
	public static Item invincRing = new ItemRing("Invincibility Ring", 0, Sprite.getSprite("ring/invincibility"))
	{
		public void addInformation(Game.ItemStack is, Game.Entities.Player p, java.util.List<String> l)
		{
			l.add("You make me feel Invincible");
		}
	};
	public static Item leatherBelt = new ItemBelt("Leather Belt", 0, Sprite.getSprite("belt/leather"));
	public static Item metalBelt = new ItemBelt("Metal Belt", 0, Sprite.getSprite("belt/metal"));
	public static Item flybook = new ItemFlyBook("Book of Flying", 0, Sprite.getSprite("research_book"));
	public static Item arrow = new Item("Arrow", 0, Sprite.getSprite("bow/metal_arrow"));
	public static Item bow = new ItemBow("Bow", 0, Sprite.getSprite("bow/legendary"));

	public static void register()
	{
		try
		{
			GameRegistry.registerItem(0, rustSword);
			GameRegistry.registerItem(1, metalSword);
			GameRegistry.registerItem(2, broadSword);
			GameRegistry.registerItem(3, poisonSword);
			GameRegistry.registerItem(4, legendarySword);
			GameRegistry.registerItem(5, seashellSword);
			GameRegistry.registerItem(6, plantSword);
			GameRegistry.registerItem(7, fierySword);
			GameRegistry.registerItem(8, icySword);
			GameRegistry.registerItem(9, darkSword);
			GameRegistry.registerItem(10, dragonSword);
			GameRegistry.registerItem(11, gemSword);
			GameRegistry.registerItem(12, heatSword);

			GameRegistry.registerItem(13, manaRing);
			GameRegistry.registerItem(14, lifeRing);
			GameRegistry.registerItem(15, damageRing);
			GameRegistry.registerItem(16, armorRing);
			GameRegistry.registerItem(17, leatherBelt);
			GameRegistry.registerItem(18, metalBelt);
			GameRegistry.registerItem(19, flybook);
			GameRegistry.registerItem(20, invincRing);
			GameRegistry.registerItem(21, arrow);
			GameRegistry.registerItem(22, bow);

		}
		catch (RegistrationException e)
		{
			e.printStackTrace();
		}
	}
}
