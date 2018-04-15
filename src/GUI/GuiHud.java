package GUI;

import API.Focusable;
import Core.Screen;
import Core.KIJCore;
import UCS.GuiEvent;
import UCS.GuiEvent.RenderType;

public class GuiHud extends Focusable
{

	public GuiHud()
	{
		super(Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);
	}

	@Override
	public void drawGui(int k, int l)
	{
		GuiEvent hre = GuiEvent.instance.new HudRenderEvent(this, KIJCore.p, RenderType.bars, k, l);
		KIJCore.EVENT_BUS.raiseEvent(hre);
	}
}
