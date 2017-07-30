package Registry;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import Utilities.Configuration;

public class Settings
{
	public int keyAttack = Keyboard.KEY_D;
	public int keyInterract = Keyboard.KEY_R;
	public int keyInventory = Keyboard.KEY_I;
	public int keyEquip = Keyboard.KEY_U;

	public int keyCycleR = Keyboard.KEY_E;
	public int keyCycleL = Keyboard.KEY_Q;

	public int keyLeft = Keyboard.KEY_LEFT;
	public int keyRight = Keyboard.KEY_RIGHT;
	public int keyJump = Keyboard.KEY_SPACE;
	public int keyStrafe = Keyboard.KEY_LSHIFT;
	public int keyDebug = Keyboard.KEY_O;
	public int keyWave = Keyboard.KEY_J;

	public void load()
	{
		loadKey("Attack", "D");
		loadKey("Interract", "R");
		loadKey("Inventory", "I");
		loadKey("Equip", "U");

		loadKey("CycleR", "E");
		loadKey("CycleL", "Q");

		loadKey("Left", "LEFT");
		loadKey("Right", "RIGHT");
		loadKey("Jump", "SPACE");
		loadKey("Strafe", "LSHIFT");
		loadKey("Debug", "O");
		loadKey("Wave", "J");

	}

	public void loadKey(String key, String word)
	{
		Class<?> clazz = this.getClass();
		try
		{
			Field k = clazz.getField("key" + key);

			Configuration.setWarn(false);
			Configuration.addProperty("Settings", key, word);
			Configuration.setWarn(true);

			String b = Configuration.getString("Settings", key, word);
			k.set(this, Keyboard.getKeyIndex(b));
			Binds.registerKey((int) k.get(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
