// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class BeginMulOp extends MulOperations {

    private MulOperations MulOperations;
    private Mulop Mulop;
    private Factor Factor;

    public BeginMulOp (MulOperations MulOperations, Mulop Mulop, Factor Factor) {
        this.MulOperations=MulOperations;
        if(MulOperations!=null) MulOperations.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public MulOperations getMulOperations() {
        return MulOperations;
    }

    public void setMulOperations(MulOperations MulOperations) {
        this.MulOperations=MulOperations;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulOperations!=null) MulOperations.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulOperations!=null) MulOperations.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulOperations!=null) MulOperations.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BeginMulOp(\n");

        if(MulOperations!=null)
            buffer.append(MulOperations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BeginMulOp]");
        return buffer.toString();
    }
}
