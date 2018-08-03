package mainWindow;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import data.CONST;
import utilDatatypes.CEnums.Mode;
import utils.ImageProcessor;
import utils.ParameterPackage;
import windowComponents.ControlPanel;
import windowComponents.DrawingPanel;
import windowComponents.MenuBar;
import windowComponents.ReferencePanel;


public class MainWindow extends JFrame{
	
	private MenuBar menuBar;
	private ControlPanel controlPanel;
	private JPanel leftPanel, rightPanel;
	private ReferencePanel referencePanel;
	private DrawingPanel leftDrawingPanel, rightDrawingPanel;
	private ParameterPackage leftParameters, rightParameters;
	
	private ImageProcessor imageProcessor;
	private Mode mode;
	private boolean imageLoaded;
	
	public MainWindow(){
		
		initWindow();
		
		setupComponents();
		setupAdditionalMenuListeners();
		setupAdditionalControlListeners();

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
		
		controlPanel = new ControlPanel(leftParameters, rightParameters);
		contentPanel.add(controlPanel);
		
		add(contentPanel);
	}
	
	private void setupAdditionalMenuListeners() {
		menuBar.openFileItem.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File(CONST.defaultPath));
				fileChooser.setDialogTitle("Open File...");
				
				if (fileChooser.showOpenDialog(menuBar.openFileItem) != JFileChooser.APPROVE_OPTION) return;
				System.gc();
				
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				imageProcessor = new ImageProcessor(filePath);
				imageLoaded = imageProcessor.imageProcessed;
				if (!imageLoaded) return;
					
				updateContentPanels();
				
		}});

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
	
	private void setupAdditionalControlListeners() {
		controlPanel.runPanel.runpause.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
			if (!imageLoaded) return;
			
			rightDrawingPanel.drawLines();
			if (mode == Mode.OPTIMIZATION) leftDrawingPanel.drawLines();
			
		}});
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
