/**
 * @author Gil
 * - @project BigData
 *  - @package view
 *   - @file GuiWindow.java
 * @version 1.0
 */
package view;

import java.io.IOException;
import java.util.HashMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowData;

import presenter.Command;
import solution.Properties;
import stocks.Stock;

public class GuiWindow extends BasicWindow implements View {

	int userCommand=0;
	HashMap<String, Command> viewCommandMap;
	Text hostText;
	Text userText;
	Text passText;
	Text textStocks;
	Text textAnalyze;
	Text clusterText;
	Label serverStatus;
	Label serverAddress;
	Label changed;
	Button startStopButton;
	Properties properties;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GuiWindow(String title, int width, int height,Properties properties) {
		super(title, width, height);
		this.properties=properties;
	}

	/**
	 * Create contents of the dialog.
	 */
	void initWidgets() {
		shell.setSize(450, 300);
		shell.setLocation(390, 190);
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 444, 271);
		
		
		TabItem propertiesItem = new TabItem(tabFolder, SWT.NONE);
		propertiesItem.setText("Properties");
		
		Composite propertiesComposite = new Composite(tabFolder, SWT.NONE);
		propertiesItem.setControl(propertiesComposite);
		
		Label startLabel = new Label(propertiesComposite, SWT.NONE);
		startLabel.setAlignment(SWT.CENTER);
		startLabel.setBounds(95, 31, 190, 50);
		startLabel.setText("Properties:");
		
		FontData[] font1 = startLabel.getFont().getFontData();
		font1[0].setStyle(SWT.BOLD);
		font1[0].setHeight(15);
		startLabel.setFont( new Font(display,font1[0]));
		
		Label hostLabel = new Label(propertiesComposite, SWT.NONE);
		hostLabel.setAlignment(SWT.CENTER);
		hostLabel.setBounds(43, 85, 75, 15);
		hostLabel.setText("IP Address:");
		
		hostText = createText(propertiesComposite, SWT.SINGLE | SWT.BORDER, properties.getHost(), 147, 15);
		hostText.setBounds(124, 82, 137, 21);
		
		Label userNameLabel = new Label(propertiesComposite, SWT.NONE);
		userNameLabel.setAlignment(SWT.CENTER);
		userNameLabel.setBounds(43, 120, 75, 15);
		userNameLabel.setText("Username:");
		
		userText = createText(propertiesComposite, SWT.SINGLE | SWT.BORDER, properties.getUserName(), 147, 15);
		userText.setBounds(124, 117, 137, 21);
		
		Label passLabel = new Label(propertiesComposite, SWT.NONE);
		passLabel.setAlignment(SWT.CENTER);
		passLabel.setBounds(43, 158, 75, 15);
		passLabel.setText("Password:");
		
		passText = createText(propertiesComposite, SWT.PASSWORD | SWT.BORDER, properties.getPassword(), 147, 15);
		passText.setBounds(124, 155, 137, 21);
		
		changed = new Label(propertiesComposite, SWT.CENTER);
		changed.setBounds(124, 190, 160, 15);
		
		Button submitButton = new Button(propertiesComposite, SWT.NONE);
		submitButton.setBounds(302, 100, 100, 60);
		submitButton.setText("Set");
		FontData[] fontButton = submitButton.getFont().getFontData();
		fontButton[0].setStyle(SWT.BOLD);
		fontButton[0].setHeight(10);
		submitButton.setFont( new Font(display,fontButton[0]));
		
		TabItem hadoopItem = new TabItem(tabFolder, SWT.NONE);
		hadoopItem.setText("Analyze");
		
		Composite clouderaComposite = new Composite(tabFolder, SWT.NONE);
		hadoopItem.setControl(clouderaComposite);
		
		serverAddress = createLabel(clouderaComposite, SWT.CENTER|SWT.BOTTOM, "Host: "+properties.getHost());
		serverAddress.setBounds(54, 21, 119, 20);
		serverAddress.setBackground(new Color(display,129,210,255));
		
		serverStatus = new Label(clouderaComposite, SWT.NONE|SWT.CENTER);
		serverStatus.setBounds(276, 21, 80, 20);
		serverStatus.setText("Status: OFF");
		serverStatus.setBackground(new Color(display,255,0,0));
		FontData[] font = serverAddress.getFont().getFontData();
		font[0].setStyle(SWT.BOLD);
		font[0].setHeight(10);
		serverStatus.setFont( new Font(display,font[0]));
		
		
		Label labelStocks = new Label(clouderaComposite, SWT.NONE);
		labelStocks.setBounds(10, 73, 55, 15);
		labelStocks.setText("Stocks:");
		
		textStocks = new Text(clouderaComposite, SWT.BORDER);
		textStocks.setBounds(71, 70, 76, 21);
		
		Label labelAnalyze = new Label(clouderaComposite, SWT.NONE);
		labelAnalyze.setBounds(10, 107, 55, 15);
		labelAnalyze.setText("Days:");
		
		textAnalyze = new Text(clouderaComposite, SWT.BORDER);
		textAnalyze.setBounds(71, 104, 76, 21);
		
		startStopButton = new Button(clouderaComposite, SWT.NONE);
		startStopButton.setBounds(271, 175, 107, 38);
		startStopButton.setText("Analyze");
		FontData[] font2 = startStopButton.getFont().getFontData();
		font2[0].setStyle(SWT.BOLD);
		font2[0].setHeight(10);
		startStopButton.setFont( new Font(display,font2[0]));
		
		clusterText = new Text(clouderaComposite, SWT.BORDER);
		clusterText.setBounds(71, 173, 76, 21);
		
		Label Clusterslabel = new Label(clouderaComposite, SWT.NONE);
		Clusterslabel.setText("Clusters:");
		Clusterslabel.setBounds(10, 173, 55, 15);
		
		Label label_1 = new Label(clouderaComposite, SWT.NONE);
		label_1.setText("Features:");
		label_1.setBounds(10, 143, 55, 15);
		
		Button buttonOpen = new Button(clouderaComposite, SWT.CHECK);
		buttonOpen.setText("Open");
		buttonOpen.setBounds(71, 142, 50, 16);
		
		Button buttonHigh = new Button(clouderaComposite, SWT.CHECK);
		buttonHigh.setText("High");
		buttonHigh.setBounds(138, 142, 55, 16);
		
		Button buttonLow = new Button(clouderaComposite, SWT.CHECK);
		buttonLow.setText("Low");
		buttonLow.setBounds(204, 142, 50, 16);
		
		Button buttonClose = new Button(clouderaComposite, SWT.CHECK);
		buttonClose.setText("Close");
		buttonClose.setBounds(271, 141, 55, 16);
		
		TabItem aboutItem = new TabItem(tabFolder, SWT.NONE);
		aboutItem.setText("About");
		
		Composite aboutComposite = new Composite(tabFolder, SWT.NONE);
		aboutItem.setControl(aboutComposite);
		
		Label aboutlabel = new Label(aboutComposite, SWT.NONE);
		aboutlabel.setText("© 2016 This Appication Created by \n    Gil Doron, ALL RIGHTS RESERVED");
		aboutlabel.setBounds(109, 81, 280, 50);
		
		startStopButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
					if (startStopButton.getText().toString().equals("Analyze"))
					{
						if (!textStocks.getText().trim().isEmpty() && !textAnalyze.getText().trim().isEmpty() && !clusterText.getText().trim().isEmpty())
						{
							int numberOfStocks = new Integer(new String(textStocks.getText()).trim()).intValue();
							int analyze = new Integer(new String(textAnalyze.getText()).trim()).intValue();
							int cluster = new Integer(new String(clusterText.getText()).trim()).intValue();
							boolean open = buttonOpen.getSelection();
							boolean high = buttonHigh.getSelection();
							boolean close = buttonClose.getSelection();
							boolean low = buttonLow.getSelection();
							
							serverStatus.setText("Status: ON");
							serverStatus.setBackground(new Color(display,28,253,43));
							FontData[] fD = serverStatus.getFont().getFontData();
							fD[0].setStyle(SWT.BOLD);
							fD[0].setHeight(10);
							serverStatus.setFont( new Font(display,fD[0]));
							
							FontData[] font = serverStatus.getFont().getFontData();
							font[0].setStyle(SWT.BOLD);
							font[0].setHeight(10);
							Font f = new Font(display, font);
							startStopButton.setText("Working...");
							startStopButton.setFont(f);
							startStopButton.setEnabled(false);
							
							remoteSolve(numberOfStocks,analyze,cluster,open,high,low,close);
						}
						else
						{
							MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.ABORT);
					        messageBox.setText("Warning");
					        messageBox.setMessage("Some Parameters Are Missing.");
					        messageBox.open();
						}
					}
				}
					
			
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		/* What happens when a user clicks "[Update]". */ 
		submitButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if((properties.getHost().equals(hostText.getText())) && (properties.getUserName().equals(userText.getText())) && (properties.getPassword().equals(passText.getText()))){
					errorNoticeToUser("Nothing Changed.");
				}
				else{
					properties.setHost(hostText.getText());
					properties.setUserName(userText.getText());
					properties.setPassword(passText.getText());
					serverAddress.setText("Host: " +hostText.getText());
					changed.setText("Changed! , GO TO ANALYZE");
					changed.setBackground(new Color(display, 0, 255, 94));
					serverAddress.setBackground(new Color(display,189,211,255));
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void errorNoticeToUser(String s) {
		Display.getDefault().asyncExec(new Runnable() {
		    public void run() {
		    	MessageBox messageBox = new MessageBox(new Shell(display),SWT.ICON_INFORMATION|SWT.OK);
				messageBox.setMessage(s);
				messageBox.setText("Notification");
				messageBox.open();
		    }
		});		
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public int getUserCommand() {
		return userCommand;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setUserCommand(int commandID) 
	{
		this.setChanged();
		this.userCommand = commandID;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void displayData(Object data) {
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void start() {
		this.run();		
	}
	
	public void exit(){
		viewCommandMap.get("Exit").doCommand(new String[]{"Bye!"});
		display.dispose(); // dispose OS components
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setCommands(HashMap<String, Command> viewCommandMap) {
		this.viewCommandMap = viewCommandMap;
	}
	
	@Override
	public HashMap<String, Command> getViewCommandMap() {
		return viewCommandMap;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setCommandsMenu(String cliMenu) {}
	
	/**
	 * This method will create label with\by the following values
	 * @param parent represent the composite to be added to
	 * @param style represent the style
	 * @param placeholder represent the text value of the label
	 * @param width represent label width
	 * @param height represent label height
	 * @return label
	 */
	private Label createLabel(Composite parent, int style, String placeholder, int width, int height){
		Label label = new Label(parent, style);
		label.setLayoutData(new RowData(width, height));
		label.setText(placeholder);
		return label; 		
	}
	
	/**
	 * This method will create label with\by the following values
	 * @param parent represent the composite to be added to
	 * @param style represent the style
	 * @param placeholder represent the text value of the label
	 * @return label
	 */
	private Label createLabel(Composite parent, int style, String placeholder){
		return createLabel(parent, style, placeholder, 120, 30); 		
	}
	/**
	 * This method will create text box with\by the following values
	 * @param parent represent the composite to be added to
	 * @param style represent the style
	 * @param placeholder represent the text value of the label
	 * @param width represent Text width
	 * @param height represent Text height
	 * @return the text box
	 */
	private Text createText(Composite parent, int style, String placeholder, int width, int height){
		Text text = new Text(parent, style);
	    text.setText(placeholder);
	    text.setLayoutData(new RowData(width, height));
	    return text; 
	}

	/**
	 * This method will create combo box with\by the following values
	 * @param parent represent the parent to be added to
	 * @param style represent the style
	 * @param options represent the String[] of strings to put at the combo
	 * @param placeholder represent the first value of the combo
	 * @param width represent the combo width
	 * @param height represent the combo height
	 * @return combo 
	 */
	private Combo createCombo(Composite parent, int style, String[] options, String placeholder, int width, int height){
		Combo combo = new Combo(parent, style);
		for (int i = 0; i < options.length; i++) {
			combo.add(options[i]);
		}
		combo.setText(placeholder);
		combo.setLayoutData(new RowData(width, height));
		return combo;
	}
	
	@SuppressWarnings("unused")
	/**
	 * This method will create combo box with\by the following values
	 * @param parent represent the parent to be added to
	 * @param style represent the style
	 * @param options represent the String[] of strings to put at the combo
	 * @param placeholder represent the first value of the combo
	 * @return combo 
	 */
	private Combo createCombo(Composite parent, int style, String[] options, String placeholder){
		return createCombo(parent, style, options, placeholder, 90, 20);
	}
	
	public void disconnect()
	{
		viewCommandMap.get("Exit").doCommand(new String[] {"null"});
		System.exit(0);

		exit();
	}
	
	/**
	 * This method will do the following:
	 * 		Open new SSH connection with Hadoop job server.
	 * 		Copy all files under "input" to "jobServerInputFolderPath" setted on Properties.xml
	 * 		Copy job's jar located under "Jars" to Hadoop at /home/training/
	 * 		Upload input files and the jar from the linux to cloudera Hadoop
	 * 		Run the job
	 * 		Copy from linux to windows the results. 
	 */
	public void remoteSolve(int numberOfStocks, int analyze, int clusters,boolean open, boolean high, boolean low, boolean close)
	{
		
		try {
			viewCommandMap.get("Analyze").doCommand(new String[]{numberOfStocks+"",analyze+"",clusters+"",open+"",high+"",low+"",close+""});
		} catch (Exception e) {
			System.out.println("Something went wrong!");
		}
	}



	@Override
	public void setStockMap(HashMap<String, Stock> stocksMap) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				serverStatus.setText("Status: OFF");
				serverStatus.setBackground(new Color(display,255,0,0));
				startStopButton.setEnabled(true);
				FontData[] font = serverStatus.getFont().getFontData();
				font[0].setStyle(SWT.BOLD);
				font[0].setHeight(10);
				Font f = new Font(display, font);
				startStopButton.setFont(f);
				startStopButton.setText("Analyze");
			}
		});
		
		try {
			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start firefox.exe Output/graphs.html"});
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
