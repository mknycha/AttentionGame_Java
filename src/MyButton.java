import javax.swing.JButton;

public class MyButton extends JButton {
	private boolean isValid;
	
	MyButton(String text, boolean value) {
		super(text);
		isValid = value;
	}
	
	public void setValue(boolean value) {
		isValid = value;
	}
	
	public boolean getValue() {
		return isValid;
	}
}
