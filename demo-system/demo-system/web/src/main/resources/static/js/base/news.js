var btnType;
var getSelectRows;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $("#continueBtn").on("click", function () {
        $('#myModal').modal('hide');
        if (btnType == 'delete') {
            var ids = '';
            getSelectRows.forEach(function (data) {
                ids += data.id + ',';
            })
            $.ajax({
                url: '/news/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: JSON.stringify(ids)},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_news').bootstrapTable('refresh');
                    }
                }
            });
        }
    })

});

function search() {
    $('#tb_news').bootstrapTable('refresh');
}

function houseListReload() {
    var oTable = new TableInit();
    oTable.Init();
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_news').bootstrapTable({
            url: '/news/findList',                 //请求后台的URL（*）
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
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10, 30],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            // showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                {
                    checkbox: true
                }, {
                    field: 'cover',
                    title: '封面',
                    formatter: function (value, row, index) {
                        return '<img src="' + value + '" style="width: 70px">';
                    }
                }, {
                    field: 'title',
                    title: '标题'
                }, {
                    field: 'desc',
                    title: '描述',
                    cellStyle: {'css': {'width': '350px'}},
                    formatter: function (value, row, index) {
                        if (value.length > 20) {
                            return value.substring(0, 20) + '...';
                        }
                        return value;
                    }
                }, {
                    field: 'createAt',
                    title: '创建时间',
                    formatter: function (value, row, index) {
                        return value;
                    }
                }, {
                    field: 'type',
                    title: '类型',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '活动资讯';
                        }
                        return '头条资讯';
                    }
                }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'title': $('#search_newsTitle').val()
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

$(document).on('click', '#btn_add', function () {
    parent.window.addTab(7, '新增资讯', 'addNews.html');
});


function updateFunction() {
    btnType = 'update';
    getSelectRows = $('#tb_news').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length == 1) {
        parent.window.addTab(8, '修改资讯', 'updateNews.html?id=' + getSelectRows[0].id);
    }
    if (getSelectRows.length > 1) {
        $('#myModal').modal('show');
        $('#successMsg').text('修改资讯数据只能选中一条！');
        return;
    }
}

function deleteFunction() {
    btnType = 'delete';
    getSelectRows = $('#tb_news').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length > 0) {
        $('#myModal').modal('show');
        $('#successMsg').text('确认要删除选中的资讯数据吗？');

    }
}







