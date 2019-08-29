$(function () {

    $('#saleTime').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        todayHighlight: 1,//今天高亮
        autoclose: 1,//选择后自动关闭
        language: 'zh-CN',
    }).on('changeDate', function (ev) {
        var saleTimeStamp = $('#saleTime').datetimepicker('getDate').getTime();
        $('#saleTimeStamp').val(saleTimeStamp);
    });

    $('#commitTime').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        todayHighlight: 1,//今天高亮
        autoclose: 1,//选择后自动关闭
        language: 'zh-CN'
    }).on('changeDate', function (ev) {
        var commitTimeStamp = $('#commitTime').datetimepicker('getDate').getTime();
        $('#commitTimeStamp').val(commitTimeStamp);
    });


    validateForm();

    $("#updateHouseForm").submit(function (ev) {
        ev.preventDefault();
    });
    $("#validaterBtn").on("click", function () {
        vm.checkLabel(0);
        if (vm.labelTipShow) {
            return;
        }
        $("#updateHouseForm").bootstrapValidator('validate');
        if (vm.cover == null) {
            return;
        }
        if ($("#updateHouseForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                $('#myModal').attr('aria-hidden', false);

                var imgUrls = '';
                if (vm.imgUrls != null && vm.imgUrls.length > 0) {
                    vm.imgUrls.forEach(function (url) {
                        imgUrls = imgUrls + url + ',';
                    })
                    imgUrls = imgUrls.substring(0, imgUrls.length - 1);
                }

                var entity = new FormData($('#updateHouseForm')[0]);
                entity.append('imgUrls', imgUrls);
                entity.append('logoUrl', vm.cover);
                $.ajax({
                    url: '/house/updateHouse',
                    type: 'POST',
                    dataType: 'json',
                    data: entity,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if (data.state == 11) {
                            $('#successModal').modal('show');
                            $("#closeBtn").on("click", function () {
                                $('#successModal').modal('hide');
                                parent.window.closeTabLinkToHouseList();
                            })
                        }
                    }
                });
                // document.getElementById("addHouseForm").submit();
            });
        } else {
            return;
        }
    });

    $('.selectpicker').selectpicker('val', ['Mustard', 'Relish']);

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
                }
            }
        });
    })
});

function uploadCover() {
    $('#coverFile').click();
}

function saveHouse() {
    $('#updateHouseForm').bootstrapValidator('validate');
}

function validateForm() {
    $('#updateHouseForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '楼盘名称验证失败',
                validators: {
                    notEmpty: {
                        message: '楼盘名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '楼盘名称长度不得超过50'
                    }
                }
            },
            labelNameStr0: {
                message: '楼盘标题验证失败',
                validators: {
                    notEmpty: {
                        message: '楼盘标题1不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '楼盘名称长度不得超过10'
                    },

                }
            },
            devCompany: {
                validators: {
                    notEmpty: {
                        message: '开发商名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '开发商名称长度不得超过50'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: '楼盘单价不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/,
                        message: '请输入正确的楼盘单价'
                    }
                }
            },
            totalPrice: {
                validators: {
                    notEmpty: {
                        message: '楼盘总价不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/,
                        message: '请输入正确的楼盘总价'
                    }
                }
            },
            averagePrice: {
                validators: {
                    notEmpty: {
                        message: '参考均价不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/,
                        message: '请输入正确的参考均价'
                    }
                }
            },
            saleTime: {
                validators: {
                    notEmpty: {
                        message: '最新开盘时间不能为空'
                    }
                }
            },

            commitTime: {
                validators: {
                    notEmpty: {
                        message: '交房时间不能为空'
                    }
                }
            },
            cityName: {
                validators: {
                    notEmpty: {
                        message: '区域位置不能为空'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '楼盘地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '楼盘地址长度不得超过50'
                    }
                }
            },
            saleAddress: {
                validators: {
                    notEmpty: {
                        message: '售楼处地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '售楼处地址长度不得超过50'
                    }
                }
            },
            buildingType: {
                validators: {
                    notEmpty: {
                        message: '建筑类型不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '建筑类型长度不得超过50'
                    }
                }
            },
            decoration: {
                validators: {
                    notEmpty: {
                        message: '装修标准不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '装修标准长度不得超过50'
                    }
                }
            },
            decorationPrice: {
                validators: {
                    notEmpty: {
                        message: '装修价格不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/,
                        message: '请输入正确的装修价格'
                    }
                }
            },
            years: {
                validators: {
                    notEmpty: {
                        message: '产权年限不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的产权年限'
                    }
                }
            },
            greenRate: {
                validators: {
                    notEmpty: {
                        message: '绿化率不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^([1-9]\d?(\.\d{1,2})?|0.\d{1,2}|100)$/,
                        message: '请输入正确的产权年限'
                    }
                }
            },
            familyCount: {
                validators: {
                    notEmpty: {
                        message: '规划户数不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^[+]{0,1}(\d+)$/,
                        message: '请输入正确的规划户数'
                    }
                }
            },
            carPlace: {
                validators: {
                    notEmpty: {
                        message: '规划车位不能为空'
                    }
                    // ,
                    // regexp: {
                    //     regexp: /^[+]{0,1}(\d+)$/,
                    //     message: '请输入正确的规划车位'
                    // }
                }
            },
            houseCompany: {
                validators: {
                    notEmpty: {
                        message: '物业公司不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '物业公司长度不得超过50'
                    }
                }
            },
            housePay: {
                validators: {
                    notEmpty: {
                        message: '物业费不能为空'
                    }
                    ,
                    regexp: {
                        regexp: /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/,
                        message: '请输入正确的物业费'
                    }
                }
            },
            hotAir: {
                validators: {
                    notEmpty: {
                        message: '供暖方式不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '供暖方式长度不得超过50'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '咨询电话不能为空'
                    },
                    stringLength: {
                        min: 8,
                        max: 50,
                        message: '咨询电话长度限制为8-20'
                    }
                }
            },
            tip: {
                validators: {
                    notEmpty: {
                        message: '激励政策不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '激励政策长度不得超过50'
                    }
                }
            },
            commissionDesc: {
                validators: {
                    notEmpty: {
                        message: '佣金说明不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '佣金说明长度不得超过1000'
                    }
                }
            },
            customerReferralDesc: {
                validators: {
                    notEmpty: {
                        message: '客户转介不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '客户转介长度不得超过1000'
                    }
                }
            },
            commissionSettlementDesc: {
                validators: {
                    notEmpty: {
                        message: '佣金结算说明不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '佣金结算说明长度不得超过1000'
                    }
                }
            },
            lookProcessDesc: {
                validators: {
                    notEmpty: {
                        message: '带看流程不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '带看流程长度不得超过1000'
                    }
                }
            },
            provinceId: {
                validators: {
                    notEmpty: {
                        message: '省份不能为空'
                    }
                }
            },
            cityId: {
                validators: {
                    notEmpty: {
                        message: '城市不能为空'
                    }
                }
            },
            districtId: {
                validators: {
                    notEmpty: {
                        message: '区域不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();
        // parent.window.closeTabTest();
    });
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


var HouseEditFileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl, photos) {
        var control = $('#' + ctrlName);
        var ipc = [];
        var imgUrl = [];
        if (photos != null && photos.length > 0) {
            photos.forEach(function (photo) {
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