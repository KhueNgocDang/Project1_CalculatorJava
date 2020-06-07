package Calculators;

public class MassConverter {

	private double carat_to_gram = 0.2;
	private double dekagram_to_gram = 10;
	private double hectogram_to_gram = 100;
	private double ounce_to_gram = 28.34952;
	private double pound_to_gram = 452.5924;
	private double decigram_to_gram = 0.1;
	private double gram_to_gram = 1;
	private double milligram_to_gram = 0.001;
	private double centigram_to_gram = 0.01;
	private double kilogram_to_gram = 1000;
	private double metrictones_to_gram = 1000000;
	
	private double factor;
	
	public MassConverter(String unit) 
	{
		switch(unit) 
		{
		case "Carats": factor = carat_to_gram;break;
		case "Milligrams": factor = milligram_to_gram;break;
		case "Grams": factor = gram_to_gram;break;
		case "Decigrams": factor = decigram_to_gram;break;
		case "Centigrams": factor = centigram_to_gram;break;
		case "Kilograms": factor = kilogram_to_gram;break;
		case "Dekagrams": factor = dekagram_to_gram;break;
		case "Hectograms": factor = hectogram_to_gram;break;
		case "Ounce": factor = ounce_to_gram;break;
		case "Pounds": factor = pound_to_gram;break;
		case "Metric tones": factor = metrictones_to_gram;break;
		}
	}
	
	public double fromgrams(double measurement) 
	{
		return (measurement/factor);
	}
	
	public double tograms(double measurement) 
	{
		return (measurement*factor);
	}
	
	public static double convert(double measurement, String fromUnit, String toUnit) 
	{
		MassConverter from = new MassConverter(fromUnit);
		MassConverter to = new MassConverter(toUnit);
		
		double grams = from.tograms(measurement);
		double converted = to.fromgrams(grams);
		return converted;
	}
	
}
