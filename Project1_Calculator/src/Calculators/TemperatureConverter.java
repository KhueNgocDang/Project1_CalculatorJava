package Calculators;

public class TemperatureConverter {

	private int factor;
	
	public TemperatureConverter(String unit) 
	{
		switch(unit) 
		{
		case "Kelvin": factor = 2;break;
		case "Celsius": factor = 1;break;
		case "Fahrenheit": factor = 0;break;
		}
	}
	
	public double fromCelsius(double measurement) 
	{
		double fromC = 0;
		switch(factor) 
		{
		case 0: fromC = (5./9.)*(measurement-32);break;
		case 1: fromC = measurement;break;
		case 2: fromC = measurement-273.15;break;
		}
		return fromC;
	}
	
	public double toCelsius(double measurement) 
	{
		double toC = 0;
		switch(factor) 
		{
		case 0: toC = measurement*(9./5.)+32;break;
		case 1: toC = measurement;break;
		case 2: toC = measurement+273.15;break;
		}
		return toC;
	}
	
	public static double convert(double measurement, String fromUnit, String toUnit) 
	{
		TemperatureConverter from = new TemperatureConverter(fromUnit);
		TemperatureConverter to = new TemperatureConverter(toUnit);
		
		double Celsius = from.toCelsius(measurement);
		double converted = to.fromCelsius(Celsius);
		return converted;
	}
	
}
