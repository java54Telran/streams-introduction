package telran.streams;

import java.util.Random;

record MinMaxAmount(int min, int max, int amount) {}
public class SportLoto {
	static int nVariants = 1;
	public static void main(String[] args) {
		//printing out sportloto random numbers
		//application with command line mandatory arguments
		//first argument minimal inclusive value
		//second argument maximal inclusive value
		//third argument amount of the random numbers
		//Example: java -jar sportLoto 1 49 7
		// 3, 10, 49, 1, 40, 6, 7
		
		try {
			MinMaxAmount mma = getMinMaxAmount(args);
			checkArguments(mma);
			nVariants = getN_variants(args);
			displayLottoNumbers(mma);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static int getN_variants(String[] args) throws Exception {
		int res = 1;
		if (args.length == 4) {
			try {
				res = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				throw new Exception("number of the variants must be a number");
			}
			if (res < 1) {
				throw new Exception("number of the variants must be greater than 0");
			}
		}
		return res;
	}

	private static void displayLottoNumbers(MinMaxAmount mma) {
		Random gen = new Random();
		for (int i = 0; i < nVariants; i++) {
			System.out.println();
			gen.ints(mma.min(), mma.max() + 1).distinct().limit(mma.amount())
					.forEach(n -> System.out.print(n + " "));
		}
		
	}

	private static void checkArguments(MinMaxAmount mma) throws Exception{
		int min = mma.min();
		if (min < 0) {
			throw new Exception("minimal number must be greater than 0");
		}
		int max = mma.max();
		if (max <= min) {
			throw new Exception("maximal number must be greater than minimal number");
		}
		int amount = mma.amount();
		int rangeLength = max - min + 1;
		if (amount < 1 || amount > rangeLength) {
			throw new Exception(String.format("amount must be greater than 0"
					+ " and not greater than range length %d", rangeLength));
		}
		
	}

	private static MinMaxAmount getMinMaxAmount(String[] args) throws Exception{
		int min, max, amount;
		if(args.length < 3) {
			throw new Exception("Usage: first argument - minimal number,"
					+ " second argument - maximal number, third argument - amount of numbers");
		}
		try {
			min = Integer.parseInt(args[0]);
			max = Integer.parseInt(args[1]);
			amount = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			throw new Exception("all arguments must be the numbers");
		}
		
		return new MinMaxAmount(min, max, amount);
	}

}
