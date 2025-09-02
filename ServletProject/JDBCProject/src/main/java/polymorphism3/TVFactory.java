package polymorphism3;

public class TVFactory {

	public TV getTV(String tvName) {
		if(tvName.equals("lg")) {
			return new LgTV();
		} else if(tvName.equals("samsung")) {
			return new SamsungTV();
		}
		return null;
	}
}
