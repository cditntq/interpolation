/**
 * Created by ShuangYang on 2016/4/14.
 */
//构造原始模型
function zTreeViewModel(params) {
    var self = this;
    self.zTree = "";//申明zTree树
    self.zTreeNodeValue = params.zTreeNodeValue;//zTree节点数据
    self.zTreeNodeValues = ko.observableArray([]);//从json中获取数据集
    self.zTreeInitNode = params.zTreeInitNode;
    self.currentZTreeNode = ko.observable(new params.zTreeNodeValue());//当前点击事件获取的节点
    //url操作
    self.zTreeQueryUrl = params.zTreeQueryUrl();//获取查询树所有节点的的url
    self.zTreeNodeAddUrl = params.zTreeNodeAddUrl();//添加新节点
    self.zTreeNodeUpdateUrl = params.zTreeNodeUpdateUrl();//更新节点
    self.zTreeNodeDeleteUrl = params.zTreeNodeDeleteUrl();//删除节点
    //权限树相关设置
    self.setting = {
        showLine: true,                  //是否显示节点间的连线
        async: {
            enable: true,
            url: self.zTreeQueryUrl,//请求
            dataType: "text"
        },
        data: {
            key: {
                name: "name",
                url: "_url"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        check: { //每个节点上是否显示 CheckBox
            enable: true,
            chkboxType: {
                "Y": "s", "N": "s"
            }
        },
        callback: {
            onClick: clickNode,//点击节点触发的事件
        }
    };


    //初始化集合操作
    self.init = function () {
        self.initZtree();//初始化树节点
    };
    //获取分页数量 并进行分页相关处理

    //获取查询的数据 int page, int size, String whereCondition
    self.zTreeNodesQuery = function () {
        $.ajax({
            type: "GET",
            url: self.zTreeQueryUrl,
            success: function (data) {
                var dataObj = self.js2ko(data);
                self.zTreeNodeValues.push(self.js2ko(dataObj));
            }
        })
    };
    //初始化树
    self.initZtree = function () {
        self.zTree = $.fn.zTree.init($("#tree"), self.setting);
        self.zTree.expandAll(true);//无效
    };
    //删除
    self.remove = function () {
        var nodes = self.zTree.getCheckedNodes(true);//获取所有选择的节点
        if (nodes.length == 0) {
            alert("请先选择节点")
            return false;
        }
        var idsStr = "";
        for (var i = 0; i < nodes.length; i++) {
            idsStr += nodes[i].id + ",";
            //获取删除的id
            self.zTree.removeNode(nodes[i]);
            //组装删除条件

        }
        idsStr = idsStr.substring(0, idsStr.length - 1);
        //进行删除操作
        $.ajax({
            type: "post",
            url: self.zTreeNodeDeleteUrl,
            data: {ids: idsStr},
            success: function (data) {

            }
        })

    };


    //点击事件
    function clickNode(e, treeId, treeNode) {
        //获取节点信息
        self.zTreeNodeValue(ko.mapping.fromJS(treeNode));
        return false;
    }


    //添加新的节点
    self.addZtreeNode = function () {


        //判断点击节点个数
        var nodes = self.zTree.getCheckedNodes(true);
        var length = nodes.length;
        //判断是否只有一个节点被选中
        if (length == 0) {

            alert("请先选中节点")
            return false;
        }
        else if (length > 1) {
            alert("新增时只能选中一个节点,请修改选择")
            return false;
        }
        //获取节点
        var zTreeNode = self.ko2js(self.zTreeInitNode());
        zTreeNode.pid = nodes[0].id;//获取父节点
        zTreeNode.name = "新增节点";
        //传递给后台,
        //更新节点信息
        $.ajax({
            type: "post",
            url: self.zTreeNodeAddUrl,
            contentType: 'application/json',
            data: JSON.stringify(zTreeNode),
            dataType: 'json',
            success: function (data) {
                //alert("更新后台返回:" + data);
                //更新zTreNode节点
                if (data == 1) {//更新节点
                    self.zTree.destroy();//删除树
                    //再生成树
                    self.initZtree()

                }


            }
        })
    }

    //更新节点信息
    self.updateZtreeNode = function () {//先删除再添加
        var zTreeNode = self.ko2js(self.zTreeNodeValue);
        var dataValue = self.treeNode2jsData(zTreeNode);
        //获取父节点
        var parentNode = self.zTree.getNodeByTId(zTreeNode.parentTId);
        var node = self.zTree.getNodeByTId(zTreeNode.tId);
        self.zTree.removeNode(node);
        //判断是否为空
        if (zTreeNode == "") {
            alert("请选中节点");
            return;
        }
        else {
            //更新节点信息
            $.ajax({
                type: "post",
                url: self.zTreeNodeUpdateUrl,
                contentType: 'application/json',
                data: JSON.stringify(dataValue),
                dataType: 'json',
                success: function (data) {
                    //alert("更新后台返回:" + data);
                    //更新zTreNode节点
                    if (data == 1) {//更新节点
                        self.zTree.addNodes(parentNode, zTreeNode);
                        /* self.zTree.updateNode(self.ko2js(self.zTreeNodeValue))*/
                    }


                }
            })


        }
        //ajax请求
        //当在系统里面更新了 就能够

    }
    //存入或者加入新的操作
    self.saveOrUpdate = function () {
        self.updateZtreeNode();
    };


    self.ko2js = function (data) {
        return ko.mapping.toJS(data);
    }
    self.js2ko = function (data) {
        return ko.mapping.fromJS(data);
    }
    self.ko2Json = function (data) {
        return ko.mapping.toJSON(data);
    }
    //从treeNode截取数据放入返回实体
    self.treeNode2jsData = function (treeNode) {
        var obj = self.ko2js(self.zTreeInitNode);
        for (var p in obj) {
            var name = p;
            var value = treeNode[p];
            obj[p] = value;
        }
        return obj;
    }

}

////得到选中的节点信息
//function computNodes() {
//    var s_child = "", s_parent = "";
//    var treeObj = $.fn.zTree.getZTreeObj("tree");
//    var nodes = treeObj.getCheckedNodes(true);//获取所有选择的节点
//    for (var i = 0; i < nodes.length; i++) {
//        if (nodes[i].isParent) {
//            s_parent += nodes[i].name + ",";//父节点
//            alert(nodes[i].name);
//        } else {
//            s_child += nodes[i].name + ",";//子节点
//            alert(nodes[i].name);
//        }
//    }
//}
