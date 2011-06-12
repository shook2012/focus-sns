package org.osforce.connect.web.module.commons;

import java.util.Collections;

import org.osforce.connect.entity.commons.VoteRecord;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.VoteRecordService;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.1
 * @create Jun 3, 2011 - 6:06:26 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/commons/vote")
public class VoteWidget {

	private VoteRecordService voteRecordService;
	
	public VoteWidget() {
	}
	
	@Autowired
	public void setVoteRecordService(VoteRecordService voteRecordService) {
		this.voteRecordService = voteRecordService;
	}
	
	@RequestMapping(value="/vote-action", method=RequestMethod.GET)
	public @ResponseBody Object doVoteAction(@RequestParam String code,
			@RequestParam Long linkedId,@RequestParam String entity, @RequestAttr User user) {
		VoteRecord voteRecord = voteRecordService.getVoteRecord(linkedId, entity, user);
		if(voteRecord==null) {
			voteRecord = new VoteRecord(code, linkedId, entity);
			voteRecord.setUser(user);
			voteRecordService.createVoteRecord(voteRecord);
		} else {
			voteRecord.setCode(code);
			voteRecord.setLinkedId(linkedId);
			voteRecord.setEntity(entity);
			voteRecord.setUser(user);
			voteRecordService.updateVoteRecord(voteRecord);
		}
		return Collections.singletonMap("id", voteRecord.getId());
	}
}
