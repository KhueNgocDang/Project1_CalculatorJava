package Calculators;

public class EnergyConverter {

	private double volts_to_joules = 0.2;
	private double kilojoules_to_joules = 10;
	private double Tcalories_to_joules = 0.1;
	private double joules_to_joules = 1;
	private double Fcalories_to_joules = 0.001;
	private double Footpounds_to_joules = 0.01;
	private double British_thermal_units_to_joules = 1000;
	
	private double factor;
	
	public EnergyConverter(String unit) 
	{
		switch(unit) 
		{
		case "Electron volts": factor = volts_to_joules;break;
		case "Joules": factor = joules_to_joules;break;
		case "Kilojoules": factor = kilojoules_to_joules;break;
		case "Thermal calories": factor = Tcalories_to_joules;break;
		case "Food calories": factor = Fcalories_to_joules;break;
		case "Foot-pounds": factor = Footpounds_to_joules;break;
		case "British thermal units": factor = British_thermal_units_to_joules;break;
		}
	}
	
	public double fromJoules(double measurement) 
	{
		return (measurement/factor);
	}
	
	public double toJoules(double measurement) 
	{
		return (measurement*factor);
	}
	
	public static double convert(double measurement, String fromUnit, String toUnit) 
	{
		EnergyConverter from = new EnergyConverter(fromUnit);
		EnergyConverter to = new EnergyConverter(toUnit);
		
		double joules = from.toJoules(measurement);
		double converted = to.fromJoules(joules);
		return converted;
	}
	
}
