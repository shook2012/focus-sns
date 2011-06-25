package org.osforce.connect.task.knowledge;

import java.util.Map;

import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.service.commons.VoteRecordService;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.spring4me.task.AbstractTask;
import org.osforce.spring4me.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

@Task
public class AnswerVoteCountTask extends AbstractTask {

	private AnswerService answerService;
	private VoteRecordService voteRecordService;
	
	public AnswerVoteCountTask() {
	}
	
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	@Autowired
	public void setVoteRecordService(VoteRecordService voteRecordService) {
		this.voteRecordService = voteRecordService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long answerId = (Long) context.get("answerId");
		Answer answer = answerService.getAnswer(answerId);
		Long useful = voteRecordService.countVoteRecords(
				VoteRecord.CODE_USEFUL, answer.getId(), Answer.NAME);
		Long useless = voteRecordService.countVoteRecords(
				VoteRecord.CODE_USELESS, answer.getId(), Answer.NAME);
		answer.setUseful(useful);
		answer.setUseless(useless);
		answer.setVotes(useful + useless);
		answerService.updateAnswer(answer);
	}

}
