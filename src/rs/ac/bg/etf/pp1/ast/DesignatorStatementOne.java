// generated with ast extension for cup
// version 0.8
// 17/11/2022 2:19:31


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementOne extends DesignatorStatement {

    private Designator Designator;
    private AssignOpExpr AssignOpExpr;

    public DesignatorStatementOne (Designator Designator, AssignOpExpr AssignOpExpr) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.AssignOpExpr=AssignOpExpr;
        if(AssignOpExpr!=null) AssignOpExpr.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public AssignOpExpr getAssignOpExpr() {
        return AssignOpExpr;
    }

    public void setAssignOpExpr(AssignOpExpr AssignOpExpr) {
        this.AssignOpExpr=AssignOpExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(AssignOpExpr!=null) AssignOpExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(AssignOpExpr!=null) AssignOpExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(AssignOpExpr!=null) AssignOpExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementOne(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignOpExpr!=null)
            buffer.append(AssignOpExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementOne]");
        return buffer.toString();
    }
}
