package com.mgs.fantasi.ui.wireframe;

public class Margin {

	private final int top;
	private final int right;
	private final int bottom;
	private final int left;

	public Margin(int top, int right, int bottom, int left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public static Margin noMargin() {
		return new Margin(0, 0, 0, 0);
	}

	public boolean isEmpty (){
		return top == 0 && right == 0 && bottom == 0 && left == 0;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}

	public int getLeft() {
		return left;
	}

	@Override
	public String toString() {
		return "Margin{" +
				"bottom=" + bottom +
				", top=" + top +
				", right=" + right +
				", left=" + left +
				'}';
	}
}
