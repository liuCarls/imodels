package com.carl.ienum.m2;

/**
 * Created by user on 2018/8/9.
 */
public enum Color {
    Red(1, 102, 1),
    Blue(1, 3, 2);


    private int chain;
    private int funId;
    private int sort;
    private Color(int chain, int funId, int sort) {
        this.chain = chain;
        this.funId = funId;
        this.sort = sort;
    }
    public int getSort() {
        return sort;
    }

    public Color getColor(int chain, int funId) {
        for (Color c : Color.values()) {
            if(c.chain==chain&&c.funId==funId){
                return c;
            }
        }
        return null;
    }

    public void execute(){
        System.out.print("execute");
    }

    /**
     * 使用关键字enum定义的枚举类型，在编译期后，也将转换成为一个实实在在的类，
     * 而在该类中，会存在每个在枚举类型中定义好变量的对应实例对象，
     * 如上述的MONDAY枚举类型对应public static final Day MONDAY;，
     * 同时编译器会为该类创建两个方法，分别是values()和valueOf()。
     * https://blog.csdn.net/javazejian/article/details/71333103
     */
}
