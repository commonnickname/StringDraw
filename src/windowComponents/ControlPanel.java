package windowComponents;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import data.CONST;
import utils.helpers.ParameterPackage;
import windowComponents.controlComponents.AlgorithmSelectionPanel;
import windowComponents.controlComponents.OptimizationPanel;
import windowComponents.controlComponents.RunPanel;
import windowComponents.controlComponents.TuningPanel;

public class ControlPanel extends JPanel{
	public RunPanel runPanel;
	private OptimizationPanel optimizationPanel;
	private AlgorithmSelectionPanel algorithmSelectionPanel;
	public TuningPanel tuningPanelLeft, tuningPanelRight;
	
	public ControlPanel(MainWindow parentFrame) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(CONST.CONTROL_W, CONST.FRAME_H + 15));

		runPanel = new RunPanel(parentFrame);
		optimizationPanel = new OptimizationPanel();
		algorithmSelectionPanel = new AlgorithmSelectionPanel();
		tuningPanelLeft = new TuningPanel("Left", -1);
		tuningPanelRight = new TuningPanel("Right", 1);
		
		tuningPanelLeft.setEnabled(false);
		tuningPanelRight.setEnabled(true);
		
		add(runPanel);
		add(optimizationPanel);
		add(algorithmSelectionPanel);
		add(tuningPanelLeft);
		add(tuningPanelRight);
	}

	public ParameterPackage getParameterPackage(String string) {
		ParameterPackage p = null;
		if (string.equals("Right")) p =  tuningPanelRight.getParameterPackage();
		else if (string.equals("Left")) p =  tuningPanelLeft.getParameterPackage();

		if (p != null) {
			p.setKey(CONST.algKey);
			p.setStrategy(CONST.algStrategy);
		}
		
		return p;
	}
}
