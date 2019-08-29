var btnType;
var getSelectRows;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});

function search() {
    $('#tb_feed_back').bootstrapTable('refresh');
}

function houseListReload() {
    var oTable = new TableInit();
    oTable.Init();
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_feed_back').bootstrapTable({
            url: '/feedBack/findList',                 //请求后台的URL（*）
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
            columns: [{
                field: 'name',
                title: '用户'
            }, {
                field: 'phone',
                title: '联系方式'
            }, {
                field: 'brand',
                title: '手机型号'
            }, {
                field: 'appVersion',
                title: 'app版本'
            }, {
                field: 'osVersion',
                title: '手机系统版本'
            }, {
                field: 'isRead',
                title: '是否阅读',
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return '是';
                    }
                    return '否';
                }
            }, {
                field: 'createAt',
                title: '提交时间',
                formatter: function (value, row, index) {
                    return value;
                }
            }, {
                field: 'content',
                title: '内容',
                formatter: function (value, row, index) {
                    return '<a href="#" onclick="showFBContent(\'' + row.id + '\')">查看内容</a>';
                }
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'content': $('#search_feedBackContent').val()
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

function showFBContent(id) {
    $.post('/feedBack/updateRead', {id: id}, function (data) {
        var img = data.feedback.imgUrl.split(',');
        var imgHTML = '';
        if (img != null && img.length > 0)
            img.forEach(function (url) {
                imgHTML += '<img src="' + url + '" width="250px">';
            });
        $('#contentMsg').html(data.feedback.content + '<br/>' + imgHTML);
        $('#myModal').modal('show');
        $('#tb_feed_back').bootstrapTable('refresh');
    })

}