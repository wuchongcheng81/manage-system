$(function () {

    $.get('/website/getOne', function (result) {
        if(result != null && result.data != null ) {
            vm.website = result.data;
            vm.currentId = result.data.id;
            if(result.data.logo != null && result.data.logo != '') {
                vm.cover = result.data.logo;
                $('#coverDiv').removeClass('coverDiv');
            }
            if(result.data.wechatLogo != null && result.data.wechatLogo != '') {
                vm.coverW = result.data.wechatLogo;
                $('#coverWDiv').removeClass('coverDiv');
            }
        }
    });


    $("#editForm").submit(function (ev) {
        ev.preventDefault();
    });
    $('#validaterBtn').on('click', function () {
        $("#editForm").bootstrapValidator('validate');
        if ($("#editForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                var entity = new FormData($('#editForm')[0]);
                entity.append('id', vm.currentId);
                entity.append('logo', vm.cover);
                entity.append('wechatLogo', vm.coverW);
                $.ajax({
                    url: '/website/update',
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
                                parent.window.closeTabLinkTo(22, '网站信息配置', 'website/editWebsite.html');
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

    $('#coverWFile').on('change', function () {
        var formData = new FormData($('#coverWForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.coverW = result.data;
                    $('#coverWDiv').removeClass('coverDiv');
                }
            }
        });
    })
})

function uploadCover() {
    $('#coverFile').click();
}

function uploadWCover() {
    $('#coverWFile').click();
}

function validateForm() {
    $('#editForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '网站名称长度不得超过300'
                    }
                }
            },
            keyWord: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '网站关键字长度不得超过300'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '网站描述长度不得超过300'
                    }
                }
            },
            domainName: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '网站域名长度不得超过300'
                    }
                }
            },
            adCooperate: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '广告合作长度不得超过300'
                    }
                }
            },
            copyright: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '版权信息长度不得超过300'
                    }
                }
            },
            olService: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '在线客服字样长度不得超过300'
                    }
                }
            },
            inforJoin: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '咨询加盟字样长度不得超过300'
                    }
                }
            },
            searchTip: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '搜索框提示语长度不得超过300'
                    }
                }
            },
            popularType: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '热门搜索品牌长度不得超过300'
                    }
                }
            },
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
    });
}
