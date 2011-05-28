<#import "/META-INF/spring.ftl" as spring />

<#assign id = widgetConfig.id />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if albumOptions?? && albumOptions?size == 0 >
		<div class="notice">请先添加相册！</div>
		<#else>
		<@spring.bind "photo" />
		<form id="photo-form${id}" class="photo-form"
			action="${base}/gallery/photo/form-action" method="post">
			<div>
				<label>目标相册</label>
				<br/>
				<@spring.formSingleSelect path="photo.albumId" options=albumOptions />
			</div>
			<div>
				<input type="file" name="file" id="select-file${id}"/>
			</div>
			<div id="file-queue${id}" class="file-queue">
			
			</div>
			<div>
				<button type="submit">提交</button>
				<@spring.formHiddenInput path="photo.id" />
				<@spring.formHiddenInput path="photo.enteredId" />
				<@spring.formHiddenInput path="photo.modifiedId" />
				<@spring.formHiddenInput path="photo.realFileId" />
				<input type="hidden" name="uniqueId" value="${project.uniqueId}" />
			</div>
		</form>
		</#if>
	</div>
</div>