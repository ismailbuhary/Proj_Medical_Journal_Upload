/*
package com.mib.io.journals.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import com.mib.io.journals.Application;
import com.mib.io.journals.model.Journal;
import com.mib.io.journals.model.Publisher;
import com.mib.io.journals.model.User;
import com.mib.io.journals.repository.PublisherRepository;
import com.mib.io.journals.service.JournalService;
import com.mib.io.journals.service.ServiceException;
import com.mib.io.journals.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JournalServiceTest {

	private final static String NEW_JOURNAL_NAME = "New Journal";

	@Autowired
	private JournalService journalService;

	@Autowired
	private UserService userService;

	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	public void browseSubscribedUser() {
		List<Journal> journals = journalService.listAll(getUser("user1"));
		Assert.assertNotNull(journals);
		Assert.assertEquals(1, journals.size());

		Assert.assertEquals(new Long(1), journals.get(0).getId());
		Assert.assertEquals("Medicine", journals.get(0).getName());
		Assert.assertEquals(new Long(1), journals.get(0).getPublisher().getId());
		Assert.assertNotNull(journals.get(0).getPublishDate());
	}

	@Test
	public void browseUnSubscribedUser() {
		List<Journal> journals = journalService.listAll(getUser("user2"));
		Assert.assertEquals(0, journals.size());
	}

	@Test
	public void listPublisher() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		List<Journal> journals = journalService.publisherList(p.get());
		Assert.assertEquals(2, journals.size());

		Assert.assertEquals(new Long(1), journals.get(0).getId());
		Assert.assertEquals(new Long(2), journals.get(1).getId());

		Assert.assertEquals("Medicine", journals.get(0).getName());
		Assert.assertEquals("Test Journal", journals.get(1).getName());
		journals.stream().forEach(j -> Assert.assertNotNull(j.getPublishDate()));
		journals.stream().forEach(j -> Assert.assertEquals(new Long(1), j.getPublisher().getId()));

	}

	@Test(expected = ServiceException.class)
	public void publishFail() throws ServiceException {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName("New Journal");

		journalService.publish(p.get(), journal, 1L);
	}

	@Test(expected = ServiceException.class)
	public void publishFail2() throws ServiceException {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName("New Journal");

		journalService.publish(p.get(), journal, 150L);
	}

	@Test()
	public void publishSuccess() {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);

		Journal journal = new Journal();
		journal.setName(NEW_JOURNAL_NAME);
		journal.setUuid("SOME_EXTERNAL_ID");
		try {
			journalService.publish(p.get(), journal, 3L);
		} catch (ServiceException e) {
			Assert.fail(e.getMessage());
		}

		List<Journal> journals = journalService.listAll(getUser("user1"));
		Assert.assertEquals(2, journals.size());

		journals = journalService.publisherList(p.get());
		Assert.assertEquals(2, journals.size());
		Assert.assertEquals(new Long(3), journals.get(0).getId());
		Assert.assertEquals(new Long(4), journals.get(1).getId());
		Assert.assertEquals("Health", journals.get(0).getName());
		Assert.assertEquals(NEW_JOURNAL_NAME, journals.get(1).getName());
		journals.stream().forEach(j -> Assert.assertNotNull(j.getPublishDate()));
		journals.stream().forEach(j -> Assert.assertEquals(new Long(2), j.getPublisher().getId()));
	}

	@Test(expected = ServiceException.class)
	public void unPublishFail() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 4L);
	}

	@Test(expected = ServiceException.class)
	public void unPublishFail2() {
		User user = getUser("publisher1");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 100L);
	}

	@Test
	public void unPublishSuccess() {
		User user = getUser("publisher2");
		Optional<Publisher> p = publisherRepository.findByUser(user);
		journalService.unPublish(p.get(), 4L);

		List<Journal> journals = journalService.publisherList(p.get());
		Assert.assertEquals(1, journals.size());
		journals = journalService.listAll(getUser("user1"));
		Assert.assertEquals(1, journals.size());
	}

	protected User getUser(String name) {
		Optional<User> user = userService.getUserByLoginName(name);
		if (!user.isPresent()) {
			Assert.fail("user1 doesn't exist");
		}
		return user.get();
	}

}
*/
