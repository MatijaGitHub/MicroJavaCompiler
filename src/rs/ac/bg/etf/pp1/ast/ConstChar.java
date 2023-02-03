// generated with ast extension for cup
// version 0.8
// 3/1/2023 18:14:20


package rs.ac.bg.etf.pp1.ast;

public class ConstChar extends Const {

    private Character c;

    public ConstChar (Character c) {
        this.c=c;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c=c;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstChar(\n");

        buffer.append(" "+tab+c);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstChar]");
        return buffer.toString();
    }
}
