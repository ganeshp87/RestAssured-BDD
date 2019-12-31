package pojo;

public class PostsWithBuliderPattern {
    private int id;
    private String title;
    private String author;

    PostsWithBuliderPattern() {}

    public PostsWithBuliderPattern(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    //Builder pattern
    
    public static class Builder{
    	private int id;
    	private String title;
    	private String author;
    	
    	public Builder() {}

		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}


		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		
		public Builder setAuthor(String author) {
			this.author = author;
			return this;
		}
		
		public PostsWithBuliderPattern build() {
			return new PostsWithBuliderPattern(id,title,author);
		}
    }
    
}
