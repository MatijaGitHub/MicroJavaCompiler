// generated with ast extension for cup
// version 0.8
// 17/11/2022 2:19:31


package rs.ac.bg.etf.pp1.ast;

public class NoDesignatorList extends DesignatorList {

    public NoDesignatorList () {
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
        buffer.append("NoDesignatorList(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignatorList]");
        return buffer.toString();
    }
}