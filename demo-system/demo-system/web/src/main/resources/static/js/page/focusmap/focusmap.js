var code = getUrlParam('code');
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    validateAddForm();

    $('#coverFile').on('change', function () {
        var formData = new FormData($('#coverForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.imgUrl = result.data;
                    $('#coverDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#aCoverFile').on('change', function () {
        var formData = new FormData($('#coverForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.aImgUrl = result.data;
                    $('#aCoverDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $("#confirmBtn").on("click", function () {
        if(vm.currentId != null) {
            $.post('/focusMap/delete', {id : vm.currentId}, function(data) {
                if(data != null && data.state == 11) {
                    $('#confirmModal').modal('hide');
                    $('#successMsg').text('删除焦点图成功！');
                    $('#successModal').modal('show');
                    $('#tb_body').bootstrapTable('refresh');
                }
            })
        }
    })

    $('#cancelUpdate').on("click", function () {
        $('#updateModal').modal('hide');
    })

    $('#aCancelUpdate').on("click", function () {
        $('#addModal').modal('hide');
    })

    $('#aConfirmUpdate').on("click", function () {
        $("#addForm").bootstrapValidator('validate');
        if ($("#addForm").data('bootstrapValidator').isValid()) {
            $.post('/focusMap/save', {
                link: $('#a_link').val(),
                imgUrl: vm.imgUrl,
                imgIsShow: $("input[name='aIsShow']:checked").val(),
                sort: $('#a_sort').val(),
                positionCode: code
            },function(data) {
                if(data.state == 11) {
                    $('#addModal').modal('hide');
                    $('#successMsg').text('新增焦点图成功！');
                    $('#successModal').modal('show');
                    $('#tb_body').bootstrapTable('refresh');
                }else{
                    $('#successMsg').text('新增焦点图失败！');
                    $('#successModal').modal('show');
                }
            })
        }
    })

    $('#confirmUpdate').on("click", function () {
        $.post('/focusMap/update',
            {
                id: vm.currentId,
                imgUrl: vm.imgUrl,
                link: $('#u_link').val(),
                imgIsShow: $("input[name='uIsShow']:checked").val(),
            }, function(data) {
                if(data.state == 11) {
                    $('#updateModal').modal('hide');
                    $('#successMsg').text('修改焦点图成功！');
                    $('#successModal').modal('show');
                    $('#tb_body').bootstrapTable('refresh');
                }else {
                    $('#successMsg').text('修改焦点图失败！');
                    $('#successModal').modal('show');
                }
            });
        $('#updateModal').modal('hide');
    })
});

function search() {
    $('#tb_body').bootstrapTable('refresh');
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_body').bootstrapTable({
            url: '/focusMap/findPage',                 //请求后台的URL（*）
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
                    field: 'sort',
                    title: '序号'
                },
                {
                    field: 'imgUrl',
                    title: '广告图',
                    cellStyle: {'css': {'text-align': 'center', 'width': '20%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="70px">';
                            return img;
                        }
                        return '';
                    }
                },
                {
                    field: 'position',
                    title: '广告图位置'
                },{
                    field: 'link',
                    title: '广告图链接'
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
            'positionCode':code,
            'pageNumber': this.pageNumber,  //页面大小
            'pageSize': this.pageSize       //页码
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
    vm.currentId = id;
    $.get('/focusMap/get', {id: id}, function (result) {
        if(result != null && result.state == 11) {
            var focusMap = result.data;
            vm.focusMap = focusMap;
            if(focusMap.imgUrl != null) {
                $('#coverDiv').removeClass('coverDiv');
                vm.imgUrl = focusMap.imgUrl;
            }
            if(focusMap.imgIsShow == 1) {
                $('input:radio[name="uIsShow"]').eq(1).attr('checked',true);
            }else {
                $('input:radio[name="uIsShow"]').eq(0).attr('checked',true);
            }
        }
    });
    $('#updateModal').modal('show');
}

function uploadCover() {
    $('#coverFile').val('');
    $('#coverFile').click();
}

function add() {
    vm.imgUrl = null;
    $('#addModal').modal('show');
}

function validateAddForm() {
    $('#addForm').bootstrapValidator({
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
            link: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 50,
                        message: '品牌分类名称不得超过50'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
    });
}