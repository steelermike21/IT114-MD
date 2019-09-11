
public class Session3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "beard";
		
		if(name.equals("beard")) {
			System.out.println("Beard is a good name");
		}
		else {
			System.out.println("You should reconsider it being beard");
		}
		
		int count =0;
		float floatcount =0.0f;
		float total =0f;
		
			for(int i=0; i<10;i++) {
				
				count++;
				floatcount+=.1f;
				
				
			}
			System.out.println("count = " + count);
			System.out.println("float count = " + floatcount);
			
			if(floatcount ==1) {
				System.out.println("Its actually 10!");
		}
	}

}
