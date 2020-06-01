package Calculators;

public class VolumeConverter {
	private double teaspoon_to_liter = 0.00492892;
	private double tablespoon_to_liter = 0.0147868;
	private double fluidounce_to_liter = 0.0295735;
	private double cup_to_liter = 0.236588;
	private double pint_to_liter = 0.473176;
	private double quart_to_liter = 0.946353;
	private double gallon_to_liter = 3.785412;
	private double cubinch_to_liter = 0.0163871;
	private double cubfeet_to_liter = 28.31685;
	private double cubyard_to_liter = 764.5549;
	private double ml_to_liter = 0.001;
	private double cubm_to_liter = 0.001;
	
	private double factor;
	
	public VolumeConverter(String unit) 
	{
		switch(unit) 
		{
		case "Teaspoons": factor = teaspoon_to_liter;break;
		case "Tablespoons": factor = tablespoon_to_liter;break;
		case "Fluid ounces": factor = fluidounce_to_liter;break;
		case "Cups": factor = cup_to_liter;break;
		case "Pints": factor = pint_to_liter;break;
		case "Quarts": factor = quart_to_liter;break;
		case "Gallons": factor = gallon_to_liter;break;
		case "Cubic inches": factor = cubinch_to_liter;break;
		case "Cubic feet": factor = cubfeet_to_liter;break;
		case "Cubic yards": factor = cubyard_to_liter;break;
		case "Milliliter": factor = ml_to_liter;break;
		case "Cubic meters": factor = cubm_to_liter;break;
		case "Liters": factor = 1;
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
		VolumeConverter from = new VolumeConverter(fromUnit);
		VolumeConverter to = new VolumeConverter(toUnit);
		
		double meters = from.toMeters(measurement);
		double converted = to.fromMeters(meters);
		return converted;
	}
	public static void main(String[] args) 
    { 
		
      // System.out.println(EvaluateString.Eval("10.5 + 2 ^ 6")); 
       // System.out.print(EvaluateString.Eval(" 10.5+----2^6"));
        //System.out.print(EvaluateString.Eval(" 10.5+-(-2)^6"));
        System.out.println(VolumeConverter.convert(75,"Liters","Teaspoons")); 
        //System.out.println(EvaluateString.Eval("100.8^9 * ( 2 + 12 )")); 
       // System.out.println(EvaluateString.Eval("100 * ( 2 + 12 ) / 14")); 
    } 
}
