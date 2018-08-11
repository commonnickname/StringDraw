package utils;

import java.util.ArrayList;

import utils.datatypes.CLine;
import utils.datatypes.Pin;

/*
 * Utility Functions for custom data types that involve different data types;
 */

public class CUtils {
	// extracts an ArrayList<Pin> of unique Pins from an ArrayList<CLine>
	public static ArrayList<Pin> extractUniquePins(ArrayList<CLine> lineList){
		ArrayList<Pin> pinList = new ArrayList<Pin>();
		for(CLine l: lineList) {
			if (!pinList.contains(l.from)) pinList.add(l.from);
			if (!pinList.contains(l.to)) pinList.add(l.to);
		}
		return pinList;
	}
}
