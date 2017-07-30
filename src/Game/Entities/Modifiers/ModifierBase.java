package Game.Entities.Modifiers;

import java.util.Hashtable;

public class ModifierBase
{
	public String name;
	public double baseValue;
	public String affValue;

	public static Hashtable<String, ModifierBase> bases = new Hashtable<String, ModifierBase>();

	public ModifierBase(String name, double baseValue, String affValue)
	{
		this.name = name;
		this.baseValue = baseValue;
		this.affValue = affValue;
	}

	public static ModifierBase createModifierBase(String base, double basevalue, String affValue)
	{
		if (bases.containsKey(base))
		{
			return bases.get(base);
		}
		else
		{
			ModifierBase bs = new ModifierBase(base, basevalue, affValue);
			bases.put(base, bs);
			return bs;
		}
	}
}
