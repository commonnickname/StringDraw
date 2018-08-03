package windowComponents;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class RunPanel extends JPanel{
	public JButton runpause;
	private JButton previousLine, nextLine, stop;
	
	public RunPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		runpause = new JButton("Run");
		previousLine = new JButton("<");
		nextLine = new JButton(">");
		stop = new JButton("Stop");
		
		add(runpause, BorderLayout.PAGE_START);
		add(previousLine, BorderLayout.LINE_START);
		add(stop, BorderLayout.CENTER);
		add(nextLine, BorderLayout.LINE_END);
		
		previousLine.setEnabled(false);
		stop.setEnabled(false);
		nextLine.setEnabled(false);
	}
}
