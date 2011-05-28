/*
 *	jQuery AdubyTree Plugin 
 *	version: 5.0 (2011-05-20)
 *
 * 	Copyright (c) 2010, Crystal, shimingxy@163.com
 * 	Dual licensed under the MIT and GPL licenses:
 * 	http://www.opensource.org/licenses/mit-license.php
 * 	http://www.gnu.org/licenses/gpl.html
 * 	Date: 2011-05-20 rev 13
 *
 */

AdubyTree Plugin  based jquery,Simple and util Utility 、OpenSource and FREE.
	support XML and JSON 
	support local data and remote AJAX data.
	support checkbox that you need.,checkbox can before or after the leaf node.
	support three style,simple、strengthen、extend
	have Basic、Sample、Books、Org、OrgUser、Vista six themes，you can custom themes youself
	have Context Menu with user function define,you can move node user Context Menu
	Homepage&Blog:
		http://blog.163.com/shimingxy/ 
		any questions ,you can visite 
	download source:
		http://code.google.com/p/jquery-adubytree/

AdubyTree Plugin基于JQuery框架，实用，调用简单，基于开源协议，免费使用。

	支持XML和JSON两种数据格式
	支持本地数据和动态AJAX远程数据。
	支持checkbox复选框，方便实际应用,checkbox叶节点的前面或者后面。
	支持三种样式，简单样式、增强样式和扩展样式
	自带Basic、Sample、Books、Org、OrgUser、Vista六套主题，用户可以扩展定制主题。
	支持右键菜单，可自定义事件，可通过右键菜单移动节点
	项目博客：http://blog.163.com/shimingxy/
	源代码的下载:
         http://code.google.com/p/jquery-adubytree
		 

============================================================================================
在网页的头加入js和css,在body中加入adubytree的定义标签<div id="adubytree" ></div>
<html>
	<head>
		<script src="jquery.js" type="text/javascript"></script>
		<script src="jquery.easing.1.3.js" type="text/javascript"></script>
		<script src="jquery.adubytree.js" type="text/javascript"></script>
		<link rel="StyleSheet" href="themes/basic/adubytree.css" type="text/css" />
	</head>
	<body>
		<div id="adubytree" ></div>
	</body>
</html>


============================================================================================
$("#adubytree").AdubyTree({
	url:"",//if you want get data from server,set url,else load data from data param
	id:"-1",//if url param is set,send the root id to server
	type: "GET",//if url param is set,the POST method ,GET or POST,default is GET
	dataType:"json",//datatype json、xml，default is json
	checkboxes:true,//is use checkbox,true is user,false is not,default is false
	themes	: "themes/vista/images/",//default is "themes/basic/images/"
	data:"dataEg",//if url param is set ,not need set data
	treeType:"simple",//simple、strengthen、extend,default is extend
	checkboxPos:"before",//checkbox position before or after,default is before
	cookie: true,//Is use cookie,true or false
	themes:"Basic",//Basic,Sample,Books,Org,OrgUser,Vista six themes
	onSelected:function(node){	
		//selected node add you code,node is return Node type
	},
	onClick:function(node){	
		//onClick node add you code,node is return Node type
	},
	onDblClick:function(node){	
		//onDblClick node add you code,node is return Node type
	}
	onCBXClick:function(node){	
		//onCBXClick node add you code,node is return Node type
	},
	onCBXDblClick:function(node){	
		//onCBXDblClick node add you code,node is return Node type
	},
	onMouseOver:function(node){	
		//onMouseOver node add you code,node is return Node type
	},
	onMouseOut:function(node){	
		//onMouseOut node add you code,node is return Node type
	},
	onOpen:function(node){	
		//onOpen node add you code,node is return Node type
	},
	onAddNewNode:function(node){	
		//onAddNewNode add you code,click contextMenu add
	},
	onEditNode:function(node){	
		//onEditNode node add you code,click contextMenu Edit
	},
	onMoveNode:function(node){	
		//onMoveNode node add you code,click contextMenu Move
	},
	onMoveToNextNode:function(node){	
		//onMoveToNextNode node add you code,click contextMenu MoveToNext
	},
	onMoveToChildNode:function(node){	
		//onMoveToChildNode node add you code,click contextMenu MoveToChild
	},
	onDeleteNode:function(node){	
		//onDeleteNode node add you code,click contextMenu delete
	}
	});
============================================================================================
Opration function
open all tree items
$("#adubytree").openAll();

close all tree  items
$("#adubytree").closeAll();

get the  node you give the nodeid
$("#adubytree").getNode(nodeid)

get the current selected node id
$("#adubytree").getSelected()

add node to adubtytree pid is nodeid
node ={ 
	id : "node-3-1-1-2" , 
	data: "node3.1.1.2",
};
node.id=node.id+"-"+x;
node.data=node.data+"-"+x;
$("#adubytree").addNode(node,nodeid);

modify node when id eq nodeid,value is in node
$("#adubytree").modify(nodeid,node)

remove the Node id eq nodeid	
$("#adubytree").removeNode(nodeid);

get all checkbox checked item ids
$("#adubytree").getChecked()

refresh the adubytree 
$("#adubytree").refresh();
============================================================================================
dataEg
JSON
var jsondata ={id : "node-0" , data: "C:",
		children: [
			{ id : "node-1" ,data: "node1",
				children: [
					{ id : "node-1-1" ,data: "node1.1",
						children: [
						{ id : "node-1-1-1" ,data: "node1.1.1"},
						{ id : "node-1-1-2" ,data: "node1.1.2"}
						]},
					{id : "node-1-2" , data: "node1.2"},
					{id : "node-1-3" ,data: "node1.3"}
				] 
			},
			{id : "node-2" ,data: "node2"},
			{id : "node-3", data: "node3",
				children: [
					{ id : "node-3-1" ,data: "node3.1",
					children: [
						{id : "node-3-1-1" , data: "node3.1.1",
							children: [
								{ id : "node-3-1-1-1" , data: "node3.1.1-1"},
								{ id : "node-3-1-1-2" , data: "node3.1.1-2"}
							] 
						}
					] 
					}
				] 
			}
		] 
	};
XML
var xmldata ='<?xml version="1.0"?><root>';
	xmldata +="<id>node-1</id><data>c:</data><children>";
	xmldata +=		"<node><id>node-1-1</id><data>node-1-1</data></node>";
	xmldata +=		"<node><id>node-1-2</id><data>node-1-2</data></node>";
	xmldata +=		"<node><id>node-1-3</id><data>node-1-3</data><children>";
	xmldata +=			"<node><id>node-3-1</id><data>node-3-1</data><children>";
	xmldata +=				"<node><id>node-3-1-1</id><data>node-3-1-1</data></node>";
	xmldata +=				"<node><id>node-3-1-2</id><data>node-3-1-2</data></node>";
	xmldata +=			"</children></node>";
	xmldata +=		"<node><id>node-3-2</id><data>node-3-2</data></node>";
	xmldata +=	"</children></node>";
	xmldata +="</children></root>";
============================================================================================
adubytreeWeb 
is eclipse Project ,you can import to eclipse workspace
is include src,WebRoot
lib
	commons-logging-1.1.1.jar
		Apache Commons Logging
		Copyright 2003-2007 The Apache Software Foundation

		This product includes software developed by
		The Apache Software Foundation (http://www.apache.org/).
	flexjson-2.0.jar
		Apache License
        Version 2.0, January 2004
        http://www.apache.org/licenses/
		http://flexjson.sourceforge.net/

adubytreeWeb.war
	deploy to tomcat ,or copy to $TOMCAT_HOME$/webapps
	visit url http://website:port/adubytreeWeb/demo.html
			  http://website:port/adubytreeWeb/styledemo.html
============================================================================================
 1、支持浏览器/supported browsers are:
	Internet Explorer 6+ *
	Mozilla Firefox 3+
	Google Chrome
2、未测试浏览器/UnTest browsers are:
	Safari 3+
	Opera 9+
============================================================================================