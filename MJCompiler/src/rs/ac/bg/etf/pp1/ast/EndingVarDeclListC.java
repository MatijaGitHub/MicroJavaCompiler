// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class EndingVarDeclListC extends VarDeclList {

    private EndingVarDeclList EndingVarDeclList;

    public EndingVarDeclListC (EndingVarDeclList EndingVarDeclList) {
        this.EndingVarDeclList=EndingVarDeclList;
        if(EndingVarDeclList!=null) EndingVarDeclList.setParent(this);
    }

    public EndingVarDeclList getEndingVarDeclList() {
        return EndingVarDeclList;
    }

    public void setEndingVarDeclList(EndingVarDeclList EndingVarDeclList) {
        this.EndingVarDeclList=EndingVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EndingVarDeclList!=null) EndingVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EndingVarDeclList!=null) EndingVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EndingVarDeclList!=null) EndingVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EndingVarDeclListC(\n");

        if(EndingVarDeclList!=null)
            buffer.append(EndingVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EndingVarDeclListC]");
        return buffer.toString();
    }
}
