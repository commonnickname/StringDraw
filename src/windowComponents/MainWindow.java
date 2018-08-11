package windowComponents;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import utils.datatypes.CEnums.Mode;
import utils.objects.ImageProcessor;
import utils.objects.ParameterPackage;


public class MainWindow extends JFrame{
	
	private MenuBar menuBar;
	private ControlPanel controlPanel;
	private JPanel leftPanel, rightPanel;
	private ReferencePanel referencePanel;
	public DrawingPanel leftDrawingPanel, rightDrawingPanel;
	private ParameterPackage leftParameters, rightParameters;
	
	public ImageProcessor imageProcessor;
	public Mode mode;
	public boolean imageLoaded;
	
	public MainWindow(){
		
		initWindow();
		
		setupComponents();

		pack();
		setVisible(true);
	}
	
	private void initWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("StringDraw");
		setResizable(false);
		
		leftParameters = new ParameterPackage();
		rightParameters = new ParameterPackage();
		mode = Mode.SINGLE;
		imageLoaded = false;
	}
	
	private void setupComponents() {
		JPanel contentPanel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		contentPanel.setLayout(layout);
		
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
		
		leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		referencePanel = new ReferencePanel();
		leftPanel.add(referencePanel);
		contentPanel.add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		rightDrawingPanel = new DrawingPanel(rightParameters);
		rightPanel.add(rightDrawingPanel);
		contentPanel.add(rightPanel);
		
		controlPanel = new ControlPanel(this, leftParameters, rightParameters);
		contentPanel.add(controlPanel);
		
		add(contentPanel);
	}
	
	public void changeMode(Mode m) {
		if (mode == m) return;
		
		if (m == Mode.SINGLE) {
			mode = Mode.SINGLE;
			controlPanel.tuningPanelLeft.setEnabled(false);
		}
		if (m == Mode.OPTIMIZATION) {
			mode = Mode.OPTIMIZATION;
			controlPanel.tuningPanelLeft.setEnabled(true);
		}
	
		updateLeftPanel();
	}
	
	public void updateContentPanels() {
		updateLeftPanel();
		updateRightPanel();
		
	}
	
	public void updateLeftPanel() {
		if (imageProcessor == null) return;
		leftPanel.removeAll();
		
		if (mode == Mode.SINGLE) {
			leftDrawingPanel = null;
			referencePanel = new ReferencePanel();
			leftPanel.add(referencePanel);
			referencePanel.drawNew(imageProcessor.displayImg);
			leftPanel.revalidate();
		}else if (mode == Mode.OPTIMIZATION) {
			referencePanel = null;
			leftDrawingPanel = new DrawingPanel(leftParameters);
			leftPanel.add(leftDrawingPanel);
			leftDrawingPanel.initialize(imageProcessor.imgFloatValues);
			leftPanel.revalidate();
		}
		
		leftPanel.revalidate();
	}
	
	public void updateRightPanel() {
		if (imageProcessor == null) return;
		rightPanel.removeAll();
		rightDrawingPanel = null;
		rightDrawingPanel = new DrawingPanel(rightParameters);
		rightPanel.add(rightDrawingPanel);
		rightDrawingPanel.initialize(imageProcessor.imgFloatValues);
		rightPanel.revalidate();
	}

	

}
