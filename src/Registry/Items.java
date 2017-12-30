package Registry;

import Game.Items.Cannon;
import Game.Items.Flamethrower;
import Game.Items.GlassGun;
import Game.Items.Laser;
import Game.Items.LightningGun;
import Graphics.Icon;
import Registry.Registry.RegistrationException;

public class Items
{
	public static Cannon cannon = new Cannon("gun_standart", 10, Icon.getIcon("items/gun_standart"), Icon.getIcon("player/guns/standart"));
	public static LightningGun lightning_gun = new LightningGun("lightning_gun", 20, Icon.getIcon("items/lightning_gun"), Icon.getIcon("player/guns/lightning"));
	public static Flamethrower flamethrw = new Flamethrower("flamethrw", 20, Icon.getIcon("items/flame_gun"), Icon.getIcon("player/guns/fire"));
	public static Laser Laser = new Laser("laser", 20, Icon.getIcon("items/laser"), Icon.getIcon("player/guns/laser"));
	public static GlassGun GlassGun = new GlassGun("glass", 20, Icon.getIcon("items/glass_gun"), Icon.getIcon("player/guns/border"));

	public static void register()
	{
		try
		{
			GameRegistry.registerItem(0, cannon);
			GameRegistry.registerItem(1, lightning_gun);
			GameRegistry.registerItem(2, flamethrw);
			GameRegistry.registerItem(3, Laser);
			GameRegistry.registerItem(4, GlassGun);

		}
		catch (RegistrationException e)
		{
			e.printStackTrace();
		}
	}
}
