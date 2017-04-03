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
    <script src="${baseUrl}/js/knockout_common.js"></script>
</head>
<script type="text/javascript">
    var isChecked = false;//用于选择
    var model;
    //节点数据
    var originValue = ko.observable({
        id: 0,
        /*企业名称*/
        companyName: '',
        /*公司电话*/
        companyPhone: '',
        /*简历投递邮箱*/
        resumeMail: "",
        /*联系人微信ID*/
        contactWeixinId: "",
        /*招聘类型*/
        recruitType: "",
        /*企业类型*/
        companyType: "",
        /*招聘人姓名*/
        recruiterName: "",
        /*是否有效*/
        isValid:"",
        /*公司简介*/
        companySynopsis: ""

    });
    var params = {
        pageSize: 2,//显示的页面大小
        currentPage: 0,//当前页
        //初始化节点数据
        EntityValue: ko.observable(new originValue()),
        //用于清空数据
        initValue: ko.observable(new originValue()),
        whereCondition: ko.observable(""),
        // 询的数据
        entityQueryUrl: function () {
            return "${baseUrl}/companyInfo/queryCompanyInfoListByCondition.action";
        },
        //新增实体url
        entityAddUrl: function () {
            return "${baseUrl}/companyInfo/addCompanyInfos.action";
        },
        //更改实体url
        entityUpdateUrl: function () {
            return "${baseUrl}/companyInfo/updateCompanyInfos.action";
        },
        //删除实体url
        entityDeleteUrl: function () {
            return "${baseUrl}/companyInfo/deleteCompanyInfoListByIds.action";
        },
        //总共记录查询
        totalCountQueryUrl: function () {
           // return "${baseUrl}/companyInfo/totalCount.action";

        },
        //返回选中框的标题
        checkedboxName: function () {
            return "itemCheck";
        }
    };
    $(function () {

        var model = new globalViewModel(params);
        //查询数据
        model.init();

        //激活绑定数据
        ko.applyBindings(model);
//        model.getPage = ko.observable(1);
        //拼接查询条件并执行查询计划
        model.getWhereConditionAndQuery = function () {
            var map={
                "jobCode":$("#jobCode").val(),
                "jobSeekerPhone":$("#jobSeekerPhone").val()
            }
            /*var map = new Map(['jobCode', $("#jobCode").val()], ['jobSeekerPhone', $("#jobSeekerPhone").val()]);*/
            // var dealStatus=$("#dealStatusId").val();
            /*拼接查询条件*/
            //var tmpWhereCondtion = "jobCode=" + $("#jobCode").val() + "&jobSeekerPhone=" + $("#jobSeekerPhone").val();
            /*   if (dealStatus!='0'){
             tmpWhereCondtion=tmpWhereCondtion+"&dealStatus="+dealStatus;
             }*/
            model.whereCondition = model.js2ko(map);
            model.dataQuery();

        };
    })


</script>


<body class="nav-md">

<div class="container body">


    <div class="main_container">
        <%--导航--%>
        <%-- <%@include file="navbar.jspf" %>
 --%>
        <!-- 主页内容-->
        <div class="right_col" role="main" style="min-height: 1000px;">
            <div class="page-title">
                <div class="title_left">
                    <h3>
                        求职者用户管理
                    </h3>
                </div>

            </div>
            <%--弹窗--%>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title " id="myModalLabel">公司信息</h4>
                        </div>
                        <%--主题内容--%>
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
                                    <label for="companyName" class="control-label col-md-3 col-sm-3 col-xs-12">企业名称
                                        <span class="required">*</span>
                                    </label>

                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                               data-bind='value:companyName'
                                               id="companyName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="companyPhone" class="control-label col-md-3 col-sm-3 col-xs-12">公司电话
                                        <span class="required">*</span>
                                    </label>

                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                               data-bind='value:companyPhone' id="companyPhone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="resumeMail" class="control-label col-md-3 col-sm-3 col-xs-12">投递邮箱
                                        <span class="required">*</span>
                                    </label>

                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="email" class="form-control col-md-7 col-xs-12" required="required"
                                               data-bind='value:resumeMail' id="resumeMail">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="contactWeixinId" class="control-label col-md-3 col-sm-3 col-xs-12">联系人微信ID
                                        <span class="required">*</span>
                                    </label>

                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" class="form-control col-md-7 col-xs-12" required="required"
                                               data-bind='value:contactWeixinId' id="contactWeixinId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="recruiterName" class="control-label col-md-3 col-sm-3 col-xs-12">招聘人姓名
                                        <span class="required">*</span>
                                    </label>

                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" class="form-control col-md-7 col-xs-12"
                                               required="required"
                                               data-bind='value:recruiterName' id="recruiterName">
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

        <%--表格内容显示--%>
            <div class="x_panel">
                <%--  <div class="form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="jobCode"><span class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="jobCode" required="required" class="form-control col-md-7 col-xs-12">
                      </div>
                  </div>--%>

                <div class="x_title">
                    <form class="form-horizontal form-label-left" style="margin-top: 1%">
                        <div class="form-group" >
                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="dealStatusId"><span
                                    class="required">处理状态</span>
                            </label>
                            <div class="col-md-2 col-sm-3 col-xs-4">
                                <select id="dealStatusId" class="form-control col-md-3 col-xs-6">
                                    <option value="0">全部</option>
                                    <option value="1">待处理</option>
                                    <option value="2">已投递至企业</option>
                                    <option value="3">企业筛选未通过</option>
                                    <option value="4">企业筛选通过</option>
                                    <option value="5">内推圈建立筛选未通过</option>
                                    <option value="6">面试未通过</option>
                                    <option value="7">面试成功</option>
                                </select>
                            </div>

                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="jobCode"><span
                                    class="required">职位编号</span>
                            </label>
                            <div class="col-md-2 col-sm-3 col-xs-4">
                                <input type="text" id="jobCode" required="required"
                                       class="form-control col-md-3 col-xs-6">
                            </div>

                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="jobSeekerPhone"><span
                                    class="required">求职者电话</span>
                            </label>
                            <div class="col-md-2 col-sm-3 col-xs-4">
                                <input type="text" id="jobSeekerPhone" required="required"
                                       class="form-control col-md-3 col-xs-6">
                            </div>
                            <div class="col-md-1 col-sm-1 col-xs-1">
                                <button type="button" class="btn btn-warning"
                                        data-bind="click:$root.getWhereConditionAndQuery">查询
                                </button>
                            </div>
                        </div>
                    </form>
                    <a class="btn btn-danger " data-bind="click:$root.remove"><i class="glyphicon glyphicon-trash"
                                                                                 style="font-size:15px"></i><font
                            style="font-size: 15px">批量删除</font></a>
                    <a class="btn btn-primary " data-bind="click:$root.add"><i
                            class="glyphicon glyphicon-plus"
                            style="font-size:15px"></i><font
                            style="font-size: 15px">添加用户</font></a>


                    <%-- <span style="float: right;font-size: 16px;color: black">共<u data-bind="text:$root.totalCount"></u>记录</span>--%>
                </div>
                <div class="x_content">


                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th style="width: 45px" id="checkItemId" name="itemCheck"
                                data-bind="click:$root.selectAll"><input type="checkbox">
                            </th>
                            <th>公司名称</th>
                            <th>公司电话</th>
                            <th>简历投递邮箱</th>
                            <th>联系人微信ID</th>
                            <th>企业类型</th>
                            <th>招聘类型</th>
                            <th>招聘人姓名</th>
                            <th>是否有效</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <%--绑定需操作数据--%>
                        <tbody data-bind="foreach:EntityValues">
                        <tr>
                            <th style="width: 45px"><input type="checkbox" name="itemCheck"
                                                           data-bind="value:id,attr:{id:id}"></th>
                            <td data-bind="text:companyName" class="col-sm-1"></td>
                            <td data-bind="text:companyPhone" class="col-sm-1"></td>
                            <td data-bind="text:resumeMail" class="col-sm-1"></td>
                            <td data-bind="text:contactWeixinId" class="col-sm-1"></td>
                            <td data-bind="text:companyType" class="col-sm-1"></td>
                            <td data-bind="text:recruitType" class="col-sm-1"></td>
                            <td data-bind="text:recruiterName" class="col-sm-1"></td>
                            <td data-bind="text:isValid" class="col-sm-1"></td>
                            <td style="width:45px"><a href="javascript:" data-bind="click:$root.edit"
                            > <i class="glyphicon glyphicon-edit" style="font-size: large"></i></a></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
                <div>
                    当前第<u data-bind="text:$root.currentPage"></u>页, 共 <u data-bind="text:$root.totalPage"></u> 页
                    <button class="btn btn-default " type="button" style="float:right" data-bind="click:$root.nextPage">
                        下一页
                    </button>
                    <button class="btn btn-default " type="button" style="float:right" data-bind="click:$root.prePage">
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
