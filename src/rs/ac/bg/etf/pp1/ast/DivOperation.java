// generated with ast extension for cup
// version 0.8
// 17/11/2022 2:19:31


package rs.ac.bg.etf.pp1.ast;

public class DivOperation extends Mulop {

    public DivOperation () {
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
        buffer.append("DivOperation(\n");

        buffer.append(tab);
        buffer.append(") [DivOperation]");
        return buffer.toString();
    }
}