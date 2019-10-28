layui.config({
    base: Swear.static + '/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form', 'admin', 'element'], function () {
    var $ = layui.$, form = layui.form,
        layer = layui.layer, setter = layui.setter,
        admin = layui.admin;

    setter.serverUrl = Swear.serverUrl;
    setter.ctxPath = Swear.ctxPath;

    //监听提交
    form.on('submit(LAYF-menu-form-add-submit)', function (data) {
        var field = data.field;       //获取提交的字段
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //提交 Ajax 成功后，关闭当前弹层并重载表格
        $.ajax({
            url: setter.ctxPath + '/sys/menu/add.ajax',
            data: field,
            method: 'POST',
            success: function (res) {
                // console.log(res);
                if (res.code == setter.response.statusCode.ok) {
                    parent.layer.msg(res.msg, {
                        icon: 1,
                        time: 1800
                    });
                    parent.layui.table.reload('menuTable'); //重载表格
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.msg(res.msg, {
                        icon: 1,
                        time: 1800
                    });
                }

            }
        });
    });

    //渲染菜单下拉选择
    $.ajax({
        url: setter.ctxPath + '/sys/menu/list.ajax',
        data: {
            isMenu: true,
            page: -1      //不分页
        },
        method: 'GET',
        success: function (res) {
            // console.log(res)
            if (res.code == setter.response.statusCode.ok) {
                var listData = res.data;
                var $select = $('#menu-select');
                $select.append('<option value="">请选择</option>')
                for (var i in listData) {
                    $select.append('<option value="' + listData[i].id + '">' + listData[i].name + '</option>')
                }
                form.render('select', 'LAYF-menu-form-add'); //刷新select选择框
            }
        }
    });

})