<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<@spring.bind "album" />
		<form id="album-form${id}" class="album-form" 
			action="${base}/gallery/album/form-action" method="post">
			<div>
				<label for="name" class="title">相册名 <span class="required"> * </span></label>
				<br/>
				<@spring.formInput path="album.name" />
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<@spring.formHiddenInput path="album.id" />
				<@spring.formHiddenInput path="album.enteredId" />
				<@spring.formHiddenInput path="album.modifiedId" />
				<@spring.formHiddenInput path="album.projectId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form>
	</div>
</div>