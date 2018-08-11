package windowComponents;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import data.CONST;
import utils.objects.ParameterPackage;
import windowComponents.controlComponents.OptimizationPanel;
import windowComponents.controlComponents.RunPanel;
import windowComponents.controlComponents.TuningPanel;

public class ControlPanel extends JPanel{
	public RunPanel runPanel;
	private OptimizationPanel optimizationPanel;
	public TuningPanel tuningPanelLeft, tuningPanelRight;
	
	public ControlPanel(MainWindow parentFrame, ParameterPackage parametersLeft, ParameterPackage parametersRight) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(CONST.CONTROL_W, CONST.FRAME_H + 15));

		runPanel = new RunPanel(parentFrame);
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
