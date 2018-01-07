package Game.Gui;

import Core.GUI;
import Game.Gui.Base.ContainerBase;
import Game.Gui.Base.GuiButton;
import Game.Gui.Base.GuiSwitch;
import Game.Gui.Base.GuiText;

public class ContainerEquipment extends ContainerBase
{
	public ContainerEquipment()
	{
		this.addBackground(-138, 30, 300, 164);

		// this.addSlot(1, 0, KIJCore.p.equip, -88, 16);
		// this.addSlot(2, 0, KIJCore.p.equip, -108, 16);
		//
		// this.addSlot(0, 2, KIJCore.p.equip, -68, 16);
		// this.addSlot(4, 3, KIJCore.p.equip, -68, 36);
		// this.addSlot(5, 5, KIJCore.p.equip, -68, 56);
		// this.addSlot(6, 7, KIJCore.p.equip, -68, 76);
		//
		// this.addSlot(3, 1, KIJCore.p.equip, -128, 16);
		// this.addSlot(7, 4, KIJCore.p.equip, -128, 36);
		// this.addSlot(8, 6, KIJCore.p.equip, -128, 56);
		// this.addSlot(9, 8, KIJCore.p.equip, -128, 76);
		//
		// this.addSlot(10, 10, KIJCore.p.equip, -68, 96);
		// this.addSlot(11, 11, KIJCore.p.equip, -88, 96);
		// this.addSlot(12, KIJCore.p.equip, -108, 96);
		// this.addSlot(13, 9, KIJCore.p.equip, -128, 96);

		this.addPlayerSlots(-48, 47);
		this.addSlot(0, 12, new InventoryTrashCan(), 100, 31.5d);

		GuiSwitch butt = new GuiSwitch(-128, -42, 16, 6)
		{
			@Override
			public boolean mousePressed(double x, double y, int button)
			{
				if (GUI.focus != null)
					GUI.focus.pause = !GUI.focus.pause;
				this.switchState();
				return true;
			}

			@Override
			public boolean getState()
			{
				return GUI.focus.pause;
			}
		};
		this.addButton(butt);

		this.addText("Pause", -108, -50, 40, 8, 12);

		GuiButton clos = new GuiButton(162, 100, 16, 16)
		{
			@Override
			public boolean mousePressed(double x, double y, int button)
			{
				GUI.screenObjs.remove(this.gui);
				GUI.removeFocus();

				return true;
			}
		};
		this.addButton(clos);
	}
}
