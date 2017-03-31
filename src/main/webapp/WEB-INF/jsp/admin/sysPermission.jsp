<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>权限管理</title>
    <%@include file="/common/common_css_js.jspf" %>
    <%--引入zTree树--%>
    <!-- ZTree树形插件 -->
    <link rel="stylesheet" href="${baseUrl}/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${baseUrl}/js/zTree/js/jquery.ztree.all.min.js"></script>
    <%--引入自定义通用工具类--%>
    <script src="${baseUrl}/js/knockout_zTree.js"></script>

</head>
<script type="text/javascript">
 window.ClipboardData.setData()
    var zTreeModel;
    //节点数据
    var originValue = ko.observable({
        id: 0,
        name: '',
        type: '',
        url: '',
        percode: '',
        pid: '',
        parentids: '',
        sortstring: '',
        available: ''

    });
    var zTreeParams = {
        zTreeNodeValue: ko.observable(new originValue()),
        zTreeInitNode: ko.observable(new originValue()),
        // 获取权限树的数据
        zTreeQueryUrl: function () {
            return "${baseUrl}/sysPermission/queryPermissions.action";
        },
        //添加节点的url
        zTreeNodeAddUrl: function () {
            return "${baseUrl}/sysPermission/addSysPermission.action";
        },
        //更新节点的url
        zTreeNodeUpdateUrl: function () {
            return "${baseUrl}/sysPermission/updatePermission.action";
        },
        //删除节点的url
        zTreeNodeDeleteUrl: function () {
            return "${baseUrl}/sysPermission/deletePermissions.action";
        }

    }

    $(function () {
        zTreeModel = new zTreeViewModel(zTreeParams);
        ko.applyBindings(zTreeModel);
        zTreeModel.init();

    });
    //得到选中的节点信息
    function computNodes() {
        var s_child = "", s_parent = "";
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes(true);//获取所有选择的节点
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].isParent) {
                s_parent += nodes[i].name + ",";//父节点
                alert(nodes[i].name);
            } else {
                s_child += nodes[i].name + ",";//子节点
                alert(nodes[i].name);
            }
        }
    }
</script>


<body class="nav-md">

<div class="container body">


    <div class="main_container">
        <%--导航--%>
        <%@include file="navbar.jspf" %>

        <!-- 主页内容-->
        <div class="right_col" role="main" style="min-height: 980px;">

            <%--弹窗--%>
            <%--zTree树结构--%>
            <div class="x_title">
                <div class="page-title col-md-6 col-sm-6 col-xs-12">
                    <div class="title_left">
                        <h3 onclick="computNodes()">
                            权限管理
                        </h3>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="row" style="clear: both;width: 95%;margin-left: auto;margin-right: auto">
                    <div class="x_title">
                        <div style="float:left">
                            <a class="btn btn-danger" data-bind="click:$root.remove"><i
                                    class="glyphicon glyphicon-trash"
                                    style="font-size:15px"></i><font
                                    style="font-size: 15px">批量删除</font></a>
                            <a class="btn btn-primary " data-bind="click:$root.addZtreeNode"
                               style="float: right"><i
                                    class="glyphicon glyphicon-plus"
                                    style="font-size:15px"></i><font
                                    style="font-size: 15px">新增节点</font></a>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>
                <div class="x_content" style="clear: both;width: 95%;margin-left: auto;margin-right: auto">

                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="row" style="clear: both;margin-left: 25%">
                                <div class="col-md-3 col-sm-3 col-xs-6">
                                    <ul id="tree" class="ztree" style="height:600px;clear: both"></ul>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-4 col-xs-12" style="float: right">

                            <div class="row">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>节点信息
                                        </h2>

                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <div class="modal-body">
                                            <form class="form-horizontal form-label-left" data-parsley-validate=""
                                                  id="demo-form2"
                                                  data-bind="with: zTreeNodeValue">

                                                <div class="form-group">
                                                    <label for="name" class="control-label col-md-3 col-sm-3 col-xs-12">名称
                                                        <span class="required">*</span>
                                                    </label>

                                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                                        <input type="text" class="form-control col-md-7 col-xs-12"
                                                               required="required"
                                                               data-bind='value:name'
                                                               id="name">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="percode"
                                                           class="control-label col-md-3 col-sm-3 col-xs-12">权限标识
                                                        <span class="required">*</span>
                                                    </label>

                                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                                        <input type="text" class="form-control col-md-7 col-xs-12"
                                                               required="required"
                                                               data-bind='value:percode'
                                                               id="percode">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="permission_Url"
                                                           class="control-label col-md-3 col-sm-3 col-xs-12">访问路径
                                                        <span class="required">*</span>
                                                    </label>

                                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                                        <input type="text" class="form-control col-md-7 col-xs-12"
                                                               required="required"
                                                               data-bind='value:url'
                                                               id="permission_Url">
                                                    </div>
                                                </div>
                                            </form>
                                            <button class="btn btn-default " type="button" style="float:right"
                                                    data-bind="click:$root.saveOrUpdate">
                                                保存
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <footer>
                <div class="copyright-info">
                    <p class="pull-right">Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
                    </p>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->
        </div>
        <!-- /page content -->

    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>


</body>

</html>
