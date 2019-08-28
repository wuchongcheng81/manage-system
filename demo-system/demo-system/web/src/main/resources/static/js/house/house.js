var btnType;
var getSelectRows;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $("#closeStandradBtn").on("click", function () {
        $('#standradModal').modal('hide');
    })
    $("#closeActivityBtn").on("click", function () {
        $('#activityModal').modal('hide');
    })
    $("#closeActivityEditBtn").on("click", function () {
        $('#activityEditModal').modal('hide');
    })
    $("#continueBtn").on("click", function () {
        $('#myModal').modal('hide');
        if (btnType == 'delete') {
            var ids = '';
            getSelectRows.forEach(function (data) {
                ids += data.id + ',';
            })
            $.ajax({
                url: '/house/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: JSON.stringify(ids)},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_house').bootstrapTable('refresh');
                    }
                }
            });
        }
    })

    $("#activityContinueBtn").on("click", function () {
        $('#activityTipModal').modal('hide');
        $.ajax({
            url: '/houseActivity/delete',
            type: 'POST',
            dataType: 'json',
            data: {id: vm.activityId},
            success: function (data) {
                if (data.state == '11') {
                    $('#tb_house_activity').bootstrapTable('refresh');
                }
            }
        });
    })


    $('#file').on('change', function () {
        var formData = new FormData($('#standardForm')[0]);
        $.ajax({
            url: '/houseStandard/uploadImages',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.state == 11) {
                    var sTable = new standradInit();
                    sTable.Init();
                }
            }
        });
    })

});

function search() {
    $('#tb_house').bootstrapTable('refresh');
}

function houseListReload() {
    var oTable = new TableInit();
    oTable.Init();
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_house').bootstrapTable({
            url: '/house/findList',                 //请求后台的URL（*）
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
                    title: '楼盘名称'
                }, {
                    field: 'priceDesc',
                    title: '单价',
                    formatter: function (value, row, index) {
                        return value;
                    }
                }, {
                    field: 'totalPrice',
                    title: '总价',
                    formatter: function (value, row, index) {
                        return value + '万';
                    }
                }, {
                    field: 'decoration',
                    title: '装修标准'
                }, {
                    field: 'houseTypeName',
                    title: '类型'
                }, {
                    field: 'address',
                    title: '详细地址'
                }, {
                    field: 'cityName',
                    title: '城市'
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="standardModal(\'' + value + '\')">主力户型</button>';
                        var activity = '<button type="button" class="btn btn-warning" onclick="activityModal(\'' + value + '\')">楼盘头条</button>';
                        return standard + activity;
                    }
                }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'name': $('#search_houseName').val()
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
    parent.window.addTab(3, '添加楼盘', 'addHouse.html');
});

function saveHouse() {
    $('#addHouseForm').bootstrapValidator('validate');
}

function updateFunction() {
    btnType = 'update';
    getSelectRows = $('#tb_house').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length == 1) {
        parent.window.addTab(2, '修改楼盘', 'updateHouse.html?id=' + getSelectRows[0].id);
    }
    if (getSelectRows.length > 1) {
        $('#myModal').modal('show');
        $('#successMsg').text('修改楼盘数据只能选中一条！');
        return;
    }
}

function deleteFunction() {
    btnType = 'delete';
    getSelectRows = $('#tb_house').bootstrapTable('getSelections', function (rows) {
        return rows;
    });
    if (getSelectRows.length > 0) {
        $('#myModal').modal('show');
        $('#successMsg').text('确认要删除选中的楼盘数据吗？');

    }
}


function standardModal(houseId) {
    vm.houseId = houseId;
    var sTable = new standradInit();
    sTable.Init();

    $('#standradModal').modal('show');
}

function addStandard() {
    $.post('/houseStandard/addDefault', {houseId: vm.houseId}, function (result) {
        if (result.state == '11') {
            var sTable = new standradInit();
            sTable.Init();
        }
    });
}

function deleteStandrad(id) {
    $.post('/houseStandard/delete', {id: id}, function (result) {
        if (result.state == '11') {
            var sTable = new standradInit();
            sTable.Init();
        }
    });
}

function uploadFile(id) {
    $('#standardId').val(id);
    $('#file').click();
}


function activityModal(houseId) {
    vm.houseId = houseId;
    // $('#standradContent').html(houseId);
    var aTable = new activitydInit();
    aTable.Init();
    $('#activityModal').modal('show');
}

function editActivity(id) {

    parent.window.addTab(5, '编辑楼盘头条', 'updateActivity.html?id=' + id);
}

function deleteActivity(id) {
    vm.activityId = id;
    $('#activityTipModal').modal('show');
}

function addActivity() {
    parent.window.addTab(4, '新增楼盘头条', 'addActivity.html?houseId=' + vm.houseId);

}


var standradInit = function () {
    var curRow = {};
    var sdTableInit = new Object();
    //初始化Table
    sdTableInit.Init = function () {
        $('#tb_house_standrad').bootstrapTable('destroy');
        $('#tb_house_standrad').bootstrapTable({
            url: '/houseStandard/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#standradToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: sdTableInit.queryParams,//传递参数（*）
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
                    url: "/houseStandard/update",
                    data: row,
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
                    field: 'imgUrl',
                    title: '图片',
                    cellStyle: {'css': {'text-align': 'center', 'width': '100px'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="100%">';
                            return img;
                        }
                        return '';
                    }
                },
                {
                    field: 'name',
                    title: '户型名',
                    editable: {
                        type: 'text',
                        title: '户型名',
                        validate: function (v) {
                            if (!v) return '户型名不能为空';

                        }
                    }
                }, {
                    field: 'desc',
                    title: '描述',
                    editable: {
                        type: 'text',
                        title: '描述',
                        validate: function (v) {
                            if (!v) return '描述不能为空';

                        }
                    }
                }, {
                    field: 'acreage',
                    title: '面积',
                    formatter: function (value, row, index) {
                        return value;
                    },
                    editable: {
                        type: 'text',
                        title: '面积',
                        validate: function (v) {
                            if (!v) return '面积不能为空';

                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '200px'}},
                    formatter: function (value, row, index) {
                        var uploadBtn = '<button type="button" class="btn btn-info" style="margin-right: 15px" onclick="uploadFile(\'' + value + '\')">上传图片</button>';
                        var deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteStandrad(\'' + value + '\')">删除</button>';
                        return uploadBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    sdTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'houseId': vm.houseId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return sdTableInit;
};


var activitydInit = function () {
    var aTableInit = new Object();
    //初始化Table
    aTableInit.Init = function () {
        $('#tb_house_activity').bootstrapTable('destroy');
        $('#tb_house_activity').bootstrapTable({
            url: '/houseActivity/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#activityToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: aTableInit.queryParams,//传递参数（*）
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
            columns: [
                {
                    field: 'title',
                    title: '标题'
                }, {
                    field: 'desc',
                    title: '描述',
                    formatter: function (value, row, index) {
                        if (value.length > 10) {
                            return value.substring(0, 10) + '...';
                        }
                        return value;
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '200px'}},
                    formatter: function (value, row, index) {
                        var editBtn = '<button type="button" class="btn btn-info" style="margin-right: 15px" onclick="editActivity(\'' + value + '\')">编辑</button>';
                        var deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteActivity(\'' + value + '\')">删除</button>';
                        return editBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    aTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'houseId': vm.houseId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return aTableInit;
};
