/**
 * @author Gil
 * - @project BigData
 *  - @package boot
 *   - @file Driver.java
 * @version 1.0
 */
package boot; 

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import solution.Properties;
import view.MyView;
import view.GuiWindow;
import view.View;

import java.util.Observable;

/**
 * The Class Driver.
 */
public class Driver {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		View view;
		XMLDecoder decoder=null;
		Properties properties=null;
		//Reading properties file.
		try 
		{
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Settings/properties.xml")));
			properties=(Properties)decoder.readObject();
			decoder.close();			
		} catch (FileNotFoundException e) 
		{
			System.out.println("ERROR: File Settings/properties.xml not found");
		}
		
		//Instantiate the view according to the properties file
		if(properties.getUI().equals("CLI"))
			view = new MyView(new BufferedReader(new InputStreamReader(System.in)),(new PrintWriter(System.out)),properties);
		else{
			view = new GuiWindow("Stock Analysis", 240, 400, properties);
		}
			
		
		//Instantiate Presenter and Model
		MyModel model = new MyModel(properties);
		Presenter presenter = new Presenter(view, model);
		((Observable)view).addObserver(presenter);
		model.addObserver(presenter);
		
		//Start the app.
		view.start();
	}

}
