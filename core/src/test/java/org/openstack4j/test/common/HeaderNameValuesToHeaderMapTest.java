package org.openstack4j.test.common;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.header.HeaderNameValue;
import org.openstack4j.openstack.common.functions.HeaderNameValuesToHeaderMap;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * Tests HeaderNameValue based transformation
 * 
 * @author Jeremy Unruh
 *
 */
public class HeaderNameValuesToHeaderMapTest {

    private static List<HeaderNameValue> VALUES = Lists.newArrayList(
            new HeaderNameValue("Test 1", "Value 1"),
            new HeaderNameValue("Test 2", "Value 2"),
            new HeaderNameValue("Test 3", "Value 3"),
            new HeaderNameValue("Test 4", "Value 4")
    );
    
    @Test
    public void keyTest() {
        Map<String, Object> map = HeaderNameValuesToHeaderMap.INSTANCE.apply(VALUES);
        for (HeaderNameValue hnv : VALUES) {
            assertTrue(map.containsKey(hnv.getName()));
        }
    }
    
    @Test
    public void valueTest() {
        Map<String, Object> map = HeaderNameValuesToHeaderMap.INSTANCE.apply(VALUES);
        for (HeaderNameValue hnv : VALUES) {
            assertTrue(map.containsValue(hnv.getValue()));
        }
    }
    
}
