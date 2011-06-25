<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<form id="inviteForm${id}" action="${base}/team/member/invite-action" method="post">
			<div>
				<label>角色</label>
				<br/>
				<select name="roleId">
					<#list roles as role>
					<option value="${role.id}">${role.name}</option>
					</#list>
				</select>
			</div>
			<div>
				<label>自动提示:</label>
				<br/>
				<input id="query${id}">
			</div>
			<div>
				<label for="emails">Emails:</label>
				<br/>
				<textarea id="emails" name="emails"></textarea>
			</div>
			<div>
				<button type="submit" class="button">发送</button>
				<button type="reset">重置</button>
				<input type="hidden" name="uniqueId" value="${project.uniqueId}">
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#query${id}').autocomplete({
		serviceUrl:'${base}/team/member/auto-action',
	    minChars:2,
	    delimiter: /(,|;)\s*/, // regex or character
	    maxHeight:400,
	    width:300,
	    zIndex: 9999,
	    deferRequestBy: 0, //miliseconds
	    params: { uniqueId:"${project.uniqueId}" }, //aditional parameters
	    noCache: false, //default is false, set to true to disable caching
	    // callback function:
	    onSelect: function(value, data){
	    	var emails = $('#emails').val();
	    	emails += value + '\r\n';
	    	$('#emails').val(emails);
	    	$('#query${id}').val('');
	    }
	});
});
</script>