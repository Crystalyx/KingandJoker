package Game.Gui.Base;

public class ObjType
{
	public ObjType(Objs typ, int id, int px, int py, int sizex, int sizey)
	{
		this.type = typ;
		this.id = id;
		this.px = px;
		this.py = py;
		this.sizex = sizex;
		this.sizey = sizey;
	}

	public Objs type;
	public int id;
	public int px;
	public int py;
	public int sizex;
	public int sizey;
	public GuiBase gui;

	public void setGui(GuiBase gui)
	{
		this.gui = gui;
	}
}
