$(function () {
    $('#summernoteDescription').summernote(
        {
            placeholder: '请输入品牌简介',
            focus: false,
            lang: 'zh-CN',
            height: 300,
            width: '70%',
            callbacks: {
                onImageUpload: function (files) {
                    sendFileA(files);
                }
            }
        }
    );

    $('#summernotePolicy').summernote(
        {
            placeholder: '请输入招商政策',
            focus: false,
            lang: 'zh-CN',
            height: 300,
            width: '70%',
            callbacks: {
                onImageUpload: function (files) {
                    sendFileB(files);
                }
            }
        }
    );


    $("#addForm").submit(function (ev) {
        ev.preventDefault();
    });
    $('#validaterBtn').on('click', function () {
        $("#addForm").bootstrapValidator('validate');
        if ($("#addForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                var isInvest = 0;
                if($('#isInvest').is(':checked'))
                    isInvest = 1;
                var isPublish = 0;
                if($('#isPublish').is(':checked'))
                    isPublish = 1;
                var labelAuth = 0;
                if($('#labelAuth').is(':checked'))
                    labelAuth = 1;
                var labelHonest = 0;
                if($('#labelHonest').is(':checked'))
                    labelHonest = 1;
                var labelVip = 0;
                if($('#labelVip').is(':checked'))
                    labelVip = 1;
                var labelQuality = 0;
                if($('#labelQuality').is(':checked'))
                    labelQuality = 1;

                var recIndex = 0;
                if($('#recIndex').is(':checked'))
                    recIndex = 1;
                var recTypeIndex = 0;
                if($('#recTypeIndex').is(':checked'))
                    recTypeIndex = 1;
                var recTypeRank = 0;
                if($('#recTypeRank').is(':checked'))
                    recTypeRank = 1;
                var recTypeInfor = 0;
                if($('#recTypeInfor').is(':checked'))
                    recTypeInfor = 1;
                var recInforHead = 0;
                if($('#recInforHead').is(':checked'))
                    recInforHead = 1;
                var recInforEval = 0;
                if($('#recInforEval').is(':checked'))
                    recInforEval = 1;
                var recInforDetail = 0;
                if($('#recInforDetail').is(':checked'))
                    recInforDetail = 1;


                var entity = new FormData($('#addForm')[0]);
                entity.append('logo', vm.cover);
                entity.append('logoA', vm.coverA);
                entity.append('logoB', vm.coverB);
                entity.append('logoC', vm.coverC);
                entity.append('logoViewpoint', vm.coverV);
                entity.append('isInvest', isInvest);
                entity.append('isPublish', isPublish);
                entity.append('labelAuth', labelAuth);
                entity.append('labelHonest', labelHonest);
                entity.append('labelVip', labelVip);
                entity.append('labelQuality', labelQuality);

                entity.append('recIndex', recIndex);
                entity.append('recTypeIndex', recTypeIndex);
                entity.append('recTypeRank', recTypeRank);
                entity.append('recTypeInfor', recTypeInfor);
                entity.append('recInforHead', recInforHead);
                entity.append('recInforEval', recInforEval);
                entity.append('recInforDetail', recInforDetail);

                entity.append('description', $('#summernoteDescription').summernote('code'));
                entity.append('investPolicy', $('#summernotePolicy').summernote('code'));

                $.ajax({
                    url: '/brand/save',
                    type: 'POST',
                    dataType: 'json',
                    data: entity,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if (data.state == 11) {
                            $('#successModal').modal('show');
                            $("#closeSuccessBtn").on("click", function () {
                                $('#successModal').modal('hide');
                                parent.window.closeTabLinkTo(34, '品牌管理', 'brand/brandList.html');
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

    $('#coverAFile').on('change', function () {
        var formData = new FormData($('#logoAForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.coverA = result.data;
                    $('#coverADiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#coverBFile').on('change', function () {
        var formData = new FormData($('#logoBForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.coverB = result.data;
                    $('#coverBDiv').removeClass('coverDiv');
                }
            }
        });
    })

    $('#coverCFile').on('change', function () {
        var formData = new FormData($('#logoCForm')[0]);
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

    $('#coverVFile').on('change', function () {
        var formData = new FormData($('#logoVForm')[0]);
        $.ajax({
            url: '/upload/image',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                if(result != null && result.data != null) {
                    vm.coverV = result.data;
                    $('#coverVDiv').removeClass('coverDiv');
                }
            }
        });
    })
})

function uploadCover() {
    $('#coverFile').click();
}
function uploadACover() {
    $('#coverAFile').click();
}
function uploadBCover() {
    $('#coverBFile').click();
}
function uploadCCover() {
    $('#coverCFile').click();
}
function uploadVCover() {
    $('#coverVFile').click();
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
            name: {
                validators: {
                    notEmpty: {
                        message: '品牌名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 8,
                        message: '品牌名称长度不得超过8'
                    }
                }
            },
            companyName: {
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '公司名称长度不得超过30'
                    }
                }
            },
            originPlace: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '发源地长度不得超过30'
                    }
                }
            },
            brandPopular: {
                validators: {
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的品牌热度'
                    }
                }
            },
            joinCondition: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '加盟条件长度不得超过30'
                    }
                }
            },
            investType: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '招商类型长度不得超过30'
                    }
                }
            },
            investArea: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '招商地区长度不得超过50'
                    }
                }
            },
            establishYear: {
                validators: {
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的品牌创立年份'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();

    });
}

/** * 发送图片文件给服务器端 */
function sendFileA(files) {
    var file = new FormData();
    file.append("file", files[0]);
    $.ajax({
        url: '/upload/image', // 图片上传url
        type: 'POST',
        data: file,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json',     // 以json的形式接收返回的数据
        // 图片上传成功
        success: function ($result) {
            if($result != null && $result.data != null) {
                var imgNode = document.createElement("img");
                imgNode.src = $result.data;
                $('#summernoteDescription').summernote('insertNode', imgNode);
            }
        },
        // 图片上传失败
        error: function () {
            console.log('图片上传失败');
        }
    });
}

function sendFileB(files) {
    var file = new FormData();
    file.append("file", files[0]);
    $.ajax({
        url: '/upload/image', // 图片上传url
        type: 'POST',
        data: file,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json',     // 以json的形式接收返回的数据
        // 图片上传成功
        success: function ($result) {
            if($result != null && $result.data != null) {
                var imgNode = document.createElement("img");
                imgNode.src = $result.data;
                $('#summernotePolicy').summernote('insertNode', imgNode);
            }
        },
        // 图片上传失败
        error: function () {
            console.log('图片上传失败');
        }
    });
}
