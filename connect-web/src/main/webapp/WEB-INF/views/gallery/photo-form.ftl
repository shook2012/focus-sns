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
		<form id="photo-form${id}" class="photo-form" method="post"
			action="${base}/gallery/photo/form-action" enctype="multipart/form-data">
			<div>
				<label>目标相册</label>
				<br/>
				<@spring.formSingleSelect path="photo.albumId" options=albumOptions />
			</div>
			<div>
				<label>显示名</label>
				<br/>
				<@spring.formInput path="photo.name"/>
			</div>
			<div>
				<label>选择文件</label>
				<br/>
				<input type="file" name="file" id="select-file${id}"/>
			</div>
			<#if Session.photos??>
			<div class="file-queue">
				<label>最近上传</label>
				<br/>
				<table>
					<thead>
						<th>ID</th>
						<th>相片名</th>
						<th>操作</th>
					</thead>
					<tbody>
						<#list Session.photos as photo>
						<tr>
							<td>${photo.id}</td>
							<td><#if photo.name?? && photo.name != ''>${photo.name}<#else>${photo.realFile.name}</#if></td>
							<td>
								<@security code="photo-edit">
								<a href="${base}/${project.uniqueId}/gallery/photo/form?photoId=${photo.id}">编辑</a>
								</@security>
								<@security code="photo-edit">  
								|
								<a href="${base}/${project.uniqueId}/gallery/photo/delete-action?photoId=${photo.id}">删除</a>
								</@security>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
			</#if>
			<div>
				<#if photo.id??>
				<button type="submit">保存</button>
				<#else>
				<button type="submit">上传</button>
				</#if>
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