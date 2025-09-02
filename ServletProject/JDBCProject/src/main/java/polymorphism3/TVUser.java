package polymorphism3;

public class TVUser {
	public static void main(String[] args) {
		TVFactory factory = new TVFactory();
		
		TV tv = factory.getTV(args[0]);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
