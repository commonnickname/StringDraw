package windowComponents.controlComponents;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import data.CONST;
import utils.objects.ParameterPackage;

public class TuningPanel extends JPanel{
	JPanel parameterPanel, buttonsPanel;
	JLabel cutouffLabel, dExpLabel, dOpacityLabel, gammaLabel;
	JFormattedTextField cutoffField, dExpField, dOpacityField, gammaField;
	NumberFormat format;
	public JButton applyButton, revertButton;
	private int justification;
	private String borderName;
	private ParameterPackage params;
	
	public TuningPanel(ParameterPackage params, String borderName, int justification) {
		setLayout(new BorderLayout());
		this.params = params;
		
		this.justification = justification;
		this.borderName = borderName;
		
		reset();
	}
	
	public void reset() {
		setBorderName();
		setupParameterPanel();
		setupButtons();
		setupButtonListeners();
	}
	
	public void setBorderName() {
		TitledBorder border = BorderFactory.createTitledBorder(
			  	   			  		BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
			  	   			  		borderName);
		switch(justification) {
			case -1: { border.setTitleJustification(TitledBorder.LEFT); break; }
			case 1: { border.setTitleJustification(TitledBorder.RIGHT); break; }
			default: { border.setTitleJustification(TitledBorder.CENTER); break; }
		}
		this.setBorder(border);
	}
	
	private void setupParameterPanel() {
		parameterPanel = new JPanel();
		parameterPanel.setPreferredSize(new Dimension(CONST.CONTROL_W, 170));
		add(parameterPanel);
		
		setupFields();
		setupLabels();
		setupParameterLayout();
	}
	
	private void setupFields() {
		format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        
        cutoffField = new JFormattedTextField(format);
        dExpField = new JFormattedTextField(format);
        dOpacityField = new JFormattedTextField(format);
        gammaField = new JFormattedTextField(format);
        
        updateFields();
	}
	
	public void updateFields() {
		cutoffField.setValue(params.cutoff * 100);
        dExpField.setValue(params.deltaExponent);
        dOpacityField.setValue(params.deltaOpacity * 100);
        gammaField.setValue(params.gamma);
	}
	
	private void setupLabels() {
		cutouffLabel = new JLabel("Cutoff (0, 100]");
        dExpLabel = new JLabel("D. Exponent (0, 5]");
        dOpacityLabel = new JLabel("D. Opacity (1, 100]");
        gammaLabel = new JLabel("Gamma (0, 5]");
	}
	
	private void setupParameterLayout() {
		GroupLayout layout = new GroupLayout(parameterPanel);
		parameterPanel.setLayout(layout);
		
		//layout.setAutoCreateGaps(true);
		//layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup().addGap(3);
		hGroup.addGroup(layout.createParallelGroup().
				addComponent(cutouffLabel).
				addComponent(dExpLabel).
				addComponent(dOpacityLabel).
				addComponent(gammaLabel));
		hGroup.addGroup(layout.createParallelGroup().
				addComponent(cutoffField).
				addComponent(dExpField).
				addComponent(dOpacityField).
				addComponent(gammaField));
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup().addGap(2);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(cutouffLabel).
				addComponent(cutoffField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(dExpLabel).
				addComponent(dExpField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(dOpacityLabel).
				addComponent(dOpacityField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(gammaLabel).
				addComponent(gammaField));

		layout.setVerticalGroup(vGroup);
	}
	


	private void setupButtons() {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		add(buttonsPanel, BorderLayout.PAGE_END);
		
		applyButton = new JButton("Apply");
		revertButton = new JButton("Revert");
		
		buttonsPanel.add(applyButton);
		buttonsPanel.add(revertButton);
	}
	
	private void setupButtonListeners() {
		applyButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
    		float nCutoffValue = ((Number)cutoffField.getValue()).floatValue()/100;
    		double dExpValue = ((Number)dExpField.getValue()).doubleValue();
    		float dOpacityValue = ((Number)dOpacityField.getValue()).floatValue()/100;
    		double gammaValue = ((Number)gammaField.getValue()).doubleValue();

    		if (nCutoffValue > 0 && nCutoffValue <= 1) params.cutoff = nCutoffValue;
    		if (dExpValue > 0 && dExpValue <= 5) params.deltaExponent = dExpValue;
    		if (dOpacityValue > 0.01 && dOpacityValue <= 1) params.deltaOpacity = dOpacityValue;
    		if (gammaValue > 0 && gammaValue <= 5) params.gamma = gammaValue;
    		
    		updateFields();
    	}});
    	
    	revertButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
    		updateFields();
    	}});
	}
	
	public void setEnabled(boolean b) {
		cutoffField.setEnabled(b);
        dExpField.setEnabled(b);
        dOpacityField.setEnabled(b);
        gammaField.setEnabled(b);
        
        applyButton.setEnabled(b);
		revertButton.setEnabled(b);
	}
	
}
