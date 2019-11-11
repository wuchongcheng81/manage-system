var btnType;
var currentId;
$(function () {

    initTable();

    $("#confirmBtn").on("click", function () {
        if(vm.currentId != null) {
            if(vm.isParent == 0) {
                $.post('/type/deleteType', {id : vm.currentId}, function(data) {
                    $('#confirmModal').modal('hide');
                    if(data != null && data.state == 00) {
                        $('#successMsg').text(data.note);
                        $('#successModal').modal('show');
                    }else {
                        $('#successMsg').text('删除品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
                    }
                })
            }else {
                $.post('/type/deleteParent', {id : vm.currentId}, function(data) {
                    $('#confirmModal').modal('hide');
                    if(data != null && data.state == 00) {
                        $('#successMsg').text(data.note);
                        $('#successModal').modal('show');
                    }else {
                        $('#successMsg').text('删除品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
                    }
                })
            }

        }
    })

    validateAddForm();
    validateUpdateForm();
    validateAddParentForm();
    validateUpdateParentForm();

    $('#cancelUpdate').on("click", function () {
        $('#updateModal').modal('hide');
    })

    $('#a_cancelUpdate').on("click", function () {
        $('#addModal').modal('hide');
    })

    $('#a_p_cancelUpdate').on("click", function () {
        $('#addParentModal').modal('hide');
    })

    $('#p_cancelUpdate').on("click", function () {
        $('#updateParentModal').modal('hide');
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
                    parentId: $('#a_parent').val()
                }, function(data) {
                    if(data.state == 11) {
                        $('#addModal').modal('hide');
                        $('#successMsg').text('添加品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
                    }else {
                        $('#successMsg').text('添加品牌分类失败！');
                        $('#successModal').modal('show');
                    }
                });

            $('#addModal').modal('hide');
        }
    })

    $('#a_p_confirmUpdate').on("click", function () {
        $("#addParentTypeForm").bootstrapValidator('validate');
        if ($("#addParentTypeForm").data('bootstrapValidator').isValid()) {
            $.post('/type/save',
                {
                    name: $('#a_p_name').val(),
                    sort: $('#a_p_sort').val(),
                    parentId: 0
                }, function(data) {
                    if(data.state == 11) {
                        $('#addParentModal').modal('hide');
                        $('#successMsg').text('添加品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
                    }else {
                        $('#successMsg').text('添加品牌分类失败！');
                        $('#successModal').modal('show');
                    }
                });

            $('#addParentModal').modal('hide');
        }
    })

    $('#p_confirmUpdate').on("click", function () {
        $("#updateParentTypeForm").bootstrapValidator('validate');
        if ($("#updateParentTypeForm").data('bootstrapValidator').isValid()) {
            $.post('/type/update',
                {
                    id: vm.currentId,
                    name: $('#u_p_name').val(),
                    sort: $('#u_p_sort').val(),
                    parentId: 0
                }, function(data) {
                    if(data.state == 11) {
                        $('#updateParentModal').modal('hide');
                        $('#successMsg').text('修改品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
                    }else {
                        $('#successMsg').text('修改品牌分类失败！');
                        $('#successModal').modal('show');
                    }
                });
            $('#updateParentModal').modal('hide');
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
                    parentId: $('#u_parent').val()
                }, function(data) {
                    if(data.state == 11) {
                        $('#updateModal').modal('hide');
                        $('#successMsg').text('修改品牌分类成功！');
                        $('#successModal').modal('show');
                        reload();
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

function reload() {
    // parent.window.addTab(5, '品牌类型管理', 'type/typeList.html');
    $("#tb_body").bootstrapTable('destroy');
    initTable();
}

function initTable() {
    $.get('/type/findAllList', function(response) {
        var $table = $('#tb_body');

        $table.bootstrapTable({
            data: response,
            idField: 'id',
            dataType:'jsonp',
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
                    cellStyle: {'css': {'text-align': 'center', 'width': '300px','min-width': '265px'}},
                    formatter: function (value, row, index) {
                        var addBtn;
                        var editBtn;
                        var deleteBtn;
                        if(row.parentId == 0) {
                            addBtn = '<button type="button" style="margin-right: 15px" class="btn btn-default" onclick="addChileModal(\'' + value + '\')">添加下级分类</button>';
                            editBtn = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="updateParentModal(\'' + value + '\')">修改</button>';
                            deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteParentFunction(\'' + value + '\')">删除</button>';
                            return addBtn + editBtn + deleteBtn;
                        }else {
                            editBtn = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="updateModal(\'' + value + '\')">修改</button>';
                            deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                            return editBtn + deleteBtn;
                        }
                    }
                }
            ],
            treeShowField: 'sort',
            parentIdField: 'parentId',
            onResetView: function(data) {
                //console.log('load');
                $table.treegrid({
                    initialState: 'collapsed',// 所有节点都折叠
                    // initialState: 'expanded',// 所有节点都展开，默认展开
                    treeColumn: 0,
                    // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
                    // expanderCollapsedClass: 'glyphicon glyphicon-plus',
                    onChange: function() {
                        $table.bootstrapTable('resetWidth');
                    }
                });

                //只展开树形的第一级节点
                // $table.treegrid('getRootNodes').treegrid('expand');
            },
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
    })
}

function add() {
    $("#addParentTypeForm").data('bootstrapValidator').destroy();
    $('#addParentTypeForm').data('bootstrapValidator', null);
    validateAddParentForm();
    $('#addParentModal').modal('show');
}

function addChileModal() {
    $("#addTypeForm").data('bootstrapValidator').destroy();
    $('#addTypeForm').data('bootstrapValidator', null);
    validateAddForm();
    $.get('/type/findAllParent', function(response) {
        vm.parentTypes = [];
        response.forEach(function (p) {
            vm.parentTypes.push({id: p.id, name: p.name});
        })
    });

    $('#addModal').modal('show');
}


function deleteFunction(id) {
    vm.currentId = id;
    vm.isParent = 0;
    $('#confirmModal').modal('show');
}

function deleteParentFunction(id) {
    vm.currentId = id;
    vm.isParent = 1;
    $('#confirmModal').modal('show');
}

function updateParentModal(id) {
    $("#updateParentTypeForm").data('bootstrapValidator').destroy();
    $('#updateParentTypeForm').data('bootstrapValidator', null);
    validateUpdateParentForm();

    vm.type = {};
    vm.currentId = id;

    $.get('/type/get', {id: id}, function (result) {
        if(result != null && result.state == 11) {
            var type = result.data;
            vm.type = type;
        }
    });
    $('#updateParentModal').modal('show');
}

function updateModal(id) {
    $("#updateTypeForm").data('bootstrapValidator').destroy();
    $('#updateTypeForm').data('bootstrapValidator', null);
    validateUpdateForm();

    vm.type = {};
    vm.currentId = id;
    $.get('/type/findAllParent', function(response) {
        vm.parentTypes = [];
        response.forEach(function (p) {
            vm.parentTypes.push({id: p.id, name: p.name});
        })
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

function validateAddParentForm() {
    $('#addParentTypeForm').bootstrapValidator({
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
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
    });
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
            parentId: {
                validators: {
                    notEmpty: {
                        message: '上级分类不能为空'
                    }
                }
            },
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

function validateUpdateParentForm() {
    $('#updateParentTypeForm').bootstrapValidator({
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
            parentId: {
                validators: {
                    notEmpty: {
                        message: '上级分类不能为空'
                    }
                }
            },
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

