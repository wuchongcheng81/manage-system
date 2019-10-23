var btnType;
var currentId;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $("#confirmBtn").on("click", function () {
        if(vm.currentId != null) {
            $.post('/type/delete', {id : vm.currentId}, function(data) {
                if(data != null && data.state == 11) {
                    $('#confirmModal').modal('hide');
                    $('#successMsg').text('删除品牌分类成功！');
                    $('#successModal').modal('show');
                    $('#tb_body').bootstrapTable('refresh');
                }
            })
        }
    })

    validateAddForm();
    validateUpdateForm();

    $('#cancelUpdate').on("click", function () {
        $('#updateModal').modal('hide');
    })

    $('#a_cancelUpdate').on("click", function () {
        $('#addModal').modal('hide');
    })

    $('#a_confirmUpdate').on("click", function () {
        $("#addTypeForm").bootstrapValidator('validate');
        if ($("#addTypeForm").data('bootstrapValidator').isValid()) {
            $.post('/type/save',
                {
                    name: $('#a_name').val(),
                    sort: $('#a_sort').val(),
                    icon: $('#a_icon').val(),
                    linkColor: $('#a_linkColor').val(),
                    pageImgUrl: vm.aPageImgUrl,
                    detailImgUrl: vm.aDetailImgUrl,
                    pageImgIsShow: $("input[name='aPageisShow']:checked").val(),
                    detailImgIsShow: $("input[name='aDetailisShow']:checked").val(),
                }, function(data) {
                    if(data.state == 11) {
                        $('#addModal').modal('hide');
                        $('#successMsg').text('添加品牌分类成功！');
                        $('#successModal').modal('show');
                        $('#tb_body').bootstrapTable('refresh');
                    }else {
                        $('#successMsg').text('添加品牌分类失败！');
                        $('#successModal').modal('show');
                    }
                });

            $('#addModal').modal('hide');
        }
    })

    $('#confirmUpdate').on("click", function () {
        $("#updateTypeForm").bootstrapValidator('validate');
        if ($("#updateTypeForm").data('bootstrapValidator').isValid()) {
            $.post('/type/update',
                {
                    id: vm.currentId,
                    name: $('#u_name').val(),
                    sort: $('#u_sort').val(),
                    icon: $('#u_icon').val(),
                    linkColor: $('#u_linkColor').val(),
                    pageImgUrl: vm.uPageImgUrl,
                    detailImgUrl: vm.uDetailImgUrl,
                    pageImgIsShow: $("input[name='uPageisShow']:checked").val(),
                    detailImgIsShow: $("input[name='uDetailisShow']:checked").val(),
                }, function(data) {
                    if(data.state == 11) {
                        $('#updateModal').modal('hide');
                        $('#successMsg').text('修改品牌分类成功！');
                        $('#successModal').modal('show');
                        $('#tb_body').bootstrapTable('refresh');
                    }else {
                        $('#successMsg').text('修改品牌分类失败！');
                        $('#successModal').modal('show');
                    }
                });
            $('#updateModal').modal('hide');
        }
    })

    $('#coverPageFile').on('change', function () {
        var formData = new FormData($('#coverPageForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null && result.data != '') {
                    vm.uPageImgUrl = result.data;
                    $('#coverPageDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#coverDetailFile').on('change', function () {
        var formData = new FormData($('#coverDetailForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null && result.data != '') {
                    vm.uDetailImgUrl = result.data;
                    $('#coverDetailDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#a_coverPageFile').on('change', function () {
        var formData = new FormData($('#a_coverPageForm')[0]);

        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.aPageImgUrl = result.data;
                    $('#a_coverPageDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#a_coverDetailFile').on('change', function () {
        var formData = new FormData($('#a_coverDetailForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.aDetailImgUrl = result.data;
                    $('#a_coverPageDiv').removeClass('coverDiv');
                }
            }
        });
    })

});

function search() {
    $('#tb_body').bootstrapTable('refresh');
}

function add() {
    $("#addTypeForm").data('bootstrapValidator').destroy();
    $('#addTypeForm').data('bootstrapValidator', null);
    validateAddForm();
    $('#addModal').modal('show');
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_body').bootstrapTable({
            url: '/type/findPage',                 //请求后台的URL（*）
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
            ]
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

function deleteFunction(id) {
    vm.currentId = id;
    $('#confirmModal').modal('show');
}

function updateModal(id) {
    $("#updateTypeForm").data('bootstrapValidator').destroy();
    $('#updateTypeForm').data('bootstrapValidator', null);
    validateUpdateForm();


    vm.currentId = id;
    $.get('/type/get', {id: id}, function (result) {
        if(result != null && result.state == 11) {
            var type = result.data;
            vm.type = type;
            if(type.pageImgUrl != null && type.pageImgUrl != '') {
                $('#coverPageDiv').removeClass('coverDiv');
                vm.uPageImgUrl = type.pageImgUrl;
            }else {
                $('#coverPageDiv').addClass('coverDiv');
                vm.uPageImgUrl = null;
            }
            if(type.detailImgUrl != null && type.detailImgUrl != '') {
                $('#coverDetailDiv').removeClass('coverDiv');
                vm.uDetailImgUrl = type.detailImgUrl;
            }else {
                $('#coverDetailDiv').addClass('coverDiv');
                vm.uDetailImgUrl = null;
            }
            if(type.pageImgIsShow == 1) {
                $('input:radio[name="uPageisShow"]').eq(1).attr('checked',true);
            }else {
                $('input:radio[name="uPageisShow"]').eq(0).attr('checked',true);
            }
            if(type.detailImgIsShow == 1) {
                $('input:radio[name="uDetailisShow"]').eq(1).attr('checked',true);
            }else {
                $('input:radio[name="uDetailisShow"]').eq(0).attr('checked',true);
            }

        }
    });
    $('#updateModal').modal('show');
}

function addPageCover() {
    $('#a_coverPageFile').click();
}

function addDetailCover() {
    $('#a_coverDetailFile').click();
}

function uploadPageCover() {
    $('#coverPageFile').click();
}

function uploadDetailCover() {
    $('#coverDetailFile').click();
}

function validateAddForm() {
    $('#addTypeForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            sort: {
                validators: {
                    notEmpty: {
                        message: '序号不能为空'
                    },
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的序号'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '品牌分类名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '品牌分类名称不得超过300'
                    }
                }
            },
            linkColor: {
                validators: {
                    notEmpty: {
                        message: '链接颜色不能为空'
                    }
                }
            },
            icon: {
                validators: {
                    notEmpty: {
                        message: 'icon不能为空'
                    }
                },
                stringLength: {
                    min: 1,
                    max: 300,
                    message: 'icon不得超过300'
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
    });
}

function validateUpdateForm() {
    $('#updateTypeForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            sort: {
                validators: {
                    notEmpty: {
                        message: '序号不能为空'
                    },
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的序号'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '品牌分类名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '品牌分类名称不得超过300'
                    }
                }
            },
            linkColor: {
                validators: {
                    notEmpty: {
                        message: '链接颜色不能为空'
                    }
                }
            },
            icon: {
                validators: {
                    notEmpty: {
                        message: 'icon不能为空'
                    }
                },
                stringLength: {
                    min: 1,
                    max: 300,
                    message: 'icon不得超过300'
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
    });
}

