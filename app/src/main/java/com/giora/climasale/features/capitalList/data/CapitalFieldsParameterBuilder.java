package com.giora.climasale.features.capitalList.data;

import com.giora.climasale.features.capitalList.domain.CapitalProperty;

public class CapitalFieldsParameterBuilder implements ICapitalFieldsParameterBuilder {

	@Override
	public String build(CapitalProperty[] capitalProperties) {
		StringBuilder stringBuilder = new StringBuilder(map(capitalProperties[0]));
		for (int capitalPropertyIndex = 1; capitalPropertyIndex < capitalProperties.length; ++capitalPropertyIndex) {
			stringBuilder.append(';');
			stringBuilder.append(map(capitalProperties[capitalPropertyIndex]));
		}
		return stringBuilder.toString();
	}

	private String map(CapitalProperty capitalProperty) {
		switch (capitalProperty) {
			case City:
				return "capital";
			case Country:
				return "name";
			case FlagImageUrl:
				return "flag";
			default:
				return "";
		}
	}
}
