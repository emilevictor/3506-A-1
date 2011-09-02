package test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Random;

import org.junit.Test;

import student41759468.*;

public class TestAgent {

	//@Test
	public void testParseLine() {
		try {
			Agent a = new Agent();
			a.parseInput("src/test/test_files/test1.txt"); // May need to check this path
			assertEquals("Should be 2 stocks in buy", 2, a.sizeBuy());
			assertEquals("Should be 1 stock in sell", 1, a.sizeSell());
			assertEquals("Should be 0 stocks in transaction", 0,
					a.sizeTransaction());
		} catch (Exception e) {
			fail("Exception occurred.");
		}
	}

	//@Test
	public void testExchange() {
		try {
			// Can't exchange
			Agent a = new Agent();
			a.parseInput("src/test/test_files/test2.txt"); // May need to check this path
			assertEquals("Should be 2 stocks in buy", 2, a.sizeBuy());
			assertEquals("Should be 1 stock in sell", 1, a.sizeSell());
			assertEquals("Should be 0 stocks in transaction", 0,
					a.sizeTransaction());
			a.exchange();
			assertEquals("Should be 2 stocks in buy", 2, a.sizeBuy());
			assertEquals("Should be 1 stock in sell", 1, a.sizeSell());
			assertEquals("Should be 0 stocks in transaction", 0,
					a.sizeTransaction());

			// A buy and sell exactly match
			Agent b = new Agent();
			b.parseInput("src/test/test_files/test3.txt"); // May need to check this path
			assertEquals("Should be 1 stock in buy", 1, b.sizeBuy());
			assertEquals("Should be 1 stock in sell", 1, b.sizeSell());
			assertEquals("Should be 0 stock in transaction", 0,
					b.sizeTransaction());
			b.exchange();
			assertEquals("Should be 0 stocks in buy", 0, b.sizeBuy());
			assertEquals("Should be 0 stocks in sell", 0, b.sizeSell());
			assertEquals("Should be 1 stock in transaction", 1,
					b.sizeTransaction());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			fail("Exception occurred.");
		}
	}

	//@Test
	public void testPrintTransactions() {
		try {
			// Can't exchange
			Agent a = new Agent();
			a.parseInput("src/test/test_files/test4.txt"); // May need to check this path
			a.exchange();
			assertEquals("Should be 1 stock in buy", 1, a.sizeBuy());
			assertEquals("Should be 0 stock in sell", 0, a.sizeSell());
			assertEquals("Should be 1 stock in transaction", 1,
					a.sizeTransaction());
			assertEquals("Print transactions", "HIJK 50 $5.00", a.printTransactions());

		} catch (Exception e) {
			fail("Exception occurred.");
		}
	}

	//@Test
	public void testPrintQueues() {
		try {
			Agent a = new Agent();
			a.parseInput("src/test/test_files/test4.txt"); // May need to check this path
			assertEquals("Print queues", "buy HIJK 100 $10.00\nsell HIJK 50 $5.00", a.printQueues());

		} catch (Exception e) {
			fail("Exception occurred.");
		}
	}

	@Test
	public void testAdditionalTransactions() {
		//we're testing further transactions to make sure exchange happens properly
		Agent a = new Agent();
		a.parseInput("src/test/test_files/test5.txt"); // May need to check this path
		assertEquals("Should be 4 stocks in buy", 4, a.sizeBuy());
		assertEquals("Should be 4 stock in sell", 4, a.sizeSell());
		a.exchange();
		assertEquals("Should be 1 stocks in buy", 1, a.sizeBuy());
		assertEquals("Should be 1 stock in sell", 1, a.sizeSell());
		assertEquals("Should be 6 stocks in transaction", 6,
				a.sizeTransaction());
	}

	@Test
	public void confirm10Transactions() {
		//we're testing further transactions to make sure exchange happens properly
		Agent a = new Agent();
		a.parseInput("src/test/test_files/test6.txt"); // May need to check this path
		assertEquals("Should be 4 stocks in buy", 3, a.sizeBuy());
		assertEquals("Should be 7 stock in sell", 7, a.sizeSell());
		a.exchange();
		assertEquals("Should be 1 stocks in buy", 1, a.sizeBuy());
		assertEquals("Should be 6 stock in sell", 6, a.sizeSell());
		assertEquals("Should be 3 stocks in transaction", 3,
				a.sizeTransaction());
	}

	//@Test
	public void test10Transactions() {
		long beginTime, endTime;
		Agent a = new Agent();

		beginTime = System.currentTimeMillis();
		a.parseInput("src/test/test_files/stocklist-1.txt");
		endTime = System.currentTimeMillis();
		System.out.print("Parse 10 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.exchange();
		endTime = System.currentTimeMillis();
		System.out.print("Exchage 10 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printQueues();
		endTime = System.currentTimeMillis();
		System.out.print("Print records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printTransactions();
		endTime = System.currentTimeMillis();
		System.out.print("Print Transactions = ");
		System.out.println(endTime - beginTime);
	}	

	@Test
	public void test100Transactions() {
		long beginTime, endTime;
		Agent a = new Agent();

		beginTime = System.currentTimeMillis();
		a.parseInput("src/test/test_files/stocklist-2.txt");
		endTime = System.currentTimeMillis();
		System.out.print("Parse 100 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.exchange();
		endTime = System.currentTimeMillis();
		System.out.print("Exchange 100 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printQueues();
		endTime = System.currentTimeMillis();
		System.out.print("Print records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printTransactions();
		endTime = System.currentTimeMillis();
		System.out.print("Print Transactions = ");
		System.out.println(endTime - beginTime);
	}

	@Test
	public void test1000Transactions() {
		long beginTime, endTime;
		Agent a = new Agent();

		beginTime = System.currentTimeMillis();
		a.parseInput("src/test/test_files/stocklist-3.txt");
		endTime = System.currentTimeMillis();
		System.out.print("Parse 1000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.exchange();
		endTime = System.currentTimeMillis();
		System.out.print("Exchange 1000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printQueues();
		endTime = System.currentTimeMillis();
		System.out.print("Print records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printTransactions();
		endTime = System.currentTimeMillis();
		System.out.print("Print Transactions = ");
		System.out.println(endTime - beginTime);
	}

	@Test
	public void test10000Transactions() {
		long beginTime, endTime;
		Agent a = new Agent();

		beginTime = System.currentTimeMillis();
		a.parseInput("src/test/test_files/stocklist-4.txt");
		endTime = System.currentTimeMillis();
		System.out.print("Parse 10000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.exchange();
		endTime = System.currentTimeMillis();
		System.out.print("Exchange 10000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printQueues();
		endTime = System.currentTimeMillis();
		System.out.print("Print records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printTransactions();
		endTime = System.currentTimeMillis();
		System.out.print("Print Transactions = ");
		System.out.println(endTime - beginTime);
	}

	/**
	 * WARNING: THIS TAKES A VERY LONG TIME.
	 */
	//@Test
	public void test100000Transactions() {
		long beginTime, endTime;
		Agent a = new Agent();

		beginTime = System.currentTimeMillis();
		a.parseInput("src/test/test_files/stocklist-5.txt");
		endTime = System.currentTimeMillis();
		System.out.print("Parse 100000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.exchange();
		endTime = System.currentTimeMillis();
		System.out.print("Exchange 100000 records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printQueues();
		endTime = System.currentTimeMillis();
		System.out.print("Print records = ");
		System.out.println(endTime - beginTime);
		beginTime = System.currentTimeMillis();
		a.printTransactions();
		endTime = System.currentTimeMillis();
		System.out.print("Print Transactions = ");
		System.out.println(endTime - beginTime);
	}


	/**
	 * WARNING: createFile() will generate 10^x string combinations.
	 * magnitude 'x' can be increased by adjusting the loop maximum.
	 * This can take considerable length of time.
	 * @throws IOException
	 */
	//@Test
	public void createFile() throws IOException {

		//create 5 files, 10, 100, 1000, 10000, 100000 in length
		for(int x = 1; x <= 5; x++) {

			int fileLength = (int) Math.pow(10, x);

			File file = new File("stocklist-" + (x) + ".txt");

			if(file.createNewFile() == false) {
				throw new IOException("File could not be created");
			}

			if(!file.exists() || !file.isFile() || !file.canWrite()) {
				throw new IOException("Invalid File");
			}

			String[] contents = new String[fileLength];
			PrintWriter output = new PrintWriter(file);

			for(int j = 0; j < fileLength; j++) {

				String alphabet = "ABC";
				Random random = new Random();

				int r = 0;
				StringBuffer stringBuff = new StringBuffer();
				String stockName = "";

				//create stock names
				for(int i = 0; i < 4; i++) {
					r = random.nextInt(alphabet.length());
					char randomChar = alphabet.charAt(r);
					stringBuff.append(randomChar);
				}
				stockName = stringBuff.toString();

				//create stock quantity
				r = random.nextInt(9);
				int stockQuantity = (r + 1) * 10;

				//create stock price
				r = random.nextInt(9);
				Double stockPrice = (double) ((r + 1) * 5);

				//create buy or sell string
				String buySell = "";
				r = random.nextInt(2);
				if(r == 0) {
					buySell = buySell.concat("buy");
				} else {
					buySell = buySell.concat("sell");
				}

				//toString
				contents[j] = (buySell + " " + stockName + " " + Integer.toString(stockQuantity) + " $" + Double.toString(stockPrice) + System.getProperty("line.separator"));
				output.print(contents[j]);
			}
			output.close();
		}
		return;
	}

}
