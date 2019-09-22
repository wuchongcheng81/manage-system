var getSelectRows
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_body').bootstrapTable({
            url: '/brandInvest/findPage',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [5, 10, 30],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            // showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 650,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                {
                    checkbox: true
                },
                {
                    field: 'title',
                    title: '标题'
                },
                {
                    field: 'brandName',
                    title: '品牌名字'
                },
                {
                    field: 'investLogo',
                    title: '招商广告图',
                    cellStyle: {'css': {'text-align': 'center', 'width': '20%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="70px">';
                            return img;
                        }
                        return '';
                    }
                },{
                    field: 'type',
                    title: '类别'
                },{
                    field: 'investType',
                    title: '招商类型'
                },{
                    field: 'investArea',
                    title: '招商地区'
                },{
                    field: 'isPublish',
                    title: '状态',
                    formatter: function (value, row, index) {
                        if(value == 1)
                            return '已发布';
                        return '未审核';
                    }
                },{
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-primary" onclick="edit(\'' + value + '\')">编辑</button>';
                        var order;
                        if(row.isPublish == 0) {
                            order = '<button type="button" class="btn btn-success" onclick="updateStatus(\'' + value + '\',1)">通过审核</button>';
                        } else {
                            order = '<button type="button" class="btn btn-warning" onclick="updateStatus(\'' + value + '\',0)">置未审核</button>';
                        }
                        return standard + order;
                    }
                }
            ]
        });
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {

        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageNumber,   //页面大小
            'pageSize': this.pageSize,  //页码
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

function updateStatus(id, status) {
    vm.currentId = id;
    vm.status = status;
    if(status == 1) {
        $('#modalDiv').html('确认通过审核该品牌招商吗？');
    }else {
        $('#modalDiv').html('确认将该品牌招商置未审核吗？');
    }
    $('#myModal').modal('show');
}

function confirmUpdate() {
    $.post('/brandInvest/update', {id: vm.currentId, isPublish: vm.status}, function(result) {
        if(result != null && result.state == 11) {
            $('#tb_body').bootstrapTable('refresh');
            $('#myModal').modal('hide');
            $('#modalSuccessDiv').html('修改成功！');
            $('#mySuccessModal').modal('show');
        }
    });
}

function deleteFunction() {
    getSelectRows = $('#tb_body').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length > 0) {
        $('#myDModal').modal('show');
    }
}

function confirmDelete() {
    var ids = '';
    getSelectRows.forEach(function (data) {
        ids += data.id + ',';
    })
    $.post('/brandInvest/deleteByIds', {ids: ids}, function(result) {
        if(result != null && result.state == 11) {
            $('#tb_body').bootstrapTable('refresh');
            $('#myDModal').modal('hide');
            $('#modalSuccessDiv').html('删除成功！');
            $('#mySuccessModal').modal('show');
        }
    })
}


function add() {
    parent.window.addTab(64, '新增品牌招商', 'brandInvest/addBrandInvest.html');
}

function edit(id) {
    parent.window.addTab(61, '编辑品牌招商', 'brandInvest/editBrandInvest.html?id=' + id);
}