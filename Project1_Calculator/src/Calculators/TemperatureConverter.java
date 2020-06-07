package Calculators;

public class TemperatureConverter {

	private double kenvin_to_celsius = 0.0254;
	private double fahrenheit_to_celsius = 0.3048;
	
	private double factor;
	
	public TemperatureConverter(String unit) 
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
		TemperatureConverter from = new TemperatureConverter(fromUnit);
		TemperatureConverter to = new TemperatureConverter(toUnit);
		
		double meters = from.toMeters(measurement);
		double converted = to.fromMeters(meters);
		return converted;
	}
	
}
