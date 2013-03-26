package com.mgs.fizzbuzz;

import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MathExpertTest extends TestCase {
	@Test
	public void positiveTestingIsMultipleOf() {
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(1, 1));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(2, 2));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(3, 3));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(4, 4));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(4, 2));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(5, 5));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(15, 5));
		Assert.assertTrue(new MathExpertImpl().isMultipleOf(15, 3));
	}

	@Test
	public void negativeTestingIsMultipleOf() {
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(1, 2));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(2, 3));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(3, 5));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(4, 3));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(4, 5));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(5, 2));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(5, 3));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(15, 6));
		Assert.assertFalse(new MathExpertImpl().isMultipleOf(15, 9));
	}
}
