package com.thomas.test.spring.contactor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "comment")
@Audited
public class Comment implements Serializable, Comparable<Comment> {

	private static final long serialVersionUID = -6064395958056769645L;

	@Id
	@Column(name = "comment_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "comment.comment_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long CommentId;

	@Column(name = "comment")
	@Size(max = 255)
	private String comment;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "contact_id", nullable = false, updatable = false)
	private Contact contact;

	public Comment() {

	}

	public Long getCommentId() {
		return CommentId;
	}

	public void setCommentId(Long commentId) {
		CommentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getCommentId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(Comment.class)) {
				return Objects.equal(getCommentId(), ((Comment) other).getCommentId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(Comment other) {
		if (other != null) {
			return getCommentId().compareTo(other.getCommentId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("Comment").add("contact", getContact())
				.add("comment", getComment().substring(0, 10)).toString();
	}

}
