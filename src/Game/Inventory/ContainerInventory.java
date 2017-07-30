package Game.Inventory;

import Game.Inventory.Base.ContainerBase;

public class ContainerInventory extends ContainerBase
{
	public ContainerInventory()
	{
		this.addBackground(0, 60, 200, 164);
		this.addPlayerSlots(0, 80);
		this.addSlot(0,12, new InventoryTrashCan(), 188, 10);
	}
}
