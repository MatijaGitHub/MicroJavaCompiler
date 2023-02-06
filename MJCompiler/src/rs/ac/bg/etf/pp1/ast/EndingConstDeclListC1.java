// generated with ast extension for cup
// version 0.8
// 6/1/2023 16:41:21


package rs.ac.bg.etf.pp1.ast;

public class EndingConstDeclListC1 extends ConstDeclList {

    private EndingConstDeclList EndingConstDeclList;

    public EndingConstDeclListC1 (EndingConstDeclList EndingConstDeclList) {
        this.EndingConstDeclList=EndingConstDeclList;
        if(EndingConstDeclList!=null) EndingConstDeclList.setParent(this);
    }

    public EndingConstDeclList getEndingConstDeclList() {
        return EndingConstDeclList;
    }

    public void setEndingConstDeclList(EndingConstDeclList EndingConstDeclList) {
        this.EndingConstDeclList=EndingConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EndingConstDeclList!=null) EndingConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EndingConstDeclList!=null) EndingConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EndingConstDeclList!=null) EndingConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EndingConstDeclListC1(\n");

        if(EndingConstDeclList!=null)
            buffer.append(EndingConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EndingConstDeclListC1]");
        return buffer.toString();
    }
}
