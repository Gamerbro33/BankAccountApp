package personalprogram.model;

public class Game {
	private int copies;
	private String format;
	private String name;
	private double price;
	
	public Game(int copies, String string, String name, double price)
	{
		this.copies = copies;
		this.format = string;
		this.name = name;
		this.price = price;
	}
	public int subCopies()	
	{  	
		--copies;
		
		return copies;
	}
	public int getCopies()
	{ 
		
		return copies;
		
	}
	
	public String getFormat()
	{
		return format;
	}
	
	public String getName()
	{
		return name;
	}
	public double getPrice() {
		return price;
	}
	public double getTotal() {
		double total= 0.0;
		total += price;
		
		return total;
	}

	
}
