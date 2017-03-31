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

    <title>用户管理</title>
    <%@include file="/common/tag.jspf" %>
    <%@include file="/common/common_css_js.jspf" %>
    <%--引入自定义通用工具类--%>
    <%-- <script src="${baseUrl}/js/knockout_common.js"/>--%>
    <script src="${baseUrl}/js/angular_common.js"></script>
</head>
<script type="text/javascript">
    //初始化页面请求参数
    var params = {
        pageSize: 2,//显示的页面大小
        currentPage: 1,//当前页
        controllerName: "myCtrl",//设置控制器名称
        moduleName: "myApp",//设置模块名称
        entityQueryUrl: function () {
            return "${baseUrl}/sysUser/querySysUsers.action";
        },
        //新增实体url
        entityAddUrl: function () {
            return "${baseUrl}/sysUser/addSysUser.action";
        },
        //更改实体url
        entityUpdateUrl: function () {
            return "${baseUrl}/sysUser/updateSysUser.action";
        },
        //删除实体url
        entityDeleteUrl: function () {
            return "${baseUrl}/sysUser/deleteSysUsers.action";
        },
        //总共记录查询
        totalCountQueryUrl: function () {
            return "${baseUrl}/sysUser/totalCount.action";
        }
    };
    //初始化模块
    var moduleName = 'gridApp';
    var controllerName = "gridCtrl";
    var app = angular.module(moduleName, []);
    //创建工厂模式自定义服务
    /*   app.factory('entityQueryUrl',function () {

     });*/
    app.controller(controllerName, function ($scope, $http) {
        var scope = $scope;
        var http = $http;
        var model = new angularGrid(params, scope, http);
        //通用模块操作
        universalCall(model, scope);
        //扩展方法
        angularExtend(scope);
    });

    //通用方法调用模块
    /**
     * @param model 视图
     * @param scope
     */
    function universalCall(model, scope) {
        model.init();
        scope.clickTest = model.clickTest;
        scope.prePage = model.prePage;
        scope.nextPage = model.nextPage;
    }
    //自定义扩展模块
    function angularExtend(scope) {
    }
</script>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%--导航--%>
        <%@include file="navbar.jspf" %>
        <!-- 主页内容-->
        <div class="right_col" role="main" style="min-height: 1000px;">
            <div class="page-title">
                <div class="title_left">
                    <h3>
                        用户管理
                    </h3>
                </div>
                <div class="title_right">
                    <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                        <div class="input-group">
                            <input type="text" placeholder="Search for..." class="form-control">
                  <span class="input-group-btn">
                            <button type="button" class="btn btn-default">查找</button>
                        </span>
                        </div>
                    </div>
                </div>
            </div>
            <%--    &lt;%&ndash;弹窗&ndash;%&gt;
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title " id="myModalLabel">用户信息</h4>
                            </div>
                            &lt;%&ndash;主题内容&ndash;%&gt;
                            <div class="modal-body">
                                <form class="form-horizontal form-label-left" data-parsley-validate="" id="demo-form2"
                                      data-bind="with: EntityValue">
                                    <div class="form-group">
                                        <label for="nameid" class="control-label col-md-3 col-sm-3 col-xs-12">id
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                                   data-bind='value:id'
                                                   id="nameid">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="control-label col-md-3 col-sm-3 col-xs-12">姓名
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                                   data-bind='value:username'
                                                   id="name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="usercode" class="control-label col-md-3 col-sm-3 col-xs-12">编号
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                                   data-bind='value:usercode' id="usercode">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="control-label col-md-3 col-sm-3 col-xs-12">邮箱
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="email" class="form-control col-md-7 col-xs-12" required="required"
                                                   data-bind='value:email' id="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="salt" class="control-label col-md-3 col-sm-3 col-xs-12">盐
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                                   data-bind='value:salt' id="salt">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="control-label col-md-3 col-sm-3 col-xs-12">密码
                                            <span class="required">*</span>
                                        </label>

                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="password" class="form-control col-md-7 col-xs-12"
                                                   required="required"
                                                   data-bind='value:password' id="password">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <a type="button" class="btn btn-primary" data-bind="click:$root.saveOrUpdate">保存</a>
                            </div>
                        </div>
                    </div>
                </div>
    --%>
            <%--表格内容显示--%>
            <div class="x_panel" ng-app="gridApp" ng-controller="gridCtrl">
                <div class="x_title">
                    <%--<a class="btn btn-danger " data-bind="click:$root.remove"><i class="glyphicon glyphicon-trash"
                                                                                 style="font-size:15px"></i><font
                            style="font-size: 15px">批量删除</font></a>
                    <a class="btn btn-primary " data-bind="click:$root.add"><i
                            class="glyphicon glyphicon-plus"
                            style="font-size:15px"></i><font
                            style="font-size: 15px">添加用户</font></a>
                    <span style="float: right;font-size: 16px;color: black">共<u data-bind="text:$root.totalCount"></u>记录</span>--%>
                </div>
                <div class="x_content">

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th style="width: 45px" id="checkItemId" name="itemCheck"
                                data-bind="click:$root.selectAll"><input type="checkbox">
                            </th>
                            <th>用户名</th>
                            <th>用户编号</th>
                            <th>用户邮箱</th>
                            <th>盐</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <%--绑定需操作数据--%>

                        <tr ng-repeat="entityValue in EntityValues">
                            <th style="width: 45px"><input type="checkbox" name="itemCheck" value="{{entityValue.id}}">
                            </th>
                            <td class="col-sm-3">{{entityValue.username}}</td>
                            <td class="col-sm-3">{{entityValue.usercode}}</td>
                            <td class="col-sm-3">{{entityValue.email}}</td>
                            <td style="width: auto">{{entityValue.salt}}</td>
                            <td style="width:45px"><a href="javascript:" data-bind="click:$root.edit"
                            > <i class="glyphicon glyphicon-edit" style="font-size: large"></i></a></td>
                        </tr>
                    </table>

                </div>
                <div>
                    当前第<u>{{currentIndex}}</u>页, 共 <u>{{totalPage}}</u> 页
                    <button class="btn btn-default " type="button" style="float:right" ng-click="nextPage()">
                        下一页
                    </button>
                    <button class="btn btn-default " type="button" style="float:right" ng-click="prePage()">
                        上一页
                    </button>

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
