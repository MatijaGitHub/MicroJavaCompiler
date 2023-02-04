// generated with ast extension for cup
// version 0.8
// 4/1/2023 17:3:1


package rs.ac.bg.etf.pp1.ast;

public class NonEndingConstDeclListC1 extends ConstDeclList {

    private NonEndingConstDeclList NonEndingConstDeclList;
    private ConstDeclList ConstDeclList;

    public NonEndingConstDeclListC1 (NonEndingConstDeclList NonEndingConstDeclList, ConstDeclList ConstDeclList) {
        this.NonEndingConstDeclList=NonEndingConstDeclList;
        if(NonEndingConstDeclList!=null) NonEndingConstDeclList.setParent(this);
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
    }

    public NonEndingConstDeclList getNonEndingConstDeclList() {
        return NonEndingConstDeclList;
    }

    public void setNonEndingConstDeclList(NonEndingConstDeclList NonEndingConstDeclList) {
        this.NonEndingConstDeclList=NonEndingConstDeclList;
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonEndingConstDeclList!=null) NonEndingConstDeclList.accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonEndingConstDeclList!=null) NonEndingConstDeclList.traverseTopDown(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonEndingConstDeclList!=null) NonEndingConstDeclList.traverseBottomUp(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEndingConstDeclListC1(\n");

        if(NonEndingConstDeclList!=null)
            buffer.append(NonEndingConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEndingConstDeclListC1]");
        return buffer.toString();
    }
}
