// generated with ast extension for cup
// version 0.8
// 5/1/2023 18:32:34


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementOne extends DesignatorStatement {

    private AssignOpExpr AssignOpExpr;

    public DesignatorStatementOne (AssignOpExpr AssignOpExpr) {
        this.AssignOpExpr=AssignOpExpr;
        if(AssignOpExpr!=null) AssignOpExpr.setParent(this);
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
        if(AssignOpExpr!=null) AssignOpExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignOpExpr!=null) AssignOpExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignOpExpr!=null) AssignOpExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementOne(\n");

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
