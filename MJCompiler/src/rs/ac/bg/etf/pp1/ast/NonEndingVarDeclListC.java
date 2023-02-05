// generated with ast extension for cup
// version 0.8
// 5/1/2023 18:32:34


package rs.ac.bg.etf.pp1.ast;

public class NonEndingVarDeclListC extends VarDeclList {

    private NonEndingVarDeclList NonEndingVarDeclList;
    private VarDeclList VarDeclList;

    public NonEndingVarDeclListC (NonEndingVarDeclList NonEndingVarDeclList, VarDeclList VarDeclList) {
        this.NonEndingVarDeclList=NonEndingVarDeclList;
        if(NonEndingVarDeclList!=null) NonEndingVarDeclList.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public NonEndingVarDeclList getNonEndingVarDeclList() {
        return NonEndingVarDeclList;
    }

    public void setNonEndingVarDeclList(NonEndingVarDeclList NonEndingVarDeclList) {
        this.NonEndingVarDeclList=NonEndingVarDeclList;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonEndingVarDeclList!=null) NonEndingVarDeclList.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonEndingVarDeclList!=null) NonEndingVarDeclList.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonEndingVarDeclList!=null) NonEndingVarDeclList.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEndingVarDeclListC(\n");

        if(NonEndingVarDeclList!=null)
            buffer.append(NonEndingVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEndingVarDeclListC]");
        return buffer.toString();
    }
}
