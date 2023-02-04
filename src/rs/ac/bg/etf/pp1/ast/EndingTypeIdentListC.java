// generated with ast extension for cup
// version 0.8
// 4/1/2023 1:1:55


package rs.ac.bg.etf.pp1.ast;

public class EndingTypeIdentListC extends TypeIdentList {

    private EndingTypeIdentList EndingTypeIdentList;

    public EndingTypeIdentListC (EndingTypeIdentList EndingTypeIdentList) {
        this.EndingTypeIdentList=EndingTypeIdentList;
        if(EndingTypeIdentList!=null) EndingTypeIdentList.setParent(this);
    }

    public EndingTypeIdentList getEndingTypeIdentList() {
        return EndingTypeIdentList;
    }

    public void setEndingTypeIdentList(EndingTypeIdentList EndingTypeIdentList) {
        this.EndingTypeIdentList=EndingTypeIdentList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EndingTypeIdentList!=null) EndingTypeIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EndingTypeIdentList!=null) EndingTypeIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EndingTypeIdentList!=null) EndingTypeIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EndingTypeIdentListC(\n");

        if(EndingTypeIdentList!=null)
            buffer.append(EndingTypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EndingTypeIdentListC]");
        return buffer.toString();
    }
}
