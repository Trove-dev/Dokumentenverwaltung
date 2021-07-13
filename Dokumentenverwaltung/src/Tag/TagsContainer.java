package Tag;

public class TagsContainer implements TagsContainerInterface{
	private Tag listeTags[];
	private static TagsContainer uniqueInstance = null;
	
	public TagsContainer() {
	}
	
	public void addiereNeuesTag() {
		

	}
	
	public void loescheTag(TagsContainer tagsContainer){
		
	}
	
	public static TagsContainer getInstance() {
		if(uniqueInstance == null) uniqueInstance = new TagsContainer();
		return uniqueInstance;
	}
}
