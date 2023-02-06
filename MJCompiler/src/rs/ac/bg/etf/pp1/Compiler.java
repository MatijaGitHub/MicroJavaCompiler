package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;


import rs.ac.bg.etf.pp1.util.Log4JUtils;
public class Compiler {

	public static void tsdump() {
		Tab.dump();
	}
	static {
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
    }
	public static void main(String[] args) throws Exception {
	        
	        Logger log = Logger.getLogger(MJParserTest.class);
	        if(args.length < 2) {
	        	log.error("Too few program arguments!");
	        	return;
	        }
	        String progName = args[0];
	        String objName = args[1];
	        Reader br = null;
	        try {
	        	//String fileName = "run_test";
	            File sourceCode = new File(progName);
	            log.info("Compiling source file: " + sourceCode.getAbsolutePath());
	            
	            br = new BufferedReader(new FileReader(sourceCode));
	            Yylex lexer = new Yylex(br);
	            
	            MJParser p = new MJParser(lexer);
	            Symbol s = p.parse();  //pocetak parsiranja
	            
	            Program prog = (Program)(s.value); 
	            // ispis sintaksnog stabla
	            log.info(prog.toString(""));
	            log.info("===================================");
	            Tab.init();
	            // ispis prepoznatih programskih konstrukcija
	            SemanticAnalyzer v = new SemanticAnalyzer();
	            prog.traverseBottomUp(v); 
	            tsdump();
	            //Tab.dump();
	            if(!p.errorDetected && !v.errorExists()) {
	            	File objFile = new File(objName);
	            	if(objFile.exists()) objFile.delete();
	            	CodeGenerator cg = new CodeGenerator();
	                prog.traverseBottomUp(cg);
	                
	                Code.dataSize = v.numOfVars;
	                Code.mainPc = cg.getProgStartAdr();
	                Code.write(new FileOutputStream(objFile));
	                System.out.println("Code generated!");
	            }
	            
	            
	        } 
	        finally {
	            if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
	        }
	
	    }

}
