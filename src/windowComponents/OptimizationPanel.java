package windowComponents;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class OptimizationPanel extends JPanel{
	private JButton optimize, left, equal, right;
	public OptimizationPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		optimize = new JButton("Optimize!");
		left = new JButton("Left");
		equal = new JButton("=");
		right = new JButton("Right");
		
		add(optimize, BorderLayout.PAGE_START);
		add(left, BorderLayout.LINE_START);
		add(equal, BorderLayout.CENTER);
		add(right, BorderLayout.LINE_END);
		
		optimize.setEnabled(false);
		left.setEnabled(false);
		equal.setEnabled(false);
		right.setEnabled(false);
	}
}