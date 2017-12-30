package Game.Inventory;

import Core.KIJCore;
import Game.Inventory.Base.ContainerBase;

public class ContainerEquipment extends ContainerBase
{
	public ContainerEquipment()
	{
		this.addBackground(-138, 30, 300, 164);
		
		this.addSlot(1, 0, KIJCore.p.equip, -88, 16);
		this.addSlot(2, 0, KIJCore.p.equip, -108, 16);

		this.addSlot(0, 2, KIJCore.p.equip, -68, 16);
		this.addSlot(4, 3, KIJCore.p.equip, -68, 36);
		this.addSlot(5, 5, KIJCore.p.equip, -68, 56);
		this.addSlot(6, 7, KIJCore.p.equip, -68, 76);

		this.addSlot(3, 1, KIJCore.p.equip, -128, 16);
		this.addSlot(7, 4, KIJCore.p.equip, -128, 36);
		this.addSlot(8, 6, KIJCore.p.equip, -128, 56);
		this.addSlot(9, 8, KIJCore.p.equip, -128, 76);

		this.addSlot(10, 10, KIJCore.p.equip, -68, 96);
		this.addSlot(11, 11, KIJCore.p.equip, -88, 96);
		this.addSlot(12, KIJCore.p.equip, -108, 96);
		this.addSlot(13, 9, KIJCore.p.equip, -128, 96);

		this.addPlayerSlots(-48, 57);
		this.addSlot(0, 12, new InventoryTrashCan(), 140, -6);
	}
}
