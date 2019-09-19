package windowComponents;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import data.CONST;
import utils.datatypes.CEnums.Mode;
import utils.helpers.ImageProcessor;

public class MenuBar extends JMenuBar {
	private JMenu fileCategory, settingsCategory, selectMode;
	public JMenuItem openFileItem, graphicalSettings;
	public JRadioButtonMenuItem localNaive, globalNaive, 
								localDelta, globalDelta,
								localDeltaLog, globalDeltaLog,
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
				JFileChooser chooser = new JFileChooser();
				ImagePreviewPanel preview = new ImagePreviewPanel();
				chooser.setAccessory(preview);
				chooser.addPropertyChangeListener(preview);
				chooser.setCurrentDirectory(new java.io.File(CONST.defaultPath));
				chooser.setDialogTitle("Open File...");
				chooser.setPreferredSize(new Dimension(700, 500));
				
				if (chooser.showOpenDialog(openFileItem) != JFileChooser.APPROVE_OPTION) return;
				System.gc();
				CONST.defaultPath = chooser.getCurrentDirectory().getAbsolutePath();
				String filePath = chooser.getSelectedFile().getAbsolutePath();
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
		
		instantiateModeSelection();
		instantiateModeSelectionListeners();
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
