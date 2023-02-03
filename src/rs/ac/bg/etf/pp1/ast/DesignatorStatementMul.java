// generated with ast extension for cup
// version 0.8
// 3/1/2023 18:14:20


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementMul extends DesignatorStatement {

    private DesignatorOpt DesignatorOpt;
    private DesignatorList DesignatorList;
    private Designator Designator;

    public DesignatorStatementMul (DesignatorOpt DesignatorOpt, DesignatorList DesignatorList, Designator Designator) {
        this.DesignatorOpt=DesignatorOpt;
        if(DesignatorOpt!=null) DesignatorOpt.setParent(this);
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorOpt getDesignatorOpt() {
        return DesignatorOpt;
    }

    public void setDesignatorOpt(DesignatorOpt DesignatorOpt) {
        this.DesignatorOpt=DesignatorOpt;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOpt!=null) DesignatorOpt.accept(visitor);
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOpt!=null) DesignatorOpt.traverseTopDown(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOpt!=null) DesignatorOpt.traverseBottomUp(visitor);
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementMul(\n");

        if(DesignatorOpt!=null)
            buffer.append(DesignatorOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementMul]");
        return buffer.toString();
    }
}
