import computation.contextfreegrammar.*;

import java.util.*;

public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {
		/*************************************Variables******************************************/
		Map<Character,Variable> variables = new HashMap();
		variables.put('S',new Variable('S'));
		variables.put('E',new Variable('E'));
		variables.put('T',new Variable('T'));
		variables.put('F',new Variable('F'));
		variables.put('Y',new Variable('Y'));
		variables.put('Z',new Variable('Z'));
		variables.put('Q',new Variable('Q'));
		variables.put('R',new Variable('R'));
		variables.put('U',new Variable('U'));
		variables.put('B',new Variable('B'));
		variables.put('C',new Variable('C'));
		variables.put('D',new Variable('D'));
		/*************************************Terminals******************************************/
		Map<Character,Terminal> terminals = new HashMap();
		terminals.put('p',new Terminal('p'));
		terminals.put('+',new Terminal('+'));
		terminals.put('r',new Terminal('r'));
		terminals.put('&',new Terminal('&'));
		terminals.put('(',new Terminal('('));
		terminals.put(')',new Terminal(')'));
		terminals.put('-',new Terminal('-'));
		/*************************************Rules******************************************/
		List<Rule> rules = new LinkedList();
		rules.add(new Rule(variables.get('S'),/* -> */new Word(variables.get('B'),variables.get('T'))));
		rules.add(new Rule(variables.get('E'),/* -> */new Word(variables.get('B'),variables.get('T'))));
		rules.add(new Rule(variables.get('E'),/* -> */new Word(variables.get('T'))));
		rules.add(new Rule(variables.get('T'),/* -> */new Word(variables.get('C'),variables.get('F'))));
		rules.add(new Rule(variables.get('T'),/* -> */new Word(variables.get('F'))));
		rules.add(new Rule(variables.get('F'),/* -> */new Word(variables.get('D'),variables.get('R'))));
		rules.add(new Rule(variables.get('F'),/* -> */new Word(variables.get('U'),variables.get('F'))));
		rules.add(new Rule(variables.get('F'),/* -> */new Word(terminals.get('p'))));
		rules.add(new Rule(variables.get('F'),/* -> */new Word(terminals.get('r'))));
		rules.add(new Rule(variables.get('Y'),/* -> */new Word(terminals.get('+'))));
		rules.add(new Rule(variables.get('Z'),/* -> */new Word(terminals.get('&'))));
		rules.add(new Rule(variables.get('Q'),/* -> */new Word(terminals.get('('))));
		rules.add(new Rule(variables.get('R'),/* -> */new Word(terminals.get(')'))));
		rules.add(new Rule(variables.get('U'),/* -> */new Word(terminals.get('-'))));
		rules.add(new Rule(variables.get('B'),/* -> */new Word(variables.get('E'),variables.get('Y'))));
		rules.add(new Rule(variables.get('C'),/* -> */new Word(variables.get('Z'),variables.get('T'))));
		rules.add(new Rule(variables.get('D'),/* -> */new Word(variables.get('Q'),variables.get('E'))));
		/*************************************StartVariable******************************************/
		Variable startVariable = new Variable('S');
		/*************************************ContextFreeGrammar******************************************/
		ContextFreeGrammar cfg = new ContextFreeGrammar(new HashSet<Variable>(variables.values()), new HashSet<Terminal>(terminals.values()), rules, startVariable);
		return cfg;
	}
}
