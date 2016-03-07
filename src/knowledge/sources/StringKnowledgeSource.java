package knowledge.sources;

import knowledge.KnowledgeSource;

public class StringKnowledgeSource extends KnowledgeSource {

    @Override
    public String toString() {
        return "StringKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	//Nothing, this is used as a base class for some other knowledge sources
    }

}
