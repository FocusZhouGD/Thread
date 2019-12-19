package com.company.base.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component c) {
        this.children.add(c);
    }

    @Override
    public void remove(Component c) {
        this.remove(c);
    }

    @Override
    public void display(int depth) {
        String prefix = "";
        for (int i =0;i<depth;i++){
            prefix += "-";
        }
        System.out.println(prefix+name);
        for (Component child : children) {
            child.display(depth+2);
        }

    }

}
