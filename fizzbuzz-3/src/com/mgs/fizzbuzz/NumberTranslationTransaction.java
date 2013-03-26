package com.mgs.fizzbuzz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberTranslationTransaction {
	private final Counter counter = new Counter();

	public String process(int valueToPrint, TranslationResult translationResult) {
		if (translationResult.wasSuccessful()) {
			String translation = translationResult.getTranslation();
			counter.addSuccess(translation);
			return translation;
		} else {
			counter.addFailure();
			return String.valueOf(valueToPrint);
		}
	}

	public List<CountRecord> close() {
		List<CountRecord> records = new ArrayList<CountRecord>();

		records.add(new CountRecord("fizz", counter.getCount("fizz")));
		records.add(new CountRecord("buzz", counter.getCount("buzz")));
		records.add(new CountRecord("fizzbuzz", counter.getCount("fizzbuzz")));
		records.add(new CountRecord("lucky", counter.getCount("lucky")));
		records.add(new CountRecord("integer", counter.failures));

		return records;
	}

	private static class Counter {
		private int failures = 0;
		private Map<String, Integer> successes = new HashMap<String, Integer>();

		private int getCount(String toCount) {
			Integer count = successes.get(toCount);
			return count == null ? 0 : count;
		}

		public void addSuccess(String label) {
			int current = getCount(label);
			if (current == 0) {
				successes.put(label, 1);
			} else {
				successes.remove(label);
				successes.put(label, ++current);
			}
		}

		public void addFailure() {
			failures++;
		}
	}

	public static class CountRecord {
		private final String label;
		private final int count;

		private CountRecord(String label, int count) {
			this.label = label;
			this.count = count;
		}

		public String getLabel() {
			return label;
		}

		public int getCount() {
			return count;
		}
	}
}
