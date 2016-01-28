package knowledge;

import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Assumption;

/**
 * Created by mickey.liu on 1/21/16.
 */
public interface KnowledgeSource {
	void evaluate();
	
	ConcurrentLinkedQueue<Assumption> getPastAssumptions();
}
