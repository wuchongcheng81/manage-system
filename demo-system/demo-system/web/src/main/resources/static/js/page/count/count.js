$(function () {
    $('#startDate').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        todayHighlight: 1,//今天高亮
        autoclose: 1,//选择后自动关闭
        language: 'zh-CN'
    })
    $("#startDate").datetimepicker("setDate", new Date());

    $('#endDate').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        todayHighlight: 1,//今天高亮
        autoclose: 1,//选择后自动关闭
        language: 'zh-CN'
    })
    $("#endDate").datetimepicker("setDate", new Date());

})