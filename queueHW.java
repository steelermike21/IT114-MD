package Session5Queues;

import java.util.LinkedList;
import java.util.Queue;

// class to give queue some output
class NSPair{
	public int number;
	public String string;
	public NSPair(int k, String v) {
		this.number = k;
		this.string = v;
}
	// to string for easier output
	@Override
	public String toString() {
		return "{'Number':'" + this.number + "', 'String':'" + this.string + "'}";
	}
	
}


public class queueHW {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creates queue and fills it with data
		Queue<NSPair> q =  new LinkedList<NSPair>();
		for(int i=10; i >0 ; i--) {
			q.add(new NSPair(i, "Priority"));
		}
		
		System.out.println("The current queue: " +q);
		//Polling example
		NSPair polling = q.poll();
		System.out.println("The first value is" + polling + ", It is now removed from the queue");;
		
		System.out.println("The new queue is " + q);
		//Size example
		System.out.println("The current size of the queue is " + q.size());
		
		//Peeking Example
		NSPair peeking = q.peek();
		System.out.println("Taking a peek at the first value " + peeking);
		System.out.println("Peeking does not remove the item so the queue is still" + q);
		
		//Removing another index example
		NSPair removing = q.remove();
		System.out.println("The next one out of the queue is " + removing + " This will not be put back in the queue");
		System.out.println("The new queue is " + q);
	}

}
