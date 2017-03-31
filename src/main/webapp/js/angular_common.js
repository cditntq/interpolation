/**
 * 创建Grid模板类型
 * @param params
 */
function angularGrid(params, scope, http) {
    var self = this;
    self.pageSize = params.pageSize;//初始化分页参数
    self.currentIndex = params.currentPage;//初始化页码
    self.scope = scope;//视图作用域(包含页面参数)
    self.http = http;//http请求相关操作
    self.totalCount = 0;//当前查询条件下总记录条数
    //数据集合
    self.EntityValues = [];//数据集合
    self.EntityValue = "";
    //定义请求方法参数:GET和POST
    self.GET = "GET";
    self.POST = "POST";
    //url操作
    self.entityQueryUrl = params.entityQueryUrl();//获取查询的url
    self.entityAddUrl = params.entityAddUrl();//新增实体url
    self.entityUpdateUrl = params.entityUpdateUrl();//更新实体url
    self.entityDeleteUrl = params.entityDeleteUrl();//删除实体url
    self.totalCountQueryUrl = params.totalCountQueryUrl();//
    self.isAdd = false;//是否增加
    /**
     * 初始化操作
     */
    self.init = function () {
        self.dataQuery();
        self.pageCountQuery();
    };
    //数据查询
    self.dataQuery = function () {
        self.http({
            url: self.entityQueryUrl,
            method: self.GET,
            params: {page: self.currentIndex, size: self.pageSize, whereCondition: ""}
        }).success(function (data) {
            var EntityValues = []; //清空数据
            $.each(data, function (i, result) {//遍历加载数据
                EntityValues.push(result);
            });
            self.scope.EntityValues = EntityValues;
        });
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
    //上一页
    self.prePage = function () {
        var currentItem = self.currentIndex;
        if (currentItem == 1) {
            alert("已到最前面")
        }
        else {
            currentItem--;
            self.setCurrentIndex(currentItem);
            self.dataQuery();
        }
    };
    //下一页
    self.nextPage = function () {
        var currentItem = self.currentIndex;
        var total = self.totalPage;
        if (currentItem == total) {
            alert("已无更多数据")
        }
        else {
            currentItem++;
            self.setCurrentIndex(currentItem);
            //查询
            self.dataQuery();

        }
    };
    //获取分页数量 并进行分页相关处理
    self.pageCountQuery = function () {
        self.http({
            url: self.totalCountQueryUrl,
            method: self.GET
            // params: {whereCondition: ""}//可根据条件查询数量
        }).success(function (data) {
            self.scope.totalCount = self.totalCount = data;
            self.totalPageComput();
        });

    };
    //计算总页数
    self.totalPageComput = function () {
        var count = self.totalCount;//获取总的数据
        var page = parseInt(count / self.pageSize);//取整
        if ((count % self.pageSize) > 0) {
            page++;
        }
        self.scope.totalPage = page;
        self.setCurrentIndex(self.currentIndex);
    };

    /**
     * 设置当前页码
     * @param currentIndex
     */
    self.setCurrentIndex = function (currentIndex) {
        self.scope.currentIndex = self.currentIndex = currentIndex;
    }

}