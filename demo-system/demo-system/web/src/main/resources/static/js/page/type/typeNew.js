$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

var $table = $('#tb_body')

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_body').bootstrapTable({
            url: '/type/findAllList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            striped: true,
            sidePagination: 'server',
            idField: 'id',
            showColumns: true,
            columns: [
                {
                    field: 'sort',
                    title: '序号'
                },
                {
                    field: 'name',
                    title: '品牌分类名称'
                },
                {
                    field: 'pageImgUrl',
                    title: '分类页面广告',
                    cellStyle: {'css': {'text-align': 'center', 'width': '15%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="40%">';
                            return img;
                        }
                        return '';
                    }
                },{
                    field: 'detailImgUrl',
                    title: '分类详情页广告',
                    cellStyle: {'css': {'text-align': 'center', 'width': '15%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="40%">';
                            return img;
                        }
                        return '';
                    }
                },{
                    field: 'brandCount',
                    title: '关联品牌数量'
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="updateModal(\'' + value + '\')">修改</button>';
                        var order = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                        return standard + order;
                    }
                }
            ],
            treeShowField: 'name',
            parentIdField: 'parentId',
            onPostBody: function() {
                var columns = $table.bootstrapTable('getOptions').columns
                if (columns && columns[0][1].visible) {
                    $table.treegrid({
                        treeColumn: 1,
                        onChange: function() {
                            $table.bootstrapTable('resetWidth')
                        }
                    })
                }
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageNumber,   //页面大小
            'pageSize': this.pageSize,  //页码
            'name': $('#search_menuTitle').val()
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };
    return oInit;
};