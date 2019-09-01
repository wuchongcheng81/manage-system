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
                url: '/user/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: JSON.stringify(ids)},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_user').bootstrapTable('refresh');
                    }
                }
            });
        }
        if(btnType == 'update') {
            $.ajax({
                url: '/user/update',
                type: 'POST',
                dataType: 'json',
                data: {id: vm.id, status: vm.status},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_user').bootstrapTable('refresh');
                    }
                }
            });
        }
    })

    $("#closeRoleBtn").on("click", function () {
        $('#roleModal').modal('hide');
    })

});

function search() {
    $('#tb_user').bootstrapTable('refresh');
}

function houseListReload() {
    var oTable = new TableInit();
    oTable.Init();
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_user').bootstrapTable({
            url: '/user/findList',                 //请求后台的URL（*）
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
                    field: 'userName',
                    title: '账号'
                }, {
                    field: 'realName',
                    title: '真实姓名'
                }, {
                    field: 'email',
                    title: '邮箱'
                }, {
                    field: 'phone',
                    title: '联系方式'
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                        if(value != null && value != '') {
                            if (value == 1) {
                                return '男';
                            }
                            return '女';
                        }
                    }
                },
                {
                    field: 'birthday',
                    title: '生日'
                },
                {
                    field: 'updateAt',
                    title: '更新时间'
                },
                {
                    field: 'status',
                    title: '状态',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '启用';
                        }
                        if(value == 2) {
                            return '禁用';
                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '280px'}},
                    formatter: function (value, row, index) {
                        var standard = '';
                        if(row.status == 1) {
                            standard = '<button type="button" style="margin-right: 15px" class="btn btn-warning" onclick="updateFunction(2, \'' + value + '\')">禁用</button>';
                        }
                        if(row.status == 2) {
                            standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="updateFunction(1, \'' + value + '\')">启用</button>';
                        }
                        var role = '<button type="button" style="margin-right: 15px" class="btn btn-primary" onclick="bindRoleModal(\'' + value + '\',\'' + row.roleId + '\')">绑定角色</button>';
                        var activity = '<button type="button" class="btn btn-danger" onclick="activityModal(\'' + value + '\')">重置密码</button>';
                        return role + standard;
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
            'email': $('#search_emailTitle').val()
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


function updateFunction(status, userId) {
    vm.status = status;
    vm.id = userId;
    btnType = 'update';
    $('#myModal').modal('show');
    if(status == 2)
        $('#successMsg').text('确认要禁用吗！');
    if(status == 1)
        $('#successMsg').text('确认要启用吗！');
}

function deleteFunction() {
    btnType = 'delete';
    getSelectRows = $('#tb_user').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length > 0) {
        $('#myModal').modal('show');
        $('#successMsg').text('确认要删除选中的用户数据吗？');

    }
}


function bindRoleModal(userId, roleId) {
    vm.userId = userId;
    vm.roleId = roleId;
    var roTableInit = new rTableInit();
    roTableInit.Init();

    // console.log($('#roleList input[type="radio"]'));
    $('#roleModal').modal('show');
}


var rTableInit = function () {
    var rableInit = new Object();
    //初始化Table
    rableInit.Init = function () {
        $('#tb_role').bootstrapTable({
            url: '/role/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#roleToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: rableInit.queryParams,//传递参数（*）
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
            // height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                {
                    field: 'id',
                    title:'',
                    formatter: function(value, row, index) {
                        if(value == vm.roleId) {
                            return '<input type="radio" checked="checked" name="roleId">';
                        }
                        return '<input type="radio" name="roleId">';
                    }
                },
                {
                    field: 'name',
                    title: '角色名称'
                }
            ]
        });
    };

    //得到查询的参数
    rableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'state': 1
        };
        return temp;
    };
    return rableInit;
};






