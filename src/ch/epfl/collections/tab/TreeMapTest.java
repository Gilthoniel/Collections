package ch.epfl.collections.tab;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeMapTest {

	@Test
	public void test() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		assertEquals(true, map.isEmpty());
		
		map.put("a", 5);
		map.put("b", 6);
		map.put("c", 7);
		
		assertEquals(false, map.isEmpty());
		
		assertEquals(5, (int) map.get("a"));
		assertEquals(6, (int) map.get("b"));
		assertEquals(7, (int) map.get("c"));
		assertEquals(false, map.containsKey("d"));
		assertEquals(true, map.containsKey("a"));
		
		map.remove("b");
		assertEquals(5, (int) map.get("a"));
		assertEquals(7, (int) map.get("c"));
		assertEquals(false, map.containsKey("b"));
		
		map.remove("a");
		map.remove("c");
		assertEquals(true, map.isEmpty());
	}

}
