package Game.Entities.Modifiers;

public class SharedModifiers
{
	public static final ModifierBase Life = ModifierBase.createModifierBase("Life", 150, "Max Life");
	public static final ModifierBase Mana = ModifierBase.createModifierBase("Mana", 60, "Max Mana");
	public static final ModifierBase Damage = ModifierBase.createModifierBase("Damage", 15, "Damage");
	public static final ModifierBase Armor = ModifierBase.createModifierBase("Armor", 0, "Armor");
	public static final ModifierBase FireResist = ModifierBase.createModifierBase("FireResist", 0, "Fire Resistance");
	public static final ModifierBase InternalResist = ModifierBase.createModifierBase("InternalResist", 0, "Internal Resistance");
	public static final ModifierBase VoidResist = ModifierBase.createModifierBase("VoidResist", 0, "Void Resistance");
	public static final ModifierBase AbilityFly = ModifierBase.createModifierBase("AbilityFly", 0, "Ability To Fly");

}
