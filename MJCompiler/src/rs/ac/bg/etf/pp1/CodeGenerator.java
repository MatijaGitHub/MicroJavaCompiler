package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
public class CodeGenerator extends VisitorAdaptor {
	private class ObjectIndexPair{
		public Obj obj;
		public Integer index;
	}
	private int mainStart;
	private Stack<Obj> functionCalls = new Stack<>();
	private int indexOfArray = 0;
	//private int itemsToStore = 0;
	ArrayList<ObjectIndexPair> itemsToStore = new ArrayList<>();
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
	
	public void visit(FuncCall funcCall) {
		functionCalls.push(funcCall.obj);
	}
	public void visit(DesignatorFactorAct designatorFactorAct) {
		Obj func = designatorFactorAct.getFuncCall().obj;
		if(func.getName().equals("ord") || func.getName().equals("chr")) return;
		if(func.getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		int offset = func.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		functionCalls.pop();
	}
	public void visit(AssignOpExprParen assignOpExprParen) {
		Obj func = assignOpExprParen.getFuncCall().obj;
		if(func.getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		int offset = func.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(func.getType() != Tab.noType)
			Code.put(Code.pop);
		functionCalls.pop();
	}
	
	public void visit(PrintStatement printStmt) {
 		if(printStmt.getNumberConst() instanceof NoNumberConst) {
			if(printStmt.getExpr().struct.getKind() == Struct.Int) {
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else if(printStmt.getExpr().struct.getKind() == Struct.Bool) {
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else {
				Code.loadConst(1);
				Code.put(Code.bprint);
			}
		}
		else {
			int len = ((NumberConstC)printStmt.getNumberConst()).getLen();
			Code.loadConst(len);
			if(printStmt.getExpr().struct.getKind() == Struct.Int) {
				Code.put(Code.print);
			}
			else if(printStmt.getExpr().struct.getKind() == Struct.Bool) {
				Code.put(Code.print);
			}
			else {
				Code.put(Code.bprint);
			}
		}
	}
	public void visit(DesignatorFactorNumber constNum) {
		Obj constNumObj = Tab.insert(Obj.Con, "/", new Struct(Struct.Int));
		constNumObj.setLevel(0);
		constNumObj.setAdr(constNum.getN1());
		Code.load(constNumObj);
	}
	public void visit(DesignatorFactorChar constChar) {
		Obj constCharObj = Tab.insert(Obj.Con, "/", new Struct(Struct.Char));
		constCharObj.setLevel(0);
		constCharObj.setAdr(constChar.getC1());
		Code.load(constCharObj);
	}
	public void visit(DesignatorFactorBool cosntBool) {
		Obj constBoolObj = Tab.insert(Obj.Con, "/", new Struct(Struct.Bool));
		constBoolObj.setLevel(0);
		constBoolObj.setAdr(cosntBool.getB1()?1:0);
		Code.load(constBoolObj);
	}
	public void visit(DesignatorFactor constDesignator) {
		Code.load(constDesignator.getDesignator().obj);
	}
//	public void visit(DesignatorIdent designator) {
//		Code.load(designator.obj);
//	}
	public void visit(EndingConstDeclListC endingConstDecl) {
		
	}
	public void visit(NonEndingConstDeclListC nonEndingConstDecl) {
//		Code.store(nonEndingConstDecl.ge)
//		Obj obk;
//		obk.
	}
	
	public void visit(AssignOpExprBase assignOp) {
		Code.store(assignOp.getDesignator().obj);
	}
	
	public void visit(BeginMulOp beginMulOp) {
		if(beginMulOp.getMulop() instanceof MulOperation) {
			Code.put(Code.mul);
		}
		else if(beginMulOp.getMulop() instanceof DivOperation) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
			
	}
	
	public void visit(NotEndAddopTerm addopOps) {
		if(addopOps.getAddop() instanceof AddOperation) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(AssignOpExprInc inc) {
		if(inc.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(inc.getDesignator().obj);
		}
		else if(inc.getDesignator().obj.getKind() == Obj.Elem) {
			
		}
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);
	}
	public void visit(AssignOpExprDec dec) {
		if(dec.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(dec.getDesignator().obj);
		}
		else if(dec.getDesignator().obj.getKind() == Obj.Elem) {
			
		}
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dec.getDesignator().obj);
	}
	public void visit(MinusExpr minus) {
		Code.loadConst(-1);
		Code.put(Code.mul);
		
	}
	
	public void visit(DesignatorFactorNewExpr newArray) {
		Code.put(Code.newarray);
		if(newArray.getType().struct.getKind() == Struct.Bool || newArray.getType().struct.getKind() == Struct.Char) {
			Code.put(0);
			
		}
		else {
			Code.put(1);
		}
		
		
	}
	public void visit(DesignatorExpr arrayElem) {
		
		Code.load(arrayElem.getDesignator().obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		//Code.put(Code.aload);
	}
	
	public void visit(ReadStatement readStmt) {
		if(readStmt.getDesignator().obj.getType().getKind() == Struct.Bool || readStmt.getDesignator().obj.getType().getKind() == Struct.Char) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(DesignatorStatementMul designatorStatementMul) {
		Code.load(designatorStatementMul.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.loadConst(indexOfArray);
		Code.putFalseJump(Code.lt, 0);
		int adr = Code.pc - 2;
		Code.put(Code.trap);
		Code.put(1);
		Code.fixup(adr);
		for(int i = 0; i < itemsToStore.size(); i++) {
			Code.load(designatorStatementMul.getDesignator().obj);
			Code.loadConst(itemsToStore.get(i).index);
			Code.put(Code.aload);
			Code.store(itemsToStore.get(i).obj);
		}
		indexOfArray = 0;
		itemsToStore.clear();
	}
	public void visit(DesignatorOptC designatorOptC) {
		ObjectIndexPair pair = new ObjectIndexPair();
		pair.obj = designatorOptC.getDesignator().obj;
		pair.index = indexOfArray;
		itemsToStore.add(pair);
		indexOfArray++;
		

	}
	public void visit(NoDesignatorOpt noDesignatorOpt) {
		indexOfArray++;
	}
	
	public void visit(AndTermsC andTerms) {
		Code.put(Code.mul);
	}
	
	public void visit(OrTermsC orTerms) {
		Code.put(Code.dup2);
		Code.put(Code.add);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.mul);
		Code.put(Code.sub);
	}
	
	public void visit(OneCondFact oneCondFact) {
		
	}
	public void visit(TwoCondFact twoCondFact) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
