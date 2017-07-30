package Utilities;

public class Constants
{
	public static final String pathBase = System.getProperty("user.dir");

	public static final double Kelvin = 273.15D;

	public static double To_K(double celsium)
	{
		return celsium + Kelvin;
	}

	public static double To_C(double kelvins)
	{
		return kelvins - Kelvin;
	}

	/**
	 * Constant equals 6.67E-11 can be used in Physical calculations
	 */
	public static final double GravConst = 6.67E-11;// m^3/(kg*s^2)

	public static final double magnetConst = 4 * Math.PI * 10e-7;//

	public static final double lightSpeed = 299792458;// m/s

	public static final double electrConst = 1 / (magnetConst * lightSpeed * lightSpeed);//

	public static final double AvogadroConst = 6.02E23;// mol^-1

	public static final double eCharge = 1.6021766208e-19;// Kl
	public static final double eMass = 0.5109989461D;

	public static final double atomMUnit = 1e-3 / AvogadroConst;// kg

}
