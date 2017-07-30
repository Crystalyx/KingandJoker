package Graphics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Hashtable;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Utilities.Logger;

public class Sprite
{
	public static final Sprite head = new Sprite("head");
	public static final Sprite sky = new Sprite("sky");
	public static final Sprite grass = new Sprite("grass");
	public static final Sprite dirt = new Sprite("dirt");
	public static final Sprite player = new Sprite("player");
	public static final Sprite manequen = new Sprite("manequen");
	public static final Sprite arrinter = new Sprite("arrowinterr");
	public static final Sprite inv = new Sprite("inv");
	public static final Sprite hot = new Sprite("hotbar");
	public static final Sprite sqr = new Sprite("sqr");
	public static final Sprite plate = new Sprite("plate");
	public static final Sprite alch = new Sprite("alchstone");
	public static final Sprite frame = new Sprite("frame");
	public static final Sprite equ = new Sprite("equ");
	public static final Sprite nil = new Sprite("null");
	public static final Sprite trace = new Sprite("trace");

	public static final Hashtable<String, Sprite> sprites = new Hashtable<String, Sprite>();
	private Texture texture;
	private String path;

	public Sprite(String texturename)
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
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		return this.texture;
	}

	public static Sprite getSprite(String path)
	{
		if (sprites.containsKey(path))
		{
			return sprites.get(path);
		}
		else
		{
			Sprite s = new Sprite(path);
			sprites.put(path, s);
			return s;
		}
	}

	public static Sprite[] values()
	{
		Class<?> clazz = Sprite.class;
		Field[] f = clazz.getFields();
		Sprite[] ret = new Sprite[f.length];
		for (int i = 0; i < ret.length; i++)
		{
			try
			{
				ret[i] = (Sprite) f[i].get(null);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Sprite valueOf(String path)
	{
		Class<?> clazz = Sprite.class;
		try
		{
			Field f = clazz.getField(path);

			return (Sprite) f.get(null);
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
}
