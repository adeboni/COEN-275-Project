package knowledge.sources;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

public class LegalStringKnowledgeSource extends StringKnowledgeSource {



    @Override
    public String toString() {
        return "LegalStringKnowledgeSource";
    }


    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        
        
    }

}
