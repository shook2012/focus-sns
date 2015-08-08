下面是一段首页的页面定义文件，接下来我们通过这段定义来了解一下 FocusSNS 的页面自定义。
```
<page parent="parent/global" path="/home">
	<placeholder name="mainColumn">
		<widget name="contentWidget" path="/commons/content/detail-view" cache="60">
			<title>主列内容</title>
			<prefs>
				<content><![CDATA[
			        主列内容详情
			        ]]></content>
			</prefs>
		</widget>
	</placeholder>
	<placeholder name="rightColumn">
		<widget name="contentWidget" path="/commons/content/detail-view">
			<title>公告</title>
			<prefs>
				<content><![CDATA[
			        公告内容详情
			        ]]></content>
			</prefs>
		</widget>
	</placeholder>
</page>
```
页面定义文件是基于位置的定义，在上面的定义中定义了两个位置，mainColumn 和 rightColumn，当然 page 元素中还有一个 parent 元素所指定的父定义，注意：子定义可以覆盖父定义。

widget 元素是一个组件的定义，组件由 Spring 容器管理。