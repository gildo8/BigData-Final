/**
 * @author Gil
 * - @project BigData
 *  - @package view
 *   - @file View.java
 * @version 1.0
 */
package view;

import java.util.HashMap;

import presenter.Command;
import stocks.Stock;

public interface View {
	/**
	 * Starting the View layer
	 */
	public void start();
	
	/**
	 * This method will set view Commands
	 * @param viewCommandMap represent HashMap of commands
	 */
	public void setCommands(HashMap<String, Command> viewCommandMap);
	/**
	 * Set the cliMenu to be the menu itself. 
	 * @param cliMenu - The menu to be put in cliMenu
	 */
	public void setCommandsMenu(String cliMenu);
	/**
	 * Display a generic error message to the user 
	 * @param s - the error to display (String). 
	 */
	public void errorNoticeToUser(String s);
	/**
	 * @return view command ID
	 */
	public int getUserCommand();
	/**
	 * This method will set user command ID
	 * @param commandID represent the user command ID
	 */
	public void setUserCommand(int commandID);
	
	public void notifyObservers();

	/**
	 * This method will display data to the user.
	 * @param data
	 */
	public void displayData(Object data);
	
	/**
	 * @return HashMap of commands to execute
	 */
	public HashMap<String, Command> getViewCommandMap();

	public void setStockMap(HashMap<String, Stock> stocksMap);
	
}
