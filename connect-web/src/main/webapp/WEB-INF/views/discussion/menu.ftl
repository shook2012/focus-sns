<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<ul class="actions">
			<li>
				<a href="${base}/${project.uniqueId}/discussion/forum/form">添加版块</a>
			</li>
			<li class="last"> 
				<a href="${base}/${project.uniqueId}/discussion/topic/form">我有话题</a>
			</li>
		</ul>
	</div>
</div>