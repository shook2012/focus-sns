<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "resource"/>
		<form class="resource-form" action="${base}/system/resource/form-action" method="post">
			<div>
				<label for="name">名称: <span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="resource.name" />
				<@spring.showErrors separator="<br/>" classOrStyle="error" />
			</div>
			<div>
				<label for="code">编码: <span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="resource.code" />
				<@spring.showErrors separator="<br/>" classOrStyle="error" />				
			</div>
			<div>
				<label for="description">描述:</label>
				<br/>
				<@spring.formTextarea path="resource.description" />
			</div>
			<div>
				<label for="enabled">启用:</label>
				<br/>
				<@spring.formCheckbox path="resource.enabled" />
			</div>
			<div>
				<button type="submit">提交</button>
				<button type="reset">重置</button>
				<@spring.formHiddenInput path="resource.id" />
			</div>
		</form>
	</div>
</div>