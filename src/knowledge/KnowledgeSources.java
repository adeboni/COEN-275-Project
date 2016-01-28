package knowledge;

public interface KnowledgeSources {

    /**
     * Load and initialize knowledge sources
     * 
     * @throws InitializationException
     * @throws CollectionLoadingException
     */
    void init();

    /**
     * Clear, reset, load and initialize the knowledge
     * sources collection
     */
    void reset();

    /**
     * This method is called by init() and reset() to load unique knowledge
     * sources
     */
    void loadKnowledgeSources();

    /**
     * This method is called by init() and reset() to load knowledge sources
     * with attribute data
     * 
     * @throws InitializationException
     */
    void initializeKnowledgeSources();

}