// generated with ast extension for cup
// version 0.8
// 17/11/2022 15:59:0


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private TypeOrVoid TypeOrVoid;
    private FormsParametars FormsParametars;
    private VarDeclarations VarDeclarations;
    private Statements Statements;

    public MethodDecl (TypeOrVoid TypeOrVoid, FormsParametars FormsParametars, VarDeclarations VarDeclarations, Statements Statements) {
        this.TypeOrVoid=TypeOrVoid;
        if(TypeOrVoid!=null) TypeOrVoid.setParent(this);
        this.FormsParametars=FormsParametars;
        if(FormsParametars!=null) FormsParametars.setParent(this);
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public TypeOrVoid getTypeOrVoid() {
        return TypeOrVoid;
    }

    public void setTypeOrVoid(TypeOrVoid TypeOrVoid) {
        this.TypeOrVoid=TypeOrVoid;
    }

    public FormsParametars getFormsParametars() {
        return FormsParametars;
    }

    public void setFormsParametars(FormsParametars FormsParametars) {
        this.FormsParametars=FormsParametars;
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeOrVoid!=null) TypeOrVoid.accept(visitor);
        if(FormsParametars!=null) FormsParametars.accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeOrVoid!=null) TypeOrVoid.traverseTopDown(visitor);
        if(FormsParametars!=null) FormsParametars.traverseTopDown(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeOrVoid!=null) TypeOrVoid.traverseBottomUp(visitor);
        if(FormsParametars!=null) FormsParametars.traverseBottomUp(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(TypeOrVoid!=null)
            buffer.append(TypeOrVoid.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormsParametars!=null)
            buffer.append(FormsParametars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
