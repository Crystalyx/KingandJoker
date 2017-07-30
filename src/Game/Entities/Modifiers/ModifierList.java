package Game.Entities.Modifiers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ModifierList
{
	public Hashtable<String, List<Modifier>> list = new Hashtable<String, List<Modifier>>();

	public void addModifier(Modifier m)
	{
		if (list.containsKey(m.base.name))
		{
			List<Modifier> lm = list.get(m.base.name);
			for (int i = 0; i < lm.size(); i++)
			{
				Modifier mr = lm.get(i);
				if (mr.uuid.equals(m.uuid))
				{
					list.get(m.base.name).remove(mr);
				}
			}
			list.get(m.base.name).add(m);
		}
		else
		{
			List<Modifier> lm = new ArrayList<Modifier>();
			lm.add(m);
			list.put(m.base.name, lm);
		}
	}

	public double countValue(ModifierBase base)
	{
		double cval = base.baseValue;
		if (list.containsKey(base.name))
		{
			List<Modifier> mdfs = list.get(base.name);
			if (!mdfs.isEmpty())
			{
				for (Modifier mr : mdfs)
				{
					if (mr.operation == 0)
					{
						cval = mr.value;
					}
					if (mr.operation == 1)
					{
						cval += mr.value;
					}
					if (mr.operation == 2)
					{
						cval *= (1 + mr.value / 100d);
					}
					if (mr.operation == 3)
					{
						cval *= mr.value;
					}
				}
			}
		}
		return cval;
	}
}
