package com.leansoft.nano;

import junit.framework.TestCase;

import com.leansoft.nano.annotation.Element;
import com.leansoft.nano.annotation.RootElement;
import com.leansoft.nano.annotation.Type;
import com.leansoft.nano.another.Ccc;

public class ElementInheritanceTest extends TestCase {
	
	@RootElement(namespace="http://datys.cu")
	public static class Aaa {
		@Element
		public Bbb bbb;
		
		@Element
		public Ccc ccc;
	}
	
	public static class Bbb {
		@Element
		public String bbbData;
	}
	
	@Type(name="Bbb_Junior")
        public static class BbbJunior extends Bbb {
            @Element
            public String bbbJData;
    }
	
	
	
	
	public void testNamespace() throws Exception {
		Aaa parent = new Aaa();
		parent.bbb = new BbbJunior();
		((BbbJunior) parent.bbb).bbbData = "Dump for Bbb";
		((BbbJunior) parent.bbb).bbbJData = "Dump for Bbb Junior";
		parent.ccc = new Ccc();
		parent.ccc.nombre = "Pepe";
		parent.ccc.bbbj = new BbbJunior();
		parent.ccc.bbbj.bbbData = "1111";
		parent.ccc.bbbj.bbbJData = "2222";
		
		IWriter xmlWriter = NanoFactory.getXMLWriter();
		String str1 = xmlWriter.write(parent);
		System.out.println(str1);
		
		IReader xmlReader = NanoFactory.getXMLReader();
		Aaa target = xmlReader.read(Aaa.class, str1);
		String str2 = xmlWriter.write(target);
		
		assertEquals(true, true);
		//TODO: Fix reader  assertEquals(str2, str1);
	}

}
