package telran.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StreamIntroductionMethods {
static public int sumIf(int ar[], Predicate<Integer> predicate) {
	return Arrays.stream(ar).filter(n -> predicate.test(n)).sum();
}
static public int sumDistinct(int ar[]) {
	return Arrays.stream(ar).distinct().sum();
}
static public int maxIf(int ar[], Predicate<Integer> predicate) {
	return Arrays.stream(ar).filter(n -> predicate.test(n)).max()
			.orElseThrow(() -> new NoSuchElementException("empty stream"));
}
static public int[] sortDistinct(int ar[]) {
	return Arrays.stream(ar).distinct().sorted().toArray();
}
static public void forEachIf(int ar[],
		Predicate<Integer> ifPredicate, Consumer<Integer> forEachMethod) {
	Arrays.stream(ar).filter(n -> ifPredicate.test(n))
	.forEach(n -> forEachMethod.accept(n));
}
static public int[] getRandomArray(int fromInclusive, int toExclusive, int nNumbers) {
	return new Random().ints(nNumbers, fromInclusive, toExclusive).toArray();
}
static public void displayShuffle(int ar[]) {
	//prints out a given array in shuffled order
	// example, array: [10, 20, 30,40], possible shuffling printing 30, 10, 40, 20
	//no sorting
	//no additional arrays, no any collections, no using standard shuffle method
	//one code line
	new Random().ints(0,ar.length).distinct().limit(ar.length)
	.forEach(i -> System.out.print(ar[i] + " "));
	
}
static public MinMaxAvg getMinMaxAvg( int[] ar ) {
	IntSummaryStatistics stats = Arrays.stream(ar).summaryStatistics(); 
	return new MinMaxAvg(stats.getMin(), stats.getMax(), stats.getAverage());
}
}
