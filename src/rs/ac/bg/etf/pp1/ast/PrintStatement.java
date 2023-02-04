// generated with ast extension for cup
// version 0.8
// 4/1/2023 1:1:55


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Statement {

    private Expr Expr;
    private NumberConst NumberConst;

    public PrintStatement (Expr Expr, NumberConst NumberConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.NumberConst=NumberConst;
        if(NumberConst!=null) NumberConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public NumberConst getNumberConst() {
        return NumberConst;
    }

    public void setNumberConst(NumberConst NumberConst) {
        this.NumberConst=NumberConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(NumberConst!=null) NumberConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(NumberConst!=null) NumberConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(NumberConst!=null) NumberConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumberConst!=null)
            buffer.append(NumberConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
