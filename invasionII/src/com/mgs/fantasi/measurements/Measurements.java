package com.mgs.fantasi.measurements;

import com.mgs.fantasi.ui.wireframe.Margin;

public class Measurements {
	public static Measurement simpleMeasurement (int measurement){
		return new SimpleMeasurement (measurement);
	}

	public static Measurement unit() {
		return new FutureMeasurement();
	}

	public static class SimpleMeasurement implements Measurement {
		private final int measurement;

		public SimpleMeasurement(int measurement) {
			this.measurement = measurement;
		}

		@Override
		public Margin asMargin() {
			return new Margin(measurement, measurement, measurement, measurement);
		}

		@Override
		public Measurement half() {
			return new SimpleMeasurement(measurement / 2);
		}

		@Override
		public boolean isZero() {
			return measurement == 0;
		}

		public int getIntValue() {
			return measurement;
		}
	}

	public static class FutureMeasurement implements Measurement {
		@Override
		public Margin asMargin() {
			return new Margin(this, this, this, this);
		}

		@Override
		public Measurement half() {
			return new FractionOfAMeasurement (this, Fractions.half());
		}

		@Override
		public boolean isZero() {
			return false;
		}
	}

	private static class FractionOfAMeasurement extends FutureMeasurement {
		private final FutureMeasurement from;
		private final Fraction fraction;

		public FractionOfAMeasurement(FutureMeasurement from, Fraction fraction) {
			this.from = from;
			this.fraction = fraction;
		}

		@Override
		public Margin asMargin() {
			return null;
		}

		@Override
		public Measurement half() {
			return new FractionOfAMeasurement (this, Fractions.half());
		}

		@Override
		public boolean isZero() {
			return false;
		}
	}
}
