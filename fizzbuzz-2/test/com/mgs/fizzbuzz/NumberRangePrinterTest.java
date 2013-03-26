package com.mgs.fizzbuzz;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.PrintStream;

import static com.mgs.fizzbuzz.TranslationResult.failure;
import static com.mgs.fizzbuzz.TranslationResult.successful;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NumberRangePrinterTest {

	@Mock
	private NumberTranslator numberTranslator;
	@Mock
	private PrintStream printStream;

	@BeforeMethod
	protected void setUp() throws Exception {
		initMocks(this);

		when(numberTranslator.translate(1)).thenReturn(failure());
		when(numberTranslator.translate(2)).thenReturn(failure());
		when(numberTranslator.translate(3)).thenReturn(successful("lucky"));
		when(numberTranslator.translate(4)).thenReturn(failure());
		when(numberTranslator.translate(5)).thenReturn(successful("buzz"));
		when(numberTranslator.translate(6)).thenReturn(successful("fizz"));
		when(numberTranslator.translate(13)).thenReturn(successful("lucky"));
		when(numberTranslator.translate(14)).thenReturn(failure());
		when(numberTranslator.translate(15)).thenReturn(successful("fizzbuzz"));
		when(numberTranslator.translate(16)).thenReturn(failure());
	}

	@Test
	public void shouldPrintCorrectly1() {
		NumberRangePrinter numberRangePrinter = new NumberRangePrinter(numberTranslator, printStream);
		numberRangePrinter.print(1, 1);

		verify(printStream).print("1 ");
	}

	@Test
	public void shouldPrintCorrectlyFrom1To2() {
		NumberRangePrinter numberRangePrinter = new NumberRangePrinter(numberTranslator, printStream);
		numberRangePrinter.print(1, 2);

		verify(printStream).print("1 ");
		verify(printStream).print("2 ");
	}

	@Test
	public void shouldPrintCorrectlyFrom1To3() {
		NumberRangePrinter numberRangePrinter = new NumberRangePrinter(numberTranslator, printStream);
		numberRangePrinter.print(1, 3);

		verify(printStream).print("1 ");
		verify(printStream).print("2 ");
		verify(printStream).print("lucky ");
	}

	@Test
	public void shouldPrintCorrectlyFrom1To6() {
		NumberRangePrinter numberRangePrinter = new NumberRangePrinter(numberTranslator, printStream);
		numberRangePrinter.print(1, 6);

		verify(printStream).print("1 ");
		verify(printStream).print("2 ");
		verify(printStream).print("lucky ");
		verify(printStream).print("4 ");
		verify(printStream).print("buzz ");
		verify(printStream).print("fizz ");
	}

	@Test
	public void shouldPrintCorrectlyFrom14To16() {
		NumberRangePrinter numberRangePrinter = new NumberRangePrinter(numberTranslator, printStream);
		numberRangePrinter.print(13, 16);

		verify(printStream).print("lucky ");
		verify(printStream).print("14 ");
		verify(printStream).print("fizzbuzz ");
		verify(printStream).print("16 ");
	}
}
