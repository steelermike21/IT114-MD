import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class session4Lists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> myStrings = new ArrayList<String>();
		String[] stringArray = new String[3];
		
		myStrings.add("Hi");
		myStrings.add("Bye");
		myStrings.add("Hi again");
		myStrings.add("And im still here");
		myStrings.add("See you tomorrow");
		
		//TODO make this list sort alphabetically 
		
		Collections.sort(myStrings);
		System.out.println(myStrings);
		
		
		//TODO reverse sort
		Collections.sort(myStrings, Collections.reverseOrder());
		System.out.println(myStrings);
		
		//TODO shuffle
		
		Collections.shuffle(myStrings);
		System.out.println(myStrings);
		
		//TODO create list of int, each index has a value of its index
		//sum and show result (size of 10)
		
		List<Integer> intList =new ArrayList<Integer>();
		
		for(int i =0; i <10; i++) {
			intList.add(i);
		}
		
		int total = 0;
		
		for(int i=0; i<intList.size(); i++) {
			total += intList.get(i);
		}
		
		System.out.println("My total:" + total);
		
		//TODO using the integer list, print the value and if its odd or even
		
		System.out.println(intList);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * stringArray[0]= "Hi"; stringArray[1]="Bye"; stringArray[2]="Hi Again"; //TODO
		 * Switch the values of the first and last element //Without assigning the hard
		 * coded string again for stringArray String temp = stringArray[2];
		 * stringArray[2] = stringArray[0]; stringArray[0] = temp;
		 * 
		 * int size = myStrings.size(); for(int i = 0; i<size; i++) { if(i==1) {
		 * myStrings.remove(i); }
		 * 
		 * System.out.println("Index[" + i + "] =>" + myStrings.get(i));
		 * //System.out.println("Array Index[" + i + "] => " + stringArray[i]); }
		 */

	}

}
