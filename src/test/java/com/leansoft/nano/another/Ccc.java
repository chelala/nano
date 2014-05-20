package com.leansoft.nano.another;

import java.util.List;

import com.leansoft.nano.ElementInheritanceTest;
import com.leansoft.nano.annotation.Element;

public class Ccc {
    @Element
    public String nombre;
    
    @Element
    public ElementInheritanceTest.BbbJunior bbbj;
    
    @Element(name = "Type")
    public List<SingleTypeType> type;

}
