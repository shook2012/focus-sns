package org.osforce.connect.web.module.review;

import javax.validation.Valid;

import org.osforce.connect.entity.review.Rating;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.rating.RatingService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.spring4me.web.bind.annotation.RequestAttr;
import org.osforce.spring4me.web.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.2
 * @create Jun 23, 2011 - 11:25:27 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Widget
@RequestMapping("/review/rating")
public class RatingWidget {
	
	private RatingService ratingService;
	
	public RatingWidget() {
	}
	
	@Autowired
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@RequestMapping("/list-view")
	public String doListView() {
		return "review/rating-list";
	}
	
	@RequestMapping("/form-view")
	public String doFormView(@Valid @ModelAttribute Rating rating,
			BindingResult result, Model model, Boolean showErrors,
			@RequestAttr Project project, @RequestAttr User user) {
		if(!showErrors) {
			rating.setProject(project);
			rating.setEnteredBy(user);
			rating.setModifiedBy(user);
			//
			model.addAttribute(AttributeKeys.RATING_KEY_READABLE);
		}
		return "review/rating-form";
	}
	
	@RequestMapping("/form-action")
	public String doFormAction(@Valid @ModelAttribute Rating rating, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute(AttributeKeys.SHOW_ERRORS_KEY_READABLE, true);
			model.addAttribute(AttributeKeys.FEATURE_CODE_KEY_READABLE, ProjectFeature.FEATURE_REVIEW);
			return "page:review/rating-form";
		}
		//
		if(rating.getId()==null) {
			ratingService.createRating(rating);
		} else {
			ratingService.updateRating(rating);
		}
		return String.format("redirect:/%s/review", rating.getProject().getUniqueId());
	}
	
}
