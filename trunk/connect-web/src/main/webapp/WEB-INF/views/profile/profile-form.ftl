<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<form class="logo-form" action="${base}/profile/profile/logo-action" method="post">
			<div>
			<#if profile.logo??>
				<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>			
			<#else>
				<img class="thumbnail" src="${base}/themes/${theme}/stock/${profile.project.category.code}.png"/>
			</#if>
			</div>
			<div>
				<input type="file" id="select-file${id}" name="file">
				<input type="hidden" name="forward" value="/profile/logo"/>
				<input type="hidden" name="profileId" value="${profile.id}"/>
			</div>
		</form>

		<@spring.bind "profile" />
		<form class="profile-form" action="${base}/profile/profile/form-action" method="post">
			<div>
				<label for="title">标题 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="profile.title" />
			</div>
			<div>
				<label for="shortDescription">简介 <span class="required">*</span></label>
				<br/>
				<@spring.formTextarea path="profile.shortDescription" />
			</div>
			<div>
				<label for="description">描述</label>
				<br/>
				<@spring.formTextarea path="profile.description"/>
			</div>
			<br/>
			<label>自定义属性 (<a id="addRow${id}" href="#">添加行</a>)</label>
			<div id="profile-attributes${id}" class="profile-attributes">
				<#list profile.attributeList as attribute>
				<div>
					<input name="labels" value="${attribute[0]}" size="10"/>
					<input name="values" value="${attribute[1]}" size="40"/>
				</div>
				</#list>
				<div id="blankRow${id}">
					<input name="labels" size="10"/>
					<input name="values" size="40"/>
				</div>
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="profile.id" />
				<@spring.formHiddenInput path="profile.enteredId" />
				<@spring.formHiddenInput path="profile.entered" />
				<@spring.formHiddenInput path="profile.modifiedId" />
				<@spring.formHiddenInput path="profile.logoId" />
			</div>
		</form:form>
	</div>
</div>