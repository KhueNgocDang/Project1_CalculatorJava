package Calculators;

public class LengthConverter {

	private double inch_to_meter = 0.0254;
	private double foot_to_meter = 0.3048;
	private double mile_to_meter = 1609.344;
	private double naticamiles_to_meter = 1852;
	private double nm_to_meter = 0.000000001;
	private double mc_to_meter = 0.000001;
	private double mm_to_meter = 0.001;
	private double cm_to_meter = 0.01;
	private double km_to_meter = 1000;
	private double yd_to_meter = 0.9144;
	
	private double factor;
	
	public LengthConverter(String unit) 
	{
		switch(unit) 
		{
		case "Inches": factor = inch_to_meter;break;
		case "Feet": factor = foot_to_meter;break;
		case "Miles": factor = mile_to_meter;break;
		case "Millimeters": factor = mm_to_meter;break;
		case "Centimeters": factor = cm_to_meter;break;
		case "Kilometers": factor = km_to_meter;break;
		case "Yards": factor = yd_to_meter;break;
		case "Nanometers": factor = nm_to_meter;break;
		case "Microns": factor = mc_to_meter;break;
		case "Nautical Miles": factor = naticamiles_to_meter;break;
		}
	}
	
	public double fromMeters(double measurement) 
	{
		return (measurement/factor);
	}
	
	public double toMeters(double measurement) 
	{
		return (measurement*factor);
	}
	
	public static double convert(double measurement, String fromUnit, String toUnit) 
	{
		LengthConverter from = new LengthConverter(fromUnit);
		LengthConverter to = new LengthConverter(toUnit);
		
		double meters = from.toMeters(measurement);
		double converted = to.fromMeters(meters);
		return converted;
	}
	public static void main(String[] args) 
    { 
		
      // System.out.println(EvaluateString.Eval("10.5 + 2 ^ 6")); 
       // System.out.print(EvaluateString.Eval(" 10.5+----2^6"));
        //System.out.print(EvaluateString.Eval(" 10.5+-(-2)^6"));
        System.out.println(LengthConverter.convert(20,"in","yd")); 
        //System.out.println(EvaluateString.Eval("100.8^9 * ( 2 + 12 )")); 
       // System.out.println(EvaluateString.Eval("100 * ( 2 + 12 ) / 14")); 
    } 
}
