package com.huawei.openstack4j.model.common.serializer;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.Lists;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:41:42
 */
public class BooleanDeserializer extends JsonDeserializer<Boolean> {

	ArrayList<String> trueValues = Lists.newArrayList("Y", "1", "YES");
	ArrayList<String> falseValues = Lists.newArrayList("N", "0", "NO");

	@Override
	public Boolean deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String value = parser.getText();
		
		if (value != null) {
			if (trueValues.contains(value.toUpperCase())) {
				return true;
			}
			
			if (falseValues.contains(value.toUpperCase())) {
				return false;
			}
		}

		return null;
	}
}
