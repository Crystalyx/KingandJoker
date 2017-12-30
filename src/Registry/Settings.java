package Registry;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import Core.KIJCore;

public class Settings
{
	public int keyAttack = Binds.MOUSE_LEFT;
	public int keyInterract = Keyboard.KEY_R;
	public int keyEquip = Keyboard.KEY_U;

	public int keyCycleR = Keyboard.KEY_E;
	public int keyCycleL = Keyboard.KEY_Q;

	public int keyLeft = Keyboard.KEY_A;
	public int keyRight = Keyboard.KEY_D;
	public int keyUp = Keyboard.KEY_W;
	public int keyDown = Keyboard.KEY_S;
	public int keyDebug = Keyboard.KEY_O;
	public int keyWave = Keyboard.KEY_J;
	public int keyReset = Keyboard.KEY_L;

	public void load()
	{
		loadKey("Attack", keyAttack);
		loadKey("Interract", keyInterract);
		loadKey("Equip", keyEquip);

		loadKey("CycleR", keyCycleR);
		loadKey("CycleL", keyCycleL);

		loadKey("Left", keyLeft);
		loadKey("Right", keyRight);
		loadKey("Up", keyUp);
		loadKey("Down", keyDown);
		loadKey("Debug", keyDebug);
		loadKey("Wave", keyWave);
		loadKey("Reset", keyReset);

	}

	public void loadKey(String key, int word)
	{
		Class<?> clazz = this.getClass();
		try
		{
			Field k = clazz.getField("key" + key);

			KIJCore.cfg.setWarn(false);
			KIJCore.cfg.addProperty("Settings", key, word);
			KIJCore.cfg.setWarn(true);

			int b = KIJCore.cfg.getInt("Settings", key, word);
			k.set(this, b);
			Binds.registerKey((int) k.get(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
