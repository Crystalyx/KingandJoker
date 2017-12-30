package Game.Entities;

import java.util.ArrayList;
import java.util.List;

import Core.GUI;
import Core.KIJCore;
import Game.ItemStack;
import Game.Skill;
import Game.Entities.API.Entity;
import Game.Entities.API.EntityLiving;
import Game.Entities.Modifiers.Modifier;
import Game.Entities.Modifiers.SharedModifiers;
import Game.Gui.PlayerEquip;
import Game.Gui.PlayerInventory;
import Math.Vec.Vec2;
import Registry.Binds;
import Utilities.Tag;
import Utilities.Utils;

public class Player extends EntityLiving
{
	public Player(Vec2 pos, double width, double height)
	{
		super(pos, width, height);
		this.maxVelocity = new Vec2(480, 360);
	}

	public Player()
	{
		super(new Vec2(0, 190), GUI.PLAYER_WIDTH, GUI.PLAYER_HEIGHT);
		this.maxVelocity = new Vec2(480, 360);
		this.applyModifier(new Modifier(SharedModifiers.Energy, "Player", 180, 0));
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	public String name = "Lord_Crystalyx";

	public List<Skill> skills = new ArrayList<Skill>();
	public PlayerInventory inv = new PlayerInventory();
	public PlayerEquip equip = new PlayerEquip();
	public int trace = 500;
	public int hotselect = 0;// 0<=hotselect <10
	public double[] xs = new double[trace], ys = new double[trace];
	public String base = "base";
	public String gun = "lightning";
	public boolean doublegun = false;
	public String hat = "hat";
	public boolean needTrace = true;

	@Override
	public void update(long time)
	{
		if (!Binds.pressed(KIJCore.SETTINGS.keyAttack))
			this.addEnergy(1);

		for (int i = 0; i < this.inv.getSizeInventory(); i++)
		{
			ItemStack is = this.inv.getStackInSlot(i);
			if (is != null)
			{
				is.item.update(is, this, false, i == this.hotselect);
			}
		}

		for (int i = 0; i < this.equip.getSizeInventory(); i++)
		{
			ItemStack is = this.equip.getStackInSlot(i);
			if (is != null)
			{
				is.item.update(is, this, true, false);
			}
		}

		// this.ableToFly = InvUtils.contains(equip, Items.flybook);

		Vec2 v = this.pos.add(new Vec2(0, this.height / 2));
		List<Entity> l = GUI.room.getEntitiesWithinSquare(Entity.class, v.extendBoth(new Vec2(this.width, this.height)));

		l.remove(this);
		if (Binds.keyClick(KIJCore.SETTINGS.keyAttack))
		{
			if (this.getCurrentStack() != null)
			{
				this.getCurrentStack().item.onItemAttack(this, null);
			}
		}

		if (Binds.pressed(KIJCore.SETTINGS.keyAttack))
		{
			if (this.getCurrentStack() != null)
			{
				this.getCurrentStack().item.onItemUse(this);
			}
		}
		if (Binds.keyClick(KIJCore.SETTINGS.keyAttack) && !l.isEmpty() && (l.get(0) instanceof EntityLiving))
		{
			attackEntityFrom((EntityLiving) l.get(0));
		}
		if (Binds.keyClick(KIJCore.SETTINGS.keyInterract) && !l.isEmpty())
		{
			l.get(0).interract(this);
		}

		if (Binds.keyClick(KIJCore.SETTINGS.keyCycleL))
		{
			hotselect = Utils.limit(hotselect + 1, 0, 9);
		}
		if (Binds.keyClick(KIJCore.SETTINGS.keyCycleR))
		{
			hotselect = Utils.limit(hotselect - 1, 0, 9);
		}

		for (int i = 0; i < trace - 1; i++)
		{
			xs[i] = xs[i + 1];
		}
		xs[trace - 1] = this.pos.x;
		for (int i = 0; i < trace - 1; i++)
		{
			ys[i] = ys[i + 1];
		}
		ys[trace - 1] = this.pos.y;
		super.update(time);
	}

	public ItemStack getCurrentStack()
	{
		return this.inv.getStackInSlot(this.hotselect);
	}

	public void setTrace(int trace)
	{
		this.trace = trace;
		double[] xss = new double[trace];
		double[] yss = new double[trace];
		for (int i = 0; i < xss.length; i++)
		{
			xss[i] = xs[i];
			yss[i] = ys[i];
		}
	}

	public void write(Tag tag)
	{
		super.write(tag);
		tag.add("Name", this.name);
		tag.add("Level", this.level);
		tag.add("Strength", this.strength);
		tag.add("Life", this.life);
		tag.add("Mana", this.energy);

		tag.add("Skills", this.skills);

		Tag equip = new Tag();
		for (int i = 0; i < this.equip.equip.length; i++)
		{
			if (this.equip.equip[i] != null)
				this.equip.equip[i].write(equip, "Equip_" + i);
		}
		tag.appendTag("Equipment", equip);

		Tag inv = new Tag();
		for (int i = 0; i < this.inv.inv.length; i++)
		{
			if (this.inv.inv[i] != null)
				this.inv.inv[i].write(inv, "Inventory_" + i);
		}
		tag.appendTag("Inventory", inv);
	}

	public void read(Tag tag)
	{
		super.read(tag);
		this.name = tag.getString("Name");
		this.level = tag.getInt("Level");
		this.strength = tag.getInt("Strength");
		this.life = tag.getInt("Life");
		this.energy = tag.getInt("Mana");

		this.skills = tag.get("Skills");

		Tag equip = tag.getTagAt("Equipment");
		for (int i = 0; i < this.equip.equip.length; i++)
		{
			this.equip.equip[i] = ItemStack.read(equip, "Equip_" + i);
		}

		Tag inv = tag.getTagAt("Inventory");
		for (int i = 0; i < this.inv.getSizeInventory(); i++)
		{
			this.inv.inv[i] = ItemStack.read(inv, "Inventory_" + i);
		}
	}

	public boolean consumeEnergy(double energy)
	{
		if (this.energy < energy)
			return false;
		this.energy -= energy;
		return true;
	}

	public void addEnergy(double energy)
	{
		this.energy = Utils.limit(this.energy + energy, 0, this.maxenergy);
	}

	public void restoreEnergy(double energy)
	{
		this.energy = this.maxenergy;
	}
}
