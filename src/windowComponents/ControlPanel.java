package windowComponents;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import data.CONST;
import utils.ParameterPackage;

public class ControlPanel extends JPanel{
	public RunPanel runPanel;
	private OptimizationPanel optimizationPanel;
	public TuningPanel tuningPanelLeft, tuningPanelRight;
	
	public ControlPanel(ParameterPackage parametersLeft, ParameterPackage parametersRight) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(CONST.CONTROL_W, CONST.FRAME_H + 15));

		runPanel = new RunPanel();
		optimizationPanel = new OptimizationPanel();
		tuningPanelLeft = new TuningPanel(parametersLeft, "Left", -1);
		tuningPanelRight = new TuningPanel(parametersRight, "Right", 1);
		
		tuningPanelLeft.setEnabled(false);
		tuningPanelRight.setEnabled(true);
		
		add(runPanel);
		add(optimizationPanel);
		add(tuningPanelLeft);
		add(tuningPanelRight);
	}
}
