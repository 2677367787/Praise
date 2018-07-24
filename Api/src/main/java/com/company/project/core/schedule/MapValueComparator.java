package com.company.project.core.schedule;

import java.util.Comparator;
import java.util.Map;

class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

		return me2.getValue() - me1.getValue();
	}
}