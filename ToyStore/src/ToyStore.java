//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.System.*;
import java.util.*;

public class ToyStore
{
	private ArrayList<Toy> toyList = new ArrayList<Toy>();

	public ToyStore()
	{

	}

	public void loadToys( String toys )
	{
		String[] s = toys.split(" ");
		for(int i = 0; i < s.length; i++)
		{
			if(getThatToy(s[i]) != null)
			{
				Toy found = toyList.get(toyList.indexOf(getThatToy(s[i])));
				toyList.get(toyList.indexOf(getThatToy(s[i]))).setCount(found.getCount() + 1);
			}
			else
			{
				Toy nextToy = new Toy(s[i]);
				nextToy.setCount(1);
				toyList.add(nextToy);
			}
		}
	}
  
  	public Toy getThatToy( String nm )
  	{
  		for(int i =0 ; i < toyList.size(); i++)
		{
			if(toyList.get(i).getName().equals(nm))
				return toyList.get(i);
		}
  		return null;
  	}
  
  	public String getMostFrequentToy()
  	{
  		Toy mt = toyList.get(0);
  		for(int i = 0 ; i < toyList.size(); i++)
  		{
  			if(toyList.get(i).getCount() > mt.getCount())
				mt = toyList.get(i);
		}
  		return mt.getName();
  	}  
  
  	public void sortToysByCount()
  	{
		Collections.sort(toyList, new Comparator<Toy>() {
			@Override
			public int compare(Toy o1, Toy o2) {
				return o1.getCount() - o2.getCount();
			}
		});
  	}

	public String toString()
	{
		String repr = "";
		for(int i = 0; i <toyList.size(); i++)
		{
			Toy t = toyList.get(i);
			repr += t.getName() +  " ( " + t.getCount() + " ) \n";
		}
	    return repr;
	}
}