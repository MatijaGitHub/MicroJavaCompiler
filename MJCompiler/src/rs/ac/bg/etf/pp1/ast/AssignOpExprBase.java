// generated with ast extension for cup
// version 0.8
// 6/1/2023 20:57:28


package rs.ac.bg.etf.pp1.ast;

public class AssignOpExprBase extends AssignOpExpr {

    private AssignOpEqual AssignOpEqual;

    public AssignOpExprBase (AssignOpEqual AssignOpEqual) {
        this.AssignOpEqual=AssignOpEqual;
        if(AssignOpEqual!=null) AssignOpEqual.setParent(this);
    }

    public AssignOpEqual getAssignOpEqual() {
        return AssignOpEqual;
    }

    public void setAssignOpEqual(AssignOpEqual AssignOpEqual) {
        this.AssignOpEqual=AssignOpEqual;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignOpEqual!=null) AssignOpEqual.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignOpEqual!=null) AssignOpEqual.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignOpEqual!=null) AssignOpEqual.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignOpExprBase(\n");

        if(AssignOpEqual!=null)
            buffer.append(AssignOpEqual.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignOpExprBase]");
        return buffer.toString();
    }
}
