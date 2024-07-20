package personalprogram.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Store {

	private ArrayList<Game> gameItem = new ArrayList<>();
	protected ArrayList<Transaction> transactionList = new ArrayList<>();
	
	public Store(String configPath) throws FileNotFoundException {
		loadItems(configPath);
	}

	private void loadItems(String configPath) throws FileNotFoundException {
		// TODO Auto-generated method stub
		try {
		Scanner input = new Scanner(new File(configPath));
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			//int copies = Integer.parseInt(fields[0]);
			//int format = Integer.parseInt(fields[1]);
			//String name = fields[2];
			Game game = createGameFromFields(fields);
			if(game != null)
			{
				gameItem.add(game);
			}
			
			
		}
		input.close();
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private Game findItemByName(String itemName)
	{
		for (Game item : gameItem)
		{
			if(itemName.equalsIgnoreCase(item.getName()))
			{
				return item;
			}
		}
		return null;
	}
	private Game createGameFromFields(String[] fields)
	{
		int copies = Integer.parseInt(fields[0]);
		char type = Character.toUpperCase(fields[1].charAt(0));
		String name = fields[2];
		double price = Double.parseDouble(fields[3]);
		Game game = null;
		switch(type)
		{
		case '1':{
			game = new Game(copies, "Switch", name, price);
		}break;
		case '2':
		{
			game = new Game(copies, "PS4", name, price);
		}break;
		case '3':
		{
			game = new Game(copies, "Xbox", name, price);
		}break;
		default:
		{
			System.err.println("Invalid Game type:"+type);
			return null;
		}
		}
		
		return game;
	}
	public List<Game> getAllGames() {
		return Collections.unmodifiableList(gameItem);
	}
	public List<Transaction> getAllTransaction() {
		return Collections.unmodifiableList(transactionList);
	}
	
		
	
	
	public Game getGameItemByName(String itemName)
	{
		return findItemByName(itemName);
	}
	
	public ArrayList<Game> getGameItems()
	{
		return new ArrayList<Game>(gameItem);
	}
	 public List<String> getGameNames()
	    {
	        ArrayList<String> names = new ArrayList<>();
	        for (Game game : gameItem)
	        {
	            names.add(game.getName());
	        }
	        return names;
	    }
	 public List<String> getTransactionName()
	    {
	        ArrayList<String> names = new ArrayList<>();
	        for (Transaction game : transactionList)
	        {
	            names.add(game.getItemName());
	        }
	        return names;
	    }
	 public List<String> getGameFormat()
	    {
	        ArrayList<String> names = new ArrayList<>();
	        for (Game game : gameItem)
	        {
	            names.add(game.getFormat());
	        }
	        return names;
	    }
 
	 public void addItem(Game item) {
			transactionList.add(new Transaction(item));
	}
	public double getTransactionTotal() {
		double total = 0.0;
		for (Transaction game : transactionList) {
			total += game.getPrice();
		}
		
		return total;
	}
		
	
	public void displayCart()
	{
		double total =0;
		System.out.printf("%-30s%-20s%-15s\n","Name","Format","Price");
		for(Transaction t : transactionList)
		{
			System.out.printf("%-30s%-20s%-15.2f\n",t.getItemName(),t.getType());
			
		}
		System.out.printf("%-50s%-2.2f\n","Total:",total);
	}
	
	    
}
