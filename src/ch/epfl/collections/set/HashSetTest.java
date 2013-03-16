package ch.epfl.collections.set;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class HashSetTest {

	@Test
	public void test() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		assertEquals(true, set.contains(3));
		set.remove(3);
		assertEquals(false, set.contains(3));
	}
	
	@Test(expected=java.util.NoSuchElementException.class)
	public void testIterateur(){
		Set<String> set = new HashSet<String>();
		set.add("Mon ");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			i.next();
			i.next();
		}
	}
	
	@Test
	public void testIterator2(){
		Set<String> set = new HashSet<String>();
		set.add("Mon ");
		set.add("Nom ");
		set.add("Est ");
		set.add("Gaylor ");
		
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("------------");
		
		set.remove("Gaylor ");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("------------");
		
		set.remove("Mon ");
		set.remove("Nom ");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		
		assertEquals(1, set.size());
	}

}
