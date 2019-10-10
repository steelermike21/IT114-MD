package Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileIOAssignment {

	public void createFileAndGetDetails(String fileName) {
		try {
			File fileReference = new File(fileName);
			if(fileReference.createNewFile()) {
				System.out.println("File did not exist, creating new file");
			}
			else {
				System.out.println("File already exists");
			}
			System.out.println(fileName + " is located in the " + fileReference.getAbsolutePath() +" Directory");
			if(fileReference.canRead()) {
				System.out.println(fileName + " is a readable file");
			}
			else {
				System.out.println(fileName + " is not readable");
			}
			if(fileReference.canWrite()) {
				System.out.println(fileName + " is writable");
			}
			else {
				System.out.println(fileName + " is not writable");
			}
			if(fileReference.canExecute()) {
				System.out.println(fileName + " is executable");
			}
			else {
				System.out.println(fileName + " is not executable");
			}
		}
		catch(IOException ie) {
			ie.printStackTrace();
		
		}
	}
	public void writeToFile(String fileName, String msg) {
		//Hint: use BufferedWriter for less IO operations (better performance)
		try(FileWriter fw = new FileWriter(fileName)){
			fw.write(msg);
			System.out.println("Wrote " + msg + " to " + fileName);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readFromFile(String fileName) {
		File file = new File(fileName);
		try(Scanner reader = new Scanner(file)){
			String fullText = "";
			while (reader.hasNextLine()) {
				String nl = reader.nextLine();
				System.out.println("Next line: " + nl);
		        fullText += nl;
		        if(reader.hasNextLine()) {
		        	fullText += System.lineSeparator();
		        }
		    }
			System.out.println("Contents of " + fileName + ": ");
			System.out.println(fullText);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void appendToFile(String fileName, String msg) {
		try(FileWriter fw = new FileWriter(fileName, true);){
			fw.write(System.lineSeparator());
			fw.write(msg);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "test.txt";
		fileIOAssignment fss = new fileIOAssignment();
		fss.createFileAndGetDetails(fileName);
		fss.writeToFile(fileName, "Hello everyone! We're writing to files");
		fss.readFromFile(fileName);
		fss.appendToFile(fileName, "This text is appended to the file now!");
		fss.readFromFile(fileName);
		Scanner scan = new Scanner(System.in);
		System.out.println("How is your day 1-10?");
		String rate = scan.nextLine();
		System.out.println("User's day is " + rate + " adding to end of file");
		fss.appendToFile(fileName, rate + " Is the users day");
		fss.readFromFile(fileName);
		scan.close();
	}

	
	

}
