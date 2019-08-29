var btnType;
var getSelectRows;
var houseId;
$(function () {
    houseId = getUrlParam('id');
    var aTable = new activitydInit();
    aTable.Init();
});


function activityModal(houseId) {
    vm.houseId = houseId;
    // $('#standradContent').html(houseId);
    var aTable = new activitydInit();
    aTable.Init();
    $('#activityModal').modal('show');
}

function editActivity(id) {
    vm.imgUrls = [];
    $('#activityEditModalLabel').html('编辑楼盘头条');
    $.post('/houseActivity/queryObject', {id: id}, function (data) {
        vm.activity = data.result;
        $('#txt_file01').fileinput('destroy')
        var oFileInput = new FileInput();
        oFileInput.Init('txt_file01', '/houseActivity/activityImgUpload');

        $('#txt_file02').fileinput('destroy')
        var aFileInput = new AcvivityEditFileInput();
        aFileInput.Init('txt_file02', '/houseActivity/activityImgUpload', data.result);
        $('#activityEditModal').modal('show');
    });
}

function addActivity() {
    $('#activityEditModalLabel').html('新增楼盘头条');
    vm.activity = {};
    $('#activityEditModal').modal('show');
}

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
                    $('#activityEditModal').modal('hide');
                    var aTable = new activitydInit();
                    aTable.Init();
                }
            },
            error: function (data, status) {
            },
            complete: function () {

            }

        });
    }
}

var standradInit = function () {
    var curRow = {};
    var sdTableInit = new Object();
    //初始化Table
    sdTableInit.Init = function () {
        $('#tb_house_standrad').bootstrapTable('destroy');
        $('#tb_house_standrad').bootstrapTable({
            url: '/houseStandard/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#standradToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: sdTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/houseStandard/update",
                    data: row,
                    dataType: 'json',
                    success: function (data, status) {
                    },
                    error: function (data, status) {
                    },
                    complete: function () {

                    }

                });
            },
            columns: [
                {
                    field: 'imgUrl',
                    title: '图片',
                    cellStyle: {'css': {'text-align': 'center', 'width': '100px'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="100%">';
                            return img;
                        }
                        return '';
                    }
                },
                {
                    field: 'name',
                    title: '户型名',
                    editable: {
                        type: 'text',
                        title: '户型名',
                        validate: function (v) {
                            if (!v) return '户型名不能为空';

                        }
                    }
                }, {
                    field: 'desc',
                    title: '描述',
                    editable: {
                        type: 'text',
                        title: '描述',
                        validate: function (v) {
                            if (!v) return '描述不能为空';

                        }
                    }
                }, {
                    field: 'acreage',
                    title: '面积',
                    formatter: function (value, row, index) {
                        return value;
                    },
                    editable: {
                        type: 'text',
                        title: '面积',
                        validate: function (v) {
                            if (!v) return '面积不能为空';

                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '200px'}},
                    formatter: function (value, row, index) {
                        var uploadBtn = '<button type="button" class="btn btn-info" style="margin-right: 15px" onclick="uploadFile(\'' + value + '\')">上传图片</button>';
                        var deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteStandrad(\'' + value + '\')">删除</button>';
                        return uploadBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    sdTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'houseId': vm.houseId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return sdTableInit;
};


var activitydInit = function () {
    var aTableInit = new Object();
    //初始化Table
    aTableInit.Init = function () {
        $('#tb_house_activity').bootstrapTable('destroy');
        $('#tb_house_activity').bootstrapTable({
            url: '/houseActivity/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#activityToolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: aTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                {
                    field: 'title',
                    title: '标题'
                }, {
                    field: 'desc',
                    title: '描述',
                    formatter: function (value, row, index) {
                        if (value.length > 10) {
                            return value.substring(0, 10) + '...';
                        }
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '200px'}},
                    formatter: function (value, row, index) {
                        var editBtn = '<button type="button" class="btn btn-info" style="margin-right: 15px" onclick="editActivity(\'' + value + '\')">编辑</button>';
                        var deleteBtn = '<button type="button" class="btn btn-danger" onclick="deleteActivity(\'' + value + '\')">删除</button>';
                        return editBtn + deleteBtn;
                    }
                }]
        });
    };
    //得到查询的参数
    aTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'houseId': houseId,
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize  //页码
        };
        return temp;
    };
    return aTableInit;
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

function avtivityFileListen() {
    $("#imgDiv1").on("click", function () {
        $("#imgFile1").click();
    })

    $("#imgFile1").on('change', function () {
        getPath(
            document.getElementById('activityImg1'),
            document.getElementById('imgFile1'),
            document.getElementById('activityImg1'));
    })

    $("#imgDiv2").on("click", function () {
        $("#imgFile2").click();
    })

    $("#imgFile2").on('change', function () {
        getPath(
            document.getElementById('activityImg2'),
            document.getElementById('imgFile2'),
            document.getElementById('activityImg2'));
    })

    $("#imgDiv3").on("click", function () {
        $("#imgFile3").click();
    })

    $("#imgFile3").on('change', function () {
        getPath(
            document.getElementById('activityImg3'),
            document.getElementById('imgFile3'),
            document.getElementById('activityImg3'));
    })
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
    }
    return oFile;
};




