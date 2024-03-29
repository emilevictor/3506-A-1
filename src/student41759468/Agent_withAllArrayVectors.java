package student41759468;

//import java.util.StringTokenizer;
import datastructures.*;

import java.io.*;
//import java.lang.reflect.Array;
/**
 * Agent class
 * 
 * Allows you to deal with selling and buying data.
 * 
 * @author Emile Victor
 *
 */
public class Agent_withAllArrayVectors implements IAgent {

	private ArrayVector<Stock> buyOrders;
	private ArrayVector<Stock> sellOrders;
	private ArrayVector<Stock> transactions;

	/*
	 * Default constructor
	 */
	public Agent_withAllArrayVectors() {
		// You may choose which data structures you would like to use

		this.buyOrders = new ArrayVector<Stock>();
		this.sellOrders = new ArrayVector<Stock>();
		this.transactions = new ArrayVector<Stock>();
	}

	/**
	 * Takes a file name as input and parses the commands in the file
	 */
	public int parseInput(String fileName) {

		/**
		 * The line read from the BufferedReader
		 */
		String lineReadFromBuffer;
		String simpleRegexDelimeter = " ";
		/**
		 * Line number counter
		 */
		int lineNumber = 0;

		/**
		 * Array split array (for splitting each line)
		 */
		String[] returnedSplitArray;
		returnedSplitArray = new String[15];

		/**
		 * Boolean bad used to determine return value
		 */
		boolean bad = false;

		/**
		 * The contents of the buy/sell command
		 */
		String buySell = new String();
		String stockCode = new String();
		int quantity;
		double price;

		//		Stock stock = new Stock();

		/*
		 * Kludgey POS: adding /src/test to the file path in order to
		 * stop the FileNotFound exception in the try/catch below.
		 */
		//		StringBuilder stringBuilder = new StringBuilder(fileName);
		//		stringBuilder.insert(1, "/src/test");
		//		fileName = stringBuilder.toString();

		/*
		 * Regex for this function (perhaps):
		 * ^(buy|sell)\s\w{4}?\s\d+?\s[$]\d+?[.]\d{0,2}$
		 */

		try {
			/*
			 * Create a file input stream, data input stream from fileinput stream
			 * and then finally a buffered reader to get the lines out of the file.
			 */
			FileInputStream fs = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fs);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			/*
			 * Iterate over each line.
			 */
			while ((lineReadFromBuffer = reader.readLine()) != null) {
				Stock stock = new Stock();
//				lineNumber++;
				if (lineReadFromBuffer.equals("")) {
					System.err.println("Command on line " + lineNumber + " is not well formed.");
					bad = true;
					continue;
				}
				//Split for spaces
				returnedSplitArray = lineReadFromBuffer.split(simpleRegexDelimeter);
				//Remove the dollar signs
				if (returnedSplitArray[3].charAt(0) != '$') {
					System.err.println("Command on line " + lineNumber + " is not well formed.");
					bad = true;
					continue;
				}
				returnedSplitArray[3] = (String)returnedSplitArray[3].subSequence(1,returnedSplitArray[3].length());


				try {
					//Set stock, price and quantity for the Stock object.
					stock.setPrice(Double.parseDouble(returnedSplitArray[3]));
					stock.setQuantity(Integer.parseInt(returnedSplitArray[2]));
					stock.setName(returnedSplitArray[1]);

					//Check for malformed stock name
					if (stock.getName().length() != 4) {
						System.err.println("Command on line " + lineNumber + " is not well formed.");
						bad = true;
						continue;
					}

				} catch (Exception e) {
					System.err.println("Failing to create stock: " + e.getMessage());
					bad = true;
				}

				/*
				 * Create node with array, put into Linked List, 
				 */

				if (returnedSplitArray[0].equals("sell")) {
					//					Node<Stock> node = new Node<Stock>(stock,null);
					this.sellOrders.add(this.sellOrders.size(),stock);

				} else if (returnedSplitArray[0].equals("buy")) {
					//					Node<Stock> node = new Node<Stock>(stock,null);
					this.buyOrders.add(this.buyOrders.size(),stock);
				} else {
					System.err.println("Command on line " + lineNumber + " is not well formed.");
					bad = true;

				}

			}

			in.close();
			lineNumber = 0;


		} catch (Exception e) {
			System.err.println("Unable to read file correctly: " + e.getMessage());
			bad = true;

		}


		if (bad == true) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Tries to match buy and sell orders. See assignment spec for more detail.
	 */
	public void exchange() {
		int count = 0;
		boolean noMoreSalesCanBeMade = false;
		boolean stopProcessing = false;
		//System.out.println("made it to the while loop");
		//Loop until we iterate through entire sales queue without finding any possible transactions
		while (stopProcessing == false) {
			//			int sellOrderSize = this.sellOrders.size();
			//			int buyOrderSize = this.buyOrders.size();
			noMoreSalesCanBeMade = false;
			for (int j = 0; j < this.buyOrders.size(); j++) {
				//For each sell Order  (from the first)
				for (int i = 0; i < this.sellOrders.size(); i++) {
					//System.out.println(this.buyOrders.get(0).toString());
					count++;
//					System.out.println(Integer.toString(count));
					//Get the first, and compare it to the first one in the buy order.
					if (this.sellOrders.get(0).getName().equals(this.buyOrders.get(0).getName())) {
						//System.out.println("names match");

						//If the price is right...
						if (this.sellOrders.get(0).getPrice() <= this.buyOrders.get(0).getPrice()) {


							//If there are more for sale than offered to buy
							if (this.sellOrders.get(0).getQuantity() > this.buyOrders.get(0).getQuantity()) {
								//We need to put the buy order on transaction queue
								this.transactions.add(this.transactions.size(), this.buyOrders.get(0));
								//Modify the number of stocks for sale
								this.sellOrders.get(0).setQuantity(this.sellOrders.get(0).getQuantity() - this.buyOrders.get(0).getQuantity());
								this.buyOrders.remove(0);
								//System.out.println("sale made");
								noMoreSalesCanBeMade = true;
								//i = 0;
								continue;

								//Or if there are exactly the same amount of stocks for sale and for purchase
							} else if (this.sellOrders.get(0).getQuantity() == this.buyOrders.get(0).getQuantity()) {
								//Add the buy order to the back of the transactions queue.
								this.transactions.add(this.transactions.size(), this.buyOrders.get(0));
								//Then, remove buy and sell orders from the front.
								this.buyOrders.remove(0);
								this.sellOrders.remove(0);
								//System.out.println("sale made");
								//i = 0;
								noMoreSalesCanBeMade = true;
								continue;

								//If there are more being requested for purchase than are available for sale
							} else if (this.sellOrders.get(0).getQuantity() < this.buyOrders.get(0).getQuantity()) {
								//Add sell order to the transaction list
								this.transactions.add(this.transactions.size(), this.sellOrders.get(0));
								//Update the buy order with the proper amount
								this.buyOrders.get(0).setQuantity(this.buyOrders.get(0).getQuantity() - this.sellOrders.get(0).getQuantity());
								//Remove the sell order, because we have bought everything in it
								this.sellOrders.remove(0);
								//Put the buy order at the back of its queue.
								Stock movingStock = new Stock();
								movingStock = this.buyOrders.get(0);
								this.buyOrders.remove(0);
								this.buyOrders.add(this.buyOrders.size(), movingStock);
								//And we're done for now.
								noMoreSalesCanBeMade = true;
								//i = 0;
								//System.out.println("sale made");
								continue;
							}


							//If the price isn't right, put the sell order at the back of the sell order queue.
						} else {
							Stock movingStock = new Stock();
							movingStock = this.sellOrders.get(0);
							this.sellOrders.remove(0);
							this.sellOrders.add(this.sellOrders.size(), movingStock);
							//System.out.println("PRICE NOT RIGHT");
							continue;
						}

					} else {
						//If sell order does not match the buy order (stock name)
						//Put the sell order at the back of the sell queue.
						Stock movingStock = new Stock();
						movingStock = this.sellOrders.get(0);
						this.sellOrders.remove(0);
						this.sellOrders.add(this.sellOrders.size(), movingStock);
						//System.out.println("sale NOT MADE");
						continue;
					}
					//Check whether the last pass 

				}
			}


			if (this.buyOrders.size() > 0) {
				Stock movingStock = new Stock();
				movingStock = this.buyOrders.get(0);
				this.buyOrders.remove(0);
				this.buyOrders.add(this.buyOrders.size(), movingStock);
			} else {
				break;
			}
			//If no sale was made for that PARTICULAR buy request...

			if (noMoreSalesCanBeMade == false) {
				stopProcessing = true;
				continue;
			} else {
				noMoreSalesCanBeMade = false;
			}

		}
		//System.out.println("while loop complete");



	}

	/**
	 * Returns a string of the buy and sell orders. See assignment spec for more
	 * detail.
	 */
	public String printQueues() {
		String output = new String();
		//Print purchases
		for (int i = 0; i < buyOrders.size(); i++) {
			output = output.concat("buy ").concat(buyOrders.get(i).getName()).concat(" ").concat(Integer.toString(buyOrders.get(i).getQuantity())).concat(" ").concat("$").concat(String.format("%.2f", buyOrders.get(i).getPrice()) ).concat("\n");
		}
		//Print sales
		for (int j = 0; j < sellOrders.size(); j++) {
			output = output.concat("sell ").concat(sellOrders.get(j).getName()).concat(" ").concat(Integer.toString(sellOrders.get(j).getQuantity())).concat(" ").concat("$").concat(String.format("%.2f", sellOrders.get(j).getPrice()) ).concat("\n");
		}
		//Removing the redundant \n at the end in order to pass test.
		if (output.length() > 1) {
			StringBuffer stringBuff = new StringBuffer(output);
			stringBuff.delete(output.length()-1, output.length());
			output = stringBuff.toString();
		}
		return output;
	}

	/**
	 * Returns a string of the transactions. See assignment spec for more
	 * detail.
	 */
	public String printTransactions() {
		String output = new String();
		for (int i = 0; i < this.transactions.size(); i++) {
			output = output.concat(this.transactions.get(i).getName()).concat(" ").concat(Integer.toString(transactions.get(i).getQuantity())).concat(" ").concat("$").concat(String.format("%.2f", transactions.get(i).getPrice()) ).concat("\n");
		}
		if (output.length() > 1) {
			StringBuffer stringBuff = new StringBuffer(output);
			stringBuff.delete(output.length()-1, output.length());
			output = stringBuff.toString();
		}

		return output; // To prevent an error in the project

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
