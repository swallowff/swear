layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'jquery', 'admin'], function () {
    var form = layui.form, $ = layui.jquery, admin = layui.admin, setter = layui.setter;
    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    $.ajax({
        url: setter.ctxPath + "/sys/code/tableList",
        method: 'GET',
        success: function (res) {
            console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#table-select');
                $select.append('<option value="">请选择数据表</option>');
                for (var i in listData){
                    $select.append('<option value="'+listData[i].tableName+'">'+listData[i].tableName+'</option>')
                }
                form.render('select','LAYF-code-gen-form'); //刷新select选择框
            }
        }

    })

    //监听表单提交
    form.on('submit(LAYF-code-gen)', function (data) {
        var field = data.field;
        //执行重载
        // table.reload('dictTable', {
        //     where: field
        // });
    });







});