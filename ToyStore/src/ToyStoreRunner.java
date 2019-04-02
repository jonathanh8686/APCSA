//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.System.*;

public class ToyStoreRunner
{
	public static void main( String args[] )
	{
		ToyStore ts = new ToyStore();
		Scanner scanner = new Scanner(System.in);
		ts.loadToys(scanner.nextLine());

		System.out.println(ts);

		ts.sortToysByCount();

		System.out.println(ts);
	}
}