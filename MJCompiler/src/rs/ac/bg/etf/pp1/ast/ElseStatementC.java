// generated with ast extension for cup
// version 0.8
// 6/1/2023 20:57:28


package rs.ac.bg.etf.pp1.ast;

public class ElseStatementC extends ElseStatement {

    private BeginElseStatement BeginElseStatement;
    private Statement Statement;
    private EndElseStatement EndElseStatement;

    public ElseStatementC (BeginElseStatement BeginElseStatement, Statement Statement, EndElseStatement EndElseStatement) {
        this.BeginElseStatement=BeginElseStatement;
        if(BeginElseStatement!=null) BeginElseStatement.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.EndElseStatement=EndElseStatement;
        if(EndElseStatement!=null) EndElseStatement.setParent(this);
    }

    public BeginElseStatement getBeginElseStatement() {
        return BeginElseStatement;
    }

    public void setBeginElseStatement(BeginElseStatement BeginElseStatement) {
        this.BeginElseStatement=BeginElseStatement;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public EndElseStatement getEndElseStatement() {
        return EndElseStatement;
    }

    public void setEndElseStatement(EndElseStatement EndElseStatement) {
        this.EndElseStatement=EndElseStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BeginElseStatement!=null) BeginElseStatement.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(EndElseStatement!=null) EndElseStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BeginElseStatement!=null) BeginElseStatement.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(EndElseStatement!=null) EndElseStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BeginElseStatement!=null) BeginElseStatement.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(EndElseStatement!=null) EndElseStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStatementC(\n");

        if(BeginElseStatement!=null)
            buffer.append(BeginElseStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndElseStatement!=null)
            buffer.append(EndElseStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStatementC]");
        return buffer.toString();
    }
}
