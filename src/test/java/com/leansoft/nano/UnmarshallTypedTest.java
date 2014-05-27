package com.leansoft.nano;

import com.leansoft.nano.ElementInheritanceTest.Aaa;
import com.leansoft.nano.ElementInheritanceTest.Bbb;
import com.leansoft.nano.ElementInheritanceTest.BbbJunior;
import com.leansoft.nano.annotation.Element;
import com.leansoft.nano.annotation.RootElement;
import com.leansoft.nano.annotation.SeeAlso;
import com.leansoft.nano.annotation.Type;
import com.leansoft.nano.another.Ccc;
import com.leansoft.nano.exception.MappingException;
import com.leansoft.nano.exception.ReaderException;

import junit.framework.TestCase;

public class UnmarshallTypedTest extends TestCase {

    @RootElement(namespace = "http://docs.oasis-open.org/bias/ns/bias-1.0/")
    public static class Aaa {
        @Element
        public Bbb bbb;

        @Element
        public Ccc ccc;
        
    }

    @SeeAlso(value={BbbJunior.class})
    public static class Bbb {
        @Element
        public String bbbData;
    }

    @Type(name = "Bbb_Junior")
    public static class BbbJunior extends Bbb {
        @Element
        public String bbbJData;
    }
    
    public static String buffer = "<?xml version=\"1.0\" encoding=\"utf-8\"?><n0:aaa xmlns:n0=\"http://docs.oasis-open.org/bias/ns/bias-1.0/\"><n0:bbb n1:type=\"n0:Bbb_Junior\" xmlns:n1=\"http://www.w3.org/2001/XMLSchema-instance\"><n0:bbbData>Dump for Bbb</n0:bbbData><n0:bbbJData>Dump for Bbb Junior</n0:bbbJData></n0:bbb></n0:aaa>";
    
    public void testUnmarshallTypedElement() throws ReaderException, MappingException{
        
        IReader xmlReader = NanoFactory.getXMLReader();
        Aaa target = xmlReader.read(Aaa.class, buffer);

        assertEquals(BbbJunior.class, target.bbb.getClass());

    }
    
}
