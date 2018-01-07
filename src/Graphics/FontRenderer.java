package Graphics;

import java.util.Hashtable;

import org.lwjgl.opengl.GL11;

import Utilities.Color;
import Utilities.Graph;
import Utilities.Tessellator;

public class FontRenderer
{
	// "Þ" == Alt+0222
	public static void load()
	{
		add(" ");
		add("!");
		add("\"");
		add("#");
		add("$");
		add("%");
		add("&");
		add("'");
		add("(");
		add(")");
		add("*");
		add("+");
		add(",");
		add("-");
		add(".");
		add("/");
		add("0");
		add("1");
		add("2");
		add("3");
		add("4");
		add("5");
		add("6");
		add("7");
		add("8");
		add("9");
		add(":");
		add(";");
		add("<");
		add("=");
		add(">");
		add("?");
		add("@");
		add("A");
		add("B");
		add("C");
		add("D");
		add("E");
		add("F");
		add("G");
		add("H");
		add("I");
		add("J");
		add("K");
		add("L");
		add("M");
		add("N");
		add("O");
		add("P");
		add("Q");
		add("R");
		add("S");
		add("T");
		add("U");
		add("V");
		add("W");
		add("X");
		add("Y");
		add("Z");
		add("[");
		add("∞");
		add("]");
		add("^");
		add("_");
		add("`");
		add("a");
		add("b");
		add("c");
		add("d");
		add("e");
		add("f");
		add("g");
		add("h");
		add("i");
		add("j");
		add("k");
		add("l");
		add("m");
		add("n");
		add("o");
		add("p");
		add("q");
		add("r");
		add("s");
		add("t");
		add("u");
		add("v");
		add("w");
		add("x");
		add("y");
		add("z");
		add("{");
		add("|");
		add("}");
		add("~");
		add("⌂");
		add("α");
		add("β");
		add("γ");
		add("π");
		add("Σ");
		add("σ");
		add("μ");
		add("τ");
		add("Ξ");
		add("Θ");
		add("Ω");
		add("δ");
		add("∆");
		add("ξ");
		add("∈");
		add("⋂");
		add("≡");
		add("±");
		add("≥");
		add("≤");
		add("⌠");
		add("⌡");
		add("÷");
		add("≈");
		add("°");
		add("⋅");
		add("×");
		add("√");
		add("³");
		add("²");
		add("▪");
		add("□");
		add("А");
		add("Б");
		add("В");
		add("Г");
		add("Д");
		add("Е");
		add("Ж");
		add("З");
		add("И");
		add("Й");
		add("К");
		add("Л");
		add("М");
		add("Н");
		add("О");
		add("П");
		add("Р");
		add("С");
		add("Т");
		add("У");
		add("Ф");
		add("Х");
		add("Ц");
		add("Ч");
		add("Ш");
		add("Щ");
		add("Ъ");
		add("Ы");
		add("Ь");
		add("Э");
		add("Ю");
		add("Я");
		add("");
		add("");
		add("");
		add("");
		add("");
		add("а");
		add("б");
		add("в");
		add("г");
		add("д");
		add("е");
		add("ж");
		add("з");
		add("и");
		add("й");
		add("к");
		add("л");
		add("м");
		add("н");
		add("у");
		add("ф");
		add("х");
		add("ц");
		add("ч");
		add("ш");
		add("щ");
		add("ъ");
		add("ы");
		add("ь");
		add("э");
		add("ю");
		add("я");
		add("Ё");
		add("ё");

	}

	public static void add(String c)
	{
		chars.put(c, last);
		last++;
	}

	public void drawString(Object s, double x, double y)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glScaled(0.125, 0.125, 1);
		GL11.glRotated(180, 1, 0, 0);
		Icon.getIcon("ascii").getTexture().bind();
		char[] smbls = (s + "").toCharArray();
		for (int i = 0; i < (s + "").length(); i++)
		{
			String sim = smbls[i] + "";

			if (sim.equals("Þ"))
			{
				if (i + 1 < (s + "").length())
				{
					i += 1;
					Color c = Color.getColor(smbls[i] + "");
					Graph.colorize(c);
				}
			}
			else
			{
				drawSimbol(sim);
				GL11.glTranslated(64, 0, 0);
			}
		}
		Graph.clearColor();
		GL11.glPopMatrix();
	}

	public void drawSimbol(String s)
	{
		int id = 0;
		if (chars.containsKey(s))
		{
			id = chars.get(s);
		}
		else
			id = 127;

		int meta = Math.floorDiv(id, 16);
		int beta = Math.floorMod(id, 16);
		double sz = 1 / 16d;
		double x = beta * sz;
		double y = meta * sz;

		Tessellator t = Tessellator.instance;
		// Name
		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(0, 0, x, y);
		t.addVertexWithUV(64, 0, x + sz, y);
		t.addVertexWithUV(64, 64, x + sz, y + sz);
		t.addVertexWithUV(0, 64, x, y + sz);
		t.draw();
	}

	public void drawString(Object s, double x, double y, int size)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glScaled(0.125, 0.125, 1);
		GL11.glRotated(180, 1, 0, 0);
		Icon.getIcon("ascii").getTexture().bind();
		char[] smbls = (s + "").toCharArray();
		for (int i = 0; i < smbls.length; i++)
		{
			String sim = smbls[i] + "";

			if (sim.equals("Þ"))
			{
				if (i + 1 < (s + "").length())
				{
					i += 1;
					Color c = Color.getColor(smbls[i] + "");
					Graph.colorize(c);
				}
			}
			else
			{
				drawSimbol(sim, size);
				GL11.glTranslated(size * 4, 0, 0);
			}
		}
		Graph.clearColor();
		GL11.glPopMatrix();
	}

	public void drawString(String s, double x, double y, Color c, int size)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glScaled(0.125, 0.125, 1);
		GL11.glRotated(180, 1, 0, 0);
		Icon.getIcon("ascii").getTexture().bind();

		for (int i = 0; i < s.length(); i++)
		{
			String sim = s.toCharArray()[i] + "";

			drawSimbol(sim);
			GL11.glTranslated(size * 4, 0, 0);
		}
		GL11.glPopMatrix();
	}

	public void drawSimbol(String s, int size)
	{
		int id = 0;
		if (chars.containsKey(s))
		{
			id = chars.get(s);
		}
		else
			id = 127;

		int meta = Math.floorDiv(id, 16);
		int beta = Math.floorMod(id, 16);
		double sz = 1 / 16d;
		double x = beta * sz;
		double y = meta * sz;

		Tessellator t = Tessellator.instance;
		// Name
		t.start(GL11.GL_QUADS);
		t.addVertexWithUV(0, 0, x, y);
		t.addVertexWithUV(size * 4, 0, x + sz, y);
		t.addVertexWithUV(size * 4, size * 4, x + sz, y + sz);
		t.addVertexWithUV(0, size * 4, x, y + sz);
		t.draw();
	}

	public static int last = 0;
	public static Hashtable<String, Integer> chars = new Hashtable<String, Integer>();
}
