import javax.swing.*;
import java.awt.*;


public class MusicPanel extends JPanel {

	public MusicPanel(MainFrame theParentFrame) {

		ImageIcon dukeIcon = new ImageIcon(getClass().getResource("duke_404.gif"));
		
		JLabel dukeLabel = new JLabel(dukeIcon);
		
		this.setLayout(new BorderLayout());
		this.add(dukeLabel);
		
		this.setBackground(Color.white);
		
	}
}