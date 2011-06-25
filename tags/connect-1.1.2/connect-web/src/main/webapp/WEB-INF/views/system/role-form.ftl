<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "role"/>
		<form class="role-form" action="${base}/system/role/form-action" method="post">
			<div>
				<label for="categoryId">分类</label>
				<br/>
				<@spring.formSingleSelect path="role.categoryId" options=categoryOptions />
			</div>
			<div>
				<label for="name">角色名 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="role.name" />
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="code">编码 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="role.code" />
				<@spring.showErrors separator="<br/>" classOrStyle="error"/>
			</div>
			<div>
				<label for="level">级别 <span class="required">*</span></label>
				<br/>
				<@spring.formInput path="role.level" />
			</div>
			<div>
				<label for="description">描述</label>
				<br/>
				<@spring.formTextarea path="role.description" />
			</div>
			<div>
				<label for="enabled">启用</label>
				<br/>
				<@spring.formCheckbox path="role.enabled" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="role.id" />
				<input type="hidden" name="siteId" value="${RequestParameters.siteId}" />
			</div>
		</form>
	</div>
</div>