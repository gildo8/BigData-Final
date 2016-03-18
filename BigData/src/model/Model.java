/**
 * @author Gil
 * - @project BigData
 *  - @package model
 *   - @file Model.java
 * @version 1.0
 */
package model;

import java.util.HashMap;
import stocks.Stock;


/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * This method will open SSH connection.
	 * @param host represent Host name or IP address of the SSH server.
	 * @param userName represent the user name of the server.
	 * @param password represent the password of the server.
	 */
	public void sshConnect(String host,String userName, String password);
	/**
	 * This method will remotely execute command on SSH server.
	 * @param command represent a string of command to execute.
	 */
	public void executeCommand(String command);
	
	/**
	 * This method will transfer file from the local host to the SSH server.
	 *
	 * @param filePath represent full file name including his path.
	 * @param targetPath the target path
	 */
	public void transferFile(String filePath,String targetPath);
	
	/**
	 * This method will download files into "Output folder".
	 * The method doesn't have the option of copying files to dynamically local locations due to convenience.
	 *
	 * @param filePath represent full file name including his path.
	 * @return the f ile by name
	 */
	public void getFIleByName(String filePath);
	
	/**
	 * This close all files and threads and will terminate the model activity.
	 */
	public void exit();
	
	/**
	 * This method will return model calculation.
	 *
	 * @return object represent a model calculation
	 */
	public Object getData();
	
	/**
	 * Gets the model completed command.
	 *
	 * @return model command id
	 */
	public int getModelCompletedCommand();
	
	/**
	 * Analyze data.
	 *
	 * @param numberOfStocks the number of stocks
	 * @param analyze the analyze
	 * @param clusters the clusters
	 * @param open the open
	 * @param high the high
	 * @param low the low
	 * @param close the close
	 */
	public void analyzeData(String numberOfStocks, String analyze, String clusters, String open, String high,
			String low, String close) ;
	
	/**
	 * Gets the stocks map.
	 *
	 * @return the stocks map
	 */
	public HashMap<String, Stock> getStocksMap();
	

}
