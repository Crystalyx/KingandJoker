package Game.Entities;

import java.util.ArrayList;
import java.util.List;

import Game.ItemStack;
import Game.Skill;
import Game.Inventory.InvUtils;
import Game.Inventory.PlayerEquip;
import Game.Inventory.PlayerInventory;
import Graphics.GUI;
import Graphics.Main;
import Registry.Binds;
import Registry.Items;
import Utilities.Logger;
import Utilities.Tag;
import Utilities.Utils;
import Utilities.Vec2;

public class Player extends EntityLiving
{
	public Player(Vec2 pos, double width, double height)
	{
		super(pos, width, height);
	}

	public Player()
	{
		super(new Vec2(0, 90), GUI.PLAYER_WIDTH, GUI.PLAYER_HEIGHT);
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
	public int trace = 50;
	public int hotselect = 0;// 0<=hotselect <10
	public double[] xs = new double[trace], ys = new double[trace];
	public String body = "man";
	public String head = "man";
	public String hat = "hat";
	public boolean needTrace = false;

	@Override
	public void update(long time)
	{
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
		if (InvUtils.contains(equip, Items.invincRing))
		{
			this.life = this.maxlife;
		}

		Vec2 v = this.pos.sub(new Vec2(this.width / 2, 0));
		List<Entity> l = GUI.croom.getEntitiesWithinSquare(Entity.class, v.extend(new Vec2(this.width, this.height)));
		l.remove(this);
		if (Binds.keyClick(Main.SETTINGS.keyAttack))
		{
			if (this.getCurrentStack() != null)
			{
				this.getCurrentStack().item.onItemAttack(this, null);
			}
		}
		if (Binds.keyClick(Main.SETTINGS.keyAttack) && !l.isEmpty() && (l.get(0) instanceof EntityLiving))
		{
			attackEntityFrom((EntityLiving) l.get(0));
		}
		if (Binds.keyClick(Main.SETTINGS.keyInterract) && !l.isEmpty())
		{
			l.get(0).interract(this);
		}

		if (Binds.keyClick(Main.SETTINGS.keyCycleL))
		{
			hotselect = Utils.limit(hotselect + 1, 0, 9);
		}
		if (Binds.keyClick(Main.SETTINGS.keyCycleR))
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
		tag.add("Mana", this.mana);

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
		this.mana = tag.getInt("Mana");

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
}
