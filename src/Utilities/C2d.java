package Utilities;

public class C2d
{
	public C2d(float f, float g)
	{
		this.x = f;
		this.y = g;
	}

	public C2d(C2d copy)
	{
		this.x = copy.x;
		this.y = copy.y;
	}

	public float x;
	public float y;

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString()
	{
		return "{" + this.x + ";" + this.y + "}";
	}

	public void setx(float x)
	{
		this.x = x;
	}

	public C2d add(float x, float y)
	{
		C2d ret = new C2d(this.x + x, this.y + y);
		return ret;
	}

	public C2d substuct(float x, float y)
	{
		C2d ret = new C2d(this.x - x, this.y - y);
		return ret;
	}

	public C2d add(C2d what)
	{
		C2d ret = new C2d(this.x + what.x, this.y + what.y);
		return ret;
	}

	public C2d substuct(C2d what)
	{
		C2d ret = new C2d(this.x - what.x, this.y - what.y);
		return ret;
	}

	public void sety(int y)
	{
		this.y = y;
	}

	/**
	 * Puts coords into limit
	 * 
	 * @param xmin
	 * @param ymin
	 * @param xmax
	 * @param ymax
	 */
	public void limitize(float xmin, float ymin, float xmax, float ymax)
	{
		this.x = Utils.limit(this.x, xmin, xmax);
		this.y = Utils.limit(this.y, ymin, ymax);
	}

	/**
	 * Easier version of limitize
	 * 
	 * @param xmax
	 *            // * @param ymax
	 */
	public void limitize(float xmax, float ymax)
	{
		this.x = Utils.limit(this.x, 0, xmax);
		this.y = Utils.limit(this.y, 0, ymax);
	}
}
