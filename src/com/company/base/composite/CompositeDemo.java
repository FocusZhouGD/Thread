package com.company.base.composite;

public class CompositeDemo {
    public static void main(String[] args) {
       Composite root =new Composite("root");
       root.add(new Leaf("Leaf A"));
       root.add(new Leaf("Leaf B "));

       Composite com=new Composite("Composite X");
       com.add(new Leaf("Leaf XA"));
       com.add(new Leaf("Leaf XB"));

       root.add(com);

       Composite com2=new Composite("Composite XY");
       com2.add(new Leaf("Leaf XYA"));
       com2.add(new Leaf("Leaf XYB"));

       root.add(com2);

       Leaf leaf=new Leaf("Leaf D");
       root.add(leaf);

       root.display(1);

        System.out.println();


        root.remove(leaf);
        root.display(1);








    }
}
