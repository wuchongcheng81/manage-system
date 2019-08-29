var btnType;
var currentId;
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
            $.ajax({
                url: '/menu/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: currentId},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_menu').bootstrapTable('refresh');
                        $('#tb_child_menu').bootstrapTable('refresh');
                    }
                }
            });
        }
    })

    $('#closeChildMenuBtn').on("click", function () {
        $('#childMenuModal').modal('hide');
    })

});

function search() {
    $('#tb_menu').bootstrapTable('refresh');
}

function addMenu() {
    $.post('/menu/save', {name: '请填写菜单名称', url: '请填写菜单url'}, function (data) {
        $('#tb_menu').bootstrapTable('refresh');
    });
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_menu').bootstrapTable({
            url: '/menu/findList',                 //请求后台的URL（*）
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
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/menu/update",
                    data: {
                        id: row.id,
                        name: row.name,
                        // url: row.url,
                        sort: row.sort
                    },
                    dataType: 'json',
                    success: function (data, status) {
                    },
                    error: function (data, status) {
                    },
                    complete: function () {

                    }
                });
            },
            columns: [
                {
                    field: 'name',
                    title: '菜单名称',
                    editable: {
                        type: 'text',
                        title: '菜单名称',
                        validate: function (v) {
                            if (!v) return '菜单名称不能为空';
                        }
                    }
                },
                // {
                //     field: 'url',
                //     title: '菜单url',
                //     editable: {
                //         type: 'text',
                //         title: '菜单url',
                //         validate: function (v) {
                //             if (!v) return '菜单url不能为空';
                //         }
                //     }
                // },
                {
                    field: 'sort',
                    title: '排序',
                    editable: {
                        type: 'number',
                        title: '排序',
                        validate: function (v) {
                            if (!v) return '排序不能为空';
                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="childMenuModal(\'' + value + '\')">子菜单</button>';
                        var order = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                        return standard + order;
                    }
                }

            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'name': $('#search_menuTitle').val(),
            'level': 0
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

function deleteFunction(id) {
    currentId = id;
    btnType = 'delete';
    $('#myModal').modal('show');
    $('#successMsg').text('确认要删除该菜单吗？');
}


function childMenuModal(id) {
    vm.parentId = id;
    //1.初始化Table
    var cmTable = new childMenuTableInit();
    cmTable.Init();

    $('#childMenuModal').modal('show');
}


var childMenuTableInit = function () {
    var cmTableInit = new Object();
    //初始化Table
    cmTableInit.Init = function () {
        $('#tb_child_menu').bootstrapTable('destroy');
        $('#tb_child_menu').bootstrapTable({
            url: '/menu/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#childMenuToolbar',       //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: cmTableInit.queryParams,//传递参数（*）
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
            // height: 150,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/menu/update",
                    data: {
                        id: row.id,
                        name: row.name,
                        url: row.url,
                        sort: row.sort,
                        parentId: vm.parentId
                    },
                    dataType: 'json',
                    success: function (data, status) {
                    },
                    error: function (data, status) {
                    },
                    complete: function () {

                    }
                });
            },
            columns: [
                {
                    field: 'name',
                    title: '菜单名称',
                    editable: {
                        type: 'text',
                        title: '菜单名称',
                        validate: function (v) {
                            if (!v) return '菜单名称不能为空';
                        }
                    }
                }, {
                    field: 'url',
                    title: '菜单url',
                    editable: {
                        type: 'text',
                        title: '菜单url',
                        validate: function (v) {
                            if (!v) return '菜单url不能为空';
                        }
                    }
                }, {
                    field: 'sort',
                    title: '排序',
                    editable: {
                        type: 'number',
                        title: '排序',
                        validate: function (v) {
                            if (!v) return '排序不能为空';
                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var order = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                        return order;
                    }
                }

            ]
        });
    };

    //得到查询的参数
    cmTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'parentId': vm.parentId,
            'level': 1
        };
        return temp;
    };
    return cmTableInit;
};

function addChildMenu() {
    $.post('/menu/save', {
        name: '请填写子菜单名称',
        url: '请填写子菜单url',
        parentId: vm.parentId,
        level: 1
    }, function (data) {
        $('#tb_child_menu').bootstrapTable('refresh');
    });
}




