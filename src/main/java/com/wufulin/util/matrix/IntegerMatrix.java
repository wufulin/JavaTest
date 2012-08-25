package com.wufulin.util.matrix;


public class IntegerMatrix extends GenericMatrix<Integer> {

	@Override
	protected Integer add(Integer o1, Integer o2) {
		return new Integer(o1.intValue()+o2.intValue());
	}

	@Override
	protected Integer multiply(Integer o1, Integer o2) {
		return new Integer(o1.intValue()*o2.intValue());
	}

	@Override
	protected Integer zero() {
		return new Integer(0);
	}

}
