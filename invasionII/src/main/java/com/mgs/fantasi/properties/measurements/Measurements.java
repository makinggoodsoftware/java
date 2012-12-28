package com.mgs.fantasi.properties.measurements;

import com.mgs.fantasi.rendering.Padding;

public class Measurements {
	public static Measurement simpleMeasurement (int measurement){
		return new SimpleMeasurement (measurement);
	}

	public static Measurement futureMeasurement() {
		return new FutureMeasurement();
	}

	public static class SimpleMeasurement implements Measurement {
		private final int measurement;

		public SimpleMeasurement(int measurement) {
			this.measurement = measurement;
		}

		@Override
		public Padding asPadding() {
			return new Padding(measurement, measurement, measurement, measurement);
		}

		@Override
		public Measurement half() {
			return new SimpleMeasurement(measurement / 2);
		}

		@Override
		public boolean isZero() {
			return measurement == 0;
		}

		@Override
		public Measurement copy() {
			return new SimpleMeasurement(measurement);
		}

		public int getIntValue() {
			return measurement;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof SimpleMeasurement)) return false;

			SimpleMeasurement that = (SimpleMeasurement) o;

			if (measurement != that.measurement) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return measurement;
		}
	}

	public static class FutureMeasurement implements Measurement {
		@Override
		public Padding asPadding() {
			return new Padding(this, this, this, this);
		}

		@Override
		public Measurement half() {
			return new FractionOfAMeasurement (this, Fractions.half());
		}

		@Override
		public boolean isZero() {
			return false;
		}

		@Override
		public Measurement copy() {
			return new FutureMeasurement();
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
		public Padding asPadding() {
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

		@Override
		public Measurement copy() {
			return new FractionOfAMeasurement((FutureMeasurement) from.copy(), fraction.copy());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof FractionOfAMeasurement)) return false;

			FractionOfAMeasurement that = (FractionOfAMeasurement) o;

			if (fraction != null ? !fraction.equals(that.fraction) : that.fraction != null) return false;
			if (from != null ? !from.equals(that.from) : that.from != null) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = from != null ? from.hashCode() : 0;
			result = 31 * result + (fraction != null ? fraction.hashCode() : 0);
			return result;
		}
	}
}