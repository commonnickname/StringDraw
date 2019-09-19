package windowComponents.controlComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.CONST;
import utils.datatypes.CEnums.AlgStrategy;

public class AlgorithmSelectionPanel extends JPanel{
	private JRadioButton localRButton, globalRButton;
	private boolean localitySettingAvailable;
	
	public AlgorithmSelectionPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setPreferredSize(new Dimension(CONST.CONTROL_W, 400));
		
		localitySettingAvailable = true;
		
		instantiateSelectionList();
	    instantiateLocalGlobal();
	}
	
	private void instantiateSelectionList() {
	    JList<String> list = new JList<String>(CONST.algKeys);
	    list.setSelectedIndex(Arrays.asList(CONST.algKeys).indexOf(CONST.algKey));
	    
	    list.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent evt) {
	        	CONST.algKey = (String) list.getSelectedValue();
				CONST.algName = CONST.algNames.get(CONST.algKey);
				//System.out.println(CONST.algKey);
	        }
	    });
	    
	    JScrollPane scrollableList = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				   									 	   JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    this.add(scrollableList, BorderLayout.PAGE_START);
	}
	
	private void instantiateLocalGlobal() {
		
		ButtonGroup groupLocalGlobal = new ButtonGroup();
		
		localRButton = new JRadioButton("Local");
	    globalRButton = new JRadioButton("Global  ");
		
		groupLocalGlobal.add(localRButton);
		groupLocalGlobal.add(globalRButton);
		
		localRButton.setEnabled(localitySettingAvailable);
		globalRButton.setEnabled(localitySettingAvailable);
		
		localRButton.setSelected((CONST.algStrategy == AlgStrategy.LOCAL) ? true : false);
		globalRButton.setSelected((CONST.algStrategy == AlgStrategy.GLOBAL) ? true : false);

		localRButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algStrategy = AlgStrategy.LOCAL; }});
		globalRButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				CONST.algStrategy = AlgStrategy.GLOBAL; }});
		
		this.add(localRButton, BorderLayout.LINE_START);
	    this.add(globalRButton, BorderLayout.LINE_END);
	}
	
}
