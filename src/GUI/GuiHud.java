package GUI;

import Core.KIJCore;
import Game.Inventory.Gui;
import Graphics.GUI;
import UCS.GuiEvent;
import UCS.GuiEvent.RenderType;

public class GuiHud extends Gui
{

	public GuiHud()
	{
		super(GUI.SCREEN_WIDTH, GUI.SCREEN_HEIGHT);
	}

	@Override
	public void drawGui(int k, int l)
	{
		GuiEvent hre = GuiEvent.instance.new HudRenderEvent(this, KIJCore.p, RenderType.bars, k, l);
		KIJCore.EVENT_BUS.raiseEvent(hre);
	}
}
