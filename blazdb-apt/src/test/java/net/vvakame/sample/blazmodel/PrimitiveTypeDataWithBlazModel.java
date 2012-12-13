package net.vvakame.sample.blazmodel;

import net.vvakame.blaz.annotation.BlazModel;

/**
 * A class with fields for all the primitive types.
 * @author vvakame
 */
@BlazModel
public class PrimitiveTypeDataWithBlazModel {

	boolean bool;

	char c;

	byte b;

	short s;

	int i;

	long l;

	float f;

	double d;


	/**
	 * @return the bool
	 */
	public boolean isBool() {
		return bool;
	}

	/**
	 * @param bool
	 *            the bool to set
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/**
	 * @return the c
	 */
	public char getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(char c) {
		this.c = c;
	}

	/**
	 * @return the b
	 */
	public byte getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(byte b) {
		this.b = b;
	}

	/**
	 * @return the s
	 */
	public short getS() {
		return s;
	}

	/**
	 * @param s
	 *            the s to set
	 */
	public void setS(short s) {
		this.s = s;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i
	 *            the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the l
	 */
	public long getL() {
		return l;
	}

	/**
	 * @param l
	 *            the l to set
	 */
	public void setL(long l) {
		this.l = l;
	}

	/**
	 * @return the f
	 */
	public float getF() {
		return f;
	}

	/**
	 * @param f
	 *            the f to set
	 */
	public void setF(float f) {
		this.f = f;
	}

	/**
	 * @return the d
	 */
	public double getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(double d) {
		this.d = d;
	}
}
