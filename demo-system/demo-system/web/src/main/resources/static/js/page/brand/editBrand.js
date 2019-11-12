var id;
$(function () {
    var De = window.wangEditor;
    var editorD = new De('#editorD');
    //设置文件上传的参数名称
    editorD.customConfig.uploadFileName = 'file';
    //设置上传文件的服务器路径
    editorD.customConfig.uploadImgServer = '/upload/wangEditorUpload';
    //将图片大小限制为5M
    editorD.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
    editorD.create();

    var Po = window.wangEditor;
    var editorP = new Po('#editorP');
    //设置文件上传的参数名称
    editorP.customConfig.uploadFileName = 'file';
    //设置上传文件的服务器路径
    editorP.customConfig.uploadImgServer = '/upload/wangEditorUpload';
    //将图片大小限制为5M
    editorP.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
    editorP.create();

    id = getUrlParam('id');
    $.get('/brand/get', {id : id}, function (result) {
        if(result != null && result.data != null ) {
            var brand = result.data;
            vm.brand = brand;
            if(brand.logo != null && brand.logo != '') {
                vm.cover = brand.logo;
                $('#coverDiv').removeClass('coverDiv');
            }
            if(brand.logoA != null && brand.logoA != '') {
                vm.coverA = brand.logoA;
                $('#coverADiv').removeClass('coverDiv');
            }
            if(brand.logoB != null && brand.logoB != '') {
                vm.coverB = brand.logoB;
                $('#coverBDiv').removeClass('coverDiv');
            }
            if(brand.logoC != null && brand.logoC != '') {
                vm.coverC = brand.logoC;
                $('#coverCDiv').removeClass('coverDiv');
            }
            if(brand.logoViewpoint != null && brand.logoViewpoint != '') {
                vm.coverV = brand.logoViewpoint;
                $('#coverVDiv').removeClass('coverDiv');
            }
            if(brand.wechatShareImgUrl != null && brand.wechatShareImgUrl != '') {
                vm.coverW = brand.wechatShareImgUrl;
                $('#coverWDiv').removeClass('coverDiv');
            }

            if(brand.isPublish == 1)
                $("#isPublish").attr("checked",true)
            if(brand.isInvest == 1)
                $("#isInvest").attr("checked",true)

            if(brand.labelAuth == 1)
                $("#labelAuth").attr("checked",true);
            if(brand.labelHonest == 1)
                $("#labelHonest").attr("checked",true);
            if(brand.labelVip == 1)
                $("#labelVip").attr("checked",true);
            if(brand.labelQuality == 1)
                $("#labelQuality").attr("checked",true);

            if(brand.recIndex == 1)
                $("#recIndex").attr("checked",true);
            if(brand.recTypeIndex == 1)
                $("#recTypeIndex").attr("checked",true);
            if(brand.recTypeRank == 1)
                $("#recTypeRank").attr("checked",true);
            if(brand.recTypeInfor == 1)
                $("#recTypeInfor").attr("checked",true);
            if(brand.recInforHead == 1)
                $("#recInforHead").attr("checked",true);
            if(brand.recInforEval == 1)
                $("#recInforEval").attr("checked",true);
            if(brand.recInforDetail == 1)
                $("#recInforDetail").attr("checked",true);

            editorD.txt.html(brand.description);
            editorP.txt.html(brand.investPolicy);
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


                var entity = new FormData($('#editForm')[0]);
                entity.append('id', id);
                entity.append('logo', vm.cover);
                entity.append('logoA', vm.coverA);
                entity.append('logoB', vm.coverB);
                entity.append('logoC', vm.coverC);
                entity.append('wechatShareImgUrl', vm.coverW);
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

                entity.append('description', editorD.txt.html());
                entity.append('investPolicy', editorP.txt.html());

                $.ajax({
                    url: '/brand/update',
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
                                parent.window.closeTabLinkTo(21, '品牌管理', 'brand/brandList.html');
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
                    $('#coverDivImg').show();
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
                    $('#coverADivImg').show();
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
                    $('#coverBDivImg').show();
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
                    $('#coverCDivImg').show();
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
                    $('#coverVDivImg').show();
                }
            }
        });
    })

    $('#coverWFile').on('change', function () {
        var formData = new FormData($('#logoWForm')[0]);
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
                    $('#coverWDivImg').show();
                }
            }
        });
    })
})

function deleteCover() {
    vm.cover = '';
    document.getElementById('coverFile').value = '';
    $('#coverDivImg').hide();
    $('#coverDiv').addClass('coverDiv');
}
function deleteACover() {
    vm.coverA = '';
    document.getElementById('coverAFile').value = '';
    $('#coverADivImg').hide();
    $('#coverADiv').addClass('coverDiv');
}
function deleteBCover() {
    vm.coverB = '';
    document.getElementById('coverBFile').value = '';
    $('#coverBDivImg').hide();
    $('#coverBDiv').addClass('coverDiv');
}
function deleteCCover() {
    vm.coverC = '';
    document.getElementById('coverCFile').value = '';
    $('#coverCDivImg').hide();
    $('#coverCDiv').addClass('coverDiv');
}
function deleteVCover() {
    vm.coverV = '';
    document.getElementById('coverVFile').value = '';
    $('#coverVDivImg').hide();
    $('#coverVDiv').addClass('coverDiv');
}
function deleteWCover() {
    vm.coverW = '';
    document.getElementById('coverWFile').value = '';
    $('#coverWDivImg').hide();
    $('#coverWDiv').addClass('coverDiv');
}

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
                    notEmpty: {
                        message: '品牌名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '品牌名称长度不得超过300'
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
                        max: 300,
                        message: '公司名称长度不得超过300'
                    }
                }
            },
            originPlace: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '发源地长度不得超过300'
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
                        max: 300,
                        message: '加盟条件长度不得超过300'
                    }
                }
            },
            investType: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '招商类型长度不得超过300'
                    }
                }
            },
            investArea: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '招商地区长度不得超过300'
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
            // console.log('图片上传失败');
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
