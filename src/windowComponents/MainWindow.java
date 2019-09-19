package windowComponents;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import data.CONST;
import utils.datatypes.CEnums.Mode;
import utils.helpers.ImageProcessor;
import utils.helpers.ParameterPackage;


public class MainWindow extends JFrame{
	
	private MenuBar menuBar;
	private ControlPanel controlPanel;
	private JPanel leftPanel, rightPanel;
	private ReferencePanel referencePanel;
	public DrawingPanel leftDrawingPanel, rightDrawingPanel;
	
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
		rightDrawingPanel = new DrawingPanel();
		rightPanel.add(rightDrawingPanel);
		contentPanel.add(rightPanel);
		
		controlPanel = new ControlPanel(this);
		contentPanel.add(controlPanel);
		
		add(contentPanel);
	}
	
	public void changeMode(Mode mode) {
		if (this.mode == mode) return;
		
		this.mode = mode;
		controlPanel.tuningPanelLeft.setEnabled(mode == Mode.OPTIMIZATION);
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
			leftDrawingPanel = new DrawingPanel();
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
		rightDrawingPanel = new DrawingPanel();
		rightPanel.add(rightDrawingPanel);
		rightDrawingPanel.initialize(imageProcessor.imgFloatValues);
		rightPanel.revalidate();
	}

	public ParameterPackage getParameterPackage(String string) {

		return controlPanel.getParameterPackage(string);
	}

	

}
