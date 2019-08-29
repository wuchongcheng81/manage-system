var id;
$(function () {
    id = getUrlParam('id');
    vm.imgUrls = [];
    $.post('/houseActivity/queryObject', {id: id}, function (data) {
        vm.activity = data.result;
        $('#txt_file01').fileinput('destroy')
        var oFileInput = new FileInput();
        oFileInput.Init('txt_file01', '/houseActivity/activityImgUpload');

        $('#txt_file02').fileinput('destroy')
        var aFileInput = new AcvivityEditFileInput();
        aFileInput.Init('txt_file02', '/houseActivity/activityImgUpload', data.result);
    });

    $("#activityEditForm").submit(function (ev) {
        ev.preventDefault();
    });
    $("#validaterBtn").on("click", function () {
        updateActivity();
    })
})


function updateActivity() {
    validateActivityForm();
    $("#activityEditForm").bootstrapValidator('validate');
    if ($("#activityEditForm").data('bootstrapValidator').isValid()) {
        var postUrl = '/houseActivity/update';
        if (vm.activity.id == null || vm.activity.id == '') {
            vm.activity.houseId = vm.houseId;
            postUrl = '/houseActivity/save';
        }
        var imgUrls = '';
        vm.imgUrls.forEach(function (url) {
            imgUrls = imgUrls + url + ',';
        })
        imgUrls = imgUrls.substring(0, imgUrls.length - 1);
        vm.activity.imgUrls = imgUrls;
        vm.activity.createAt = null;
        vm.activity.photos = null;
        $.ajax({
            type: "POST",
            url: postUrl,
            data: vm.activity,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (data, status) {
                if (data.state == 11) {
                    $('#successModal').modal('show');
                    $('#closeBtn').on("click", function () {
                        parent.window.closeTabTest();
                    })
                }
            },
            error: function (data, status) {
            },
            complete: function () {

            }

        });
    }
}


//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            showClose: false,
            // showRemove: false,
            fileActionSettings: {showZoom: false, showUpload: false, showRemove: false},
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            maxFileSize: 1024,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 3, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
        });

        //导入文件上传完成之后的事件
        $("#txt_file01").on('fileuploaded', function (event, data, previewId, index) {
            if (vm.imgUrls != null && vm.imgUrls.length > 0) {
                vm.imgUrls.push(data.response.imgUrls);
            } else {
                vm.imgUrls = data.response.imgUrls;
            }
        });

        $("#txt_file01").on('fileclear', function (event, id, index) {
            vm.imgUrls = [];
        });
    }
    return oFile;
};


var AcvivityEditFileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl, activity) {
        var control = $('#' + ctrlName);
        var ipc = [];
        var imgUrl = [];
        if (activity.photos != null && activity.photos.length > 0) {
            activity.photos.forEach(function (photo) {
                ipc.push({width: "110px", url: '/photo/deleteById', key: photo.id});
                imgUrl.push(photo.photo);
            })
        }
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            showClose: false,
            showRemove: false,
            fileActionSettings: {showZoom: false, showUpload: false, showRemove: true},
            initialPreviewAsData: true,
            initialPreview: imgUrl,
            initialPreviewConfig: ipc
        });

        $('#reviewDiv').children().find('input').remove();
        // $('#reviewDiv').children().find('button').remove();
        var divLength = $('#reviewDiv').children().find('div').length;
        $('#reviewDiv').children().find('div').eq(length - 1).remove();
    }
    return oFile;
};


function validateActivityForm() {
    $('#activityEditForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            title: {
                message: '标题验证失败',
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '标题长度不得超过50'
                    }
                }
            },
            desc: {
                message: '描述验证失败',
                validators: {
                    notEmpty: {
                        message: '描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '描述长度不得超过1000'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();

    });
}