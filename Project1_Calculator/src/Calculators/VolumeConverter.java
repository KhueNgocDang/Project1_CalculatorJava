package Calculators;

public class VolumeConverter {
	private double inch_to_meter = 0.0254;
	private double foot_to_meter = 0.3048;
	private double mile_to_meter = 1609.344;
	private double naticamiles_to_meter = 1852;
	private double ml_to_liter = 0.001;
	private double cubcm_to_liter = 0.001;
	private double teaspUS_to_liter = 0.00492892;
	private double cm_to_meter = 0.01;
	private double km_to_meter = 1000;
	private double yd_to_meter = 0.9144;
	
	private double factor;
	
	public VolumeConverter(String unit) 
	{
		switch(unit) 
		{
		case "in": factor = inch_to_meter;break;
		case "ft": factor = foot_to_meter;break;
		case "mi": factor = mile_to_meter;break;
		case "mm": factor = mm_to_meter;break;
		case "cm": factor = cm_to_meter;break;
		case "km": factor = km_to_meter;break;
		case "yd": factor = yd_to_meter;break;
		case "nm": factor = nm_to_meter;break;
		case "mc": factor = mc_to_meter;break;
		case "na": factor = naticamiles_to_meter;break;
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
        System.out.println(VolumeConverter.convert(20,"in","yd")); 
        //System.out.println(EvaluateString.Eval("100.8^9 * ( 2 + 12 )")); 
       // System.out.println(EvaluateString.Eval("100 * ( 2 + 12 ) / 14")); 
    } 
}
