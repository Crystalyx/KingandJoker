package Game;
import java.util.ArrayList;
import java.util.List;

import Game.Entities.Player;
import Utilities.Tag;

public class Save
{
	public String name = "New World";
	public Player p = new Player();
	public List<Achievment> achievs = new ArrayList<Achievment>();

	public void write(Tag tag)
	{
		p.write(tag);
	}

	public void read(Tag tg)
	{
		p.read(tg);
	}
}
