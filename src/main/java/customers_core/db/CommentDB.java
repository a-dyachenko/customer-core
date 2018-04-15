package customers_core.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_comment")
public class CommentDB extends BaseObjectDB {

	private int id;
	private CustomerDB customer;
	private String commentText;
	private Date created;

	public CommentDB() {
	};

	public CommentDB(CustomerDB customer, String commentText) {
		this.customer = customer;
		this.commentText = commentText;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	public CustomerDB getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDB customer) {
		this.customer = customer;
	}

	@Column(name = "comment_text")
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
