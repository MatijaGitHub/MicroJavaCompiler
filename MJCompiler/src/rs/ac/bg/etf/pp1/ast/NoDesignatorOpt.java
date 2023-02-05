// generated with ast extension for cup
// version 0.8
// 5/1/2023 18:32:34


package rs.ac.bg.etf.pp1.ast;

public class NoDesignatorOpt extends DesignatorOpt {

    public NoDesignatorOpt () {
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
        buffer.append("NoDesignatorOpt(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignatorOpt]");
        return buffer.toString();
    }
}
