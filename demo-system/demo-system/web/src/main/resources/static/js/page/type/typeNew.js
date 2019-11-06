$(function () {
    $.get('/type/findAllList', function(response) {
        var $table = $('#tb_body');

        $table.bootstrapTable({
            data: response,
            idField: 'id',
            dataType:'jsonp',
            columns: [
                {
                    field: 'sort',
                    title: '序号'
                },
                {
                    field: 'name',
                    title: '品牌分类名称'
                },
                {
                    field: 'pageImgUrl',
                    title: '分类页面广告',
                    cellStyle: {'css': {'text-align': 'center', 'width': '15%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="40%">';
                            return img;
                        }
                        return '';
                    }
                },{
                    field: 'detailImgUrl',
                    title: '分类详情页广告',
                    cellStyle: {'css': {'text-align': 'center', 'width': '15%'}},
                    formatter: function (value, row, index) {
                        if (value != null && value != '') {
                            var img = '<img src="' + value + '" width="40%">';
                            return img;
                        }
                        return '';
                    }
                },{
                    field: 'brandCount',
                    title: '关联品牌数量'
                }, {
                    field: 'id',
                    title: '操作',
                    cellStyle: {'css': {'text-align': 'center', 'width': '220px'}},
                    formatter: function (value, row, index) {
                        var standard = '<button type="button" style="margin-right: 15px" class="btn btn-success" onclick="updateModal(\'' + value + '\')">修改</button>';
                        var order = '<button type="button" class="btn btn-danger" onclick="deleteFunction(\'' + value + '\')">删除</button>';
                        return standard + order;
                    }
                }
            ],
            treeShowField: 'sort',
            parentIdField: 'parentId',
            onResetView: function(data) {
                //console.log('load');
                $table.treegrid({
                    initialState: 'collapsed',// 所有节点都折叠
                    // initialState: 'expanded',// 所有节点都展开，默认展开
                    treeColumn: 0,
                    // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
                    // expanderCollapsedClass: 'glyphicon glyphicon-plus',
                    onChange: function() {
                        $table.bootstrapTable('resetWidth');
                    }
                });

                //只展开树形的第一级节点
                // $table.treegrid('getRootNodes').treegrid('expand');
            },
            onPostBody: function() {
                var columns = $table.bootstrapTable('getOptions').columns
                if (columns && columns[0][1].visible) {
                    $table.treegrid({
                        treeColumn: 1,
                        onChange: function() {
                            $table.bootstrapTable('resetWidth')
                        }
                    })
                }
            }
        });
    })
});