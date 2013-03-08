package ch.epfl.collections.set;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class SetTest {

	@Test
    public void test(){
    	Set<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(5);
        set.add(29);
        set.add(3);
        set.add(7);
        set.add(17);
        set.add(35);
        assertEquals(true, set.contains(7));
        assertEquals(false, set.contains(45));
        set.remove(35);
        assertEquals(false, set.contains(35));
        set.remove(10);
        assertEquals(false, set.contains(10));
        assertEquals(5, set.size());
        assertEquals(true, set.contains(7));
    }
	
	@Test
	public void test2(){
		Set<String> set = new TreeSet<String>();
		set.add("Salut");
		set.add("Mon");
		set.add("Nom");
		set.add("Est");
		set.add("Gaylor");
		
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("----------");
		
		set.remove("Salut");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("----------");
		
		set.remove("Nom");
		set.remove("Mon");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("----------");
		
		set.remove("Est");
		set.remove("Gaylor");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("----------");
		
		set.add("Marion");
		for(Iterator<String> i = set.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		System.out.println("----------");
	}
	
	@Test(expected=java.util.NoSuchElementException.class)
	public void testNext(){
		Set<String> set = new TreeSet<>();
		set.add("Salut");
		Iterator<String> i = set.iterator();
		i.next();
		i.next();
	}

}
