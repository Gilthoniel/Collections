package ch.epfl.collections;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testDeclaration(){
		LinkedList<String> a = new LinkedList<String>();
		a.add("Hello");
		
		assertEquals("Hello", a.get(0));
	}
	
	@Test
	public void testRemove(){
		LinkedList<String> a = new LinkedList<String>();
		a.add("Hello");
		a.add("Test");
		a.remove(0);
		
		assertEquals("Test", a.get(0));
	}
	
	@Test(expected=java.util.NoSuchElementException.class)
	public void testGeneral(){
		LinkedList<String> a = new LinkedList<String>();
		a.add("Hello");
		a.add(", my name");
		a.add(" is Brian");
		a.add(" and I'm");
		a.remove(2);
		
		Iterator<String> i = a.iterator();
		assertEquals("Hello", i.next());
		i.remove();
		assertEquals(", my name", i.next());
		assertEquals(" and I'm", i.next());
		assertEquals(", my name", a.get(1));
		i.next();
	}
	
	@Test
	public void testGeneral2(){
		LinkedList<String> a = new LinkedList<String>();
		a.add("Test 1");
		a.add("Test 2");
		a.add("Test 3");
		a.set(2, "Test 4");
		assertEquals("Test 4", a.get(2));
		a.remove(0);
		assertEquals("Test 2", a.get(0));
		a.remove(1);
		
		for(Iterator<String> i = a.iterator(); i.hasNext();){
			assertEquals("Test 2", i.next());
			i.remove();
		}
		assertEquals(true, a.isEmpty());
	}

}
