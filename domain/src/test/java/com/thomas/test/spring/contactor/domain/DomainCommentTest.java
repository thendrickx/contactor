package com.thomas.test.spring.contactor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class DomainCommentTest extends AbstractDomainImplTest {

	@Test
	public void testCreateComment() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		Comment comment = createNewComment();
		contact.addComment(comment);

		em.persist(user);
		em.persist(contact);
		em.persist(comment);

		Comment comment2 = createNewComment();
		contact.addComment(comment2);

		em.persist(comment2);

		assertEquals(2, contact.getComments().size());
		Set<Comment> comments = ImmutableSet.<Comment> of(comment, comment2);
		assertTrue(comments.containsAll(contact.getComments()));
	}
}
