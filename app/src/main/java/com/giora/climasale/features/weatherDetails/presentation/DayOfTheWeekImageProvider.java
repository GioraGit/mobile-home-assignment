package com.giora.climasale.features.weatherDetails.presentation;

import com.giora.climasale.R;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DayOfTheWeekImageProvider implements IDayOfTheWeekImageProvider {

	private final static int[] dayOfTheWeekImages = new int[]{
			R.drawable.sunday,
			R.drawable.monday,
			R.drawable.tuesday,
			R.drawable.wednesday,
			R.drawable.thursday,
			R.drawable.friday,
			R.drawable.saturday,
	};

	private static final Map<String, Integer> dayToIndexMap = createDayToIndexMap();

	private static Map<String, Integer> createDayToIndexMap() {
		Map<String, Integer> dayToIndexMap = new HashMap<>(7);
		dayToIndexMap.put("Sunday", 0);
		dayToIndexMap.put("Monday", 1);
		dayToIndexMap.put("Tuesday", 2);
		dayToIndexMap.put("Wednesday", 3);
		dayToIndexMap.put("Thursday", 4);
		dayToIndexMap.put("Friday", 5);
		dayToIndexMap.put("Saturday", 6);
		return Collections.unmodifiableMap(dayToIndexMap);
	}

	@Override
	public int provideImage(int dayIndex) {
		int currentDayOfTheWeekIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		int requestedDayOfTheWeekIndex = (currentDayOfTheWeekIndex + dayIndex) % 7;
		return dayOfTheWeekImages[requestedDayOfTheWeekIndex];
	}
}
