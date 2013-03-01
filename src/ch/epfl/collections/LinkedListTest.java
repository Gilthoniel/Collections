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

}
