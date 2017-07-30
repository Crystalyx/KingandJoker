package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Library
{
	public Library(String book)
	{
		this.book = book;
	}

	public String book = "common";
	public static String path = Constants.pathBase + "/library";
	public static final String ext = ".lib";
	public Hashtable<String, String> knowlege = new Hashtable<String, String>();

	public void write(Hashtable<String, String> tag)
	{
		Enumeration<String> en = tag.keys();
		while (en.hasMoreElements())
		{
			String key = en.nextElement();
			knowlege.put(key, tag.get(key));
		}
		try
		{
			File lib = new File(path, book + ext);
			File pth = new File(path);
			if (!pth.exists())
			{
				pth.mkdirs();
			}
			if (!lib.exists())
			{
				lib.createNewFile();
			}

			// read(lib);

			lib.delete();
			lib.createNewFile();
			output(lib);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void write(String exp, String value)
	{
		knowlege.put(exp, value);
		try
		{
			File lib = new File(path, book + ext);
			File pth = new File(path);
			if (!pth.exists())
			{
				pth.mkdirs();
			}
			if (!lib.exists())
			{
				lib.createNewFile();
			}

			// read(lib);

			lib.delete();
			lib.createNewFile();
			output(lib);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void read(File lib) throws IOException
	{
		FileReader fr = new FileReader(lib);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		if (line != null)
			while (!line.startsWith("}"))
			{
				if (!line.startsWith("{") && line.indexOf("=") != -1)
				{
					knowlege.put(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=") + 1));
				}
				line = br.readLine().trim();
			}

		br.close();
	}

	public String get(String key)
	{
		return knowlege.get(key);
	}

	public void output(File lib) throws IOException
	{
		FileWriter fw = new FileWriter(lib);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("{");
		bw.newLine();

		Enumeration<String> en = knowlege.keys();
		while (en.hasMoreElements())
		{
			String key = en.nextElement();
			bw.write(key + "=" + knowlege.get(key));
			bw.newLine();
		}
		bw.write("}");
		bw.flush();
		bw.close();
	}
}
