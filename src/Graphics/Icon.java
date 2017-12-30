package Graphics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Utilities.Logger;

public class Icon
{
	public static final Icon head = new Icon("head");
	public static final Icon sky = new Icon("sky");
	public static final Icon brick = new Icon("brick");
	public static final Icon cluster = new Icon("cluster");
	public static final Icon player = new Icon("player");
	public static final Icon manequen = new Icon("manequen");
	public static final Icon arrinter = new Icon("arrowinterr");
	public static final Icon inv = new Icon("inv");
	public static final Icon hot = new Icon("hotbar");
	public static final Icon sqr = new Icon("sqr");
	public static final Icon plate = new Icon("plate");
	public static final Icon alch = new Icon("alchstone");
	public static final Icon frame = new Icon("frame");
	public static final Icon equ = new Icon("equ");
	public static final Icon nil = new Icon("null");
	public static final Icon trace = new Icon("trace");

	public static final HashMap<String, Icon> icons = new HashMap<String, Icon>();
	private Texture texture;
	private String path;

	public Icon(String texturename)
	{
		try
		{
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + texturename + ".png")));
			setPath(texturename);
		}
		catch (IOException e)
		{
			Logger.info("Couldn't find texture path: " + "res/" + texturename + ".png");
			texture = nil.texture;

		}
	}

	public Texture getTexture()
	{
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		return this.texture;
	}

	public static Icon getIcon(String path)
	{
		if (icons.containsKey(path))
		{
			return icons.get(path);
		}
		else
		{
			Icon s = new Icon(path);
			icons.put(path, s);
			return s;
		}
	}

	public static Icon[] values()
	{
		Class<?> clazz = Icon.class;
		Field[] f = clazz.getFields();
		Icon[] ret = new Icon[f.length];
		for (int i = 0; i < ret.length; i++)
		{
			try
			{
				ret[i] = (Icon) f[i].get(null);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Icon valueOf(String path)
	{
		Class<?> clazz = Icon.class;
		try
		{
			Field f = clazz.getField(path);

			return (Icon) f.get(null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public void bind()
	{
		this.getTexture().bind();
	}
}
