var btnType;
var getSelectRows;
var orderBtnType;
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
                url: '/company/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: JSON.stringify(ids)},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_company').bootstrapTable('refresh');
                    }
                }
            });
        }
    })

    $("#delAgentBtn").on("click", function () {
        $('#agentTipModal').modal('hide');
        $.ajax({
            type: "post",
            url: "/agent/update",
            data: {id: vm.agentId, status: 0},
            dataType: 'json',
            success: function (data, status) {
                $('#tb_agent').bootstrapTable('refresh');
            },
            error: function (data, status) {
            },
            complete: function () {

            }

        });
    })

    var orderStateMap = {};
    orderStateMap['delete'] = 1;
    orderStateMap['audit'] = 3;
    orderStateMap['mortgage'] = 5;
    orderStateMap['checkOut'] = 6;

    $("#delOrderBtn").on("click", function () {
        $('#orderTipModal').modal('hide');
        if (orderBtnType == 'delete' || orderBtnType == 'audit' || orderBtnType == 'mortgage' || orderBtnType == 'checkOut') {
            var param = {};
            if (orderBtnType == 'delete') {
                param = {id: vm.orderId, delFlag: 1};
            } else {
                param = {id: vm.orderId, state: orderStateMap[orderBtnType]};
            }
            $.ajax({
                type: "post",
                url: "/order/update",
                data: param,
                dataType: 'json',
                success: function (data, status) {
                    $('#tb_order').bootstrapTable('refresh');
                },
                error: function (data, status) {
                },
                complete: function () {

                }

            });
        }
    })

    $('#closeOrderBtn').on("click", function () {
        $('#orderModal').modal('hide');
    })

    $('#closeAgentBtn').on("click", function () {
        $('#agentModal').modal('hide');
    })
});

function search() {
    $('#tb_company').bootstrapTable('refresh');
}

function houseListReload() {
    var oTable = new TableInit();
    oTable.Init();
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_company').bootstrapTable({
            url: '/company/findList',                 //请求后台的URL（*）
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
                    field: 'name',
                    title: '公司全称'
                }, {
                    field: 'shortName',
                    title: '公司简称'
                }, {
                    field: 'contact',
                    title: '公司联系电话'
                }, {
                    field: 'code',
                    title: '公司编码'
                }, {
                    field: 'principalName',
                    title: '负责人姓名'
                }, {
                    field: 'principalPhone',
                    title: '负责人联系方式'
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="agentModal(\'' + value + '\')">查看员工</button>';
                        var order = '<button type="button" class="btn btn-warning" onclick="orderModal(\'' + value + '\')">查看报备</button>';
                        return standard + order;
                    }
                }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'name': $('#search_name').val()
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
    parent.window.addTab(10, '新增公司', 'addCompany.html');
});


function updateFunction() {
    btnType = 'update';
    getSelectRows = $('#tb_company').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length == 1) {
        parent.window.addTab(11, '修改公司', 'updateCompany.html?id=' + getSelectRows[0].id);
    }
    if (getSelectRows.length > 1) {
        $('#myModal').modal('show');
        $('#successMsg').text('修改公司数据只能选中一条！');
        return;
    }
}

function agentModal(id) {
    vm.companyId = id;
    var aTable = new agentInit();
    aTable.Init();

    $('#agentModal').modal('show');
}

function orderModal(id) {
    vm.companyId = id;
    var oTable = new orderInit();
    oTable.Init();

    $('#orderModal').modal('show');
}

function deleteFunction() {
    btnType = 'delete';
    getSelectRows = $('#tb_company').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length > 0) {
        $('#myModal').modal('show');
        $('#successMsg').text('确认要删除选中的公司数据吗？');

    }
}


var agentInit = function () {
    var curRow = {};
    var agentTableInit = new Object();
    //初始化Table
    agentTableInit.Init = function () {
        $('#tb_agent').bootstrapTable('destroy');
        $('#tb_agent').bootstrapTable({
            url: '/agent/findListByCompanyId',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#agentToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: agentTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/agent/update",
                    data: {
                        nickName: row.nickName,
                        realName: row.realName,
                        phone: row.phone,
                        id: row.id
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
                    field: 'nickName',
                    title: '昵称',
                    editable: {
                        type: 'text',
                        title: '昵称',
                        validate: function (v) {
                            if (!v) return '昵称不能为空';
                        }
                    }
                }, {
                    field: 'realName',
                    title: '真实姓名',
                    formatter: function (value, row, index) {
                        if (value == null || value == '') {
                            return '未设置';
                        }
                        return value;
                    },
                    editable: {
                        type: 'text',
                        title: '真实姓名',
                        validate: function (v) {
                            if (!v) return '描述不能为空';

                        }
                    }
                }, {
                    field: 'phone',
                    title: '手机号',
                    formatter: function (value, row, index) {
                        return value;
                    },
                    editable: {
                        type: 'text',
                        title: '手机号',
                        validate: function (v) {
                            if (!v) return '手机号不能为空';
                        }
                    }
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '男';
                        }
                        return '女';
                    }
                }, {
                    field: 'loginTime',
                    title: '上次登录时间',
                    formatter: function (value, row, index) {
                        return format(value);
                    }
                }, {
                    field: 'status',
                    title: '状态',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '启用';
                        }
                        if (value == 2) {
                            return '禁用';
                        }
                        return '未知';
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'min-width': '220px'}},
                    formatter: function (value, row, index) {
                        var uploadBtn = '<button type="button" class="btn btn-info" style="margin-right: 15px" onclick="enabled(\'' + value + '\')">启用</button>';
                        var forbidBtn = '<button type="button" class="btn btn-message" style="margin-right: 15px" onclick="forbidden(\'' + value + '\')">禁用</button>';
                        var deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteAgent(\'' + value + '\')">删除</button>';
                        return uploadBtn + forbidBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    agentTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'companyId': vm.companyId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return agentTableInit;
};

var stateMap = {};
stateMap[0] = '未到访';
stateMap[1] = '未成交';
stateMap[2] = '未审核';
stateMap[3] = '未首付';
stateMap[4] = '未按揭';
stateMap[5] = '未结佣';
stateMap[6] = '完成';

var orderInit = function () {

    var orderTableInit = new Object();
    //初始化Table
    orderTableInit.Init = function () {
        $('#tb_order').bootstrapTable('destroy');
        $('#tb_order').bootstrapTable({
            url: '/order/findListByCompanyId',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#orderToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: orderTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/order/update",
                    data: {
                        id: row.id
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
                    field: 'houseName',
                    title: '楼盘'
                }, {
                    field: 'clientName',
                    title: '客源'
                }, {
                    field: 'agentName',
                    title: '所属经纪人'
                }, {
                    field: 'meetTime',
                    title: '预约时间',
                    formatter: function (value, row, index) {
                        return format(value);
                    }
                }, {
                    field: 'state',
                    title: '状态', formatter: function (value, row, index) {
                        return stateMap[value];
                    }
                }, {
                    field: 'type',
                    title: '报备类型',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '前三后四报备';
                        }
                        return '其他';
                    }
                }, {
                    field: 'agentPhone',
                    title: '客源联系方式'
                }, {
                    field: 'managerLead',
                    title: '带看经理名'
                }, {
                    field: 'remark',
                    title: '备注',
                    cellStyle: {'css': {'min-width': '150px'}},
                    formatter: function (value, row, index) {
                        if (value != null && value.length > 8) {
                            var remark = '<a href="#" onclick="showRemark(\'' + value + '\')">' + value.substring(0, 8) + '...</a>';
                            return remark;
                        }
                        return value;
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'right', 'min-width': '180px'}},
                    formatter: function (value, row, index) {
                        var uploadBtn = '';
                        if (row.state == 2) {
                            uploadBtn = '<button type="button" class="btn btn-success" style="margin-right: 8%" onclick="auditOrder(\'' + value + '\')">确认审核</button>';
                        }
                        if (row.state == 4) {
                            uploadBtn = '<button type="button" class="btn btn-info" style="margin-right: 8%" onclick="mortgageOrder(\'' + value + '\')">确认按揭</button>';
                        }
                        if (row.state == 5) {
                            uploadBtn = '<button type="button" class="btn btn-warning" style="margin-right: 8%" onclick="checkOutOrder(\'' + value + '\')">确认结佣</button>';
                        }
                        var deleteBtn = '<button type="button" class="btn btn-danger" style="margin-right: 8%" onclick="deleteOrder(\'' + value + '\')">删除</button>';
                        return uploadBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    orderTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'companyId': vm.companyId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return orderTableInit;
};


function enabled(id) {
    $.ajax({
        type: "post",
        url: "/agent/update",
        data: {id: id, status: 1},
        dataType: 'json',
        success: function (data, status) {
            $('#tb_agent').bootstrapTable('refresh');
        },
        error: function (data, status) {
        },
        complete: function () {

        }

    });
}

function forbidden(id) {
    $.ajax({
        type: "post",
        url: "/agent/update",
        data: {id: id, status: 2},
        dataType: 'json',
        success: function (data, status) {
            $('#tb_agent').bootstrapTable('refresh');
        },
        error: function (data, status) {
        },
        complete: function () {

        }

    });
}

function deleteAgent(id) {
    vm.agentId = id;
    $('#agentTipModal').modal('show');
}

function showRemark(content) {
    orderBtnType = 'showRemark';
    $('#orderTipModalContent').html(content);
    $('#orderTipModal').modal('show');
}

function deleteOrder(id) {
    orderBtnType = 'delete';
    vm.orderId = id;
    $('#orderTipModalContent').html('确认要删除该报备吗？');
    $('#orderTipModal').modal('show');
}

function auditOrder(id) {
    orderBtnType = 'audit';
    vm.orderId = id;
    $('#orderTipModalContent').html('确认要审核通过该报备吗？');
    $('#orderTipModal').modal('show');
}

function mortgageOrder(id) {
    orderBtnType = 'mortgage';
    vm.orderId = id;
    $('#orderTipModalContent').html('确认该报备已按揭吗？');
    $('#orderTipModal').modal('show');
}

function checkOutOrder(id) {
    orderBtnType = 'checkOut';
    vm.orderId = id;
    $('#orderTipModalContent').html('确认该报备已结佣吗？');
    $('#orderTipModal').modal('show');
}


