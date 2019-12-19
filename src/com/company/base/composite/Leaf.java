package com.company.base.composite;

public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component c) {
        System.out.println("can not add to a leaf");
    }

    @Override
    public void remove(Component c) {
        System.out.println("can not remove from a leaf");
    }

    @Override
    public void display(int depth) {
        String prefix = "";
        for (int i =0;i<depth;i++){
            prefix += "-";
        }
        System.out.println(prefix+name);
    }
}
