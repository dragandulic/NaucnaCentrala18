package naucnaCentrala.dto;

public class OverviewLaborDTO {
	
	private String commentAuthor;
	
	private String commentEditor;
	
	private String rating;
	
	public OverviewLaborDTO() {
		
	}

	public String getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentEditor() {
		return commentEditor;
	}

	public void setCommentEditor(String commentEditor) {
		this.commentEditor = commentEditor;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
