package Utilities;

import java.util.Enumeration;
import java.util.Hashtable;

public class Tag
{
	public Hashtable<String, Object> hash;

	public Tag copy()
	{
		Tag tg = new Tag();
		
		Enumeration<String> keys = hash.keys();
		while (keys.hasMoreElements())
		{
			String key = (String) keys.nextElement();
			tg.add(key, hash.get(key));
		}
		
		return tg;
	}
	
	public Tag()
	{
		this.hash = new Hashtable<String, Object>();
	}

	public Tag(Hashtable<String, Object> hash)
	{
		this.hash = hash;
	}
	
	public <T> void add(String key, T value)
	{
		if (this.hash.contains(key))
		{
			this.hash.remove(key);
		}
		this.hash.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String id)
	{
		try
		{
			return (T) this.hash.get(id);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public void appendTag(String id, Tag app)
	{
		Enumeration<String> en = app.hash.keys();
		while (en.hasMoreElements())
		{
			String key = (String) en.nextElement();
			this.hash.put(id+key, app.hash.get(key));
		}
	}
	
	public Tag getTagAt(String id)
	{
		Hashtable<String,Object> ht = new Hashtable<String,Object>();
		
		Enumeration<String> en = this.hash.keys();
		while (en.hasMoreElements())
		{
			String key = (String) en.nextElement();
			if(key.startsWith(id))
			{
				ht.put(key.replaceAll(id, ""), this.hash.get(key));
			}
		}
		return new Tag(ht);
	}

	public int getInt(String id)
	{
		try
		{
			return (int) this.hash.get(id);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public double getDouble(String id)
	{
		try
		{
			return (double) this.hash.get(id);
		}
		catch (Exception e)
		{
			return 0.0d;
		}
	}

	public float getFloat(String id)
	{
		try
		{
			return (float) this.hash.get(id);
		}
		catch (Exception e)
		{
			return 0.0f;
		}
	}

	public String getString(String id)
	{
		try
		{
			return (String) this.hash.get(id);
		}
		catch (Exception e)
		{
			return "";
		}
	}

	public boolean getBoolean(String id)
	{
		try
		{
			return (boolean) this.hash.get(id);
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean containsKey(String key)
	{
		return this.hash.containsKey(key);
	}
}
