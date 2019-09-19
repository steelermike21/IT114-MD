package HW1Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class listClass {

static Random rand =new Random();

	
	  //trying to get this code to work but could not 
	/*
	 * public void knuth (ArrayList<String> arrayList) { for(int i=0;
	 * i<arrayList.size();i++) { int randomNum=rand.nextInt(arrayList.size());
	 * String temp = arrayList<i>; arrayList<i>=arrayList<randomNum>;
	 * arrayList<randomNum>=temp;
	 */
	  
	  
	  
	 
    

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		stringList.add("Eel");
		stringList.add("Cat");
		stringList.add("Dog");
		stringList.add("Fish");
		stringList.add("Lizard");
		stringList.add("Ant");
		
		//Reverse order of list
		
		Collections.sort(stringList, Collections.reverseOrder());
		System.out.println(stringList);
		
		//Shuffle the list
		
		Collections.shuffle(stringList);
		System.out.println(stringList);
		
		//TODO create list of int, each index has a value of its index
		//sum and show result (size of 10)

		List<Integer> intList =new ArrayList<Integer>();
		
		//puts numbers up to index 9 
		for(int i =0; i <10; i++) {
			intList.add(i);
		}
		
		//prints list after being filled with numbers
		
		System.out.println(intList);
		
		// enhanced for loop to check if each number is 
		//odd or even by using modulus 
		// if it ==0 it is even, else it is odd
		for(int i: intList) {
			if(i%2 ==0 ) {
			
				System.out.println(i + " Is an even number ");
			}
			else {
				System.out.println(i + " Is an odd number");
			}
		}
		
		int total = 0;
		//finds sum of list
		for(int i=0; i<intList.size(); i++) {
			total += intList.get(i);
		}
		//prints out
		System.out.println("The total of all the numbers in intList is: " + total);
		
		
		int n = stringList.size();
        for (int i = 0; i < n; i++) {
      
            int num = (int) (Math.random() * (i + 1));
            Object swap = stringList.get(num);
            stringList.set(num, stringList.get(i));
            stringList.set(i, (String)swap);
        }
        
        System.out.println(stringList);

	}
}

