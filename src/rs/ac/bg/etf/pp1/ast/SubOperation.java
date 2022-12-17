// generated with ast extension for cup
// version 0.8
// 17/11/2022 15:59:0


package rs.ac.bg.etf.pp1.ast;

public class SubOperation extends Addop {

    public SubOperation () {
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
        buffer.append("SubOperation(\n");

        buffer.append(tab);
        buffer.append(") [SubOperation]");
        return buffer.toString();
    }
}
