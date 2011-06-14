<#import "/META-INF/spring.ftl" as spring />

<div id="${widgetConfig.id}" class="widget">
	<#if widgetConfig.title??>
	<div class="head">
		<h3>${widgetConfig.title}</h3>
	</div>
	</#if>
	<div class="body">
		<#if roles?size == 0 >
		<div class="notice">请先为当前分类添加角色！</div>
		<#else>
		<form class="permission-form" action="${base}/system/permission/form-action" method="post">
		<div>
			<span>当前分类：</span>
			<select id="select-category${widgetConfig.id}" name="categoryId">
				<#list categories as category>
				<option value="${category.id}" <#if RequestParameters.categoryId?? && category.id == RequestParameters.categoryId>selected="selected"</#if>>${category.label}</option>
				</#list>
			</select>
		</div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>编码</th>
					<th>默认角色</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			<#list resources as resource>
				<#if resourceMap[resource.code]??>
				<#assign permission = resourceMap[resource.code] />
				</#if>
				<tr>
					<td>${resource.id}</td>
					<td>${resource.name}</td>
					<td>${resource.code}</td>
					<td>
						<input type="hidden" name="resourceIds" value="${resource.id}"/>
						<select name="roleIds">
							<#list roles as role>
							<option value="${role.id}" <#if permission?? && permission.role.id == role.id>selected="selected"</#if>>${role.name}</option>
							</#list>
						</select>
					</td>
					<td>
						<#if permission?? && permission.enabled>启用<#else>禁用</#if>
					</td>
				</tr>
			</#list>
			</tbody>
			<#if resources?size gt 0>
			<tfoot>
				<tr>
					<td colspan="5">
						<button type="submit" class="button">提交</button>
						<input type="hidden" name="siteId" value="${RequestParameters.siteId}" />
					</td>
				</tr>
			</tfoot>
			</#if>
		</table>
		</form>
		</#if>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#select-category${widgetConfig.id}').change(function(){
		window.location.href='?siteId=${RequestParameters.siteId}&categoryId=' + $(this).val();
	});
});
</script/>