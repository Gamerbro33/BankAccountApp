package personalprogram.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Transaction {
	
	private Game game;
	private double total;
	
	
	public Transaction(Game game)
	{
		this.game = game;
		
	}
	
	public String getItemName()
	{
		return game.getName();
	}
	
	public String getType()
	{
		return game.getFormat();
	}
	
	public double getPrice()
	{
		return game.getPrice();
	}
}
