package org.osforce.connect.dao.knowledge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osforce.connect.entity.knowledge.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext-dao.xml")
@Transactional
public class AnswerDaoTests {
	
	@Autowired
	private AnswerDao answerDao;
	
	@Test
	public void testGetAnswer() {
		Answer answer = answerDao.get(1L);
		System.out.println(answer.getContent());
	}
	
	@Test
	public void testFindAnswerPage() {
	}
	
}