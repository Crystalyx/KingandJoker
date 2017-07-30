package Game.Inventory;

import Game.Inventory.Base.ContainerBase;
import Graphics.Main;

public class ContainerEquipment extends ContainerBase
{
	public ContainerEquipment()
	{
		this.addBackground(-138, 80, 80, 120);

		this.addSlot(1, 0, Main.p.equip, -88, 46);
		this.addSlot(2, 0, Main.p.equip, -108, 46);

		this.addSlot(0, 2, Main.p.equip, -68, 46);
		this.addSlot(4, 3, Main.p.equip, -68, 66);
		this.addSlot(5, 5, Main.p.equip, -68, 86);
		this.addSlot(6, 7, Main.p.equip, -68, 106);

		this.addSlot(3, 1, Main.p.equip, -128, 46);
		this.addSlot(7, 4, Main.p.equip, -128, 66);
		this.addSlot(8, 6, Main.p.equip, -128, 86);
		this.addSlot(9, 8, Main.p.equip, -128, 106);

		this.addSlot(10, 10, Main.p.equip, -68, 126);
		this.addSlot(11, 11, Main.p.equip, -88, 126);
		this.addSlot(12, Main.p.equip, -108, 126);
		this.addSlot(13, 9, Main.p.equip, -128, 126);
	}
}
