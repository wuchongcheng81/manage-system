$(function () {

    $('#expireDateinput').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        todayHighlight: 1,//今天高亮
        autoclose: 1,//选择后自动关闭
        language: 'zh-CN'
    }).on('changeDate', function (ev) {
        var expireDate = $('#expireDateinput').datetimepicker('getDate').getTime();
        $('#expireDate').val(expireDate);
    });

    $("#addForm").submit(function (ev) {
        ev.preventDefault();
    });
    $('#validaterBtn').on('click', function () {
        $("#addForm").bootstrapValidator('validate');
        if ($("#addForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                var isTop = 0;
                if($('#isTop').is(':checked'))
                    isTop = 1;
                var isPublish = 0;
                if($('#isPublish').is(':checked'))
                    isPublish = 1;

                var entity = new FormData($('#addForm')[0]);
                entity.append('investLogo', vm.cover);
                entity.append('investContentLogo', vm.coverC);
                entity.append('isTop', isTop);
                entity.append('isPublish', isPublish);

                $.ajax({
                    url: '/brandInvest/save',
                    type: 'POST',
                    dataType: 'json',
                    data: entity,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if (data.state == '11') {
                            $('#successModal').modal('show');
                            $("#closeSuccessBtn").on("click", function () {
                                $('#successModal').modal('hide');
                                parent.window.closeTabLinkTo(24, '品牌招商管理', 'brandInvest/brandInvestList.html');
                            })
                        }
                    }
                });
            })
        }
    });

    validateForm();


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
                    vm.cover = result.data;
                    $('#coverDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#coverCFile').on('change', function () {
        var formData = new FormData($('#coverCForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.coverC = result.data;
                    $('#coverCDiv').removeClass('coverDiv');
                }
            }
        });
    })
})

function uploadCover() {
    $('#coverFile').click();
}
function uploadCCover() {
    $('#coverCFile').click();
}


function validateForm() {
    $('#addForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            title: {
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '标题长度不得超过300'
                    }
                }
            },
            type: {
                validators: {
                    notEmpty: {
                        message: '类别不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '类别长度不得超过300'
                    }
                }
            },
            investType: {
                validators: {
                    notEmpty: {
                        message: '招商类别不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '招商类别长度不得超过300'
                    }
                }
            },
            investArea: {
                validators: {
                    notEmpty: {
                        message: '招商地区不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '招商地区长度不得超过300'
                    }
                }
            },
            topSort: {
                validators: {
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的置顶序号'
                    }
                }
            },
            brandName: {
                validators: {
                    notEmpty: {
                        message: '品牌名字不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '品牌名字长度不得超过300'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();

    });
}