// generated with ast extension for cup
// version 0.8
// 17/11/2022 15:59:0


package rs.ac.bg.etf.pp1.ast;

public class GrtEqOp extends Relop {

    public GrtEqOp () {
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
        buffer.append("GrtEqOp(\n");

        buffer.append(tab);
        buffer.append(") [GrtEqOp]");
        return buffer.toString();
    }
}
