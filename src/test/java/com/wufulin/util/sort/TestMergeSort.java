package com.wufulin.util.sort;

import java.util.Arrays;

public class TestMergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list={2,3,2,5,6,1,-2,3,14,12};
		MergeSort.mergeSort(list);
		System.out.println(Arrays.toString(list));
	}

}
