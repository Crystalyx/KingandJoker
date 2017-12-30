package UCS;

import API.Focusable;
import Game.Entities.Player;

public class GuiEvent extends Event
{
	public static GuiEvent instance = new GuiEvent(null, null, null, 0, 0);

	public Focusable hud;
	public Player p;
	public RenderType type;
	public int k;
	public int l;

	public enum RenderType
	{
		crosshairs, bars
	}

	public GuiEvent(Focusable hud, Player p, RenderType type, int k, int l)
	{
		this.hud = hud;
		this.p = p;
		this.type = type;
		this.k = k;
		this.l = l;

	}

	public class HudRenderEvent extends GuiEvent
	{
		public HudRenderEvent(Focusable hud, Player p, RenderType type, int k, int l)
		{
			super(hud, p, type, k, l);
		}
	}

	public class GuiRenderEvent extends GuiEvent
	{
		public GuiRenderEvent(Focusable hud, Player p, RenderType type, int k, int l)
		{
			super(hud, p, type, k, l);
		}
	}
}
