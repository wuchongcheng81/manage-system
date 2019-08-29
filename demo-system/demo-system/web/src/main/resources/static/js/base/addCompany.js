$(function () {
    $('#bankInfoInput').hide();
    $("#companyAddForm").submit(function (ev) {
        ev.preventDefault();
    });
    $('#validaterBtn').on('click', function () {
        $("#companyAddForm").bootstrapValidator('validate');
        if ($("#companyAddForm").data('bootstrapValidator').isValid()) {
            $('#myModal').modal('show');
            $("#continueBtn").on("click", function () {
                $('#myModal').modal('hide');
                var entity = new FormData($('#companyAddForm')[0]);

                var selectVal = $('#bankInfoSelect').children('option:selected').val();
                if (selectVal == 'other') {
                    entity.append('bankInfo', $('#bankInfoInput').val());
                } else {
                    entity.append('bankInfo', selectVal);
                }
                $.ajax({
                    url: '/company/save',
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
                                parent.window.closeTabLinkTo(9, '公司管理', 'companyList.html');
                            })
                        }
                    }
                });
            })
        }
    });

    validateForm();
    $('#bankInfoSelect').change(function () {

        var val = $(this).children('option:selected').val();
        if (val == 'other') {
            $('#bankInfoInput').show();
        } else {
            $('#bankInfoInput').hide();
        }
    })
})

function uploadCover() {
    $('#coverFile').click();
}


function validateForm() {
    $('#companyAddForm').bootstrapValidator({
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
                        message: '公司全称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: '公司全称长度不得超过100'
                    }
                }
            },
            shortName: {
                validators: {
                    notEmpty: {
                        message: '公司简称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '公司简称长度不得超过50'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '公司地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: '公司地址长度不得超过100'
                    }
                }
            },
            contact: {
                validators: {
                    notEmpty: {
                        message: '公司联系电话不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '公司联系电话长度不得超过20'
                    }
                }
            },
            principalName: {
                validators: {
                    notEmpty: {
                        message: '公司负责人姓名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '公司负责人姓名长度不得超过10'
                    }
                }
            },
            principalPhone: {
                validators: {
                    notEmpty: {
                        message: '公司负责人联系方式不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '公司负责人联系方式长度不得超过20'
                    }
                }
            },
            // bankInfo: {
            //     validators: {
            //         notEmpty: {
            //             message: '公司银行信息不能为空'
            //         }
            //     }
            // },
            creditCardNumbers: {
                validators: {
                    notEmpty: {
                        message: '公司银行卡号不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '公司银行卡号长度不得超过20'
                    }
                }
            },
            businessLicenseNo: {
                validators: {
                    notEmpty: {
                        message: '营业执照注册号不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '营业执照注册号长度不得超过500'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//验证通过后会执行这个函数。
        // Prevent submit form
        e.preventDefault();

    });
}

