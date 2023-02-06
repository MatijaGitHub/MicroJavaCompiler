// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class WhileStatement extends Statement {

    private While While;
    private Condition Condition;
    private BeginWhile BeginWhile;
    private Statement Statement;

    public WhileStatement (While While, Condition Condition, BeginWhile BeginWhile, Statement Statement) {
        this.While=While;
        if(While!=null) While.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.BeginWhile=BeginWhile;
        if(BeginWhile!=null) BeginWhile.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public While getWhile() {
        return While;
    }

    public void setWhile(While While) {
        this.While=While;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public BeginWhile getBeginWhile() {
        return BeginWhile;
    }

    public void setBeginWhile(BeginWhile BeginWhile) {
        this.BeginWhile=BeginWhile;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(While!=null) While.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(BeginWhile!=null) BeginWhile.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(While!=null) While.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(BeginWhile!=null) BeginWhile.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(While!=null) While.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(BeginWhile!=null) BeginWhile.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileStatement(\n");

        if(While!=null)
            buffer.append(While.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BeginWhile!=null)
            buffer.append(BeginWhile.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileStatement]");
        return buffer.toString();
    }
}
