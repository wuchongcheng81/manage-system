var currentId;
var btnType;
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $("#continueBtn").on("click", function () {
        $('#myModal').modal('hide');
        if (btnType == 'deleteRole') {
            $.ajax({
                url: '/role/deleteByIds',
                type: 'POST',
                dataType: 'json',
                data: {ids: currentId},
                success: function (data) {
                    if (data.state == '11') {
                        $('#tb_role').bootstrapTable('refresh');
                    }
                }
            });
        }
        if (btnType == 'saveMenu') {
            var selectMenu = $('#roleMenuBody').treeview('getChecked');
            if (selectMenu != null && selectMenu.length > 0) {
                var ids = '';
                selectMenu.forEach(function (sm) {
                    ids += sm.id + ',';
                })
                ids.substring(0, ids.length - 1);
                $.post('/roleMenu/save', {roleId: vm.roleId, menuIds: ids}, function (data) {

                })
            }
            return;
        }
    })


    $("#roleMenuConfirmBtn").on("click", function () {
        btnType = 'saveMenu';
        $('#myModal').modal('show');
        $('#successMsg').text('确认要修改权限吗？');
        console.log();
    })
});

function search() {
    $('#tb_role').bootstrapTable('refresh');
}

function addRole() {
    $.post('/role/save', {name: '请填写角色名称', state: 1}, function (data) {
        $('#tb_role').bootstrapTable('refresh');
    });
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_role').bootstrapTable({
            url: '/role/findList',                 //请求后台的URL（*）
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10, 30],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            // showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/role/update",
                    data: {
                        id: row.id,
                        name: row.name,
                        state: row.state
                    },
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
                    field: 'name',
                    title: '角色名称',
                    editable: {
                        type: 'text',
                        title: '角色名称',
                        validate: function (v) {
                            if (!v) return '角色名称不能为空';
                        }
                    }
                },
                // {
                //     field: 'state',
                //     title: '排序',
                //     formatter: function (value, row, index) {
                //         if (value == 0)
                //             return '禁用';
                //         return '启用';
                //     }
                // },
                {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '30%'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="roleMenuModal(\'' + value + '\')">分配权限</button>';
                        var order = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                        return standard + order;
                    }
                }

            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            'pageNumber': this.pageSize * (this.pageNumber - 1),   //页面大小
            'pageSize': this.pageSize,  //页码
            'name': $('#search_name').val(),
            'state': 1
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };
    return oInit;
};

function deleteFunction(id) {
    currentId = id;
    btnType = 'deleteRole';
    $('#myModal').modal('show');
    $('#successMsg').text('确认要删除该角色吗？');
}


function roleMenuModal(id) {
    vm.roleId = id;
    $.post('/role/getRoleMenu', {roleId: id}, function (data) {
        getTree(data);
        initMenuTree();
    });
    $('#roleMenuModal').modal('show');
}

function initMenuTree() {
    $('#roleMenuBody').treeview({
        data: vm.treeData,
        showCheckbox: true,
        onNodeChecked: function (event, node) {
            var selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则选中所有子节点
                $('#roleMenuBody').treeview('checkNode', [selectNodes, {silent: true}]);
            }
            var parentNode = $("#roleMenuBody").treeview("getNode", node.parentId);
            setParentNodeCheck(node);
        },
        onNodeUnchecked: function (event, node) {
            var selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则取消选中所有子节点
                $('#roleMenuBody').treeview('uncheckNode', [selectNodes, {silent: true}]);
            }
        }
    });
}

function getTree(data) {
    vm.treeData = [];
    if (data != null) {
        if (data.menu != null && data.menu.length > 0) {
            data.menu.forEach(function (m) {
                var parent = {id: m.id, text: m.name, selectable: false, state: {checked: false}, nodes: []};
                if (m.childs != null && m.childs.length > 0) {
                    m.childs.forEach(function (c) {
                        var child = {id: c.id, text: c.name, selectable: false, state: {checked: false}};
                        parent.nodes.push(child);
                    });
                }
                vm.treeData.push(parent);
            });
        }
        if (data.roleMenu != null && data.roleMenu.length > 0) {
            vm.treeData.forEach(function (m, index) {
                data.roleMenu.forEach(function (rm) {
                    if (rm.menuId == m.id) {
                        vm.treeData[index].state.checked = true;
                    }
                    if (m.nodes != null && m.nodes.length > 0) {
                        m.nodes.forEach(function (c, cIndex) {
                            if (rm.menuId == c.id) {
                                vm.treeData[index].nodes[cIndex].state.checked = true;
                            }
                        })
                    }
                })
            })
        }
    }
    return vm.treeData;
}


function getChildNodeIdArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

function setParentNodeCheck(node) {
    var parentNode = $("#roleMenuBody").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        var checkedCount = 0;
        for (x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount++;
            } else {
                break;
            }
        }
        if (checkedCount === parentNode.nodes.length) {
            $("#roleMenuBody").treeview("checkNode", parentNode.nodeId);
            setParentNodeCheck(parentNode);
        }
    }
}