// generated with ast extension for cup
// version 0.8
// 3/1/2023 18:14:20


package rs.ac.bg.etf.pp1.ast;

public class ConstBool extends Const {

    private Boolean b;

    public ConstBool (Boolean b) {
        this.b=b;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b=b;
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
        buffer.append("ConstBool(\n");

        buffer.append(" "+tab+b);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstBool]");
        return buffer.toString();
    }
}
