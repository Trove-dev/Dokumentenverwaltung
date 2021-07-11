package Tag;

public class TagsContainer implements TagsContainerInterface{
	private Tag listeTags[];
	private TagsContainer uniqueInstanceTags;
	
	public TagsContainer(Tag[] listeTags, TagsContainer uniqueInstanceTags) {
		super();
		this.listeTags = listeTags;
		this.uniqueInstanceTags = uniqueInstanceTags;
	}
	
	public void addiereNeuesTag() {
		

	}
	
	public void loescheTag(TagsContainer tagsContainer){
		
	}
	
	public TagsContainer getInstanceTags() {
		return null;
	}
	
}
