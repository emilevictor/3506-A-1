package student41759468;

import java.util.StringTokenizer;
import datastructures.*;
import java.io.*;
/**
 * Agent class
 * 
 * Allows you to deal with selling and buying data.
 * 
 * @author Emile Victor
 *
 */
public class Agent implements IAgent {

	private LinkedList<Stock> buyOrders;
	private LinkedList<Stock> sellOrders;
	private LinkedList<Stock> transactions;

	/*
	 * Default constructor
	 */
	public Agent() {
		// You may choose which data structures you would like to use

		this.buyOrders = new LinkedList<Stock>();
		this.sellOrders = new LinkedList<Stock>();
		this.transactions = new LinkedList<Stock>();
	}

	/**
	 * Takes a file name as input and parses the commands in the file
	 */
	public int parseInput(String fileName) {
		//		String currentDir = new File(".").getAbsolutePath();
		String lineReadFromBuffer;
		String simpleRegexDelimeter = " ";
		//		System.out.println(currentDir);
		
		/**
		 * Kludgey POS: adding /src/test to the file path in order to
		 * stop the FileNotFound exception in the try/catch below.
		 */
		StringBuilder stringBuilder = new StringBuilder(fileName);
		stringBuilder.insert(1, "/src/test");
		fileName = stringBuilder.toString();
		
		/**
		 * Regex for this function (perhaps):
		 * ^(buy|sell)\s\w{4}?\s\d+?\s[$]\d+?[.]\d{0,2}$
		 */
		
		try {
			/**
			 * Create a file input stream, data input stream from fileinput stream
			 * and then finally a buffered reader to get the lines out of the file.
			 */
			FileInputStream fs = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fs);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
			/**
			 * Iterate over each line.
			 */
			while ((lineReadFromBuffer = reader.readLine()) != null) {
				
				System.out.println(lineReadFromBuffer);
			}

			in.close();


		} catch (Exception e) {
			System.err.println("Unable to read file correctly:" + e.getMessage());


		}



		return 0; // To prevent an error in the project
	}

	/**
	 * Tries to match buy and sell orders. See assignment spec for more detail.
	 */
	public void exchange() {
		// Implement this method
	}

	/**
	 * Returns a string of the buy and sell orders. See assignment spec for more
	 * detail.
	 */
	public String printQueues() {
		// Implement this method
		return ""; // To prevent an error in the project
	}

	/**
	 * Returns a string of the transactions. See assignment spec for more
	 * detail.
	 */
	public String printTransactions() {
		// Implement this method
		return ""; // To prevent an error in the project

	}

	public int sizeSell() {
		return this.sellOrders.size();
	}

	public int sizeBuy() {
		return this.buyOrders.size();
	}

	public int sizeTransaction() {
		return this.transactions.size();
	}

}
