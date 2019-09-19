package windowComponents.controlComponents;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import data.CONST;
import utils.datatypes.CEnums.Mode;
import windowComponents.MainWindow;

public class RunPanel extends JPanel{
	public JButton runpause;
	private JButton previousLine, nextLine, stop;
	private MainWindow mW;
	
	public RunPanel(MainWindow parentFrame) {
		mW = parentFrame;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		setupButtons();
		setupListeners();
	}

	private void setupButtons() {
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
	
	private void setupListeners() {
		runpause.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
			if (!mW.imageLoaded) return;

			mW.rightDrawingPanel.drawLines(mW.getParameterPackage("Right"));
			
			if (mW.mode == Mode.OPTIMIZATION) {
				mW.leftDrawingPanel.drawLines(mW.getParameterPackage("Left"));
			}
				
		}});
	}
	
}
