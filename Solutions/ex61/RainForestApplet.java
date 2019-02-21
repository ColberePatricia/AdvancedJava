import javax.swing.*;
import java.awt.*;

public class RainForestApplet extends JApplet {

	public void init() {

		// TO DO:  Add your code here
		MainPanel myMainPanel = new MainPanel();
		
		Container contentPane = this.getContentPane();
		contentPane.add(myMainPanel);
		
	}
}