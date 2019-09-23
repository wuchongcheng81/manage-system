var id;
$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    //设置文件上传的参数名称
    editor.customConfig.uploadFileName = 'file';
    //设置上传文件的服务器路径
    editor.customConfig.uploadImgServer = '/upload/wangEditorUpload';
    //将图片大小限制为5M
    editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
    editor.create();

    id = getUrlParam('id');
    $.get('/information/get', {id : id}, function (result) {
        if(result != null && result.data != null ) {
            vm.information = result.data;
            if(vm.information.typeId != null) {
                vm.getBrand(vm.information.typeId);
                if(result.data.brandId != null) {
                    vm.brandId = result.data.brandId;
                }
            }

            if(result.data.coverUrl != null && result.data.coverUrl != '') {
                vm.cover = result.data.coverUrl;
                $('#coverDiv').removeClass('coverDiv');
            }

            if(result.data.isPublish == 1)
                $("#isPublish").attr("checked",true);
            if(result.data.recDay == 1)
                $("#recDay").attr("checked",true);

            editor.txt.html(result.data.content);
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
                var isPublishFlag = 0;
                var recDayFlag = 0;
                if($('#isPublish').is(':checked'))
                    isPublishFlag = 1;
                if($('#recDay').is(':checked'))
                    recDayFlag = 1;
                var entity = new FormData($('#editForm')[0]);
                entity.append('id', id);
                entity.append('content', editor.txt.html());
                entity.append('coverUrl', vm.cover);
                entity.append('isPublish', isPublishFlag);
                entity.append('recDay', recDayFlag);
                $.ajax({
                    url: '/information/update',
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
                                parent.window.closeTabLinkTo(6, '资讯管理', 'information/informationList.html');
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
})

function uploadCover() {
    $('#coverFile').click();
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
            title: {
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
            clickNum: {
                validators: {
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的序号'
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
function sendFile(files) {
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
                $('#summernote').summernote('insertNode', imgNode);
            }
        },
        // 图片上传失败
        error: function () {
            console.log('图片上传失败');
        }
    });
}
