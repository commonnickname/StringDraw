package windowComponents;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import data.CONST;
import mainWindow.MainWindow;
import utilDatatypes.CEnums.Mode;

public class MenuBar extends JMenuBar {
	private JMenu fileCategory, settingsCategory, 
				  selectAlgorithm, selectMode;
	public JMenuItem openFileItem;
	public JRadioButtonMenuItem naiveAlgorithm, globalNaiveAlgorithm, 
								deltaAlgorithm, globalDeltaAlgorithm,
								singleMode, optimizationMode;
	private MainWindow mainWindow;
	
	public MenuBar(MainWindow parentFrame) {
		this.mainWindow = parentFrame;
		
		fileCategory = new JMenu("File");
		add(fileCategory);
		instantiateFileCategory();
		
		settingsCategory = new JMenu("Settings");
		add(settingsCategory);
		instantiateAlgorithmSelection();
		instantiateAlgorithmSelectionListeners();
		instantiateModeSelection();
		instantiateModeSelectionListeners();

	}

	private void instantiateFileCategory() {
		openFileItem = new JMenuItem("Open File...");
		fileCategory.add(openFileItem);
	}
	
	private void instantiateAlgorithmSelection() {
		selectAlgorithm = new JMenu("Select Algorithm");
		naiveAlgorithm = new JRadioButtonMenuItem("Naive");
		globalNaiveAlgorithm = new JRadioButtonMenuItem("Global Naive");
		deltaAlgorithm = new JRadioButtonMenuItem("Delta");
		globalDeltaAlgorithm = new JRadioButtonMenuItem("Global Delta");
		
		ButtonGroup algGroup = new ButtonGroup();
		algGroup.add(naiveAlgorithm);
		algGroup.add(globalNaiveAlgorithm);
		algGroup.add(deltaAlgorithm);
		algGroup.add(globalDeltaAlgorithm);
		deltaAlgorithm.setSelected(true);
		
		settingsCategory.add(selectAlgorithm);
		selectAlgorithm.add(naiveAlgorithm);
		selectAlgorithm.add(globalNaiveAlgorithm);
		selectAlgorithm.add(deltaAlgorithm);
		selectAlgorithm.add(globalDeltaAlgorithm);
	}
	
	private void instantiateAlgorithmSelectionListeners() {
		naiveAlgorithm.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algorithmName = "algorithms.NaiveAlgorithm"; }});
		globalNaiveAlgorithm.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algorithmName = "algorithms.GlobalNaiveAlgorithm"; }});
		deltaAlgorithm.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algorithmName = "algorithms.DeltaAlgorithm"; }});
		globalDeltaAlgorithm.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algorithmName = "algorithms.GlobalDeltaAlgorithm"; }});
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
				mainWindow.changeMode(Mode.SINGLE); }});
		optimizationMode.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				mainWindow.changeMode(Mode.OPTIMIZATION); }});
	}
	
}
