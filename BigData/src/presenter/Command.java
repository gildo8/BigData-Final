/**
 * @author Gil
 * - @project BigData
 *  - @package presenter
 *   - @file Command.java
 * @version 1.0
 */
package presenter;

public interface Command {
	/**
	 * This method will run command with given array of strings.
	 *@param args String[] represent arguments for command.
	 */

	void doCommand(String[] args);
}
