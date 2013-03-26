package com.mgs.fizzbuzz;

public class TranslationResult {
	private final boolean result;
	private final String translation;

	public static TranslationResult successful(String translation) {
		return new TranslationResult(true, translation);
	}

	public static TranslationResult failure() {
		return new TranslationResult(false, "");
	}

	public TranslationResult(boolean result, String translation) {

		this.result = result;
		this.translation = translation;
	}

	public boolean wasSuccessful() {
		return result;
	}

	public String getTranslation() {
		return translation;
	}
}
