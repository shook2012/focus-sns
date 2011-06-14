<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "event" />
		<form id="event-form${id}" class="event-form" 
			action="${base}/calendar/event/form-action" method="post">
			<div>
				<label for="title" class="title"> 标题 <span class="required">*</span> </label>
				<br/>
				<@spring.formInput path="event.title" />
			</div>
			<div>
				<label for="description" class="description"> 描述 </label>
				<br/>
				<@spring.formTextarea path="event.description" attributes='class="xheditor-mini"'/>
			</div>
			<div>
				<label for="start"> 开始日期 <span class="required">*</span></label>
				<br/>
				<input id="start-date${id}" readonly="readonly" class="calendar"
				value='<#if event.start??>${event.start?string('yyyy-MM-dd')}</#if>'>
				<#if event.start??>
				<#assign startHour = event.start?string('H') />
				<#assign startMinute = event.start?string('m') />
				</#if>
				<select id="start-hour${id}" class="hours">
				<#list 0..23 as hour>
					<option <#if startHour?? && startHour?string==hour?string>selected="selected"</#if>>${hour}</option>
				</#list>
				</select>
				:
				<select id="start-minute${id}" class="minutes">
				<#list 0..59 as minute >
					<option <#if startMinute?? && startMinute?string==minute?string>selected="selected"</#if>>${minute}</option>
				</#list>
				</select>
			</div>
			<div>
				<label for="end"> 结束日期 <span class="required">*</span> </label>
				<br/>
				<input id="end-date${id}" readonly="readonly" class="calendar"
				value='<#if event.end??>${event.end?string('yyyy-MM-dd')}</#if>'>
				<#if event.end??>
					<#assign endHour = event.end?string('H') />
					<#assign endMinute = event.end?string('m') />
				</#if>
				<select id="end-hour${id}" class="hours">
				<#list 0..23 as hour >
					<option <#if endHour?? && endHour?string==hour?string>selected="selected"</#if>>${hour}</option>
				</#list>
				</select>
				:
				<select id="end-minute${id}" class="minutes">
				<#list 0..59 as minute >
					<option <#if endMinute?? && endMinute?string==minute?string>selected="selected"</#if>>${minute}</option>
				</#list>
				</select>
			</div>
			<div>
				<label for="url">URL</label>
				<br/>
				<@spring.formInput path="event.url" />
			</div>
			<div>
				<label for="enabled">启用</label>
				<br/>
				<@spring.formCheckbox path="event.enabled" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="event.id" />
				<@spring.formHiddenInput path="event.projectId" />
				<@spring.formHiddenInput path="event.enteredId" />
				<@spring.formHiddenInput path="event.entered" />
				<@spring.formHiddenInput path="event.modifiedId" />
				<input type="hidden" id="start${id}" name="start" value="<#if event.start??>${event.start}</#if>" />
				<input type="hidden" id="end${id}" name="end" value="<#if event.end??>${event.end}</#if>" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
			</div>
		</form:form>
		
		<div id="overlay${id}">
	   		<div id="calendar${id}"></div>
	   	</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#event-form${id}').submit(function(){
		var start = getStartDate();
		var end = getEndDate();
		if(!start || !end) {
			return false;
		};
		$('#start${id}').val(start);
		$('#end${id}').val(end);
	});
	
	function getStartDate() {
		var dateStr = $('#start-date${id}').val();
		if($.trim(dateStr)=='') {return false;}
		var hourStr = $('#start-hour${id}').val();
		var minuteStr = $('#start-minute${id}').val();
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}
	
	function getEndDate() {
		var dateStr = $('#end-date${id}').val();
		if($.trim(dateStr)=='') {return false;}
		var hourStr = $('#end-hour${id}').val();
		var minuteStr = $('#end-minute${id}').val();
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}

	Date.format = 'yyyy-m-d';
	$('#start-date${id}').datePicker();
	$('#end-date${id}').datePicker();
});
</script>