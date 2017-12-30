package Utilities;

import org.lwjgl.opengl.GL11;

import Graphics.Icon;

public class Tessellator
{
	public static boolean drawing = false;

	public static Tessellator instance = new Tessellator();

	public void start(int type)
	{
		if (!drawing)
		{
			GL11.glBegin(type);
			drawing = true;
		}
	}

	public void setVertexColor(double x, double y, Color c)
	{
		if (drawing)
		{
		}
	}

	public void addVertexWithUV(double x, double y, double u, double v)
	{
		if (drawing)
		{
			GL11.glTexCoord2d(u, v);
			GL11.glVertex2d(x, y);
		}
	}

	public void draw()
	{
		if (drawing)
		{
			GL11.glEnd();
			drawing = false;
		}
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h, Icon icon)
	{
		icon.getTexture().bind();
		this.start(GL11.GL_QUADS);

		double sx = icon.getTexture().getImageWidth();
		double sy = icon.getTexture().getImageHeight();

		this.addVertexWithUV(x, y + h, u / sx, v / sy);
		this.addVertexWithUV(x + w, y + h, (u + w) / sx, v / sy);
		this.addVertexWithUV(x + w, y, (u + w) / sx, (v + h) / sy);
		this.addVertexWithUV(x, y, u / sx, (v + h) / sy);

		this.draw();
	}
}
