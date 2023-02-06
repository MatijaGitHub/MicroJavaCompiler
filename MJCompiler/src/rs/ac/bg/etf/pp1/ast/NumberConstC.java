// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class NumberConstC extends NumberConst {

    private Integer len;

    public NumberConstC (Integer len) {
        this.len=len;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len=len;
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
        buffer.append("NumberConstC(\n");

        buffer.append(" "+tab+len);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberConstC]");
        return buffer.toString();
    }
}
