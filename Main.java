import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;

class Main {

	// Change this to true to skip straight to the tests
	// - useful if you understand the code and are just testing your parser.
	private final static boolean SKIP_TO_TESTS = false;

	// The parser we will test. 
	// If you create a new class, add your constructor here instead e.g.
	// private static IParser parser = new MyParser();
	private static IParser parser = new Parser();

	public static void customCode() {
		// You can write your own custom code here and run it with option 3.
		// Good for testing your code works!
		// Any code here is totally informal and does not count towards your submission.
		
		// Below is the kind of code you might want to write to test your parser
		
		ContextFreeGrammar cfg = MyGrammar.makeGrammar();
		List<Word> testWords = new LinkedList();
    System.out.println("This is example strings to check. ");
    System.out.println("1. p+r:");
		testWords.add(new Word("p+r"));
    System.out.println("2. pp");
		testWords.add(new Word("pp"));
    System.out.println("3. ():");
		testWords.add(new Word("()"));
    System.out.println("4. p&(r+p)");
		testWords.add(new Word("p&(r+p)"));
    System.out.println("5. --p:");
		testWords.add(new Word("--p"));
    System.out.println("6. -(p&r):");
		testWords.add(new Word("-(p&r)"));
    System.out.println("7. (p+r))");
		testWords.add(new Word("(p+r))"));
    System.out.println("8. (p&r)-p:");
		testWords.add(new Word("(p&r)-p"));
    System.out.println("9. This should be printed TRUE to test PARSER\n p+p");
    System.out.println("End of example words required to check.");
    System.out.println("Please find the results in order below\n ");
		testWords.add(new Word("p+p"));
		
		// this should print true!
		testWords.forEach(word -> System.out.println(parser.isInLanguage(cfg, word)));
	}

	public static void customWordChecker(){
		ContextFreeGrammar cfg = MyGrammar.makeGrammar();
		String input;
		try {
			System.out.println("Enter a word to be checked :");
			input = userin.nextLine();
		} catch(Exception e) {
			System.out.println("input is not valid");
			return;
		}
		boolean isInLanguage = parser.isInLanguage(cfg,new Word(input));
		System.out.println("The word '" + input + "' is " + (isInLanguage?"":"not ") + "part of the context free grammar\n");
    System.out.println("Please go to option no.8 if your word is part of the context free grammar\n Please don't try to print binary tree if your word is not part of this grammar\n Thank you.");
	}

	private static void customWordPrinter() {
    System.out.println("\nPlease use this tool to print a binary tree only if you have checked that is member of this context free grammar in the option no.7.\n");
		ContextFreeGrammar cfg = MyGrammar.makeGrammar();
		String input;
		try {
			System.out.println("Enter the word which is a member of this CFG below:" );
			input = userin.nextLine();
		} catch(Exception e) {
			System.out.println("input is not valid");
			return;
		}
    ParseTreeNode parseTreeNode = parser.generateParseTree(cfg,new Word(input));
		System.out.println("The word '" + input + "' is " + (parseTreeNode!=null?"":"not ") + "in the custom language");
		parseTreeNode.print();
	}


	/* ******************************************************************************************************
	 * You should not need to edit anything below this line.
	 * 
	 * There is nothing stopping you from editing it if you want, but make sure you know what you are doing.
	 * ******************************************************************************************************
	 */

	private static Scanner userin = new Scanner(System.in);

	public static void main(String[] args) {
		if(!SKIP_TO_TESTS) {
			System.out.println("\nWelcome to the Parser for the Context Free Grammar.\nPlease choose one of the following options.\n");
				
			int input = -1;
			do {
				System.out.println("1 – Requirements of the assignment");
				System.out.println("2 – Demo the code");
        System.out.println("3 - Context Free Grammar");
        System.out.println("4 - Chomsky Normal Form");
				System.out.println("5 – Run simple tests on Parser");
				System.out.println("6 - Test algorithm on provided example strings");
				System.out.println("7 - Test any word to check if is part of the Context Free Grammar");
				System.out.println("8 - Print binary tree for a particular word that is a member of the CFG\n(Note: Please use option no. 7 to check if the word is part of the context free grammar)\n ");
				System.out.println("9 – Quit");
        System.out.println("END OF OPTIONS");

				try {
					input = userin.nextInt();
				} catch(Exception e) {
					userin.nextLine();
					System.out.println("Invalid choice...");
					continue;
				}
				
				userin.nextLine();
				System.out.println();
				
				if(input == 1) {
					explainAssignment();
				} else if(input == 2) {
					demoCode();
         } else if(input == 3){
          ContextFreeGrammar();
        } else if(input == 4){
          CNF();
				} else if(input == 5) {
					runTests();
				} else if(input == 6) {
					customCode();
				}  else if(input == 7) {
					customWordChecker();
				}  else if(input == 8) {
					customWordPrinter();
				} else if(input != 9) {
					System.out.println("Invalid choice...");
				}
				
				if(input != 9) pause();
			} while(input != 9);
		}
		else {
			runTests();
		}
	}

	public static void explainAssignment() {
		System.out.println("The very first part of this assignment is to convert a grammar into Chomsky Normal form. "
				+ "You will want to do this on paper. As you are looking at the code, we assume you have completed this step!\n");
		
		pause();
		
		System.out.println("The next and main part of the assignment is to write a parser, then apply it to the grammar you created.\n");
		
		System.out.println("Creating the parser means writing a class. When given a context free "
				+ "grammar in Chomsky Normal form, and a string, your class will:");
		System.out.println("\t1 - Find whether this string is in the langauge generated by this grammar, and");
		System.out.println("\t2 - if so, produce a parse tree for the string.");
		
		pause();
		
		System.out.println("But you may wish to start by writing your Chomsky Normal Form grammar in Java. "
				+ "In other words, write some code which creates an object of the ContextFreeGrammar class. "
				+ "You can do this in the skeleton class called src.MyGrammar.java. "
				+ "This should help familiarise you with how the code works, and then you can write your parser, "
				+ "and apply it to the grammar."
				+ "\n\n"
				+ "Before that, choose option 1 from the menu to get an explanation of the code.\n");
		
		System.out.println("For more information see the full assignment text on Engage.\n");
		
	}

	private static void demoCode() {
		System.out.println("Each class has more information written in comments, but this is most "
				+ "clearly readable in the Javadoc documentation that has been provided in HTML form, "
				+ "available via Engage. (Or, if you have downloaded the code directly, open "
				+ "index.html in the doc folder.)");
		
		pause();
		
		System.out.println("First, we'll explain the src.Parser class. This is a blank template for your own code. "
				+ "You could also create your own class from scratch, so long as it implements IParser. "
				+ "Right now the methods in src.Parser do nothing, you must fill them out.\n");
		
		System.out.println("If you go back to the main menu and select option 2, it will run some basic tests "
				+ "on the parser code. These are not thorough! But they will demonstrate how it should work. "
				+ "Once you have written some parser code, try running them again. ");
		
		pause();
		
		System.out.println("The class ContextFreeGrammar is used to represent a grammar. If you look in the "
				+ "/src/computation/contextfreegrammar/ folder you will see all the required classes.\n");
		
		System.out.println("Terminal and Variable are subclasses of Symbol, and represent a single letter.");
		
		System.out.println("You can create a Terminal or a Variable object using their constructors.");
		System.out.println("\te.g. new Variable('S'); \t\t-- this produces: " + new Variable('S'));
		System.out.println("\t     new Terminal('a'); \t\t-- this produces: " + new Terminal('a'));
		System.out.println("\t     new Terminal('0'); \t\t-- this produces: " + new Terminal('0') + "\n");
		
		
		System.out.println("Variables can also have a subscript number from 0 to 9\n"
				+ "\te.g. new Variable(\"A0\"); \t-- this produces: " + new Variable("A0") + "\n");
		
		System.out.println("And there is a helper method to create an array of subscripted variables:\n"
				+ "\te.g. Variable.subscriptedVariables('S', 4); \t-- this produces: " + Arrays.toString(Variable.subscriptedVariables('S', 4)));
		
		pause();
		
		System.out.println("The class Word represents strings. We use the name Word because String is already taken.\n");
		
		System.out.println("One of the constructors for Word takes a Java String. It assumes upper case letters "
				+ "are variables, and everything else is a terminal. So you can't use subscript variables! "
				+ "I recommend mostly using it for strings of terminals.");
		
		System.out.println("\te.g. new Word(\"000111\"); \t-- this produces: " + new Word("000111"));
		System.out.println("\t     new Word(\"A0\"); \t\t-- this produces: " + new Word("A0") + " (variable followed by terminal)");
		System.out.println();
		
		System.out.println("The other way to construct a Word is with multiple symbols:");
		System.out.println("\te.g. new Word(new Variable(\"A0\"), new Terminal('0')); \t-- this produces: " + new Word(new Variable("A0"), new Terminal('0')));
		System.out.println();
		
		System.out.println("Or this syntax also allows you to pass in an array of symbols:");
		Symbol[] array = {new Variable("A0"),new Terminal('0')};
		System.out.println("\te.g. Symbol[] array = {new Variable(\"A0\"),new Terminal(\"0\")};");
		System.out.println("\t     new Word(array); \t\t-- this produces: " + new Word(array));
		
		pause();
		
		System.out.println("To create a grammar, create multiple Rule objects, add them to a list, then "
				+ "call the ContextFreeGrammar constructor.\n");
		
		System.out.println("Look at the code in ContextFreeGrammar.simpleCNF(). It produces this grammar:");
		System.out.println(ContextFreeGrammar.simpleCNF());
		
		pause();
		
		System.out.println("This should be enough information to start writing your CNF grammar in Java, "
				+ "and also writing the first part of the parser which checks whether the string is in the language.");
		
		pause();
		
		System.out.println("Once you are ready to make a parse tree, you will need to use the class ParseTreeNode.\n");
		
		System.out.println("The constructor takes a symbol object, plus zero, one, or two ParseTreeNodes as children.\n");
		
		System.out.println("Have a look at the code in the main method of ParseTreeNode. It produces this tree:");
		ParseTreeNode.main("");
		
		pause();
		
		System.out.println("This is the end of the demo. Dig into the code, look at the documentation, and good luck!");
		
	}
   public static void ContextFreeGrammar() {
    System.out.println("The following grammar G generates the language of syntactically correct boolean expressions involving disjunction, conjunction, negation and only two identifiers for  propositions. This grammar, which is unambiguous,  represents a fragment of the kind of expressions you might find in many common programming languages. Parsing guarantees that the expressions have indeed been generated according to the given grammar, and therefore is a correctly produced sequence of characters.");

    pause();

    System.out.println("Let G=(V,Σ,R,E) be a grammar, where V is the set of non-terminal symbols (or Variables) that includes the start symbol E ,  Σ is the set of terminal symbols, and R is the set of production rules. The grammar is specified as follows:\n");

    pause();

    System.out.println("V={E,T,F}\nΣ={+,&,−,(,),p,r}\nR={\nE→E+T\nE→T\nT→T&F\nT→F\nF→(E\nF→−F\nF→p\nF→r\n}\n"); 

    pause();

    System.out.println("The symbols E, T, and F are abbreviations for expression, term, and factor, respectively. The symbols +, &, and − stand for disjunction (OR), conjunction (AND) and negation (NOT), respectively. The symbols p, r are  two identifiers for propositions.\n");

  }


	public static void runTests() {
		ContextFreeGrammar cfg = ContextFreeGrammar.simpleCNF();
		Word test;

		if(!SKIP_TO_TESTS) {
			System.out.println("This script runs some very basic tests on your parser.");

			System.out.println("We will use a grammar that generates the language {0ⁿ1ⁿ where n ≥ 0}");
			System.out.println("The grammar is already in Chomsky Normal Form. Here are the rules:");

			System.out.println(cfg);
			pause();
		}

		System.out.println("Testing parser with " + parser.getClass());
		
		int success = 0, total = 0;
		
		test = new Word("0011");

		System.out.println(LINE+"\nTest 1:\n" + LINE + "\n");
		System.out.println("Does in-built grammar generate the string " + test);
		System.out.println("Expected result: true\nTest result:");
		total++;

		if(parser.isInLanguage(cfg, test)){
			System.out.println("\tPASS -- parser returned true");
			success++;
		} else{
			System.out.println("\tFAIL -- parser returned false");
		}

		pause();
		test = new Word("1011");

		System.out.println("Test 2:\n" + LINE + "\n");
		System.out.println("Does in-built grammar generate the string " + test);
		System.out.println("Expected result: false\nTest result:");
		total++;

		if(!parser.isInLanguage(cfg, test)){
			System.out.println("\tPASS -- parser returned false");
			success++;
		} else{
			System.out.println("\tFAIL -- parser returned true");
		}
		
		pause();
		
		test = new Word("0011");
		System.out.println("Test 3:\n" + LINE + "\n");
		System.out.println("Parse tree for string " + test);
		System.out.println("Expected result:\n");
		
		ParseTreeNode tree = new ParseTreeNode(new Variable("A0"), new ParseTreeNode(new Variable('Z'), new ParseTreeNode(new Terminal('0'))),new ParseTreeNode(new Variable('B'), new ParseTreeNode(new Variable('A'), new ParseTreeNode(new Variable('Z'), new ParseTreeNode(new Terminal('0'))), new ParseTreeNode(new Variable('Y'), new ParseTreeNode(new Terminal('1')))), new ParseTreeNode(new Variable('Y'), new ParseTreeNode(new Terminal('1')))));
		tree.print();
		
		ParseTreeNode result = parser.generateParseTree(cfg, test);
		if(result == null) {
			System.out.println("Actual result: null");
		} else {
			System.out.println("Actual result:\n");
			
			result.print();
		}
		
		total++;
		
		if(result != null && result.equals(tree)) {
			System.out.println("PASS -- trees match");
			success++;
		} else if (result == null) {
			System.out.println("FAIL -- result was null");
		} else {
			System.out.println("FAIL -- trees do not match");
		}
		
		pause();
		
		System.out.println("Total tests passed: " + success + " out of " + total);
	}

	private static void pause() {
		System.out.println("\n\n(Press enter to continue...)");
		userin.nextLine();
		System.out.println("\n" + "------------------------");
	}
	
	private static final String LINE = "-------------";

  public static void CNF() {
    System.out.println("Please find Chomsky Normal Form below:\n S→BT\nE→BT\nE→T\nT→CF\nT→F\nF→DR\nF→UF\nF→p\nF→ r\nY→ +\nZ→&\nQ→(\nR→)\nU→-\nB→EY\nC→TZ\nD→QE\n");
  }

}

