import java.util.Date;

public class MyFirstClass {
	public int myPublicInt;
	private int myPrivateInt;
	protected int myProtectedInt;
	
	public String myString;
	public char myChar;
	public Date myDate; 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		MyFirstClass myFirstClassObject = new MyFirstClass();
		
		myFirstClassObject.myPublicInt = 1;
		System.out.println(myFirstClassObject.myPublicInt);
	}

}
