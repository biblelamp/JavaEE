package tags;

public class GreetingBean {
	String greetings[] = { "Hello!", "Howdy!", "Hi There!", "How do you do?" };

	public String getGreeting() {
		return greetings[(int) (Math.random() * greetings.length)];
	}
}