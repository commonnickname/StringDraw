package windowComponents;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import algorithms.concrete.GlobalDelta;
import algorithms.concrete.GlobalNaive;
import algorithms.concrete.LocalDelta;
import algorithms.concrete.LocalNaive;
import data.CONST;
import utils.datatypes.CEnums.Mode;
import utils.objects.ImageProcessor;

public class MenuBar extends JMenuBar {
	private JMenu fileCategory, settingsCategory, selectAlgorithm, selectMode;
	public JMenuItem openFileItem, graphicalSettings;
	public JRadioButtonMenuItem localNaive, globalNaive, 
								localDelta, globalDelta,
								singleMode, optimizationMode;
	private MainWindow mW;
	
	public MenuBar(MainWindow parentFrame) {
		this.mW = parentFrame;
		
		instantiateFileCategory();
		instantiateSettingsCategory();
	}

	private void instantiateFileCategory() {
		fileCategory = new JMenu("File");
		add(fileCategory);
		openFileItem = new JMenuItem("Open File...");
		fileCategory.add(openFileItem);
		
		instantiateFileSelectionListener();
	}
	
	private void instantiateFileSelectionListener() {
		openFileItem.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File(CONST.defaultPath));
				fileChooser.setDialogTitle("Open File...");
				
				if (fileChooser.showOpenDialog(openFileItem) != JFileChooser.APPROVE_OPTION) return;
				System.gc();
				
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				mW.imageProcessor = new ImageProcessor(filePath);
				mW.imageLoaded = mW.imageProcessor.imageProcessed;
				if (!mW.imageLoaded) return;
					
				mW.updateContentPanels();
		}});
	}
	
	private void instantiateSettingsCategory() {
		settingsCategory = new JMenu("Settings");
		add(settingsCategory);
		graphicalSettings = new JMenuItem("Graphics");
		settingsCategory.add(graphicalSettings);
		
		instantiateAlgorithmSelection();
		instantiateAlgorithmSelectionListeners();
		instantiateModeSelection();
		instantiateModeSelectionListeners();
	}
	
	private void instantiateAlgorithmSelection() {
		selectAlgorithm = new JMenu("Select Algorithm");
		localNaive = new JRadioButtonMenuItem(LocalNaive.name);
		globalNaive = new JRadioButtonMenuItem(GlobalNaive.name);
		localDelta = new JRadioButtonMenuItem(LocalDelta.name);
		globalDelta = new JRadioButtonMenuItem(GlobalDelta.name);
		
		ButtonGroup algGroup = new ButtonGroup();
		algGroup.add(localNaive);
		algGroup.add(globalNaive);
		algGroup.add(localDelta);
		algGroup.add(globalDelta);
		localDelta.setSelected(true);
		
		settingsCategory.add(selectAlgorithm);
		selectAlgorithm.add(localNaive);
		selectAlgorithm.add(globalNaive);
		selectAlgorithm.add(localDelta);
		selectAlgorithm.add(globalDelta);
	}
	
	private void instantiateAlgorithmSelectionListeners() {
		localNaive.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algKey = "local-naive";
				CONST.algName = CONST.algNames.get(CONST.algKey); }});
		globalNaive.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algKey = "global-naive";
				CONST.algName = CONST.algNames.get(CONST.algKey); }});
		localDelta.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algKey = "local-delta"; 
				CONST.algName = CONST.algNames.get(CONST.algKey); }});
		globalDelta.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algKey = "global-delta"; 
				CONST.algName = CONST.algNames.get(CONST.algKey); }});
	}
	
	private void instantiateModeSelection() {
		selectMode = new JMenu("Select Mode");
		singleMode = new JRadioButtonMenuItem("Single Mode");
		optimizationMode = new JRadioButtonMenuItem("Optimization Mode");
		
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(singleMode);
		modeGroup.add(optimizationMode);
		singleMode.setSelected(true);
		
		settingsCategory.add(selectMode);
		selectMode.add(singleMode);
		selectMode.add(optimizationMode);
	}
	
	private void instantiateModeSelectionListeners() {
		singleMode.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				mW.changeMode(Mode.SINGLE); }});
		optimizationMode.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				mW.changeMode(Mode.OPTIMIZATION); }});
	}
	
}
