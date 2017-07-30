package Utilities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Utils
{
	public static Random r = new Random();

	public static double distance(double x, double y, double z)
	{
		return Math.sqrt(sqr(x) + sqr(y) + sqr(z));
	}

	public static double distance(double x, double y, double z, double i, double j, double k)
	{
		return Math.sqrt(sqr(x - i) + sqr(y - j) + sqr(z - k));
	}

	public static double distance(double x, double y)
	{
		return Math.sqrt(sqr(x) + sqr(y));
	}

	public static double distance(double x, double y, double i, double j)
	{
		return Math.sqrt(sqr(x - i) + sqr(y - j));
	}

	public static double sqr(double x)
	{
		return x * x;
	}

	public static Hashtable<String, String> sim = new Hashtable<String, String>();

	@SuppressWarnings("unchecked")
	public static <T1> List<T1> lst(T1... val)
	{
		List<T1> lst = new ArrayList<T1>();
		if (val != null)
		{
			for (int i = 0; i < val.length; i++)
			{
				lst.add(val[i]);
			}
		}
		return lst;
	}

	public static int boolToNum(boolean b)
	{
		if (b)
			return 1;
		else
			return -1;
	}

	public static int boolToNum(boolean b, int tr, int fl)
	{
		if (b)
			return tr;
		else
			return fl;
	}

	public static double boolToNum(boolean b, double tr, double fl)
	{
		if (b)
			return tr;
		else
			return fl;
	}

	public static double boolToNum(double tr, double fl)
	{
		if (r.nextBoolean())
			return tr;
		else
			return fl;
	}

	public static int boolToNum(int tr, int fl)
	{
		if (r.nextBoolean())
			return tr;
		else
			return fl;
	}

	public static int boolToNum()
	{
		boolean b = r.nextBoolean();
		if (b)
			return 1;
		else
			return -1;
	}

	public static double getNormDbl()
	{
		return r.nextDouble() * boolToNum();
	}

	public static int getIntInRange(int a, int b)
	{
		if (b > a)
		{
			int raw = r.nextInt(b - a);
			return a + raw;
		}
		else
			if (a > b)
			{
				int raw = r.nextInt(a - b);
				return b + raw;
			}
			else
				return a;
	}

	public static boolean randWPercent(double percent)
	{
		double ret = r.nextDouble();
		return ret <= percent / 100d;
	}

	public static Vec2 getPosByAngle(double angle, double dist)
	{
		double rY = Math.sin(angle) * dist;

		double rX = Math.cos(angle) * dist;

		return new Vec2(rX, rY);
	}

	// public static Pos getPosBy3DAngle(Angle3D angle, double dist)
	// {
	// double rY = Math.sin(angle.pitch) * dist;
	// double mod = Math.cos(angle.pitch) * dist;
	//
	// double rX = Math.cos(angle.yaw) * mod;
	// double rZ = Math.sin(angle.yaw) * mod;
	//
	// return new Pos(rX, rY, rZ);
	// }

	// public static Angle3D get3DAngleByPos(Pos p)
	// {
	// double mod = Math.sqrt(p.x * p.x + p.z * p.z);
	// double pitch = 0, yaw = 0;
	//
	// pitch = Math.atan2(p.y, mod);
	//
	// yaw = Math.atan2(p.z, p.x);
	//
	// return new Angle3D(yaw, pitch);
	// }
	//
	// public static Angle3D get3DAngleByPos(double x, double y, double z)
	// {
	// double mod = Math.sqrt(x * x + z * z);
	// double pitch = 0, yaw = 0;
	//
	// pitch = Math.atan2(y, mod);
	// yaw = Math.asin(z / mod);
	//
	// return new Angle3D(yaw, pitch);
	// }

	public static ArrayList<Double> resolveQuadricEquation(double a, double b, double c)
	{
		ArrayList<Double> ret = new ArrayList<Double>();

		double D = b * b - 4 * a * c;

		ret.add((-b - Math.sqrt(D)) / a);
		ret.add((-b + Math.sqrt(D)) / a);

		return ret;
	}

	public static double round(double a, double precis)
	{
		return (Math.round(a * Math.pow(10, precis))) / Math.pow(10, precis);
	}

	public static double derivPart(Function f, double... ds)
	{
		if (ds.length == 1)
		{
			double d = 0.0000001;
			return (f.count(ds[0] + d) - f.count(ds[0])) / d;
		}
		if (ds.length == 2)
		{
			double d = 0.0000001;
			return (f.count(ds[0] + d, ds[1] + d) - f.count(ds[0], ds[1])) / (d * d);
		}
		return 0;
	}

	public static double integral(double a, double b, Function f)
	{
		// How many times
		double type = (b - a) * 1000;
		double min = (b - a) / type;

		double sum = 0;

		for (int i = 0; i < type; i++)
		{
			sum += f.count(a + min * i + min / 2) * min;
		}
		return round(sum, 5);
	}

	public static double integral2(double a, double b, double c, double d, Function f)
	{
		// How many times
		double type1 = (b - a) * 1000;
		double type2 = (d - c) * 1000;

		double min = 1 / 1000d;

		double sum = 0;

		for (int i = 0; i < type1; i++)
		{
			for (int j = 0; j < type2; j++)
			{
				sum += f.count(a + min * i + min / 2, c + min * i + min / 2) * min * min;
			}
		}
		return round(sum, 5);
	}

	public static double integral3(double a, double b, double c, double d, double e, double g, Function f)
	{
		// How many times
		double type1 = (b - a) * 1000;
		double type2 = (d - c) * 1000;
		double type3 = (g - e) * 1000;

		double min = 1 / 1000d;

		double sum = 0;

		for (int i = 0; i < type1; i++)
		{
			for (int j = 0; j < type2; j++)
			{
				for (int k = 0; k < type3; k++)
				{
					sum += f.count(a + min * i + min / 2, c + min * i + min / 2, e + min * i + min / 2) * min * min * min;
				}
			}
		}
		return round(sum, 5);
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param f
	 *            : Function
	 * @param precis
	 *            : max = 19; maximal precision at 15
	 * @return
	 */
	public static double integral(double a, double b, Function f, int precis)
	{
		// How many times
		double type = (b - a) * 1000;
		double min = (b - a) / type;

		double sum = 0;

		for (long i = 0; i < type; i++)
		{
			double fu;
			try
			{
				fu = f.count(a + min * i + min / 2);
			}
			catch (Exception e)
			{
				fu = 0;
			}
			sum += fu * min;

		}
		return round(sum, precis);
	}

	public double arraySum(double[] arr, int lastInd)
	{
		double ret = 0;
		if (lastInd != 0)
			for (int i = 0; i < lastInd; i++)
			{
				ret += arr[i];
			}

		return ret;
	}

	public double arraySum(double[] arr)
	{
		double ret = 0;
		for (int i = 0; i < arr.length; i++)
		{
			ret += arr[i];
		}

		return ret;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static int limit(int a, int min, int max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Puts vector in the limits of AABB
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static Vec2 limit(Vec2 v, AABB lim)
	{
		Vec2 vv = v.copy();
		if (vv.x > lim.tx)
		{
			vv.x = lim.tx;
		}
		if (vv.x < lim.ox)
		{
			vv.x = lim.ox;
		}
		
		if (vv.y > lim.ty)
		{
			vv.y = lim.ty;
		}
		if (vv.y < lim.oy)
		{
			vv.y = lim.oy;
		}
		
		return vv;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static float limit(float a, float min, float max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Puts number in the limits
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static double limit(double a, double min, double max)
	{
		if (a > max)
		{
			a = max;
		}
		if (a < min)
		{
			a = min;
		}
		return a;
	}

	/**
	 * Checks if number lower then max and larger then min
	 * 
	 * @param a
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInLimit(double a, double min, double max)
	{
		if (a <= max && a >= min)
		{
			return true;
		}

		return false;
	}

	/**
	 * Gets first not occupied slot for the given array
	 * 
	 * @param array
	 * @return
	 */
	public static int getFNOS(Object[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	public static Object[] expandArray(Object[] array)
	{
		Object[] ret = new Object[array.length + 1];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static Object[] expandArray(Object[] array, int length)
	{
		Object[] ret = new Object[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static float[] expandArray(float[] array, int length)
	{
		float[] ret = new float[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static boolean[] expandArray(boolean[] array, int length)
	{
		boolean[] ret = new boolean[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static String[] expandArray(String[] array, int length)
	{
		String[] ret = new String[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static int[] expandArray(int[] array, int length)
	{
		int[] ret = new int[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static double getDistance(C2d p1, C2d p2)
	{
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}

	public static int rgbToHex(int i, int j, int k)
	{
		int hex = ((short) i << 16) | ((short) j << 8) | ((short) k << 0);
		return hex;
	}

	public static int rgbToHex(Color c)
	{
		int hex = (c.red << 16) | (c.green << 8) | (c.blue << 0);
		return hex;
	}

	public static Color hexToRGB(int hex)
	{
		int r = ((int) (hex >> 24));
		int g = ((int) (hex >> 16));
		int b = ((int) (hex >> 8));
		int a = ((int) (hex >> 0));
		return new Color(r, g, b, a);
	}

	public static Hashtable<String, String> cutWithNameStart(Hashtable<String, String> tag, String start)
	{
		Hashtable<String, String> nev = new Hashtable<String, String>();
		Enumeration<String> en = tag.keys();
		while (en.hasMoreElements())
		{
			String key = en.nextElement();
			if (key.startsWith(start))
			{
				nev.put(key, tag.get(key));
			}
		}

		return nev;
	}

	/**
	 * In perspective it should cut names without 'start' string
	 * 
	 * @param tag
	 * @param start
	 * @return
	 */
	public static Hashtable<String, String> cutnames(Hashtable<String, String> tag, String start)
	{
		Hashtable<String, String> nev = new Hashtable<String, String>();
		Enumeration<String> en = tag.keys();
		while (en.hasMoreElements())
		{
			String key = en.nextElement();
			if (key.startsWith(start))
			{
				nev.put(key.substring(start.length()), tag.get(key));
			}
			else
				return tag;
		}

		return nev;
	}

}
