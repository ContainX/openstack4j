package org.openstack4j.openstack.common;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelemetryDateDeserializerTest {

   private ObjectMapper mapper;
   private TelemetryDateDeserializer deserializer;

   @BeforeSuite
   public void setup() {
      mapper = new ObjectMapper();
      deserializer = new TelemetryDateDeserializer();
   }

   @Test
   public void testShortDateFormatDeserialize() throws JsonParseException, IOException, ParseException {
      Date actual = deserializer.deserialize(getParser("2017-06-28T14:00:00"), mapper.getDeserializationContext());

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date expected = sdf.parse("2017-06-28T14:00:00.000");

      assertEquals(actual, expected);
   }
   
   @Test
   public void testLongDateFormatDeserialize() throws JsonParseException, IOException, ParseException {
      Date actual = deserializer.deserialize(getParser("2017-06-28T14:00:00.123000"), mapper.getDeserializationContext());

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date expected = sdf.parse("2017-06-28T14:00:00.123");

      assertEquals(actual, expected);
   }

   private JsonParser getParser(String dateString) throws JsonParseException, IOException {
      JsonParser parser = mapper.getFactory().createParser(new ByteArrayInputStream(String
            .format("{\"date\":\"%s\"}", dateString).getBytes(StandardCharsets.UTF_8)));
      parser.nextToken();
      parser.nextToken();
      parser.nextToken();
      return parser;
   }
}
