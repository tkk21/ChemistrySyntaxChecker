package chemistry;

public class ElementBuilder {

	private StringBuilder builder;
	
	public static final int MAX_LENGTH = 3;
	
	public ElementBuilder(){
		builder = new StringBuilder();
	}
	
	public void append(char ch){
		if (builder.length() == 0){
			
		}
	}
	
	private void firstLetter(char c){
		if (Character.isUpperCase(c)){
			builder.append(c);
		}
	}
}
