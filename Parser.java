import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.util.*;
import java.util.stream.Collectors;

public class Parser implements IParser {
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    Set<Word> activeWords = new HashSet();
    activeWords.add(new Word(cfg.getStartVariable()));
    while(!activeWords.isEmpty()){
      Set<Word> newActiveWords = new HashSet();
      for(Word word : activeWords){
        for(Rule rule : cfg.getRules()){
          int variableIndex = word.indexOfFirst(rule.getVariable());
          int variationCount=1;
          while(variableIndex!=-1){
            Word newWord = word.replace(variableIndex,rule.getExpansion());
            if(isTerminated(newWord)){
              if(newWord.equals(w))
                return true;
            }else if(newWord.length()<=w.length())
              newActiveWords.add(newWord);
            variableIndex = word.indexOfNth(rule.getVariable(),variationCount++);
          }
        }
      }
      activeWords = newActiveWords;
    }
    return false;
  }

  private boolean isTerminated(Word word){
    for(Symbol symbol : word){
      if(symbol instanceof Variable)
        return false;
    }
    return true;
  }

  public Derivation getDerivation(ContextFreeGrammar cfg, Word w){
    Map<Word,Derivation> derivations = new HashMap();
    Word startingWord = new Word(cfg.getStartVariable());
    Derivation start = new Derivation(startingWord);
    derivations.put(startingWord,start);
    while(!derivations.isEmpty()){
      Map<Word,Derivation> newDerivations = new HashMap();
      for(Map.Entry<Word,Derivation> derivation:derivations.entrySet()){
        Word word = derivation.getKey();
        for(Rule rule:cfg.getRules()){
          int variableIndex = word.indexOfFirst(rule.getVariable());
          int variationCount=1;
          while(variableIndex!=-1){
            Word newWord = word.replace(variableIndex,rule.getExpansion());
            if(isTerminated(newWord)) {
              if(newWord.equals(w)){
                Derivation resultingDerivation = new Derivation(derivation.getValue());
                resultingDerivation.addStep(newWord, rule, variableIndex);
                return resultingDerivation;
              }
            }else if(newWord.length()<=w.length() && !derivations.containsKey(newWord)) {
              Derivation newDerivation = new Derivation(derivation.getValue());
              newDerivation.addStep(newWord, rule, variableIndex);
              newDerivations.put(newWord,newDerivation);
            }
            variableIndex = word.indexOfNth(rule.getVariable(),variationCount++);
          }
        }
      }
      derivations = newDerivations;
    }
    return null;
  }

  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
    return createParseTree(getDerivation(cfg,w));
  }

  private ParseTreeNode createParseTree(Derivation derivation){
    Word finalWord = derivation.getLatestWord();
    List<ParseTreeNode> nodeList = new ArrayList();
    for(Symbol symbol : finalWord){
      nodeList.add(new ParseTreeNode(symbol));
    }
    for(Step step : derivation){
      int startIndex = step.getIndex();
      if(startIndex==-1)
        break;
      int endIndex = step.getIndex() + step.getRule().getExpansion().length();
      List<ParseTreeNode> children = nodeList.subList(startIndex, endIndex);
      ParseTreeNode ancestor = new ParseTreeNode(step.getRule().getVariable(), children.toArray(new ParseTreeNode[children.size()]));
      nodeList.set(startIndex,ancestor);
      while(++startIndex<endIndex){
        nodeList.remove(startIndex);
      }
    }
    return nodeList.get(0);
  }

  private void printParseTree(ContextFreeGrammar cfg, Word w){

  }

}