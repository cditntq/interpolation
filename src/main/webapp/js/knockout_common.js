/**
 * Created by ShuangYang on 2016/4/14.
 */
//构造原始模型
function globalViewModel(params) {
    var self = this;
    self.pageSize = params.pageSize;//每页显示数据
    self.currentPage = ko.observable(1);//默认当前页为第一页
    self.totalPage = ko.observable();//总页数，默认有一页
    self.totalCount = ko.observable(0);//当前查询条件下总记录数
    self.EntityValues = ko.observableArray([]);//从json中获取数据集
    self.EntityValue = params.EntityValue;//单个数据
    self.initValue = params.initValue;//初始化数据
    //url操作
    self.entityQueryUrl = params.entityQueryUrl();//获取查询的url
    self.entityAddUrl = params.entityAddUrl();//新增实体url
    self.entityUpdateUrl = params.entityUpdateUrl();//更新实体url
    self.entityDeleteUrl = params.entityDeleteUrl();//删除实体url
    self.totalCountQueryUrl = params.totalCountQueryUrl();//
    self.isAdd = false;//是否增加
    self.checkedboxName = params.checkedboxName();//checkbox的指定名称
    self.checkedIds = ko.observable();
    self.whereCondition = params.whereCondition;//传递过来查询条件


    //初始化集合操作
    self.init = function () {
        self.dataQuery();
        self.pageCountQuery();
    };
    //获取分页数量 并进行分页相关处理
    self.pageCountQuery = function () {
        $.ajax({
            type: "GET",
            url: self.totalCountQueryUrl,
            success: function (data) {
                //获取数据
                self.totalCount(data);
                self.totalPageComput();
            }
        })

    };
    //获取查询的数据 int page, int size, String whereCondition
    self.dataQuery = function () {
        $.ajax({
            type: "GET",
            contentType: 'application/json',
            url: self.entityQueryUrl,
            data: {
                page: self.ko2js(self.currentPage),
                size: self.ko2js(self.pageSize),
                whereCondition: self.ko2js(self.whereCondition)
            },
            success: function (response) {
                //清空数据重新加载
                self.EntityValues([]);
                // data = JSON.parse(data);//解析json数据
                $.each(response.data, function (i, result) {
                    self.EntityValues.push(self.js2ko(result));
                });
            }
        })
    };
    //删除
    self.remove = function () {
        //获取选中的ids
        var ids = self.getCheckedIds();
        if (ids == -1) {
            alert("删除无效:" + ids);
        }
        else {//删除操作
            $.ajax({
                type: "post",
                url: self.entityDeleteUrl,
                data: {ids: ids},
                success: function (data) {
                    //alert("删除成功")
                    self.init();
                }
            })
        }
    };

    //编辑 展示到写字板上
    self.edit = function (item) {
        self.isAdd = false;
        self.EntityValue(item);
        $('#myModal').modal('toggle')
    };
    self.add = function () {
        self.isAdd = true;
        self.EntityValue(self.initValue);
        $('#myModal').modal('toggle')

    };
    //保存或者更新用户
    self.saveOrUpdate = function () {  //将当前数据保存到数据库
        var url = "";//当前的url
        if (self.isAdd == true) {
            url = self.entityAddUrl;
        }
        else {
            url = self.entityUpdateUrl;
        }
        //根据条件插入或者更新
        $.ajax({
            type: "post",
            url: url,
            contentType: 'application/json',
            data: ko.mapping.toJSON(self.EntityValue),
            dataType: 'json',
            success: function (data) {
                if (data == 1) {
                    //提示插入成功
                    //重新排版界面

                }
            }
        })
        $('#myModal').modal('toggle');
        //初始化数据
        //self.init();
    }

    //上一页
    self.prePage = function () {
        var currentItem = self.ko2js(self.currentPage);
        if (currentItem == 1) {
            alert("已到最前面")
        }
        else {
            currentItem--;
            self.currentPage(currentItem);
            self.dataQuery()
        }

    };
    //下一页
    self.nextPage = function () {
        var currentItem = self.ko2js(self.currentPage);
        var total = self.ko2js(self.totalPage);
        if (currentItem == total) {
            alert("已无更多数据")
        }
        else {
            currentItem++;
            self.currentPage(currentItem);
            //查询
            self.dataQuery()

        }
    };
    //计算总页数
    self.totalPageComput = function () {
        var count = self.ko2js(self.totalCount);//获取总的数据
        var page = parseInt(count / self.pageSize);//取整
        if ((count % self.pageSize) > 0) {
            page++;
        }
        self.totalPage(page);

    };

    //扩展功能
    //选中
    self.selectAll = function () {
        var num = $("input[type='checkbox'][name='" + self.checkedboxName + "']").not("input:checked").length;//获取未被选中的个数
        $("input:checkbox[name='" + self.checkedboxName + "']").each(function () {
            if (num > 0) {
                $(this).prop("checked", true);
            }
            else {
                $(this).prop("checked", false);
            }
        });

    };

    //获取的选中的ids串
    self.getCheckedIds = function () {
        var num = $("input[type='checkbox'][name='" + self.checkedboxName + "']:checked").length;//获取未被选中的个数
        if (num < 1) {
            alert("请先选中需要删除的数据");
            return -1;
        }
        alert("选中的num：" + num);
        var ids = "";//拼接传递字符串
        $("input:checkbox[name='" + self.checkedboxName + "']:checked").each(function () {
            ids += $(this).val() + ",";
        });
        ids = ids.substring(0, ids.length - 1);
        return ids;
    }

    //
    self.ko2js = function (data) {
        return ko.mapping.toJS(data);
    }
    self.js2ko = function (data) {
        return ko.mapping.fromJS(data);
    }

    /**
     * 常规的Get请求
     * @param requestUrl
     * @param jsonData
     */
    self.commonAjaxGetRequest=function (requestUrl, jsonData) {
        var resultData;
        $.ajax({
            type: "GET",
            url: requestUrl,
            contentType: 'application/json',
            data: jsonData,
            dataType: 'json',
            success: function (data) {
                resultData=data;
            }
        });
        return resultData;
    }
}
