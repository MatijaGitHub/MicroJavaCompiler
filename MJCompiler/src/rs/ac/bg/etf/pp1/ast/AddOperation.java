// generated with ast extension for cup
// version 0.8
// 6/1/2023 20:57:28


package rs.ac.bg.etf.pp1.ast;

public class AddOperation extends Addop {

    public AddOperation () {
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
        buffer.append("AddOperation(\n");

        buffer.append(tab);
        buffer.append(") [AddOperation]");
        return buffer.toString();
    }
}
