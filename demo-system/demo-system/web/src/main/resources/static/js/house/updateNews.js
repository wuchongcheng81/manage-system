var id;
$(function () {
    $('#summernote').summernote(
        {
            placeholder: '请输入文本内容',
            focus: true,
            lang: 'zh-CN',
            height: 300,
            width: '70%',
            callbacks: {
                onImageUpload: function (files) {
                    sendFile(files);
                }
            }
        }
    );
    id = getUrlParam('id');
    vm.id = id;
    $.post('/news/findDetail', {id: id}, function (data) {
        if (data.result.type == 1) {
            $('#activity').attr('checked', 'checked');
        } else {
            $('#headline').attr('checked', 'checked');
        }
        $('#summernote').summernote('code', data.result.body);
        vm.cover = data.result.cover;
        if (vm.cover != null) {
            $('#coverDiv').removeClass('coverDiv');
        }
        vm.title = data.result.title;
        vm.desc = data.result.desc;
    });


    $("#newsUpdateForm").submit(function (ev) {
        ev.preventDefault();
    });
    $('#validaterBtn').on('click', function () {
        $("#newsUpdateForm").bootstrapValidator('validate');
        if (vm.cover == null) {
            return;
        }
        if ($("#newsUpdateForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                var entity = new FormData($('#newsUpdateForm')[0]);
                entity.append('body', $('#summernote').summernote('code'));
                entity.append('cover', vm.cover);
                $.ajax({
                    url: '/news/update',
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
                                parent.window.closeTabLinkTo(6, '咨询管理', 'newsList.html');
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
            url: '/photo/uploadImageToLocal',
            type: 'POST',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.state == 11) {
                    vm.cover = data.photo;
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
    $('#newsUpdateForm').bootstrapValidator({
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
            desc: {
                validators: {
                    notEmpty: {
                        message: '描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '描述长度不得超过500'
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
        url: '/photo/uploadImageToLocal', // 图片上传url
        type: 'POST',
        data: file,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json',     // 以json的形式接收返回的数据
        // 图片上传成功
        success: function ($result) {
            var imgNode = document.createElement("img");
            imgNode.src = $result.photo;
            $('#summernote').summernote('insertNode', imgNode);
        },
        // 图片上传失败
        error: function () {
            console.log('图片上传失败');
        }
    });
}
