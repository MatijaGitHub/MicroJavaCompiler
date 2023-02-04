package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.ac.bg.etf.pp1.ast.*;
public class CodeGenerator extends VisitorAdaptor {
	
	private int mainStart;
	
	
	public int getProgStartAdr() {
		return mainStart;
	}
	
	public CodeGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void visit(MethodName methName) {
		methName.obj.setAdr(Code.pc);
		if(methName.getMethName().equals("main")) {
			mainStart = methName.obj.getAdr();
		}
		Code.put(Code.enter);
		Code.put(methName.obj.getLevel());
		Code.put(methName.obj.getLocalSymbols().size());
	}

		
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	public void visit(ReturnStatement returnStmt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(AssignOpExprParen assignOpExprParen) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
