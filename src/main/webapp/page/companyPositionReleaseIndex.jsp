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
        isValid: "",
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
            //return "${baseUrl}/companyInfo/queryCompanyInfoListByCondition.action";
        },
        //新增实体url
        entityAddUrl: function () {
           // return "${baseUrl}/companyInfo/addCompanyInfos.action";
        },
        //更改实体url
        entityUpdateUrl: function () {
           // return "${baseUrl}/companyInfo/updateCompanyInfos.action";
        },
        //删除实体url
        entityDeleteUrl: function () {
           // return "${baseUrl}/companyInfo/deleteCompanyInfoListByIds.action";
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

            var jsonData={
                phoneNumber:15123247202,
                verifyCode:"12345"
            };
            $.ajax({
                type: "GET",
                url: "${baseUrl}/companyPositionInfo/verifyMessageCode.action",
                contentType: 'application/json',
                data: jsonData,
                dataType: 'json',
                success: function (data) {
                    resultData=data;
                }
            });



        };

        /*   model.availableCountries= ko.observableArray(['France', 'Germany', 'Spain'])*/

    })


</script>

<body class="nav-md">

这是职位发布首页

<div>
    <button class="btn btn-default " type="button" style="float:right" onclick="location.href='${baseUrl}/companyInfo/positionReleasePageRedirect.action>type=1'">
        第一次发布职位
    </button>
    <button class="btn btn-default " type="button" style="float:right" onclick="location.href='${baseUrl}/companyInfo/positionReleasePageRedirect.action>type=2'">
        已发布职位
    </button>

</div>
</body>

</html>
