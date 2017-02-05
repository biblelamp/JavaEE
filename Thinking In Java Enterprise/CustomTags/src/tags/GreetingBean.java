package tags;

public class GreetingBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	String greeting = null;
	String greetings[] = { "Hello!", "Howdy!", "Hi There!", "How do you do?" };

	public void setGreeting() {
		greeting = greetings[(int) (Math.random() * greetings.length)];
	}

	public String getGreeting() {
		return greeting;
	}
}